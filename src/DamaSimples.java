import java.util.Scanner;

public class DamaSimples {

    // Contadores de capturas de cada jogador
    static int capturasP = 0;
    static int capturasB = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Entrada dos nomes dos jogadores
        System.out.print("Nome do jogador P: ");
        String nomeP = sc.nextLine();
        System.out.print("Nome do jogador B: ");
        String nomeB = sc.nextLine();

        // Criação do tabuleiro inicial e definição do jogador que começa
        String[][] tabuleiro = criarTabuleiro();
        String jogadorAtual = "P";
        String nomeAtual = nomeP;

        System.out.println();

        // Loop principal do jogo
        while (true) {
            mostrarTabuleiro(tabuleiro); // Exibe o tabuleiro e placar
            boolean jogadaFeita = fazerJogada(tabuleiro, jogadorAtual, nomeAtual, sc);

            // Verifica se o jogo acabou (sem peças de um jogador)
            if (verificarFimDeJogo(tabuleiro)) break;

            // Alterna o jogador se a jogada foi válida
            if (jogadaFeita) {
                if (jogadorAtual.equals("P")) {
                    jogadorAtual = "B";
                    nomeAtual = nomeB;
                } else {
                    jogadorAtual = "P";
                    nomeAtual = nomeP;
                }
            }
        }

