/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jmpp.lib;

import java.io.PrintWriter;
import java.util.Vector;

/**  
 * Class contains a set of utilities for floating-point test generation
 *
 * @author Dmitry I. Khukhro
 * @version @(#)JmppLibFP.java	1.15 04/03/16
 */

public class JmppLibFP extends JmppLibLang {

    protected static final int FOR_FLOAT = 0;
    protected static final int FOR_DOUBLE= 1;
    protected static final int FL = 0;
    protected static final int DB = 1;
    
    private static final String VER_FULL   = "full";
    private static final String VER_SELECT = "sel";
    private static final String VER_SIGNIF = "sign";
    
    private static final int EXPLICIT = 0;
    private static final int IMPLICIT = 1;
    
    /*private*/ protected  static final String EST_SUFF = "_est";
    protected static final String TMP_SUFF = "_tmp";
    
    protected boolean f_verify = false;
    protected boolean f_verify_err = true;
    protected boolean f_estimate = false;
    protected boolean f_est_hot = false;
    protected String verifType = VER_SELECT;

    protected Object intermedObject = null;

    public void set_f_verify(boolean val){
        this.f_verify = val;
        if (intermedObject != null){
            ((JmppLibFP)intermedObject).f_verify = val;
        }
    }

    public void set_f_verify_err(boolean val){
        this.f_verify_err = val;
        if (intermedObject != null){
            ((JmppLibFP)intermedObject).f_verify_err = val;
        }
    }

    public void set_f_estimate(boolean val){
        this.f_estimate = val;
        if (intermedObject != null){
            ((JmppLibFP)intermedObject).f_estimate = val;
        }
    }

    public void set_f_est_hot(boolean val){
        this.f_est_hot = val;
        if (intermedObject != null){
            ((JmppLibFP)intermedObject).f_est_hot = val;
        }
    }

    public void set_verifType(String val){
        this.verifType = val;
        if (intermedObject != null){
            ((JmppLibFP)intermedObject).verifType = val;
        }
    }

/**
 * If false, sources are not written out. HTML is created only.
 */
	protected boolean doSrc = true;

/**
 * Vector of vector of source file names to be inserted into
 * generated test HTML description.
 */
	protected Vector fpSources = null;

/**
 * Is used while FPMaker based tests common html file generating.
 * Is incremented when tID is except the test is skip.
 */
	protected int actTID = 0;

    protected static final int fl_exp_lower  = 11;
    protected static final int fl_exp_upper  = 32;
    protected static final int fl_prec_lower = 32;
    protected static final int fl_prec_upper = 128;
    protected static final int db_exp_lower  = 15;
    protected static final int db_exp_upper  = 40;
    protected static final int db_prec_lower = 64;
    protected static final int db_prec_upper = 192;
    
    protected String tFloat = "";
    protected String tDouble= "";
    protected String strictfpKey = "";
    protected String widefpKey   = "";

    private int estNum = 0;
    
    public void initValues(){
        tFloat = (f_estimate ? "EValue" : (f_verify ? "EFloat" : "float"));
        tDouble= (f_estimate ? "EValue" : (f_verify ? "EFloat" : "double"));
        strictfpKey = (f_est_hot ? "" : "strictfp");
        widefpKey   = (f_est_hot ? "" : "/*not strictfp*/"); // RFE 4175808
    }

/**
 * Initialize dependsOn variable for all JmppLibFP, FPMaker - based templates to
 * always depend on emath, fpcheck, ucbtest libraries.
 */
    {
        dependsOn = "emath fpcheck ucbtest";
    }

