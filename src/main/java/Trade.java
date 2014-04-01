public class Trade {
  public String ticket;
  public int amount;
  public double price;
  public boolean buy;

  public Trade(String ticket, int amount, double price, boolean buy) {
    this.ticket = ticket;
    this.amount = amount;
    this.price = price;
    this.buy = buy;
  }
}
