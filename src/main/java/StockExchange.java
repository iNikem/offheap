public interface StockExchange {

  int TRADES_PER_DAY = 1_000_000;

  void order(String ticket, int amount, double price, boolean buy);

  double dayBalance();
}
