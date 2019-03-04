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

package com.sun.jck.lib;

import com.sun.javatest.TestEnvironment;
import com.sun.javatest.util.StringArray;

public abstract class Expr
{
    public static class Fault extends Exception {
    Fault(String msg) {
        super(msg);
    }
    };

    public static Expr parse(String s) throws Fault {
    Parser p = new Parser(s);
    return p.parse();
    }

    public abstract String eval(TestEnvironment e) throws Fault;

    public boolean evalBoolean(TestEnvironment e) throws Fault {
    String s = eval(e);
    if (s.equals("true"))
        return true;
    else if (s.equals("false"))
        return false;
    else
        throw new Fault("invalid boolean value: `" + s + "' for expression `" + this + "'");
    }

    public int evalInt(TestEnvironment e) throws Fault {
    String s = eval(e);
    try {
        return Integer.parseInt(s);
    }
    catch (NumberFormatException ex) {
        throw new Fault("invalid integer value: " + s);
    }
    }

    abstract int precedence();

    Expr order() {
    return this;
    }

    static class Parser {
    Parser(String text) throws Fault {
        this.text = text;
        nextToken();
    }

    Expr parse() throws Fault {
        Expr e = parseExpr();
        expect(END);
        return e;
    }

    Expr parseExpr() throws Fault {
        for (Expr e = parseTerm() ; e != null ; e = e.order()) {
        switch (token) {
        case ADD:
            nextToken();
            e = new AddExpr(e, parseTerm());
            break;
        case AND:
            nextToken();
            e = new AndExpr(e, parseTerm());
            break;
        case DIV:
            nextToken();
            e = new DivideExpr(e, parseTerm());
            break;
        case EQ:
            nextToken();
            e = new EqualExpr(e, parseTerm());
            break;
        case GE:
            nextToken();
            e = new GreaterEqualExpr(e, parseTerm());
            break;
        case GT:
            nextToken();
            e = new GreaterExpr(e, parseTerm());
            break;
        case LE:
            nextToken();
            e = new LessEqualExpr(e, parseTerm());
            break;
        case LT:
            nextToken();
            e = new LessExpr(e, parseTerm());
            break;
        case MUL:
            nextToken();
            e = new MultiplyExpr(e, parseTerm());
            break;
        case NE:
            nextToken();
            e = new NotEqualExpr(e, parseTerm());
            break;
        case OR:
            nextToken();
            e = new OrExpr(e, parseTerm());
            break;
        case REM:
            nextToken();
            e = new RemainderExpr(e, parseTerm());
            break;
        case SUB:
            nextToken();
            e = new SubtractExpr(e, parseTerm());
            break;
        default:
            return e;
        }
        }
        // bogus return to keep compiler happy
        return null;
    }

    Expr parseTerm() throws Fault {
        switch (token) {
        case NAME:
        String id = idValue;
        nextToken();
        return new NameExpr(id);

        case NOT:
        nextToken();
        return new NotExpr(parseTerm());

        case NUMBER:
        case TRUE:
        case FALSE:
        String num = idValue;
        nextToken();
        return new NumberExpr(num);

        case LPAREN:
        nextToken();
        Expr e = parseExpr();
        expect(RPAREN);
        return new ParenExpr(e);

        case STRING:
        String s = idValue;
        nextToken();
        return new StringExpr(s);

        default:
        throw new Fault("Syntax error in expression");
        }
    }

    private void expect(int t) throws Fault {
        if (t == token)
        nextToken();
        else
        throw new Fault("Syntax error in expression");
    }

    private void nextToken() throws Fault {
        while (index < text.length()) {
        char c = text.charAt(index++);
        switch (c) {
        case ' ':
        case '\t':
            continue;

        case '&':
            token = AND;
            return;

        case '|':
            token = OR;
            return;

        case '+':
            token = ADD;
            return;

        case '-':
            token = SUB;
            return;

        case '*':
            token = MUL;
            return;

        case '/':
            token = DIV;
            return;

        case '%':
            token = REM;
            return;

        case '(':
            token = LPAREN;
            return;

        case ')':
            token = RPAREN;
            return;

        case '<':
            if (index < text.length() && text.charAt(index) == '=') {
            token = LE;
            index++;
            }
            else
            token = LT;
            return;

        case '>':
            if (index < text.length() && text.charAt(index) == '=') {
            token = GE;
            index++;
            }
            else
            token = GT;
            return;

        case '=':
            if (index < text.length() && text.charAt(index) == '=') {
            token = EQ;
            index++;
            }
            else
            throw new Fault("unexpected character after `='");
            return;

        case '!':
            if (index < text.length() && text.charAt(index) == '=') {
            token = NE;
            index++;
            }
            else
            token = NOT;
            return;

        case '\"':
            StringBuffer sb = new StringBuffer();
            if (index < text.length()) {
            c = text.charAt(index);
            index++;
            }
            else
            throw new Fault("invalid string constant");
            while (c != '\"') {
            sb.append(c);
            if (index < text.length()) {
                c = text.charAt(index);
                index++;
            }
            else
                break;
            }
            if (c == '\"') {
            token = STRING;
            idValue = String.valueOf(sb);
            }
            else
            throw new Fault("invalid string constant");
            return;

        default:
            if (Character.isUnicodeIdentifierStart(c)) {
            idValue = String.valueOf(c);
            while (index < text.length()) {
                c = text.charAt(index);
                if (Character.isUnicodeIdentifierPart(c) || c == '.') {
                if (!Character.isIdentifierIgnorable(c))
                    idValue += c;
                index++;
                }
                else
                break;
            }
            if (idValue.equalsIgnoreCase("true"))
                token = TRUE;
            else if (idValue.equalsIgnoreCase("false"))
                token = FALSE;
            else
                token = NAME;
            return;
            }
            else if (Character.isDigit(c)) {
            int n = Character.digit(c, 10);
            while (index < text.length()) {
                c = text.charAt(index);
                if (Character.isDigit(c)) {
                n = n * 10 + Character.digit(c, 10);
                index++;
                }
                else
                break;
            }
            token = NUMBER;
            idValue = String.valueOf(n);
            return;
            }
            else throw new Fault("unrecognized character: `" + c + "'");
        }
        }
        token = END;
    }

