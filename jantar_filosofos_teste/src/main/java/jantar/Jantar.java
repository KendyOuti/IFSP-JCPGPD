package jantar;

public class Jantar {
    private static final int NUM_FILOSOFOS = 5;
    private Filosofo[] filosofos;
    private Garfo[] garfos;

    public void iniciar() {
        garfos = new Garfo[NUM_FILOSOFOS];
        filosofos = new Filosofo[NUM_FILOSOFOS];

        // cria os garfos
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            garfos[i] = new Garfo(i);
        }

        // cria os filósofos (cada um compartilha garfos com os vizinhos)
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            Garfo garfoEsquerdo = garfos[i];
            Garfo garfoDireito = garfos[(i + 1) % NUM_FILOSOFOS];
            filosofos[i] = new Filosofo(i, garfoEsquerdo, garfoDireito);
        }

        // inicia as threads (opcional para o teste)
        for (Filosofo f : filosofos) {
            f.start();
        }
    }

    public Filosofo[] getFilosofos() {
        return filosofos;
    }

    public Garfo[] getGarfos() {
        return garfos;
    }

    public void encerrar() {
        for (Filosofo f : filosofos) {
            f.parar();
        }
    }
}
