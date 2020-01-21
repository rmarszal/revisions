public class HelloThreadJoin{

    public static void main(String[] args) throws InterruptedException {

        Thread[] list = new Thread [4];

        for (int i = 0; i < 4; ++i) {
            int id = i;
            list[i] = new Thread(() -> {
                for (int j = 0; j <= 5000; ++j) {
                    System.out.println("Hello " + id + " " + j);
                }
            });
            list[i].start();
        }
        for (int j = 0; j < 4; ++j) {
            list[j].join();
        }
        System.out.println("le programme est fini");
    }
}
