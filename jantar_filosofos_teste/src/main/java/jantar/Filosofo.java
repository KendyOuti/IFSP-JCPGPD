package jantar;

public class Filosofo extends Thread {
    private final int id;
    private final Garfo garfoEsquerdo;
    private final Garfo garfoDireito;
    private boolean ativo = true;

    public Filosofo(int id, Garfo garfoEsquerdo, Garfo garfoDireito) {
        this.id = id;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
    }

    public Garfo getGarfoEsquerdo() {
        return garfoEsquerdo;
    }

    public Garfo getGarfoDireito() {
        return garfoDireito;
    }

    public int getIdFilosofo() {
        return id;
    }

    @Override
    public void run() {
        try {
            while (ativo) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando...");
        Thread.sleep(100);
    }

    private void comer() throws InterruptedException {
        garfoEsquerdo.pegar();
        garfoDireito.pegar();

        System.out.println("Filósofo " + id + " está comendo...");
        Thread.sleep(100);

        garfoDireito.largar();
        garfoEsquerdo.largar();
    }

    public void parar() {
        ativo = false;
    }
}
