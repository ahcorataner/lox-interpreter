public class Main {
    public static void main(String[] args) {
        String sourceCode = "var x = 10; print(x);";  // exemplo de código que você quer analisar

        Scanner scanner = new Scanner(sourceCode);   // cria o scanner com o código
        List<Token> tokens = scanner.scanTokens();   // gera a lista de tokens

        // Mostrar tokens para ver o resultado
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
