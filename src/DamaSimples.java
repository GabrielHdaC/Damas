public class DamaSimples {

    public static void main(String[] args) {
        new DamaSimples();
    }

    public DamaSimples() {
        String[][] tabuleiro = criarTabuleiro();
        mostrarTabuleiro(tabuleiro);
    }

    public static String[][] criarTabuleiro() {
        String[][] tabuleiro = new String[8][8];

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                if ((linha + coluna) % 2 == 1) {
                    if (linha < 3) {
                        tabuleiro[linha][coluna] = " B ";
                    } else if (linha > 4) {
                        tabuleiro[linha][coluna] = " P ";
                    } else {
                        tabuleiro[linha][coluna] = "   ";
                    }
                } else {
                    tabuleiro[linha][coluna] = "   ";
                }
            }
        }

        return tabuleiro;
    }

    public static void mostrarTabuleiro(String[][] tabuleiro) {
        // Cabeçalho das colunas
        System.out.print("    ");
        for (char c = 'A'; c <= 'H'; c++) {
            System.out.print(" " + c + "  ");
        }
        System.out.println();

        // Linha superior da borda
        System.out.println("   ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗");

        for (int linha = 0; linha < 8; linha++) {
            // Conteúdo da linha
            System.out.print(" " + (linha + 1) + " ║");
            for (int coluna = 0; coluna < 8; coluna++) {
                System.out.print(tabuleiro[linha][coluna] + "║");
            }
            System.out.println();

            // Linha divisória ou inferior
            if (linha < 7) {
                System.out.println("   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣");
            } else {
                System.out.println("   ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝");
            }
        }
    }

    public static int[] converterCoordenada(String entrada) {
        entrada = entrada.toUpperCase();
        char colunaChar = entrada.charAt(0);
        int linha = Integer.parseInt(entrada.substring(1)) - 1;
        int coluna = colunaChar - 'A';
        return new int[]{linha, coluna};
    }


}
