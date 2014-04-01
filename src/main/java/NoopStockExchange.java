public class NoopStockExchange implements StockExchange {
  private boolean balance = false;
  @Override
  public void order(String ticket, int amount, double price, boolean buy) {
    balance ^= buy;
  }

  @Override
  public double dayBalance() {
    return balance ? +1 : -1;
  }
}
