import java.util.Random;

public class StockExchangeTest {

  public static int RUNS = 10;

  public static void main(String[] args) {
    long start = System.nanoTime();
    for(int i = 0; i < RUNS; i++) {
      run(new NoopStockExchange());
    }
    System.out.println((System.nanoTime() - start) / 1e9);
  }

  private static void run(StockExchange exchange) {
    Random rnd = new Random();
    for (int i = 0; i < StockExchange.TRADES_PER_DAY; i++) {
      exchange.order("Ticket" + i, rnd.nextInt(1000), 100 * rnd.nextDouble(), rnd.nextBoolean());
    }
    System.out.println(exchange.dayBalance());
  }


}
