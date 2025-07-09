package lox;

import java.util.List;

public class TestParser {
    public static void main(String[] args) {
        // Código fonte para testar
        String source = "print -123 * (45.67) == 10 + 5;";

        // 1) Scanner: converte texto em tokens
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // 2) Parser: converte tokens em AST
        Parser parser = new Parser(tokens);
        List<Stmt> statements = parser.parse(); // ✅ agora retorna lista de instruções

        // 3) Imprime a AST (usando AstPrinter nas expressões dentro dos statements)
        AstPrinter printer = new AstPrinter();

        if (statements != null && !statements.isEmpty()) {
            for (Stmt stmt : statements) {
                if (stmt instanceof Stmt.Print) {
                    Expr expr = ((Stmt.Print) stmt).expression;
                    System.out.println(printer.print(expr));
                } else if (stmt instanceof Stmt.Expression) {
                    Expr expr = ((Stmt.Expression) stmt).expression;
                    System.out.println(printer.print(expr));
                } else {
                    System.out.println("Instrução desconhecida.");
                }
            }
        } else {
            System.out.println("Erro ao analisar a expressão.");
        }
    }
}