    public static void main(String[] argv) {
        libMain(argv, new JmppLibFP());
    }

/**
 *   Overrides the method from the JmppLibTest class in order to change
 *   output file names for estimation classes generation
 */
    public void newSource(boolean includeToSources, 
                          String fileName, 
                          String extension,
                          String dirName) {
        if (f_est_hot) {
            fileName += TMP_SUFF;
        } else if (f_estimate) {
            fileName += EST_SUFF;
        }
        super.newSource(includeToSources, fileName, extension, dirName);
    }


/**  
 * Overrides the method from the JmppLibTest class to provide accessability
 * of the UCB tests support classes
 */
	public void generateProlog(PrintWriter out, String intermediateClassName) {
		out.println("package "+templatePackage+";\n");
		out.println("import javasoft.sqe.jck.fp.emath.*;");
		out.println("import javasoft.sqe.jck_internal.fp.UCBTEST.TestCase;");
		out.println("import javasoft.sqe.jck_internal.fp.UCBTEST.UCBInput;");
		out.println("import javasoft.sqe.jck_internal.fp.UCBTEST.UCBInputException;\n");
		out.println("public class "+intermediateClassName+" extends "+getClass().getName()+" {");
	}


    public void passDataTo(Object o) {
        super.passDataTo(o);
        ((JmppLibFP)o).fpSources = fpSources;
        ((JmppLibFP)o).f_verify = f_verify;
        ((JmppLibFP)o).f_estimate = f_estimate;
        ((JmppLibFP)o).f_est_hot = f_est_hot;
        ((JmppLibFP)o).f_verify_err = f_verify_err;
        ((JmppLibFP)o).verifType = verifType;
        intermedObject = o;
        ((JmppLibFP)o).initValues();
    }


/**  
 * Overrides the method from the JmppLibTest class to provide generation
 * of the common html file for FPMaker based tests
 */
	public void makeHTML() {
		if (!skipTest) {
		    if (fpSources != null && actTID < fpSources.size()) {
			    sourceFiles = (Vector) fpSources.elementAt(actTID);
			    filesSectionList = sourceFiles;
		    }
		    actTID++;
		}
		super.makeHTML();
	}


/**  
 * Overrides the method from the JmppLib class to provide generation
 * of the common html file for FPMaker based tests
 */
	public void closeOut() {
		if (!doSrc && !currentFileName.endsWith("html"))
			outBuffer = null;
		super.closeOut();
	}


////////////////////////////////////////////////////////////////////////
//                                                                    //
//                          L I T E R A L S                           //
//                                                                    //
////////////////////////////////////////////////////////////////////////

    private String literal( String val, String fmt ) {
        if ( f_estimate ) {
            return "eV("+val+")";
        } else if ( f_verify ) {
            return "EFloat.ef("+val+", "+fmt+")";
        } else { 
            return val;
        }
    }


    protected String v( String val ) {
        return literal(val, "EFormat.SINGLE_STANDARD");
    }

    protected String vd( String val ) {
        return literal(val, "EFormat.DOUBLE_STANDARD");
    }


////////////////////////////////////////////////////////////////////////
//                                                                    //
//            A R I T H M E T I C   O P E R A T I O N S               //
//                                                                    //
////////////////////////////////////////////////////////////////////////

    private String binary( String eV_meth, String ef_meth, String op,
                           String v1, String v2, String fmt ) {
        if ( f_estimate ) {
            return v1+"."+eV_meth+"("+v2+")";
        } else if ( f_verify ) {
            return v1+"."+ef_meth+"("+v2+", "+fmt+")";
        } 
        return "("+v1+" "+op+" "+v2+")";
    }


    private String unary( String eV_meth, String ef_meth, String op,
                          String val, String fmt ) {
        if ( f_estimate ) {
            return val+"."+eV_meth+"()";
        } else if ( f_verify ) {
            return val+"."+ef_meth+"("+fmt+")";
        } 
        return "("+op+val+")";
    }


    protected String add( int mode, String v1, String v2 ) {
        return (mode == FL ? addf(v1, v2) : addd(v1, v2)); 
    }

    protected String sub( int mode, String v1, String v2 ) {
        return (mode == FL ? subf(v1, v2) : subd(v1, v2)); 
    }

    protected String mul( int mode, String v1, String v2 ) {
        return (mode == FL ? mulf(v1, v2) : muld(v1, v2)); 
    }

    protected String div( int mode, String v1, String v2 ) {
        return (mode == FL ? divf(v1, v2) : divd(v1, v2)); 
    }

    protected String rem( int mode, String v1, String v2 ) {
        return (mode == FL ? remf(v1, v2) : remd(v1, v2)); 
    }

