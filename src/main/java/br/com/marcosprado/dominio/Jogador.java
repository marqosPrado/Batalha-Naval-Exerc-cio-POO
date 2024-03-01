package br.com.marcosprado.dominio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Jogador {
    private static final Logger logger = LogManager.getLogger(Jogador.class);
    private static final int TABULEIRO_LARGURA = 7;
    private static final int TABULEIRO_ALTURA = 10;
    private static final int NUMERO_MAXIMO_DE_NAVIOS = 2;

    private String nome;
    private final boolean[][] tabuleiro;
    private int pontos;
    private int numeroDeNaviosAdicionados;

    public Jogador(String nome) {
        this.nome = nome;
        tabuleiro = new boolean[TABULEIRO_LARGURA][TABULEIRO_ALTURA];
        inicializaJogador();
    }

    public void adicionarNavio(int coordenadaX, int coordenadaY) {
        if (coordenadaX < 0 || coordenadaX >= TABULEIRO_LARGURA || coordenadaY < 0 || coordenadaY >= TABULEIRO_ALTURA)
            throw new IllegalArgumentException("Coordenadas inválidas");
        if (numeroDeNaviosAdicionados >= NUMERO_MAXIMO_DE_NAVIOS)
            throw new IllegalArgumentException("Número máximo de navios atingido");

        if (tabuleiro[coordenadaX][coordenadaY]) {
            logger.info("Jogador: {} tentou adicionar um navio na posição: {} {}", nome, coordenadaX, coordenadaY);
        } else {
            tabuleiro[coordenadaX][coordenadaY] = true;
            numeroDeNaviosAdicionados++;
            logger.info("Jogador: {} adicionou um navio na posição: {} {}", nome, coordenadaX, coordenadaY);
        }
    }

    public void inicializaJogador() {
        for (boolean[] linha : tabuleiro) {
            Arrays.fill(linha, false);
        }
    }

    ///////// Getters and Setters //////////
    public boolean[][] getTabuleiro() {
        return tabuleiro;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public static int getNumeroMaximoDeNavios() {
        return NUMERO_MAXIMO_DE_NAVIOS;
    }

    public int getNumeroDeNaviosAdicionados() {
        return numeroDeNaviosAdicionados;
    }

    public void setNumeroDeNaviosAdicionados(int numeroDeNaviosAdicionados) {
        this.numeroDeNaviosAdicionados = numeroDeNaviosAdicionados;
    }

    public String getNome() {
        return nome;
    }

}
