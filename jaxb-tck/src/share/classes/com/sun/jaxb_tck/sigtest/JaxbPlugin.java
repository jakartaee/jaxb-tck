/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.jaxb_tck.sigtest;

import com.sun.tdk.signaturetest.model.AnnotationItem;
import com.sun.tdk.signaturetest.model.ClassDescription;
import com.sun.tdk.signaturetest.model.MemberDescription;
import com.sun.tdk.signaturetest.plugin.Filter;
import com.sun.tdk.signaturetest.plugin.Plugin;
import com.sun.tdk.signaturetest.plugin.PluginAPI;
import com.sun.tdk.signaturetest.plugin.Transformer;
import com.sun.tdk.signaturetest.core.ClassHierarchy;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

class JaxbAccessibleFilter implements Filter {

    public boolean accept(ClassDescription c) {

        ClassHierarchy h = c.getClassHierarchy();

        // 4. package-info
        if (c.isPackageInfo()) {
            return false;
        }

        // 3. Classes named ".*\.ObjectFactory".
        if (c.getName().endsWith("ObjectFactory")) {
            return false;
        }

        if (h.getTrackMode() == ClassHierarchy.ALL_PUBLIC) {
            return c.isPublic();
        }

        String declaringClass = null;

        for (ClassDescription cl = c; cl != null;) {

            if (declaringClass != null && c.isPublic() && c.isStatic()) {
                // public static inner class can be accessed via public subclass of the enclosing class
                // even if the enclosing class is protected or has package access level
                return cl.isPublic() || (!cl.isFinal() && cl.isProtected());
            }

            // 1. not public classes
            if (!cl.isPublic()) {
                return false;
            }

            if (cl.isSuperInterface()) {
                return true;
            }

            declaringClass = cl.getDeclaringClassName();

            if (declaringClass == null) {
                // 6. classes derived from jakarta.xml.bind.annotation.XmlAdapter.
                return isJaxbAccessible(cl);
            }

            try {
                cl = h.load(declaringClass);
            }
            catch (ClassNotFoundException e) {
                return true;
            }
        }
        return true;
    }

    /**
     * Checks for Jaxb that class is not derived from jakarta.xml.bind.annotation.XmlAdapter.
     */
    private boolean isJaxbAccessible(ClassDescription c) {

        ClassHierarchy h = c.getClassHierarchy();

        for (ClassDescription cl = c; cl != null && cl.getSuperClass() != null;) {
            if (cl.getQualifiedName().startsWith("jakarta.xml.bind.annotation.adapters.XmlAdapter")) {
                return false;
            }

            try {
                cl = h.load(cl.getSuperClass().getQualifiedName());
            }
            catch (ClassNotFoundException ex) {
                return true;
            }
        }
        return true;
    }

}

class AfterBuildMembersTransformer implements Transformer {

    public ClassDescription transform(ClassDescription cls) {

        /**
         * JAxb specific! Ignore 5. any class annotations
         */

        cls.setAnnoList(AnnotationItem.EMPTY_ANNOTATIONITEM_ARRAY);

        /* JAXB Note:
        * The list of jaxb filtering conditions that are catched in this method:
        * Ignore 2. not public methods
        * Ignore 7. not default constructors
        * Ignore 8. any field/methods annotations
        */

        for (Iterator it = cls.getMembersIterator(); it.hasNext();) {
            MemberDescription mr = (MemberDescription) it.next();

            mr.setAnnoList(AnnotationItem.EMPTY_ANNOTATIONITEM_ARRAY);

            // includes only public methods, classes, fields
            // and superclass and superinterfaces
            if (!(mr.isPublic() || mr.isSuperInterface() || mr.isSuperClass()))
                it.remove();
            else if (mr.isConstructor() && !"".equals(mr.getArgs()))
                it.remove();
        }

        return cls;
    }
}


class BeforeTestTransformer implements Transformer {

    private static HashMap<String, String> replacements = new HashMap<String, String>() {
    	{
            put("java.lang.Byte", "byte");
            put("java.lang.Char", "char");
            put("java.lang.Double", "double");
            put("java.lang.Float", "float");
            put("java.lang.Integer", "int");
            put("java.lang.Long", "long");
            put("java.lang.Short", "short");
            put("java.lang.Boolean", "boolean");
    	}
    };

    public ClassDescription transform(ClassDescription cls) {

        Set set = null;

        for (Iterator it = cls.getMembersIterator(); it.hasNext();) {
            MemberDescription mr = (MemberDescription) it.next();
            if ((mr.isMethod() || mr.isField()) && replacements.get(mr.getType()) != null) {

                if (set == null)
                    set = new HashSet();

                set.add(mr);
                it.remove();
            }
        }

        if (set != null) {
            for (Iterator it = set.iterator(); it.hasNext();) {
                MemberDescription mr = (MemberDescription) ((MemberDescription) it.next()).clone();
                mr.setType(replacements.get(mr.getType()));
            }
        }

        return cls;
    }
}

public class JaxbPlugin implements Plugin {

    public void init(PluginAPI api) {
        api.setTransformer(PluginAPI.AFTER_BUILD_MEMBERS, new AfterBuildMembersTransformer());
        api.setFilter(PluginAPI.IS_CLASS_ACCESSIBLE, new JaxbAccessibleFilter());
        api.setTransformer(PluginAPI.BEFORE_TEST, new BeforeTestTransformer());
    }

    public void release() {
        // nothing to release
    }
}
