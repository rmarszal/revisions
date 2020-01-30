package fr.umlv.conc;

public class HonorBoard {
  private final Object lock = new Object();
  private String firstName;
  private String lastName;
  
  public void set(String firstName, String lastName) {
    synchronized (lock) {
      this.firstName = firstName;
      this.lastName = lastName;
    }
  }
  
  @Override
  public String toString() {
    synchronized (lock) {
      return firstName + ' ' + lastName;
    }
  }
  
  public static void main(String[] args) {
    HonorBoard board = new HonorBoard();
    new Thread(() -> {
      for(;;) {
        board.set("John", "Doe");
      }
    }).start();
    
    new Thread(() -> {
      for(;;) {
        board.set("Jane", "Odd");
      }
    }).start();
    
    new Thread(() -> {
      for(;;) {
        System.out.println(board);
      }
    }).start();
  }
}

/*
Le premier thread set firstName et lastName et se fait de-scheduler. Alors, si le deuxieme thread set firstName, se
fait de-scheduler, et que le troisième thread produit l'affichage, on verra apparaitre John Odd.
Pour y faire face, il faut poser un bloc synchronized dans set et toString. De cette facon, meme si un thread est
de-schedule au mileu du set, les autres threads ne pourront n'y modifier les champs, n'y produire de l'affichage tant
que le verrou n'aura pas été laché par le premier thread.

On ne peut pas remplacer l'affichage
 */