    protected String neg( int mode, String val ) {
        return negf(val);  // no difference
    }

    protected String rnd( int mode, String val ) {
        return (mode == FL ? rndf(val) : rndd(val)); 
    }


    protected String addf( String v1, String v2 ) {
        return binary( "floatAdd", "add", "+", v1, v2, test+".fm" ); 
    }

    protected String subf( String v1, String v2 ) {
        return binary( "floatSub", "subtract", "-", v1, v2, test+".fm" ); 
    }

    protected String mulf( String v1, String v2 ) {
        return binary( "floatMul", "multiply", "*", v1, v2, test+".fm" ); 
    }

    protected String divf( String v1, String v2 ) {
        return binary( "floatDiv", "divide", "/", v1, v2, test+".fm" ); 
    }

    protected String remf( String v1, String v2 ) {
        return binary( "floatRem", "remainder", "%", v1, v2, test+".fm" ); 
    }

    protected String negf( String val ) {
        return unary( "negate", "negate", "-", val, "" );
    }

    protected String flt( String val ) {
        return unary( "toFloat", "round", "(float)", val, "EFormat.SINGLE_STANDARD" );
    }

    protected String rndf( String val ) {
        return unary( "toFloatStand", "round", "", val, "EFormat.SINGLE_STANDARD" );
    }


    protected String addd( String v1, String v2 ) {
        return binary( "doubleAdd", "add", "+", v1, v2, test+".fm2" ); 
    }

    protected String subd( String v1, String v2 ) {
        return binary( "doubleSub", "subtract", "-", v1, v2, test+".fm2" ); 
    }

    protected String muld( String v1, String v2 ) {
        return binary( "doubleMul", "multiply", "*", v1, v2, test+".fm2" ); 
    }

    protected String divd( String v1, String v2 ) {
        return binary( "doubleDiv", "divide", "/", v1, v2, test+".fm2" ); 
    }

    protected String remd( String v1, String v2 ) {
        return binary( "doubleRem", "remainder", "%", v1, v2, test+".fm2" ); 
    }

    protected String negd( String val ) {
        return unary( "negate", "negate", "-", val, "" );
    }

    protected String rndd( String val ) {
        return unary( "toDoubleStand", "round", "", val, "EFormat.DOUBLE_STANDARD" );
    }


//--------------------- STRICT-FP MODE CALCULATIONS ---------------------//

    protected String adds( int mode, String v1, String v2 ) {
        return (mode == FL ? addfs(v1, v2) : addds(v1, v2)); 
    }

    protected String subs( int mode, String v1, String v2 ) {
        return (mode == FL ? subfs(v1, v2) : subds(v1, v2)); 
    }

    protected String muls( int mode, String v1, String v2 ) {
        return (mode == FL ? mulfs(v1, v2) : mulds(v1, v2)); 
    }

    protected String divs( int mode, String v1, String v2 ) {
        return (mode == FL ? divfs(v1, v2) : divds(v1, v2)); 
    }

    protected String rems( int mode, String v1, String v2 ) {
        return (mode == FL ? remfs(v1, v2) : remds(v1, v2)); 
    }


    protected String addfs( String v1, String v2 ) {
        return binary( "floatSAdd", "add", "+", v1, v2, "EFormat.SINGLE_STANDARD" ); 
    }

    protected String subfs( String v1, String v2 ) {
        return binary( "floatSSub", "subtract", "-", v1, v2, "EFormat.SINGLE_STANDARD" ); 
    }

    protected String mulfs( String v1, String v2 ) {
        return binary( "floatSMul", "multiply", "*", v1, v2, "EFormat.SINGLE_STANDARD" ); 
    }

    protected String divfs( String v1, String v2 ) {
        return binary( "floatSDiv", "divide", "/", v1, v2, "EFormat.SINGLE_STANDARD" ); 
    }

    protected String remfs( String v1, String v2 ) {
        return binary( "floatSRem", "remainder", "%", v1, v2, "EFormat.SINGLE_STANDARD" ); 
    }


