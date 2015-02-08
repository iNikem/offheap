import eu.plumbr.jpoint.LinkedListStockExchange;
import eu.plumbr.jpoint.StockExchange;

public class StockExchangeTest {

  public static int RUNS = 100;

  public static void main(String[] args) {
    long start = System.nanoTime();
    for(int i = 0; i < RUNS; i++) {
      run(new LinkedListStockExchange());
    }
    System.out.println((System.nanoTime() - start) / 1e9);
  }

  private static void run(StockExchange exchange) {
    for (int i = 0; i < StockExchange.TRADES_PER_DAY; i++) {
      exchange.order(i, i, i, (i & 1) == 0);
    }
    System.out.println(exchange.dayBalance());
  }


}
