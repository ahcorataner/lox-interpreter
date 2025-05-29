package lox;

import java.util.ArrayList;
import java.util.List;

import static lox.TokenType.*;

class Scanner {
    private final String source;         // Código-fonte bruto
    private final List<Token> tokens = new ArrayList<>(); // Tokens gerados

    private int start = 0;      // Início do lexema atual
    private int current = 0;    // Próximo caractere a ser analisado
    private int line = 1;       // Linha atual no código-fonte

    Scanner(String source) {
        this.source = source;
    }

    List<Token> scanTokens() {
        while (!isAtEnd()) {
            // Marca o início do próximo lexema
            start = current;
            scanToken();
        }

        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    // Método scanToken() vai ser implementado para identificar cada token
    private void scanToken() {
        // Implementação posterior
    }
}
