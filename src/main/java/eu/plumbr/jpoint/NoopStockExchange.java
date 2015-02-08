package eu.plumbr.jpoint;

public class NoopStockExchange implements StockExchange {
  private boolean balance = false;
  @Override
  public void order(int ticket, int amount, int price, boolean buy) {
    balance ^= buy;
  }

  @Override
  public double dayBalance() {
    return balance ? +1 : -1;
  }
}
