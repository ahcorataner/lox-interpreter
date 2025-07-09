# 🔥 Interpretador Lox em Java — Compiladores | UFMA 🇧🇷

Projeto desenvolvido para a disciplina **Compiladores** da **Universidade Federal do Maranhão (UFMA)**, no curso de **Engenharia da Computação**.  
Implementa um interpretador funcional da linguagem **Lox**, inspirada no livro [_Crafting Interpreters_](https://craftinginterpreters.com/) de **Bob Nystrom**, utilizando **Java 24** com recursos modernos.

---

## 🎯 Objetivo do Projeto

Construir, etapa por etapa, um interpretador completo da linguagem Lox, aplicando conceitos de construção de linguagens:

- 📌 **Análise Léxica:** scanner e geração de tokens
- 📌 **Análise Sintática:** parser recursivo descendente
- 📌 **Construção da AST:** estrutura abstrata do código
- 📌 **Interpretação Tree-Walk:** execução da árvore sintática
- 📌 **Escopo e Fluxo de Controle:** suporte a `var`, `if`, `while`, `for`, blocos `{}`

---

## 📚 Referência

- 🧠 **Livro base:** [_Crafting Interpreters_](https://craftinginterpreters.com/)  
  ✍️ Autor: **Bob Nystrom**

---

## 🧠 Capítulos Implementados

| Capítulo | Título                      | Conteúdo                                          |
|----------|-----------------------------|---------------------------------------------------|
| 4️⃣      | Scanning                    | Scanner, tokens, espaços, comentários             |
| 5️⃣      | Representing Code           | AST, tipos de expressões e declarações            |
| 6️⃣      | Parsing Expressions         | Parser com precedência e agrupamentos             |
| 7️⃣      | Evaluating Expressions      | Interpretador aritmético e lógico                 |
| 8️⃣      | Statements and State        | Declarações `print`, `var`, escopo e variáveis    |
| 9️⃣      | Control Flow                | `if`, `else`, `while`, `for`, blocos e escopo     |

🔜 **Capítulo 10 em desenvolvimento:** Funções e chamadas

---

## 🧪 Como Compilar e Executar

### 🛠️ Compilação:

```bash
javac --enable-preview --release 24 -d bin src/lox/*.java

**### 🚀 Execução:**
java --enable-preview -cp bin lox.Lox teste.lox

🗂️ **Estrutura do Projeto**

jlox/
├── src/lox/          ← Código-fonte (.java)
│   └── tool/         ← Gerador de AST (GenerateAst.java)
├── bin/lox/          ← Arquivos compilados (.class)
├── teste.lox         ← Arquivo de testes Lox
├── .gitignore        ← Arquivos ignorados no Git
├── README.md         ← Este documento

## 👩‍💻 **Autora**

- **Nome:** Renata Costa Rocha  
- **Curso:** Engenharia da Computação  
- **Universidade:** UFMA – Universidade Federal do Maranhão  
- **Disciplina:** Compiladores  
- **GitHub:** [**@ahcorataner**](https://github.com/ahcorataner)


