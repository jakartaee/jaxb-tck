/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * https://www.eclipse.org/legal/epl-2.0.
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

public abstract class Expr {
    public static class Fault extends Exception {
        Fault(String msg) {
            super(msg);
        }
    }

    public static Expr parse(String s) throws Fault {
        Parser p = new Parser(s);
        return p.parse();
    }

    public abstract String eval(TestEnvironment e) throws Fault;

    public boolean evalBoolean(TestEnvironment e) throws Fault {
        String s = eval(e);
        if (s.equals("true")) {
            return true;
        } else if (s.equals("false")) {
            return false;
        } else {
            throw new Fault("invalid boolean value: `" + s + "' for expression `" + this + "'");
        }
    }

    public int evalInt(TestEnvironment e) throws Fault {
        String s = eval(e);
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ex) {
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
            for (Expr e = parseTerm(); e != null; e = e.order()) {
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
            if (t == token) {
                nextToken();
            } else {
                throw new Fault("Syntax error in expression");
            }
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
                        } else {
                            token = LT;
                        }
                        return;

                    case '>':
                        if (index < text.length() && text.charAt(index) == '=') {
                            token = GE;
                            index++;
                        } else {
                            token = GT;
                        }
                        return;

                    case '=':
                        if (index < text.length() && text.charAt(index) == '=') {
                            token = EQ;
                            index++;
                        } else {
                            throw new Fault("unexpected character after `='");
                        }
                        return;

                    case '!':
                        if (index < text.length() && text.charAt(index) == '=') {
                            token = NE;
                            index++;
                        } else {
                            token = NOT;
                        }
                        return;

                    case '\"':
                        StringBuilder sb = new StringBuilder();
                        if (index < text.length()) {
                            c = text.charAt(index);
                            index++;
                        } else {
                            throw new Fault("invalid string constant");
                        }
                        while (c != '\"') {
                            sb.append(c);
                            if (index < text.length()) {
                                c = text.charAt(index);
                                index++;
                            } else {
                                break;
                            }
                        }
                        if (c == '\"') {
                            token = STRING;
                            idValue = String.valueOf(sb);
                        } else {
                            throw new Fault("invalid string constant");
                        }
                        return;

                    default:
                        if (Character.isUnicodeIdentifierStart(c)) {
                            idValue = String.valueOf(c);
                            while (index < text.length()) {
                                c = text.charAt(index);
                                if (Character.isUnicodeIdentifierPart(c) || c == '.') {
                                    if (!Character.isIdentifierIgnorable(c)) {
                                        idValue += c;
                                    }
                                    index++;
                                } else {
                                    break;
                                }
                            }
                            if (idValue.equalsIgnoreCase("true")) {
                                token = TRUE;
                            } else if (idValue.equalsIgnoreCase("false")) {
                                token = FALSE;
                            } else {
                                token = NAME;
                            }
                            return;
                        } else if (Character.isDigit(c)) {
                            int n = Character.digit(c, 10);
                            while (index < text.length()) {
                                c = text.charAt(index);
                                if (Character.isDigit(c)) {
                                    n = n * 10 + Character.digit(c, 10);
                                    index++;
                                } else {
                                    break;
                                }
                            }
                            token = NUMBER;
                            idValue = String.valueOf(n);
                            return;
                        } else {
                            throw new Fault("unrecognized character: `" + c + "'");
                        }
                }
            }
            token = END;
        }

        private final String text;
        private int index;
        private int token;
        private String idValue;
    }

    protected static final int
            ADD = 0,
            AND = 1,
            DIV = 2,
            END = 3,
            ERROR = 4,
            EQ = 5,
            FALSE = 6,
            GE = 7,
            GT = 8,
            LE = 9,
            LPAREN = 10,
            LT = 11,
            MUL = 12,
            NAME = 13,
            NE = 14,
            NOT = 15,
            NUMBER = 16,
            OR = 17,
            REM = 18,
            RPAREN = 19,
            STRING = 20,
            SUB = 21,
            TRUE = 22;

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
    protected static final int PREC_GE = 3;
    protected static final int PREC_GT = 3;
    protected static final int PREC_LE = 3;
    protected static final int PREC_LT = 3;
    protected static final int PREC_EQ = 2;
    protected static final int PREC_NE = 2;
    protected static final int PREC_AND = 1;
    protected static final int PREC_OR = 0;
}
