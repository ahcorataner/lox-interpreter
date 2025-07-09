package lox;

import java.util.HashMap;
import java.util.Map;

class Environment {
    final Environment enclosing; // 🔗 Ambiente pai
    private final Map<String, Object> values = new HashMap<>();

    // Construtor para escopo global
    Environment() {
        this.enclosing = null;
    }

    // Construtor para escopo local
    Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    // Define variável no escopo atual
    void define(String name, Object value) {
        values.put(name, value);
    }

    // Busca variável (pode subir nos escopos externos)
    Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        }
        if (enclosing != null) return enclosing.get(name);

        throw new RuntimeError(name, "Variável indefinida '" + name.lexeme + "'.");
    }

    // Atribuição: atualiza no escopo onde foi definida
    void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }
        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name, "Variável indefinida '" + name.lexeme + "'.");
    }
}
