import br.com.marcosprado.dominio.Jogador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class JogadorTeste {

    private Jogador jogador;

    @BeforeEach
    public void setup() {
        jogador = new Jogador("Marcos");
    }

    @Test
    public void testaInicializacaoDoJogador() {
        jogador.inicializaJogador();

        for (boolean[] linha : jogador.getTabuleiro()) {
            for (boolean coluna : linha) {
                assertFalse(coluna);
            }
        }
    }

    @Test
    public void testaAdicaoDeNavio() {
        var coordenadaX = 0;
        var coordenadaY = 0;
        jogador.adicionarNavio(coordenadaX, coordenadaY);

        assertEquals(1, jogador.getNumeroDeNaviosAdicionados());
        assertTrue(jogador.getTabuleiro()[coordenadaX][coordenadaY]);
    }

    @Test
    public void testaAdicaoDeNavioComCoordenadasInvalidas() {
        Random random = new Random();
        int[] limites = {100, 100};

        for (int num : limites) {
            assertThrows(IllegalArgumentException.class,
                    () -> jogador.adicionarNavio((random.nextInt(num) * -1), random.nextInt(num)));
            assertThrows(IllegalArgumentException.class,
                    () -> jogador.adicionarNavio(random.nextInt(num), (random.nextInt(num) * -1)));
        }
    }
}
