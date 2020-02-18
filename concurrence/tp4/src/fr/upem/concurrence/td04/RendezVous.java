package fr.upem.concurrence.td04;

import java.util.Objects;

public class RendezVous<V> {
    private final Object lock = new Object();
    private V value;

    public void set(V value) {
        Objects.requireNonNull(value);
        synchronized (lock) {
            this.value = value;
            lock.notifyAll();
        }
    }

    public V get() throws InterruptedException {
        synchronized (lock) {
            while (value == null) {
                lock.wait();
            }
            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var rdv = new RendezVous<String>();
        new Thread(
                () -> {
                    try {
                        Thread.sleep(20_000);
                        rdv.set("Message");
                    } catch (InterruptedException e) {
                        throw new AssertionError(e);
                    }
                })
                .start();
        System.out.println(rdv.get());
    }
}