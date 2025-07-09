package lox;

import java.util.HashMap;
import java.util.Map;

class Environment {
    private final Map<String, Object> values = new HashMap<>();

    // Define uma nova variável ou atualiza no REPL
    void define(String name, Object value) {
        values.put(name, value);
    }

    // Recupera valor da variável, ou lança erro se não existir
    Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        }

        throw new RuntimeError(name, "Variável indefinida '" + name.lexeme + "'.");
    }

    // Atribui novo valor à variável já existente
    void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }

        throw new RuntimeError(name, "Variável indefinida '" + name.lexeme + "'.");
    }
}
