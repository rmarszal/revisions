package fr.upem.concurrence.td04;

public class Vote {
    public static void main(String[] args) throws InterruptedException {
        Vote vote = new Vote(3);
        new Thread(
                () -> {
                    try {
                        Thread.sleep(10_000);
                        System.out.println("The winner is " + vote.vote("1"));
                    } catch (InterruptedException e) {
                        throw new AssertionError(e);
                    }
                })
                .start();
        new Thread(
                () -> {
                    try {
                        Thread.sleep(5_000);
                        System.out.println("The winner is " + vote.vote("0"));
                    } catch (InterruptedException e) {
                        throw new AssertionError(e);
                    }
                })
                .start();
        System.out.println("The winner is " + vote.vote("0"));
    }
}
