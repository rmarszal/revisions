package fr.upem.concurrence;


public class HelloThreadBis{

    static void println(String s){
        for(int i = 0; i < s.length(); i++){
            System.out.print(s.charAt(i));
        }
        System.out.println("\n");
    }


    public static void main(String[] args) {
        int nbThreads = 4;
        Runnable runnable = () -> {
            for (int i = 0; i < 5000; i++) {
                println("hello " + i);
            }
        };

        for (int i = 0; i < nbThreads; i++){
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
