package lox;

import java.util.List;

public class TestParser {
    public static void main(String[] args) {
        // Código fonte para testar
        String source = "-123 * (45.67) == 10 + 5";

        // 1) Scanner: converte texto em tokens
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // 2) Parser: converte tokens em AST
        Parser parser = new Parser(tokens);
        Expr expression = parser.parse();

        // 3) Imprime a AST
        if (expression != null) {
            System.out.println(new AstPrinter().print(expression));
        } else {
            System.out.println("Erro ao analisar a expressão.");
        }
    }
}
