# 🔥 Interpretador Lox em Java — Compiladores | UFMA 🇧🇷

Projeto desenvolvido para a disciplina **Compiladores** da **Universidade Federal do Maranhão (UFMA)**, no curso de **Engenharia da Computação** 
Implementa um **interpretador funcional da linguagem Lox**, inspirado no livro [_Crafting Interpreters_](https://craftinginterpreters.com/) de **Bob Nystrom**, utilizando **Java 24** com recursos modernos.

---

## 🎯 Objetivo do Projeto

Construir, passo a passo, um interpretador completo da linguagem **Lox**, com recursos típicos de linguagens modernas:

- 📦 **Funções e escopo léxico**
- 🧠 **Resolução de variáveis**
- 🧬 **Classes, métodos, inicializadores**
- 🔁 **Controle de fluxo com blocos e loops**
- 🌳 **AST e execução estilo Tree-Walk**

---

## 📚 Referência

- 📖 Livro-base: [_Crafting Interpreters_](https://craftinginterpreters.com/)  
- ✍️ Autor: **Bob Nystrom**

---

## 🧠 Capítulos Implementados

| Capítulo | Tema Principal        | Conteúdo                                                                 |
|----------|-----------------------|--------------------------------------------------------------------------|
| 🔢 04     | Scanning              | Scanner, tokens, espaços, comentários                                    |
| 🔢 05     | Representing Code     | AST com classes para expressões e declarações                            |
| 🔢 06     | Parsing Expressions   | Parser recursivo com precedência                                         |
| 🔢 07     | Evaluating Expressions| Avaliação de expressões matemáticas e lógicas                            |
| 🔢 08     | Statements and State  | Escopo local, ambiente, variáveis, `print`, `var`                        |
| 🔢 09     | Control Flow          | Comandos `if`, `else`, `while`, `for`, blocos `{}` e escopo              |
| 🔢 10     | Functions             | Declaração de funções, chamadas, retorno com `return`                    |
| 🔢 11     | Resolving and Binding | Escopo léxico, resolução e visibilidade de variáveis                     |
| 🔢 12     | Classes               | Declaração de classes, inicializador `init`, métodos e uso de `this`     |


✅ **Tudo testado e funcionando com sintaxe integrada** 💙

---

## 🧪 Testes e Demonstração

Trecho de código válido e testado:

```lox
fun saudacao(nome) {
  return "Olá, " + nome + "!";
}

class Pessoa {
  init(nome) {
    this.nome = nome;
  }

  apresentar() {
    print saudacao(this.nome);
  }
}

var r = Pessoa("Renata");
r.apresentar(); // ➜ Olá, Renata!
```

> Mostra funções, escopo, classe com método e uso do `this`!

---

## 🛠️ Como Compilar e Executar

### 🔧 Compilação

```bash
javac --enable-preview --release 24 -d bin src/lox/*.java
```

### 🚀 Execução

```bash
java --enable-preview -cp bin lox.Lox teste.lox
```

---

## 🗂️ Estrutura do Projeto

```plaintext
jlox/
├── src/lox/          ← Código-fonte (.java)
│   └── tool/         ← Gerador de AST (GenerateAst.java)
├── bin/lox/          ← Arquivos compilados (.class)
├── teste.lox         ← Script de testes da linguagem
├── .gitignore        ← Ignora `.class`, binários e pastas IDE
├── README.md         ← Este documento
```
## ✅ Testes de Validação da Entrega Final

- 🔁 Execução no modo REPL testada com:
  - Variáveis (`var nome = "Renata";`)
  - Funções (`fun saudacao(n) {...}`)
  - Classes (`class Pessoa { init(...) {...} }`)

- 📄 Execução de programas completos:
  - `programa1.lox`: Funções e lógica condicional (`fatorial`, `print`, `if`)
  - `programa2.lox`: Declaração de classe `Conta`, métodos (`depositar`, `mostrar`), inicializador `init` e instância funcionando

- 🧪 Todos os programas executaram corretamente via:
  ```bash
  java --enable-preview -cp bin lox.Lox programa1.lox
  java --enable-preview -cp bin lox.Lox programa2.lox

---

## 👩‍💻 Autora

- **Nome:** Renata Costa Rocha  
- **Curso:** Engenharia da Computação  
- **Universidade:** UFMA – Universidade Federal do Maranhão  
- **Disciplina:** Compiladores  
- **GitHub:** [@ahcorataner](https://github.com/ahcorataner)

---
