package lox;

public class AstTest {
    public static void main(String[] args) {
        // Cria uma expressão do tipo: (1 + 2)
        Expr expression = new Expr.Binary(
                new Expr.Literal(1),                          // lado esquerdo: 1
                new Token(TokenType.PLUS, "+", null, 1),      // operador: +
                new Expr.Literal(2)                           // lado direito: 2
        );

        // Apenas imprime o objeto (vai mostrar o endereço de memória, mas já é um teste)
        System.out.println(expression);
    }
}
