package fr.upem.concurrence.td02;

class ExampleReordering {
    int a = 0;
    int b = 0;

    public static void main(String[] args) {
        ExampleReordering e = new ExampleReordering();
        new Thread(() -> System.out.println("a = " + e.a + "  b = " + e.b)).start();
        e.a = 1;
        e.b = 2;
    }
}

/*
    Quatre resultats possibles :

    - a = 1 b = 2 : dans ce cas, tout le main s'est execute, puis le thread a produit l'affichage.
    - a = 0 b = 0 : ici c'est l'inverse, le thread produit l'affichage tout de suite, et le main finit son execution.
    - a = 1 b = 0 : ici, le main s'execute jusqu'a l'affectation de a (a = 1) et il est ensuite deschedule. Le thread
                    prend alors le relai et produit l'affichage. Une fois le thread mort, le main reprend son execution.
    - a = 0 b = 2 : dans ce cas, le JIT a réordonné les affectation, faisant passer celle de b avant celle de a.
                    Alors, comme dans le cas precedent, le main est deschedule apres la premiere affectation
                    (celle de b), et le thread produit alors l'affichage. Une fois mort le thread repasse la main au
                    main.
*/