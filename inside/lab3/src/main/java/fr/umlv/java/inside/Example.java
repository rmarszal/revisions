package fr.umlv.java.inside;

public class Example {
    private static String aStaticHello(int value) {
        return "question " + value;
    }
    private String anInstanceHello(int value) {
        return "question " + value;
    }
}

/* Question 4

Lookup encapsule une classe et permet de verifier son accessibilité depuis une autre classe.

 */
