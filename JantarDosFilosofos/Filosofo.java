import java.util.Random;

public class Filosofo implements Runnable {

    private final Random random = new Random();
    private final String nome;

    private Filosofo proximo; 
    
    private boolean podeJantar = false; 

    public Filosofo(String nome) {
        this.nome = nome;
    }

    public void setProximoFilosofo(Filosofo next) {
        this.proximo = next;
    }
    
    public void setPodeJantar(boolean podeJantar) {
        this.podeJantar = podeJantar;
    }

    @Override
    public void run() {

        for (int i = 0; i < 20; i++) { 
            
            this.esperarParaJantar();
            
            this.jantar(i + 1);

            this.notificarProximo();
            
        }
    }


    public void esperarParaJantar() {
        synchronized(this) { 
            while (!this.podeJantar) {
                try {
                    System.out.println(
                        " <<< Filosofo " + this.nome + " está esperando ser notificado...");
                    this.wait(); 
                } catch(InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            this.podeJantar = false; 
        }
    }

    public void notificarProximo() {
        synchronized(this.proximo) {
            this.proximo.podeJantar = true;
            System.out.println(
                " >>> Filosofo " + this.nome + " notifica " + this.proximo.nome + ".");
            this.proximo.notify();
        }
    }

    public void jantar(int turn) {

        final long tempoComendo = this.random.nextLong(500, 2000); 
        final long tempoInicial = System.currentTimeMillis();

        System.out.println(
            "*** Filosofo " + this.nome + " está jantando pela " + 
            String.valueOf(turn) + "a vez e por " + 
            String.valueOf(tempoComendo / 1000.) + " segundos");

        while (System.currentTimeMillis() - tempoInicial < tempoComendo); 

        System.out.println(
            "*** Filosofo " + this.nome + " terminou de jantar!");
    }
}