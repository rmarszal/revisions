package fr.upem.concurrence.td02;

public class Counter {

    private int value;

    public void add10000() {
        for (int i = 0; i < 10_000; i++) {
            value++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Runnable runnable = () -> {counter.add10000();};
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.value);
    }
}

/*
   On observe que le résultat affiché est compris entre 10000 et 20000.

   Supposons que valeur vaut 1 et que t1 et t2 vont executer valeur + 1. Si t2 lit la valeur de valuer et est
   deschedulé, t2 lit la valeur de value (1), l'incrémente et l'écrit en mémoire. Donc value vaut 2. Le thread est
   reschedulé, il incrémente sa valeur (2) et l'écrit en mémoire.

   La valeur de value est 0.
   Le thread 1 lit cette valeur et il est déschedulé. Le thread 2 est schédulé et il termine sont runnable. Donc la valeur
   de value est 10000. Le thread 1 est reschedulé et il remet la valeur à 1.
 */