    protected String addds( String v1, String v2 ) {
        return binary( "doubleSAdd", "add", "+", v1, v2, "EFormat.DOUBLE_STANDARD" ); 
    }

    protected String subds( String v1, String v2 ) {
        return binary( "doubleSSub", "subtract", "-", v1, v2, "EFormat.DOUBLE_STANDARD" ); 
    }

    protected String mulds( String v1, String v2 ) {
        return binary( "doubleSMul", "multiply", "*", v1, v2, "EFormat.DOUBLE_STANDARD" ); 
    }

    protected String divds( String v1, String v2 ) {
        return binary( "doubleSDiv", "divide", "/", v1, v2, "EFormat.DOUBLE_STANDARD" ); 
    }

    protected String remds( String v1, String v2 ) {
        return binary( "doubleSRem", "remainder", "%", v1, v2, "EFormat.DOUBLE_STANDARD" ); 
    }


    protected String preInc(int mode, String var) {
        if (f_estimate || f_verify) {
            return "("+var+"="+add(mode, var, itoFP(mode, "1"))+")";
        } 
        return "(++"+var+")";
    }

    protected String preIncF(String var) {
        return preInc(FL, var);
    }

    protected String preIncD(String var) {
        return preInc(DB, var);
    }

    protected String preDec(int mode, String var) {
        if (f_estimate || f_verify) {
            return "("+var+"="+sub(mode, var, itoFP(mode, "1"))+")";
        } 
        return "(--"+var+")";
    }

    protected String preDecF(String var) {
        return preDec(FL, var);
    }

    protected String preDecD(String var) {
        return preDec(DB, var);
    }


////////////////////////////////////////////////////////////////////////
//                                                                    //
//            C O N V E R S I O N   O P E R A T I O N S               //
//                                                                    //
////////////////////////////////////////////////////////////////////////

    private String explCastToInt( String val, String to_type, String etype ) {
        if ( f_estimate || f_verify ) {
            return val+".entier(EType."+etype+")";
        } 
        return "(("+to_type+")"+val+")";
    }


    protected String toChar( String val ) {
        return explCastToInt( val, "char", "CHAR" );
    }

    protected String toByte( String val ) {
        return explCastToInt( val, "byte", "BYTE" );
    }

    protected String toShort( String val ) {
        return explCastToInt( val, "short", "SHORT" );
    }

    protected String toInt( String val ) {
        return explCastToInt( val, "int", "INTEGER" );
    }

    protected String toLong( String val ) {
        return explCastToInt( val, "long", "LONG" );
    }


    private String castToFP( String val, String to_type, String fmt, int cast_type ) {
        if ( f_estimate ) {
            return (to_type.equals("float") ? "eVFloat" : "eVDouble")+"("+val+")";
        } else if ( f_verify ) {
            return "EFloat.efc("+val+", "+fmt+")";
        }
        if (cast_type == EXPLICIT) {
            return "(("+to_type+")"+val+")";
        } else {
            return val;
        }
    }


    protected String intToFP( int mode, String val ) {
        return (mode == FL ? intToFloat(val) : intToDouble(val)); 
    }

    protected String intToFloat( String val ) {
        return castToFP( val, "float", "EFormat.SINGLE_STANDARD", EXPLICIT );
    }

    protected String intToDouble( String val ) {
        return castToFP( val, "double", "EFormat.DOUBLE_STANDARD", EXPLICIT );
    }

    protected String itoFP( int mode, String val ) {
        return (mode == FL ? itof(val) : itod(val)); 
    }

    protected String itof( String val ) {
        return castToFP( val, "float", "EFormat.SINGLE_STANDARD", IMPLICIT );
    }

    protected String itod( String val ) {
        return castToFP( val, "double", "EFormat.DOUBLE_STANDARD", IMPLICIT );
    }


    private String fpToFP( String val, String to_type, String fmt, int cast_type ) {
        if ( f_estimate ) {
            return val+"."+(to_type.equals("float") ? "toFloat" : "toDouble")+"()";
        } else if ( f_verify ) {
            return val + ".round("+fmt+")";
        }
        if (cast_type == EXPLICIT) {
            return "(("+to_type+")"+val+")";
        } else {
            return val;
        }
    }

