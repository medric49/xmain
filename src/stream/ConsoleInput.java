package stream;

import java.util.concurrent.*;

public class ConsoleInput {
  private final int tries;
  private final int timeout;
  private final TimeUnit unit;

  private ConsoleInput(int tries, TimeUnit unit) {
    this.tries = tries;
    this.timeout = Integer.MAX_VALUE;
    this.unit = unit;
  }

    private static volatile ConsoleInput instance = null;
     /**
      * Méthode permettant de renvoyer une instance de la classe Singleton
      * @return Retourne l'instance du singleton.
      */
     public final static ConsoleInput getInstance(int tries, TimeUnit unit) {
         //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
         //d'éviter un appel coûteux à synchronized, 
         //une fois que l'instanciation est faite.
         if (ConsoleInput.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(ConsoleInput.class) {
              if (ConsoleInput.instance == null) {
                ConsoleInput.instance = new ConsoleInput(tries, unit);
              }
            }
         }
         return ConsoleInput.instance;
     }
     
  public String readLine() throws InterruptedException {
    ExecutorService ex = Executors.newSingleThreadExecutor();
    String input = null;
    try {
      // start working
      for (int i = 0; i < tries; i++) {
        System.out.println(String.valueOf(i + 1) + ". loop");
        Future<String> result = ex.submit(
            new ConsoleInputReadTask());
        try {
          input = result.get(timeout, unit);
          break;
        } catch (ExecutionException e) {
          e.getCause().printStackTrace();
        } catch (TimeoutException e) {
          System.out.println("Cancelling reading task");
          result.cancel(true);
          System.out.println("\nThread cancelled. input is null");
        }
      }
    } finally {
      ex.shutdownNow();
    }
    return input;
  }
}