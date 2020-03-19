/*
 * Copyright (c) 2008, 2018 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.api.jakarta.xml.bind;

import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.xml.bind.ValidationEvent;
import jakarta.xml.bind.ValidationEventHandler;
import jakarta.xml.bind.ValidationEventLocator;
import jakarta.xml.bind.helpers.ValidationEventImpl;
import jakarta.xml.bind.helpers.ValidationEventLocatorImpl;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * This validation event handler collects non-warning events and
 * delegates all handling to its parent.
 */
public class ErrorCollector implements ValidationEventHandler, ErrorHandler {
    /**
     * Delegation parent.
     */
    protected ValidationEventHandler parent;

    /**
     * Set delegation parent.
     */
    public void setParent(ValidationEventHandler parent) {
        this.parent = parent;
    }

    /**
     * Array of handled error events.
     */
    protected ArrayList<ValidationEvent> events = new ArrayList<ValidationEvent>();

    public boolean hasEvents() {
        return events.size() > 0;
    }

    public String getFirstEventString() {
        return eventToString((ValidationEvent)events.get(0));
    }

    public boolean handleEvent(ValidationEvent event) {
        if (event.getSeverity() != ValidationEvent.WARNING) {
            events.add(event);
        }

        if (parent != null) {
            return parent.handleEvent(event);
        } else {
            return event.getSeverity() == ValidationEvent.WARNING;
        }
    }

    public void printEvents(PrintWriter out) {
        if( hasEvents() ) {
            out.println("Events:");
        }
        for(int i = 0; i < events.size(); ++i) {
            out.println(i + ": " + eventToString((ValidationEvent)events.get(i)));
        }
    }

    public static String eventToString(ValidationEvent e) {
        StringBuffer sb = new StringBuffer();
        switch (e.getSeverity()) {
            case ValidationEvent.ERROR: sb.append("ERROR"); break;
            case ValidationEvent.FATAL_ERROR: sb.append("FATAL_ERROR"); break;
            case ValidationEvent.WARNING: sb.append("Warning"); break;
            default: sb.append("UNDEFINED");
        }
        ValidationEventLocator locator = e.getLocator();
        sb.append(" at (" + locator.getLineNumber() + ", " + locator.getColumnNumber() + "): "
                + e.getMessage());
        return sb.toString();
    }

	public void warning(SAXParseException exception) throws SAXException {
		events.add(new ValidationEventImpl(ValidationEvent.WARNING, exception.getMessage(), new ValidationEventLocatorImpl(exception), exception));
	}

	public void error(SAXParseException exception) throws SAXException {
		events.add(new ValidationEventImpl(ValidationEvent.ERROR, exception.getMessage(), new ValidationEventLocatorImpl(exception), exception));
		throw exception;
		
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		events.add(new ValidationEventImpl(ValidationEvent.FATAL_ERROR, exception.getMessage(), new ValidationEventLocatorImpl(exception), exception));
		throw exception;
	}
}
