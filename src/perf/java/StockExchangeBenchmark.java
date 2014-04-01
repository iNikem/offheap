import java.util.concurrent.ThreadLocalRandom;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;

public class StockExchangeBenchmark {
  @GenerateMicroBenchmark
  public double noop() {
    NoopStockExchange exchange = new NoopStockExchange();
    ThreadLocalRandom rnd = ThreadLocalRandom.current();
    for (int i = 0; i < StockExchange.TRADES_PER_DAY; i++) {
      exchange.order("Ticket" + i, rnd.nextInt(1000), 100 * rnd.nextDouble(), rnd.nextBoolean());
    }
    return exchange.dayBalance();
  }
}
