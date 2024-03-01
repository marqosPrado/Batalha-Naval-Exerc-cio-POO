import br.com.marcosprado.dominio.Jogador;
import br.com.marcosprado.dominio.Jogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JogoTeste {

    private Jogo jogo;
    private Jogador jogador1;
    private Jogador jogador2;

    @BeforeEach
    public void setup() {
        jogador1 = new Jogador("Marcos");
        jogador2 = new Jogador("Fernando");
        jogo = new Jogo(jogador1, jogador2);

        configurarNavios(jogador1, new int[][] {
                {0, 0}, {1, 0}
        });

        configurarNavios(jogador2, new int[][] {
                {2, 4}, {3, 4}
        });
    }

    private void configurarNavios(Jogador jogador, int[][] coordenadas) {
        for (int[] coordenada : coordenadas) {
            jogador.adicionarNavio(coordenada[0], coordenada[1]);
        }
    }

    @Test
    public void testarAlternanciaDeTurnos() {
        assertTrue(jogo.isETurnoJogadorUm());
        jogo.atirar(0, 0);
        assertFalse(jogo.isETurnoJogadorUm());
        jogo.atirar(2, 1);
    }

    @Test
    public void testarAtirarForaDoTabuleiro() {
        assertThrows(IllegalArgumentException.class, () -> jogo.atirar(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> jogo.atirar(0, -1));
        assertThrows(IllegalArgumentException.class, () -> jogo.atirar(7, 0));
        assertThrows(IllegalArgumentException.class, () -> jogo.atirar(0, 10));
    }

    @Test
    public void testarVencedorJogador1() {
        jogador2.setPontos(2);
        assertEquals(jogador2, jogo.vencedor());
    }

    @Test
    public void testarVencedorJogador2() {
        jogador1.setPontos(2);
        assertEquals(jogador1, jogo.vencedor());
    }


}
