package br.com.marcosprado;

import br.com.marcosprado.dominio.Jogador;
import br.com.marcosprado.dominio.Jogo;

public class Iniciar {
    public static void main(String[] args) {

//      No diretório 'main/test' encontrará os testes unitário testando todas as funcionalidades do jogo.

        Jogador jogador1 = new Jogador("Marcos");
        jogador1.adicionarNavio(0, 0);
        jogador1.adicionarNavio(1, 0);

        Jogador jogador2 = new Jogador("Fernando");
        jogador2.adicionarNavio(2, 4);
        jogador2.adicionarNavio(3, 4);


        Jogo jogo = new Jogo(jogador1, jogador2);
        jogo.atirar(2, 4); // Turno do jogador1
        jogo.atirar(2, 1); // Turno do jogador2
        jogo.atirar(3,4); // Turno do jogador1
    }

}
