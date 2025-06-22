package lox;

import java.util.List;

public class TestParser {
    public static void main(String[] args) {
        // Código-fonte de teste
        String source = "-123 * (45.67)";

        // Etapa 1: Scanner transforma string em tokens
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // Etapa 2: Parser transforma tokens em AST
        Parser parser = new Parser(tokens);
        Expr expression = parser.parse();

        // Etapa 3: ASTPrinter exibe a árvore sintática
        if (expression != null) {
            System.out.println(new AstPrinter().print(expression));
        } else {
            System.out.println("Erro ao fazer parsing da expressão.");
        }
    }
}
