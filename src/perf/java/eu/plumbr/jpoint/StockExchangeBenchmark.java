package eu.plumbr.jpoint;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

public class StockExchangeBenchmark {
  @GenerateMicroBenchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public double noop() {
    NoopStockExchange exchange = new NoopStockExchange();
    for (int i = 0; i < StockExchange.TRADES_PER_DAY; i++) {
      exchange.order(i, i, i, (i & 1) == 0);
    }
    return exchange.dayBalance();
  }

  @GenerateMicroBenchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public double linkedList() {
    StockExchange exchange = new LinkedListStockExchange();
    for (int i = 0; i < StockExchange.TRADES_PER_DAY; i++) {
      exchange.order(i, i, i, (i & 1) == 0);
    }
    return exchange.dayBalance();
  }

  @GenerateMicroBenchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public double arrayList() {
    StockExchange exchange = new ArrayListStockExchange();
    for (int i = 0; i < StockExchange.TRADES_PER_DAY; i++) {
      exchange.order(i, i, i, (i & 1) == 0);
    }
    return exchange.dayBalance();
  }

  @GenerateMicroBenchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public double unsafe() {
    UnsafeStockExchange exchange = new UnsafeStockExchange();
    for (int i = 0; i < StockExchange.TRADES_PER_DAY; i++) {
      exchange.order(i, i, i, (i & 1) == 0);
    }
    double result = exchange.dayBalance();
    exchange.destroy();
    return result;
  }

  @GenerateMicroBenchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public double byteBuffer() {
    ByteBufferStockExchange exchange = new ByteBufferStockExchange(false);
    for (int i = 0; i < StockExchange.TRADES_PER_DAY; i++) {
      exchange.order(i, i, i, (i & 1) == 0);
    }
    double result = exchange.dayBalance();
    exchange.destroy();
    return result;
  }

  @GenerateMicroBenchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public double directByteBuffer() {
    ByteBufferStockExchange exchange = new ByteBufferStockExchange(true);
    for (int i = 0; i < StockExchange.TRADES_PER_DAY; i++) {
      exchange.order(i, i, i, (i & 1) == 0);
    }
    double result = exchange.dayBalance();
    exchange.destroy();
    return result;
  }

  @GenerateMicroBenchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public double mappedByteBuffer() {
    MappedFileStockExchange exchange = new MappedFileStockExchange();
    for (int i = 0; i < StockExchange.TRADES_PER_DAY; i++) {
      exchange.order(i, i, i, (i & 1) == 0);
    }
    double result = exchange.dayBalance();
    exchange.destroy();
    return result;
  }

}
