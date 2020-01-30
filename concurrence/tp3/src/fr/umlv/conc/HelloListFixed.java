package fr.umlv.conc;

import java.util.stream.IntStream;

public class HelloListFixed {
    public static void main(String[] args) throws InterruptedException {
        var nbThreads = 4;
        var threads = new Thread[nbThreads];

        var list = new fr.umlv.conc.ThreadSafeList();

        IntStream.range(0, nbThreads).forEach(j -> {
            Runnable runnable = () -> {
                for (var i = 0; i < 5000; i++) {
                    list.add(i);
                }
            };

            threads[j] = new Thread(runnable);
            threads[j].start();
        });

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("taille de la liste:" + list.size());
        System.out.println(list);
    }
}
