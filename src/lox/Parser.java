package lox;

import java.util.List;
import java.util.ArrayList;

class Parser {
    private static class ParseError extends RuntimeException {}

    private final List<Token> tokens;
    private int current = 0;

    Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    // Retorna uma lista de instruções (statements)
    List<Stmt> parse() {
        List<Stmt> statements = new ArrayList<>();
        while (!isAtEnd()) {
            statements.add(declaration()); // ✅ agora usa declaração
        }
        return statements;
    }

    // 👇 Adicionado: reconhece declarações, como var
    private Stmt declaration() {
        try {
            if (match(TokenType.VAR)) return varDeclaration();
            return statement();
        } catch (ParseError error) {
            synchronize();
            return null;
        }
    }

    // 👇 Adicionado: declaração de variável
    private Stmt varDeclaration() {
        Token name = consume(TokenType.IDENTIFIER, "Esperado nome da variável.");

        Expr initializer = null;
        if (match(TokenType.EQUAL)) {
            initializer = expression();
        }

        consume(TokenType.SEMICOLON, "Esperado ';' após declaração da variável.");
        return new Stmt.Var(name, initializer);
    }

    // Instruções
    private Stmt statement() {
        if (match(TokenType.PRINT)) return printStatement();
        return expressionStatement();
    }

    private Stmt printStatement() {
        Expr value = expression();
        consume(TokenType.SEMICOLON, "Esperado ';' após valor.");
        return new Stmt.Print(value);
    }

    private Stmt expressionStatement() {
        Expr expr = expression();
        consume(TokenType.SEMICOLON, "Esperado ';' após expressão.");
        return new Stmt.Expression(expr);
    }

    // EXPRESSÃO PRINCIPAL - menor precedência: vírgula
    private Expr expression() {
        return comma();
    }

    private Expr comma() {
        Expr expr = ternary();
        while (match(TokenType.COMMA)) {
            Token operator = previous();
            Expr right = ternary();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }

    private Expr ternary() {
        Expr expr = equality();
        if (match(TokenType.QUESTION)) {
            Expr thenBranch = expression();
            consume(TokenType.COLON, "Esperado ':' após expressão do operador ternário.");
            Expr elseBranch = ternary();
            expr = new Expr.Ternary(expr, thenBranch, elseBranch);
        }
        return expr;
    }

    private Expr equality() {
        Expr expr = comparison();
        while (match(TokenType.BANG_EQUAL, TokenType.EQUAL_EQUAL)) {
            Token operator = previous();
            Expr right = comparison();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }

    private Expr comparison() {
        Expr expr = addition();
        while (match(TokenType.GREATER, TokenType.GREATER_EQUAL,
                TokenType.LESS, TokenType.LESS_EQUAL)) {
            Token operator = previous();
            Expr right = addition();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }

    private Expr addition() {
        Expr expr = multiplication();
        while (match(TokenType.MINUS, TokenType.PLUS)) {
            Token operator = previous();
            Expr right = multiplication();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }

    private Expr multiplication() {
        Expr expr = unary();
        while (match(TokenType.SLASH, TokenType.STAR)) {
            Token operator = previous();
            Expr right = unary();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }

    private Expr unary() {
        if (match(TokenType.BANG, TokenType.MINUS)) {
            Token operator = previous();
            Expr right = unary();
            return new Expr.Unary(operator, right);
        }
        return primary();
    }

    private Expr primary() {
        if (match(TokenType.FALSE)) return new Expr.Literal(false);
        if (match(TokenType.TRUE)) return new Expr.Literal(true);
        if (match(TokenType.NIL)) return new Expr.Literal(null);

        if (match(TokenType.NUMBER, TokenType.STRING)) {
            return new Expr.Literal(previous().literal);
        }

        if (match(TokenType.IDENTIFIER)) {
            return new Expr.Variable(previous()); // ✅ suporte ao uso de variáveis
        }

        if (match(TokenType.LEFT_PAREN)) {
            Expr expr = expression();
            consume(TokenType.RIGHT_PAREN, "Esperado ')' após expressão.");
            return new Expr.Grouping(expr);
        }

        throw error(peek(), "Esperava expressão.");
    }

    // UTILITÁRIOS

    private Token consume(TokenType type, String message) {
        if (check(type)) return advance();
        throw error(peek(), message);
    }

    private ParseError error(Token token, String message) {
        Lox.error(token, message);
        return new ParseError();
    }

    private void synchronize() {
        advance();

        while (!isAtEnd()) {
            if (previous().type == TokenType.SEMICOLON) return;

            switch (peek().type) {
                case CLASS:
                case FUN:
                case VAR:
                case FOR:
                case IF:
                case WHILE:
                case PRINT:
                case RETURN:
                    return;
            }

            advance();
        }
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().type == type;
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean isAtEnd() {
        return peek().type == TokenType.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }
}