    protected String fpToFloat( String val ) {
        return fpToFP( val, "float", "fm", EXPLICIT );
    }

    protected String fpToDouble( String val ) {
        return fpToFP( val, "double", "fm2", EXPLICIT );
    }

    protected String ftod( String val ) {
        return fpToFP( val, "double", "fm2", IMPLICIT );
    }


////////////////////////////////////////////////////////////////////////
//                                                                    //
//                C L A S S   C O N S T R U C T I O N                 //
//                                                                    //
////////////////////////////////////////////////////////////////////////

    protected String asn( String var, String expr ) {
        return var+" = "+expr;
    }

    protected String call( String meth, String pars ) {
        return meth+"("+pars+")";
    }

    protected String lst( String head, String tail ) {
        return head+", "+tail;
    }


    protected void importDeclarations() {

        if ( f_estimate ) {
	    L("import java.util.Vector;");
	    L("import javasoft.sqe.jck.fp.fpcheck.FPFormat;");
	    L("import javasoft.sqe.jck.fp.emath.EValue;");
	    L("import javasoft.sqe.jck.fp.emath.EFormat;");
	} else {
	    L("import javasoft.sqe.jck.fp.fpcheck.FPCheck;");
	    if ( f_verify ) {
	        L("import javasoft.sqe.jck.fp.emath.*;");
            }
	}
    }


    protected String className() {
        if (f_est_hot) {
            return test + TMP_SUFF;
        } else if (f_estimate) {
            return test + EST_SUFF;
        }
        return test;
    }


    protected String extClause() {
        return (f_estimate ? "extends EValue" : "");
//        return "";
    }


////////////////////////////////////////////////////////////////////////
//                                                                    //
//            I N C L U D I N G   V E R I F I C A T I O N             //
//                                                                    //
////////////////////////////////////////////////////////////////////////

    protected void verifierDef() {

        if ( f_estimate ) {
//		    for (int i = 1; i <= checkNum; i++) {
//		        L("    public static EValue estim" + i + ";");
//		    }
            L("    public static Vector estim = new Vector();");
            if ( f_est_hot ) {
		        L("        static int estimCount = -1;");
	    }
		}
        if ( ! f_verify )
            return;
		L("// verifier definitions");
		L("static String fmt_par[] = new String[6];");
		L("static long fmt_att[] = {24, -126, 127, 53, -1022, 1023};");
		L("static long exp_min, exp_max;");
		L("public static EFormat fm = EFormat.SINGLE_STANDARD;");
		L("public static EFormat fm2 = EFormat.DOUBLE_STANDARD;");
		L("static int total = 0, err_num = 0, err_fails = 0;");
		L("static Vector errVect = new Vector();");
        if ( verifType.equals(VER_SELECT) ) {
		    L("static long[][] selFmt = {");
		    L("    { 24,  -126,  127,  53, -1022, 1023}, // standard");
		    L("    { 53, -1022, 1023,  53, -1022, 1023}, // both double");
		    L("    { 64,-16382,16383,  64,-16382,16383}, // Intel");
		    L("    {113,-16382,16383, 113,-16382,16383}, // Sparc, PPC, HP-700");
		    L("    { 32, -1022, 1023,  64,-16382,16383}, // min extended");
		    L("    { 33, -1022, 1023,  65,-16382,16383}, // min extended + 1");
		    L("    {128, -((1L<<31)-2), (1L<<31)-1, 256,-((1L<<47)-2), (1L<<47)-1} // large formats");
		    L("};");
        }
    }


