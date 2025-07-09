package lox;

import java.util.List;

public class Interpreter implements Expr.Visitor<Object>, Stmt.Visitor<Void> {
    private final Environment environment = new Environment();

    public void interpret(List<Stmt> statements) {
        try {
            for (Stmt statement : statements) {
                execute(statement);
            }
        } catch (RuntimeError error) {
            Lox.runtimeError(error);
        }
    }

    private void execute(Stmt stmt) {
        stmt.accept(this);
    }

    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }

    // PRINT
    @Override
    public Void visitPrintStmt(Stmt.Print stmt) {
        Object value = evaluate(stmt.expression);
        System.out.println(stringify(value));
        return null;
    }

    // EXPRESSÃO
    @Override
    public Void visitExpressionStmt(Stmt.Expression stmt) {
        evaluate(stmt.expression);
        return null;
    }

    // VARIÁVEL: declaração
    @Override
    public Void visitVarStmt(Stmt.Var stmt) {
        Object value = null;
        if (stmt.initializer != null) {
            value = evaluate(stmt.initializer);
        }

        environment.define(stmt.name.lexeme, value);
        return null;
    }

    // VARIÁVEL: acesso
    @Override
    public Object visitVariableExpr(Expr.Variable expr) {
        return environment.get(expr.name);
    }

    // LITERAL
    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.value;
    }

    // AGRUPAMENTO
    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return evaluate(expr.expression);
    }

    // UNÁRIO
    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        Object right = evaluate(expr.right);

        switch (expr.operator.type) {
            case MINUS:
                checkNumberOperand(expr.operator, right);
                return -(double) right;
            case BANG:
                return !isTruthy(right);
        }

        return null;
    }

    // BINÁRIO
    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right);

        switch (expr.operator.type) {
            case PLUS:
                if (left instanceof Double && right instanceof Double) {
                    return (double) left + (double) right;
                }
                if (left instanceof String || right instanceof String) {
                    return stringify(left) + stringify(right);
                }
                throw new RuntimeError(expr.operator, "Operandos devem ser dois números ou strings.");

            case MINUS:
                checkNumberOperands(expr.operator, left, right);
                return (double) left - (double) right;

            case STAR:
                checkNumberOperands(expr.operator, left, right);
                return (double) left * (double) right;

            case SLASH:
                checkNumberOperands(expr.operator, left, right);
                return (double) left / (double) right;

            case GREATER:
                checkNumberOperands(expr.operator, left, right);
                return (double) left > (double) right;

            case GREATER_EQUAL:
                checkNumberOperands(expr.operator, left, right);
                return (double) left >= (double) right;

            case LESS:
                checkNumberOperands(expr.operator, left, right);
                return (double) left < (double) right;

            case LESS_EQUAL:
                checkNumberOperands(expr.operator, left, right);
                return (double) left <= (double) right;

            case BANG_EQUAL:
                return !isEqual(left, right);

            case EQUAL_EQUAL:
                return isEqual(left, right);
        }

        return null;
    }

    // TERNÁRIO
    @Override
    public Object visitTernaryExpr(Expr.Ternary expr) {
        Object condition = evaluate(expr.condition);
        if (isTruthy(condition)) {
            return evaluate(expr.thenBranch);
        } else {
            return evaluate(expr.elseBranch);
        }
    }

    // AUXILIARES
    private boolean isTruthy(Object object) {
        if (object == null) return false;
        if (object instanceof Boolean) return (boolean) object;
        return true;
    }

    private boolean isEqual(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null) return false;
        return a.equals(b);
    }

    private void checkNumberOperand(Token operator, Object operand) {
        if (operand instanceof Double) return;
        throw new RuntimeError(operator, "Operando deve ser um número.");
    }

    private void checkNumberOperands(Token operator, Object left, Object right) {
        if (left instanceof Double && right instanceof Double) return;
        throw new RuntimeError(operator, "Operandos devem ser números.");
    }

    private String stringify(Object object) {
        if (object == null) return "nil";

        if (object instanceof Double) {
            String text = object.toString();
            if (text.endsWith(".0")) {
                text = text.substring(0, text.length() - 2);
            }
            return text;
        }

        return object.toString();
    }
}