    private String text;
    private int index;
    private int token;
    private String idValue;
    }

    protected static final int
    ADD     =  0,
    AND     =  1,
    DIV     =  2,
    END     =  3,
    ERROR     =  4,
    EQ     =  5,
    FALSE     =  6,
    GE     =  7,
    GT     =  8,
    LE     =  9,
    LPAREN     = 10,
    LT     = 11,
    MUL     = 12,
    NAME     = 13,
    NE     = 14,
    NOT     = 15,
    NUMBER     = 16,
    OR     = 17,
    REM     = 18,
    RPAREN     = 19,
    STRING     = 20,
    SUB     = 21,
    TRUE     = 22;

    protected static final int PREC_LIT = 6;
    protected static final int PREC_NOT = 6;
    protected static final int PREC_NUM = 6;
    protected static final int PREC_PRN = 6;
    protected static final int PREC_TRM = 6;
    protected static final int PREC_DIV = 5;
    protected static final int PREC_MUL = 5;
    protected static final int PREC_REM = 5;
    protected static final int PREC_ADD = 4;
    protected static final int PREC_SUB = 4;
    protected static final int PREC_GE  = 3;
    protected static final int PREC_GT  = 3;
    protected static final int PREC_LE  = 3;
    protected static final int PREC_LT  = 3;
    protected static final int PREC_EQ  = 2;
    protected static final int PREC_NE  = 2;
    protected static final int PREC_AND = 1;
    protected static final int PREC_OR  = 0;
}


//------------------------------------------------------------------------------

abstract class BinaryExpr extends Expr
{
    BinaryExpr(Expr left, Expr right) {
    this.left = left;
    this.right = right;
    }

    Expr order() {
    if (precedence() > left.precedence() && left instanceof BinaryExpr) {
        BinaryExpr e = (BinaryExpr)left;
        left = e.right;
        e.right = order();
        return e;
    } else
        return this;
    }

    protected Expr left;
    protected Expr right;
}

//------------------------------------------------------------------------------

class AddExpr extends BinaryExpr
{
    AddExpr(Expr left, Expr right) {
    super(left, right);
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(left.evalInt(e) + right.evalInt(e));
    }

    int precedence() {
    return PREC_ADD;
    }

    public String toString() {
    return "`" + left + "+" + right + "'";
    }
}

//------------------------------------------------------------------------------

class AndExpr extends BinaryExpr
{
    AndExpr(Expr left, Expr right) {
    super(left, right);
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(left.evalBoolean(e) & right.evalBoolean(e));
    }

    int precedence() {
    return PREC_AND;
    }

    public String toString() {
    return "`" + left + "&" + right + "'";
    }
}

//------------------------------------------------------------------------------

class DivideExpr extends BinaryExpr
{
    DivideExpr(Expr left, Expr right) {
    super(left, right);
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(left.evalInt(e) / right.evalInt(e));
    }

    int precedence() {
    return PREC_DIV;
    }

    public String toString() {
    return "`" + left + "/" + right + "'";
    }
}

//------------------------------------------------------------------------------

class EqualExpr extends BinaryExpr
{
    EqualExpr(Expr left, Expr right) {
    super(left, right);
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(left.eval(e).equalsIgnoreCase(right.eval(e)));
    }

    int precedence() {
    return PREC_EQ;
    }

    public String toString() {
    return "`" + left + "==" + right + "'";
    }
}

//------------------------------------------------------------------------------

class GreaterExpr extends BinaryExpr
{
    GreaterExpr(Expr left, Expr right) {
    super(left, right);
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(left.evalInt(e) > right.evalInt(e));
    }

    int precedence() {
    return PREC_GT;
    }