    protected void verifierBegin(int type, String out) {
    
        int par_ind = (type == FOR_FLOAT ? 0 : 3);

        if ( ! f_verify )
            return;
            
		L("total = 0;");
        if ( verifType.equals(VER_SELECT) ) {
			L("for ( int fmt_ind = 0; fmt_ind < selFmt.length; fmt_ind++ ) {");
//		    L("    prec = selFmt[fmt_ind]["+par_ind+"];");
//		    L("    exp_min = selFmt[fmt_ind]["+(par_ind+1)+"];");
//		    L("    exp_max = selFmt[fmt_ind]["+(par_ind+2)+"];");
		    L("    fm = new EFormat((int)selFmt[fmt_ind][0], selFmt[fmt_ind][1], selFmt[fmt_ind][2]);");
		    L("    fm2= new EFormat((int)selFmt[fmt_ind][3], selFmt[fmt_ind][4], selFmt[fmt_ind][5]);");
		    L("    for ( int i = 0; i < 6; i++)");
		    L("        fmt_par[i] = Long.toString(selFmt[fmt_ind][i]);");
        } else {
		    if ( type == FOR_FLOAT ) {
			    L("for ( int exp = " + fl_exp_lower + "; exp <= " + fl_exp_upper + "; exp++ ) {");
			    L("    for ( int prec = " + fl_prec_lower + "; prec <= " + fl_prec_upper + "; prec++ ) {");
		    } else {
			    L("for ( int exp = " + db_exp_lower + "; exp <= " + db_exp_upper + "; exp++ ) {");
			    L("    for ( int prec = " + db_prec_lower + "; prec <= " + db_prec_upper + "; prec++ ) {");
		    }
		    L("        fmt_att[" + par_ind + "] = prec;");
		    L("        fmt_att[" + (par_ind+1) + "] = -((1L << (exp-1)) - 2);");
		    L("        fmt_att[" + (par_ind+2) + "] = (1L << (exp-1)) - 1;");
		    L("        fm = new EFormat((int)fmt_att[0], fmt_att[1], fmt_att[2]);");
		    L("        fm2= new EFormat((int)fmt_att[3], fmt_att[4], fmt_att[5]);");
		    L("        for ( int i = 0; i < 6; i++)");
		    L("            fmt_par[i] = Long.toString(fmt_att[i]);");
		}
		L("        " + test + EST_SUFF + ".run(fmt_par, " + out + ");");
		L("        EFloat.setDefaultRoundingMode(EFloat.ROUND_DOWN);");
		L("        total++;");
		L("        for ( int ver_step = 0; ver_step < 2; ver_step++ ) {");
		L("            EFloat err_res;");
    }


    protected void verifierEnd() {

        if ( ! f_verify )
            return;
		L("            EFloat.setDefaultRoundingMode(EFloat.ROUND_NEAREST);");
		L("        }");
        if ( !verifType.equals(VER_SELECT) ) {
		    L("    }");
		}
		L("}");
		L("out.println( \"    Formats total = \" + total );");
        if ( f_verify_err ) {
		    L("out.println( \"    Incorrect results generated   : \" + err_num );");
		    L("out.println( \"    Incorrect results not detected: \" + err_fails );");
        }
    }


    protected void verifierMeth() {

        if ( ! f_verify )
            return;
    }


////////////////////////////////////////////////////////////////////////
//                                                                    //
//      G E N E R A T I O N  &  U S E   O F   E S T I M A T I O N     //
//                                                                    //
////////////////////////////////////////////////////////////////////////

/**  
 * Generate estimation class run method invocation which produces
 * estimations for all expressions used in the test.
 * In the f_estimate mode generate FPFormat object creation. Fields
 * of this object contain standard and actual extended format parameters.
 *
 * @param argv name of the arguments vector, the first parameter of the method run
 * @param out  name of the print stream, the second parameter of the method run
 */
    protected void getEstimation(String argv, String out) {

        if ( f_estimate ) {
		    L("        FPFormat fmt = new FPFormat(" + argv + ");");
            if ( !f_est_hot ) {
		        L("        int fmt_ind = FPFormat.find(fmt);");
		        L("        if (fmt_ind >= 0) {");
		        L("            estim = (Vector)" + test + "_hot.hotEstim.elementAt(fmt_ind);");
		        L("            return 0;");
		        L("        }");
	    }
		    L("        EValue.singleExtFormat = new EFormat(");
		    L("            fmt.SINGLE_EXTENDED_PRECISION,");
		    L("            fmt.SINGLE_EXTENDED_EXPONENT_MIN,");
		    L("            fmt.SINGLE_EXTENDED_EXPONENT_MAX);");
		    L("        EValue.doubleExtFormat = new EFormat(");
		    L("            fmt.DOUBLE_EXTENDED_PRECISION,");
		    L("            fmt.DOUBLE_EXTENDED_EXPONENT_MIN,");
		    L("            fmt.DOUBLE_EXTENDED_EXPONENT_MAX);");
        } else {
            if ( ! f_verify ) {
		        L("        " + test + EST_SUFF + ".run(" + argv + ", " + out + ");");
		    }
		    L("        Object estimation;");
        }
    }


