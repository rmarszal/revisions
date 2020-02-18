package fr.upem.concurrence.td04;

import java.util.ArrayDeque;

public class UnboundedSafeQueue<V> {
    private final int maxSize = 10;
    private final ArrayDeque<V> safeQueue = new ArrayDeque<>(maxSize);

    public void add(V value) {
        synchronized (safeQueue) {
            safeQueue.add(value);
            safeQueue.notifyAll();
        }
    }

    public void put(V value) throws InterruptedException {
        synchronized (safeQueue) {
            while (safeQueue.size() >= maxSize) {
                safeQueue.wait();
            }
            safeQueue.add(value);
            safeQueue.notifyAll();
        }
    }

    public V take() throws InterruptedException {
        synchronized (safeQueue) {
            while (safeQueue.isEmpty()) {
                safeQueue.wait();
            }
            safeQueue.notifyAll();
            return safeQueue.remove();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var queue = new UnboundedSafeQueue<String>();

        for (int i = 0 ; i < 50 ; ++i) {
            new Thread(
                    () -> {
                        try {
                            Thread.sleep(2000);
                            queue.put(Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            throw new AssertionError(e);
                        }
                    }).start();
        }

        for (;;) {
            System.out.println(queue.take());
        }
    }
}
