package br.com.marcosprado.dominio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Jogo {
    private static final Logger logger = LogManager.getLogger(Jogo.class);
    private static final int TABULEIRO_LARGURA = 7;
    private static final int TABULEIRO_ALTURA = 10;

    private Jogador jogadorUm;
    private Jogador jogadorDois;
    private boolean eTurnoJogadorUm;

    public Jogo(Jogador jogadorUm, Jogador jogadorDois) {
        this.jogadorUm = jogadorUm;
        this.jogadorDois = jogadorDois;
        eTurnoJogadorUm = true;
    }

    public void atirar(int coordenadaX, int coordenadaY) {
        validarCoordenadas(coordenadaX, coordenadaY);

        Jogador jogadorAtual = eTurnoJogadorUm ? jogadorUm : jogadorDois;
        Jogador jogadorOponente = eTurnoJogadorUm ? jogadorDois : jogadorUm;

        boolean temNavio = jogadorOponente.getTabuleiro()[coordenadaX][coordenadaY];
        if (temNavio) {
            atualizarJogoAposAcerto(jogadorAtual, jogadorOponente, coordenadaX, coordenadaY);
        } else {
            atualizarJogoAposErro(jogadorAtual, coordenadaX, coordenadaY);
        }

        eTurnoJogadorUm = !eTurnoJogadorUm;

        // Verifica se o jogo terminou após cada turno
        Jogador vencedor = vencedor();
        if (vencedor != null) {
            logger.info("O vencedor é: {}", vencedor.getNome());
        }
    }

    private void validarCoordenadas(int coordenadaX, int coordenadaY) {
        if (coordenadaX < 0 || coordenadaX >= TABULEIRO_LARGURA || coordenadaY < 0 || coordenadaY >= TABULEIRO_ALTURA) {
            throw new IllegalArgumentException("Coordenadas inválidas");
        }
    }

    private void atualizarJogoAposAcerto(
            Jogador jogadorAtual,Jogador jogadorOponente, int coordenadaX, int coordenadaY
    ) {
        jogadorOponente.getTabuleiro()[coordenadaX][coordenadaY] = false;
        jogadorOponente.setNumeroDeNaviosAdicionados(jogadorOponente.getNumeroDeNaviosAdicionados() - 1);
        jogadorAtual.setPontos(jogadorAtual.getPontos() + 1);
        logger.info("Jogador: {} acertou na posição: {} {}", jogadorAtual.getNome(), coordenadaX, coordenadaY);
    }

    private void atualizarJogoAposErro(Jogador jogadorAtual, int coordenadaX, int coordenadaY) {
        logger.info("Jogador: {} errou na posição: {} {}", jogadorAtual.getNome(), coordenadaX, coordenadaY);
    }

    public Jogador vencedor() {
        var numeroPontosParaGanhar = Jogador.getNumeroMaximoDeNavios();
        if (jogadorUm.getPontos() == numeroPontosParaGanhar) return jogadorUm;
        if (jogadorDois.getPontos() == numeroPontosParaGanhar) return jogadorDois;
        return null; // Nenhum jogador venceu ainda
    }

    //////// Getters ////////
    public Jogador getJogadorUm() {
        return jogadorUm;
    }

    public Jogador getJogadorDois() {
        return jogadorDois;
    }

    public boolean isETurnoJogadorUm() {
        return eTurnoJogadorUm;
    }
}
