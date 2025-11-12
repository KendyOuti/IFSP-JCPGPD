package jantar;

import java.util.concurrent.locks.ReentrantLock;

public class Garfo {
    private final int id;
    private final ReentrantLock lock;

    public Garfo(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public int getId() {
        return id;
    }

    public void pegar() {
        lock.lock();
    }

    public void largar() {
        lock.unlock();
    }

    public boolean estaDisponivel() {
        return !lock.isLocked();
    }
}