    public String toString() {
    return "`" + left + ">" + right + "'";
    }
}

//------------------------------------------------------------------------------

class GreaterEqualExpr extends BinaryExpr
{
    GreaterEqualExpr(Expr left, Expr right) {
    super(left, right);
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(left.evalInt(e) >= right.evalInt(e));
    }

    int precedence() {
    return PREC_GE;
    }

    public String toString() {
    return "`" + left + ">=" + right + "'";
    }
}

//------------------------------------------------------------------------------

class LessExpr extends BinaryExpr
{
    LessExpr(Expr left, Expr right) {
    super(left, right);
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(left.evalInt(e) < right.evalInt(e));
    }

    int precedence() {
    return PREC_LT;
    }

    public String toString() {
    return "`" + left + "<" + right + "'";
    }
}

//------------------------------------------------------------------------------

class LessEqualExpr extends BinaryExpr
{
    LessEqualExpr(Expr left, Expr right) {
    super(left, right);
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(left.evalInt(e) <= right.evalInt(e));
    }

    int precedence() {
    return PREC_LE;
    }

    public String toString() {
    return "`" + left + "<=" + right + "'";
    }
}

//------------------------------------------------------------------------------

class MultiplyExpr extends BinaryExpr
{
    MultiplyExpr(Expr left, Expr right) {
    super(left, right);
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(left.evalInt(e) * right.evalInt(e));
    }

    int precedence() {
    return PREC_MUL;
    }

    public String toString() {
    return "`" + left + "*" + right + "'";
    }
}

//------------------------------------------------------------------------------

class NameExpr extends Expr
{
    NameExpr(String key) {
    this.key = key;
    }

    public String eval(TestEnvironment e) throws Fault {
    try {
        String[] v = (e == null ? null : e.lookup(key));
        if (v == null)
        throw new Fault("name not defined: " + key);
        return StringArray.join(v);
    }
    catch (TestEnvironment.Fault ex) {
        throw new Fault("error looking up: " + key);
    }
    }

    int precedence() {
    return PREC_TRM;
    }

    public String toString() {
    return key;
    }

    private String key;
}

//------------------------------------------------------------------------------

class NotEqualExpr extends BinaryExpr
{
    NotEqualExpr(Expr left, Expr right) {
    super(left, right);
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(!left.eval(e).equalsIgnoreCase(right.eval(e)));
    }

    int precedence() {
    return PREC_NE;
    }

    public String toString() {
    return "`" + left + "!=" + right + "'";
    }
}

//------------------------------------------------------------------------------

class NotExpr extends Expr
{
    NotExpr(Expr expr) {
    this.expr = expr;
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(!expr.evalBoolean(e));
    }

    int precedence() {
    return PREC_NOT;
    }

    public String toString() {
    return "!" + expr;
    }

    private Expr expr;
}

//------------------------------------------------------------------------------

class NumberExpr extends Expr
{
    NumberExpr(String value) {
    this.value = value;
    }

    public String eval(TestEnvironment e) throws Fault {
    return value;
    }

    int precedence() {
    return PREC_NUM;
    }

    public String toString() {
    return value;
    }

    private String value;
}

//------------------------------------------------------------------------------

class OrExpr extends BinaryExpr
{
    OrExpr(Expr left, Expr right) {
    super(left, right);
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(left.evalBoolean(e) | right.evalBoolean(e));
    }

    int precedence() {
    return PREC_OR;
    }

    public String toString() {
    return "`" + left + "|" + right + "'";
    }
}

//------------------------------------------------------------------------------

class ParenExpr extends Expr
{
    ParenExpr(Expr expr) {
    this.expr = expr;
    }

    public String eval(TestEnvironment e) throws Fault {
    return expr.eval(e);
    }

    int precedence() {
    return PREC_PRN;
    }

    public String toString() {
    return "(" + expr + ")";
    }

    private Expr expr;
}

//------------------------------------------------------------------------------

class RemainderExpr extends BinaryExpr
{
    RemainderExpr(Expr left, Expr right) {
    super(left, right);
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(left.evalInt(e) % right.evalInt(e));
    }

    int precedence() {
    return PREC_REM;
    }

    public String toString() {
    return "`" + left + "%" + right + "'";
    }
}

//------------------------------------------------------------------------------

class StringExpr extends Expr
{
    StringExpr(String value) {
    this.value = value;
    }

    public String eval(TestEnvironment e) throws Fault {
    return value;
    }

    int precedence() {
    return PREC_LIT;
    }

    public String toString() {
    return '"' + value + '"';
    }

    private String value;
}

//------------------------------------------------------------------------------

class SubtractExpr extends BinaryExpr
{
    SubtractExpr(Expr left, Expr right) {
    super(left, right);
    }

    public String eval(TestEnvironment e) throws Fault {
    return String.valueOf(left.evalInt(e) - right.evalInt(e));
    }

    int precedence() {
    return PREC_SUB;
    }

    public String toString() {
    return "`" + left + "-" + right + "'";
    }
}
