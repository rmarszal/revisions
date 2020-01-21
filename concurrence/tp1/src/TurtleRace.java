public class TurtleRace {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("On your mark!");
        Thread.sleep(30_000);
        System.out.println("Go!");
        int[] times = {25_000, 10_000, 20_000, 5_000, 50_000, 60_000};

        Thread[] list = new Thread [times.length];

        for (int i = 0; i < times.length; ++i) {
            int id = i;
            list[i] = new Thread(() -> {
                try {
                    Thread.sleep(times[id]);
                    System.out.println(Thread.currentThread().getName() + " has finished");
                } catch (InterruptedException e) {
                    throw new AssertionError(e);
                }
            });
            list[i].setName("Thread " + i);
            list[i].start();
        }
    }
}

/*

Après les 30 premières secondes, les threads des tortues démarrent et le thread main meurt.
La JVM s'éteint lorsque tous les threads sont morts

*/
