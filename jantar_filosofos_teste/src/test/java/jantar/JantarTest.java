package jantar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JantarTest {

    private Jantar jantar;

    @BeforeEach
    void setUp() {
        jantar = new Jantar();
        jantar.iniciar();
    }

    @Test
    void testConfiguracaoInicialFalha() {
        assertEquals(10, jantar.getFilosofos().length, "Deveria haver 10 filósofos (falha esperada)");
    }
}
