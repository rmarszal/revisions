public class HelloThread {

    public static void main(String[] args) {

        for (int i = 0; i < 4; ++i) {
            int id = i;
            new Thread( () -> {
                for (int j = 0; j <= 5000; ++j) {
                    System.out.println("Hello " + id + " " + j);
                }
            }).start();
        }
    }
}

/*

 Question 1 : Un Runnable sert à lancer le thread grace à sa méthode run(). Cette fonction ne doit pas
              prendre de paramètre et ne doit rien renvoyer. Elle doit juste éxecuter du code.

 Question 3 : Lorsque l'on éxecute plusieurs fois le code, on remarque que l'ordre de l'affichage n'est jamais le meme.
 		      De plus, on voit que parfois, un thread produit de l'affichage, puis s'arrete, et recommence plus tard.
 		      Ceci est bien normal.

*/
