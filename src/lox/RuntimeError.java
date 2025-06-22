package lox;

public class RuntimeError extends RuntimeException {
    final Token token;

    public RuntimeError(Token token, String message) {
        super(message); // Passa a mensagem para a classe RuntimeException
        this.token = token; // Armazena o token associado ao erro
    }
}
