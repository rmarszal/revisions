package fr.upem.concurrence.td02;

public class StopThreadBug implements Runnable {
    private boolean stop = false;

    public void stop() {
        stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            System.out.println("Up");
        }
        System.out.print("Done");
    }

    public static void main(String[] args) throws InterruptedException {
        StopThreadBug st = new StopThreadBug();
        new Thread(st::run).start();
        Thread.sleep(5_000);
        System.out.println("Trying to tell the thread to stop");
        st.stop();
    }
}

/*
    Lorsque la boucle du thread ne contient pas l'affichage, le thread ne parvient pas à s'arreter. En effet, le nombre
    de tours de boucle est si élevé que le JIT finit par optimiser l'accès au champs stop en le recopiant dans une
    variable locale. De ce fait, après les 5 secondes d'attente, le champs passe bel et bien à true, mais le thread ne
    lit plus cette valeur à cause de l'optimisation.

    Si on remet l'affichage dans la boucle du thread, celui-ci prend tellement de temps à s'executer que le JIT
    n'optimise pas la boucle et finit par lire la vraie valeur de stop après les 5 secondes.
*/