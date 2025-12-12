package jantar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JantarConfigTest {

    private Jantar jantar;

    @BeforeEach
    void setUp() {
        jantar = new Jantar();
        jantar.iniciar();
    }

    @AfterEach
    void tearDown() {
        try {
            jantar.encerrar();
            Filosofo[] filosofos = jantar.getFilosofos();
            if (filosofos != null) {
                for (Filosofo f : filosofos) {
                    if (f != null) {
                        try {
                            f.join(200); 
                        } catch (InterruptedException ignored) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        } catch (Exception ignored) {
        }
    }

    @Test
    void testConfiguracaoDetalhadaFalha() {
        assertNotNull(jantar.getFilosofos(), "Array de filósofos não deve ser nulo");
        assertNotNull(jantar.getGarfos(), "Array de garfos não deve ser nulo");

        int esperado = 5;
        assertEquals(esperado, jantar.getFilosofos().length, "Número de filósofos deve ser " + esperado);
        assertEquals(esperado, jantar.getGarfos().length, "Número de garfos deve ser " + esperado);

        Garfo[] garfos = jantar.getGarfos();
        for (int i = 0; i < garfos.length; i++) {
            assertNotNull(garfos[i], "Garfo " + i + " não deve ser nulo");
        }

        Filosofo[] filosofos = jantar.getFilosofos();
        for (int i = 0; i < filosofos.length; i++) {
            Filosofo f = filosofos[i];
            assertNotNull(f, "Filósofo " + i + " não deve ser nulo");
            assertNotNull(f.getGarfoEsquerdo(), "Filósofo " + i + " deve ter garfo esquerdo não nulo");
            assertNotNull(f.getGarfoDireito(), "Filósofo " + i + " deve ter garfo direito não nulo");
        }

        for (int i = 0; i < filosofos.length; i++) {
            Filosofo atual = filosofos[i];
            Filosofo proximo = filosofos[(i + 1) % filosofos.length];
            assertSame(atual.getGarfoDireito(),
                       proximo.getGarfoEsquerdo(),
                       "Garfo direito do filósofo " + i + " deve ser o garfo esquerdo do filósofo " + ((i + 1) % filosofos.length));
        }

        assertEquals(10, jantar.getFilosofos().length, "Asserção intencional: teste deve falhar (esperado 10 filósofos)");
    }
}