        sc.close();
    }

    /**
     * Cria o tabuleiro inicial da dama (8x8),
     * posicionando as peças "P" (brancas) e "B" (pretas)
     * nas posições corretas.
     */
    public static String[][] criarTabuleiro() {
        String[][] tabuleiro = new String[8][8];
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                // Casas pretas recebem peças; casas brancas ficam vazias
                if ((linha + coluna) % 2 == 1) {
                    if (linha < 3) tabuleiro[linha][coluna] = " B ";
                    else if (linha > 4) tabuleiro[linha][coluna] = " P ";
                    else tabuleiro[linha][coluna] = "   ";
                } else {
                    tabuleiro[linha][coluna] = "   ";
                }
            }
        }
        return tabuleiro;
    }

    /**
     * Mostra o tabuleiro no console com coordenadas,
     * linhas, colunas e o placar de capturas.
     */
    public static void mostrarTabuleiro(String[][] tabuleiro) {
        System.out.print("    ");
        for (char c = 'A'; c <= 'H'; c++) System.out.print(" " + c + "  ");
        System.out.println();
        System.out.println("   ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗");

        for (int linha = 0; linha < 8; linha++) {
            System.out.print(" " + (linha + 1) + " ║");
            for (int coluna = 0; coluna < 8; coluna++) {
                System.out.print(tabuleiro[linha][coluna] + "║");
            }
            System.out.println();
            if (linha < 7) System.out.println("   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣");
            else System.out.println("   ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝");
        }

        // Mostra o placar
        System.out.println();
        System.out.println("🎯 Capturas:");
        System.out.println("P (brancas): " + capturasP);
        System.out.println("B (pretas):  " + capturasB);
        System.out.println("-------------------------------\n");
    }

    /**
     * Converte coordenadas inseridas pelo jogador (ex: "B3")
     * em índices numéricos da matriz do tabuleiro.
     */
    public static int[] converterCoordenada(String entrada) {
        entrada = entrada.toUpperCase();
        char colunaChar = entrada.charAt(0);
        int linha = Integer.parseInt(entrada.substring(1)) - 1;
        int coluna = colunaChar - 'A';
        return new int[]{linha, coluna};
    }

    /**
     * Executa a jogada de um jogador, verificando:
     * - Se o movimento é válido
     * - Se há capturas obrigatórias
     * - Se ocorre promoção para dama
     * - E se há capturas múltiplas possíveis
     */
    public static boolean fazerJogada(String[][] tabuleiro, String jogador, String nome, Scanner sc) {
        System.out.println(nome + " (" + jogador + "), digite a posição da peça:");
        String origem = sc.nextLine();
        System.out.println("Digite a posição de destino:");
        String destino = sc.nextLine();

        int[] origemCoord = converterCoordenada(origem);
        int[] destinoCoord = converterCoordenada(destino);

        int linhaOrigem = origemCoord[0];
        int colunaOrigem = origemCoord[1];
        int linhaDestino = destinoCoord[0];
        int colunaDestino = destinoCoord[1];

        String peca = tabuleiro[linhaOrigem][colunaOrigem].trim();

        // Verifica se a peça pertence ao jogador
        if (!peca.equals(jogador) && !peca.equals("D" + jogador)) {
            System.out.println("Essa peça não pertence ao jogador " + nome + ".");
            return false;
        }

        // Verifica se a casa de destino está vazia
        if (!tabuleiro[linhaDestino][colunaDestino].trim().isEmpty()) {
            System.out.println("A casa de destino não está vazia.");
            return false;
        }

        int direcao = jogador.equals("P") ? -1 : 1;
        boolean dama = peca.startsWith("D");

        // Verifica se há captura obrigatória
        boolean capturaObrigatoria = temCapturaDisponivel(tabuleiro, jogador);

        // Movimento simples (sem captura)
        if (!capturaObrigatoria && (Math.abs(linhaDestino - linhaOrigem) == 1) && (Math.abs(colunaDestino - colunaOrigem) == 1) && (dama || linhaDestino == linhaOrigem + direcao)) {

            tabuleiro[linhaDestino][colunaDestino] = " " + peca + " ";
            tabuleiro[linhaOrigem][colunaOrigem] = "   ";
            return true;
        }

        // Movimento de captura (pula sobre peça adversária)
        else if ((Math.abs(linhaDestino - linhaOrigem) == 2) && (Math.abs(colunaDestino - colunaOrigem) == 2)) {

            int linhaMeio = (linhaOrigem + linhaDestino) / 2;
            int colunaMeio = (colunaOrigem + colunaDestino) / 2;
            String pecaMeio = tabuleiro[linhaMeio][colunaMeio].trim();

            // Verifica se há peça inimiga entre origem e destino
            if (!pecaMeio.isEmpty() && !pecaMeio.equals(jogador) && !pecaMeio.equals("D" + jogador)) {

                // Move e remove a peça capturada
                tabuleiro[linhaDestino][colunaDestino] = " " + peca + " ";
                tabuleiro[linhaOrigem][colunaOrigem] = "   ";
                tabuleiro[linhaMeio][colunaMeio] = "   ";

                // Atualiza contador de capturas
                if (jogador.equals("P")) capturasP++;
                else capturasB++;

                // Verifica se há nova captura com a mesma peça
                while (podeCapturarNovamente(tabuleiro, linhaDestino, colunaDestino, jogador)) {
                    mostrarTabuleiro(tabuleiro);
                    System.out.println(nome + " pode capturar novamente com a mesma peça.");
                    System.out.println("Digite nova posição de destino:");
                    destino = sc.nextLine();
                    destinoCoord = converterCoordenada(destino);
                    linhaOrigem = linhaDestino;
                    colunaOrigem = colunaDestino;
                    linhaDestino = destinoCoord[0];
                    colunaDestino = destinoCoord[1];

                    // Repete a captura
                    if ((Math.abs(linhaDestino - linhaOrigem) == 2) && (Math.abs(colunaDestino - colunaOrigem) == 2)) {

                        linhaMeio = (linhaOrigem + linhaDestino) / 2;
                        colunaMeio = (colunaOrigem + colunaDestino) / 2;
                        pecaMeio = tabuleiro[linhaMeio][colunaMeio].trim();

                        if (!pecaMeio.isEmpty() && !pecaMeio.equals(jogador) && !pecaMeio.equals("D" + jogador) && tabuleiro[linhaDestino][colunaDestino].trim().isEmpty()) {

                            tabuleiro[linhaDestino][colunaDestino] = " " + peca + " ";
                            tabuleiro[linhaOrigem][colunaOrigem] = "   ";
                            tabuleiro[linhaMeio][colunaMeio] = "   ";

                            if (jogador.equals("P")) capturasP++;
                            else capturasB++;
                        } else {
                            System.out.println("Captura inválida.");
                            break;
                        }
                    } else {
                        System.out.println("Movimento inválido.");
                        break;
                    }
                }
            } else {
                System.out.println("Não há peça adversária para capturar.");
                return false;
            }
        } else {
            System.out.println("Movimento inválido.");
            return false;
        }

        // Promoção: transforma em dama se chegar ao fim do tabuleiro
        if (jogador.equals("P") && linhaDestino == 0) {
            tabuleiro[linhaDestino][colunaDestino] = "DP ";
        } else if (jogador.equals("B") && linhaDestino == 7) {
            tabuleiro[linhaDestino][colunaDestino] = "DB ";
        }

        return true;
    }

    /**
     * Verifica se o jogador possui alguma captura disponível no tabuleiro.
     */
    public static boolean temCapturaDisponivel(String[][] tabuleiro, String jogador) {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                String peca = tabuleiro[linha][coluna].trim();
                if (!peca.equals(jogador) && !peca.equals("D" + jogador)) continue;

                if (executarCaptura(tabuleiro, jogador, linha, coluna, peca)) return true;
            }
        }
        return false;
    }

    /**
     * Testa se uma peça em uma posição pode capturar outra.
     * Usado para verificar jogadas possíveis e capturas múltiplas.
     */
    public static boolean executarCaptura(String[][] tabuleiro, String jogador, int linha, int coluna, String peca) {
        int[] direcoes = peca.startsWith("D") ? new int[]{-2, 2} : new int[]{jogador.equals("P") ? -2 : 2};

        for (int dl : direcoes) {
            for (int dc : new int[]{-2, 2}) {
                int novaLinha = linha + dl;
                int novaColuna = coluna + dc;

                if (novaLinha >= 0 && novaLinha < 8 && novaColuna >= 0 && novaColuna < 8) {
                    int meioLinha = (linha + novaLinha) / 2;
                    int meioColuna = (coluna + novaColuna) / 2;
                    String meio = tabuleiro[meioLinha][meioColuna].trim();
                    String destino = tabuleiro[novaLinha][novaColuna].trim();

                    if (!meio.trim().isEmpty() && !meio.trim().equals(jogador) && !meio.trim().equals("D" + jogador) && destino.trim().isEmpty()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Verifica se a mesma peça pode capturar novamente após uma jogada.
     */
    public static boolean podeCapturarNovamente(String[][] tabuleiro, int linha, int coluna, String jogador) {
        String peca = tabuleiro[linha][coluna].trim();
        if (!peca.equals(jogador) && !peca.equals("D" + jogador)) return false;

        return executarCaptura(tabuleiro, jogador, linha, coluna, peca);
    }

    /**
     * Verifica se o jogo terminou (quando um dos jogadores não tem mais peças).
     */
    public static boolean verificarFimDeJogo(String[][] tabuleiro) {
        int contadorP = 0;
        int contadorB = 0;

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                String celula = tabuleiro[linha][coluna].trim();
                if (celula.equals("P") || celula.equals("DP")) contadorP++;
                if (celula.equals("B") || celula.equals("DB")) contadorB++;
            }
        }

        // Declara vencedor
        if (contadorP == 0) {
            System.out.println("Jogador B venceu!");
            return true;
        } else if (contadorB == 0) {
            System.out.println("Jogador P venceu!");
            return true;
        }

        return false;
    }
}