    protected void getRes(int type,    String res_name,
                          String expr, String title, String out) {
    
        String a_type = (type == FOR_FLOAT ? "int" : "long");
        String cast = (type == FOR_FLOAT ? "int[]" : "long[]");
        String meth = null, conv = null, rep = null;
        String est_name;

	if (expr.trim().length() > 0)	
		L("	    " + expr + ";");
//		estNum++;
//		est_name = "estim" + estNum;

        if ( f_estimate ) {	// -------
//			L("        " + est_name  + " = " + res_name + ";");
            String conv_est = (type == FOR_FLOAT ? "roundToIntBits" : "roundToLongBits");
            L("        estim.addElement(" + res_name + "." + conv_est + "());");
            if ( f_est_hot ) {
//			    L("        "+out+".println(\"estVect.addElement(new "+a_type+"[] \" + "+res_name+".toJava() + \");\");");
			    L("        "+out+".println(\"    new "+a_type+"[] \" + "+res_name+
				    ".toJava() + \" ,		// -- # \"+ (++estimCount));");
			} else {
			    L("        " + res_name  + ".trace(\"" + res_name + "\", fmt.toString(), " + out + ");");
			}
            return;	// -------  RETURN if ( f_estimate ) 
        }
        
//        checkNum++;
//		est_name = test + EST_SUFF + "." + est_name;
		est_name = "estimation";
        if ( f_verify ) {
		    L("            if ( ver_step == 0 ) {");
		    L("                errVect.addElement(" + res_name + ");");
		    L("            } else {");
		}
	L("	    " + est_name + " = " + test + EST_SUFF + ".nextEstimation();");
        if ( f_verify ) {
            meth = (type == FOR_FLOAT ? "containsAsFloat"       : "containsAsDouble");
            conv = (type == FOR_FLOAT ? "EFloat.roundToIntBits" : "EFloat.roundToLongBits");
            rep  = (type == FOR_FLOAT ? "reportFailAsFloat"     : "reportFailAsDouble");
	    L("        if ( ! FPCheck."+meth+"(("+cast+")"+est_name+", "+conv+"("+res_name+")) ) {");
	    L("            "+out+".println( \"Estimation failed for correct result:\");");
	    L("            FPCheck."+rep+"(\""+title+"\", ("+cast+")"+est_name+", "+conv+"("+res_name+"), "+out+");");
        } else {
            meth = (type == FOR_FLOAT ? "containsFloat"   : "containsDouble");
            rep  = (type == FOR_FLOAT ? "reportFailFloat" : "reportFailDouble");
	    L("	    if ( ! FPCheck."+meth+"(("+cast+")"+est_name+", "+res_name+") ) {");
	    L("		FPCheck."+rep+"(\""+title+"\" + " + test+EST_SUFF + ".estimationId()");
	    L("					, ("+cast+")"+est_name+", "+ res_name+", "+out+");");
	}
	L("		return "+statusFailed+";");
	L("	    }");
        if (f_verify && f_verify_err) {
		    L("        err_res = (EFloat)errVect.remove(0);");
		    L("        if (" + res_name + ".compareTo(err_res) != 0 && !(" +
		                   res_name + ".isNaN() && err_res.isNaN())) {");
		    L("            err_num++;");
		    L("            if ( FPCheck."+meth+"(("+cast+")"+est_name+", "+conv+"(err_res)) ) {");
            L("                "+out+".println(\"" + res_name + ":  incorrect result not detected = \"+err_res);");
		    L("                err_fails++;");
		    L("            }");
		    L("        }");
		    L("            }");
        }
    }

}
