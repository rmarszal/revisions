package fr.upem.concurrence.td02;

class ExampleLongAffectation {
    long l = -1L;

    public static void main(String[] args) {
        ExampleLongAffectation e = new ExampleLongAffectation();
        new Thread(() -> {
            System.out.println("l = " + e.l);
        }).start();
        e.l = 0;
    }
}

/*
    Ici on risque de mettre des 0 que sur l'une des deux moitiés de l'entier (les 32 premiers ou derniers bits),
    sur tous les bits (comme souhaité) ou sur aucun des bits.
*/