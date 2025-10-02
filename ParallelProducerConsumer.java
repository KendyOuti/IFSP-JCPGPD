import java.util.LinkedList;
import java.util.Queue;

// Classe que representa a lista compartilhada
class SharedList {
    private final Queue<Integer> list = new LinkedList<>();
    private final int capacity;

    public SharedList(int capacity) {
        this.capacity = capacity;
    }

    // Região crítica: produtor insere valores na lista
    public synchronized void produce(int value) throws InterruptedException {
        while (list.size() == capacity) {
            wait(); // aguarda até que haja espaço
        }
        list.add(value); // acesso à estrutura compartilhada
        System.out.println("Produziu: " + value);
        notifyAll(); // acorda consumidores
    }

    // Região crítica: consumidor remove valores da lista
    public synchronized int consume() throws InterruptedException {
        while (list.isEmpty()) {
            wait(); // aguarda até que haja item
        }
        int value = list.poll(); // acesso à estrutura compartilhada
        System.out.println("Consumiu: " + value);
        notifyAll(); // acorda produtores
        return value;
    }
}

// Classe principal
public class ParallelProducerConsumer {
    public static void main(String[] args) {
        SharedList sharedList = new SharedList(5);

        // Thread produtora
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    sharedList.produce(i); // região crítica
                    Thread.sleep(100); 
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Thread consumidora
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    sharedList.consume(); // região crítica
                    Thread.sleep(150); 
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
