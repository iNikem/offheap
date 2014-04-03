package eu.plumbr.jpoint;

public interface StockExchange {

  int TRADES_PER_DAY = 50_000_000;

  void order(int ticket, int amount, int price, boolean buy);

  double dayBalance();
}
