# ♟️ DamaSimples — Jogo de Damas em Java

Um jogo simples de **Damas** implementado em **Java**, jogado totalmente pelo **terminal**.
Feito com o objetivo de praticar **lógica de programação, manipulação de matrizes, loops, condições** e **entrada de dados**.

---

## 🚀 Funcionalidades

✅ Criação automática do tabuleiro 8x8
✅ Movimentos válidos para peças brancas (P) e pretas (B)
✅ Sistema de **capturas** (incluindo capturas múltiplas)
✅ **Promoção para dama** (`DP` e `DB`) ao chegar na última linha
✅ Exibição do **placar de capturas**
✅ Verificação automática de **vitória**
✅ Interface textual organizada e intuitiva

---

## 🧠 Regras implementadas

* As peças se movem apenas nas casas pretas do tabuleiro.
* Peças comuns andam **uma casa na diagonal** (para frente).
* Capturas são feitas **saltando sobre** uma peça inimiga.
* É possível fazer **capturas múltiplas** se a peça continuar apta a capturar.
* Ao alcançar o lado oposto, a peça é **promovida a dama** (`DP` ou `DB`).
* O jogo termina quando um jogador perde todas as suas peças.

---

## 🕹️ Como jogar

1. Compile o código:

   ```bash
   javac DamaSimples.java
   ```

2. Execute o jogo:

   ```bash
   java DamaSimples
   ```

3. Informe os nomes dos jogadores:

   ```
   Nome do jogador P: Heron
   Nome do jogador B: Victor
   ```

4. O tabuleiro será exibido assim:

   ```
       A   B   C   D   E   F   G   H
    ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗
   1║   ║ B ║   ║ B ║   ║ B ║   ║ B ║
   2║ B ║   ║ B ║   ║ B ║   ║ B ║   ║
   3║   ║ B ║   ║ B ║   ║ B ║   ║ B ║
   4║   ║   ║   ║   ║   ║   ║   ║   ║
   5║   ║   ║   ║   ║   ║   ║   ║   ║
   6║ P ║   ║ P ║   ║ P ║   ║ P ║   ║
   7║   ║ P ║   ║ P ║   ║ P ║   ║ P ║
   8║ P ║   ║ P ║   ║ P ║   ║ P ║   ║
    ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝
   ```

5. Para mover uma peça, digite:

   ```
   B3
   C4
   ```

   Onde **B3** é a posição de origem e **C4** é o destino.

---

## 📖 Estrutura do código

| Método                    | Função principal                                            |
| ------------------------- | ----------------------------------------------------------- |
| `criarTabuleiro()`        | Inicializa o tabuleiro com as peças nas posições padrão     |
| `mostrarTabuleiro()`      | Exibe o tabuleiro e o placar                                |
| `converterCoordenada()`   | Converte coordenadas como “B3” em índices de matriz         |
| `fazerJogada()`           | Processa a jogada, verificando validade, captura e promoção |
| `temCapturaDisponivel()`  | Detecta se há jogadas obrigatórias de captura               |
| `executarCaptura()`       | Confere se uma peça pode capturar alguma outra              |
| `podeCapturarNovamente()` | Permite capturas múltiplas com a mesma peça                 |
| `verificarFimDeJogo()`    | Checa se um dos jogadores venceu                            |

---

## 🧩 Estrutura de dados usada

* **Tabuleiro:**
  Representado por uma matriz 8x8 de `String`

  ```java
  String[][] tabuleiro = new String[8][8];
  ```
* Cada posição contém:

  * `" P "` → peça branca
  * `" B "` → peça preta
  * `"DP "` → dama branca
  * `"DB "` → dama preta
  * `"   "` → espaço vazio

---

## 🧑‍💻 Tecnologias utilizadas

* **Java 17+**
* **Scanner (entrada de dados)**
* **Programação estruturada** com foco em controle de fluxo e condições

---

## 🏁 Condições de vitória

O jogo termina automaticamente quando:

* Um dos jogadores **não tem mais peças** no tabuleiro.
* O sistema anuncia o vencedor:

  ```
  Jogador P venceu!
  ```

  ou

  ```
  Jogador B venceu!
  ```

---

## 💡 Possíveis melhorias futuras

* Implementar **movimentos livres de dama** (movendo várias casas diagonais)
* Forçar captura obrigatória de forma automática
* Adicionar **menu inicial** (jogar, ver regras, sair)

---

## 👨‍🎓 Autore

* **Gabriel Heron da Cunha**

> Universidade Regional de Blumenau (FURB)
> Curso de Sistemas de Informação
> 2025
