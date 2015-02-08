package eu.plumbr.jpoint;

public class Trade {
  public int ticket;
  public int amount;
  public int price;
  public boolean buy;

  public Trade(int ticket, int amount, int price, boolean buy) {
    this.ticket = ticket;
    this.amount = amount;
    this.price = price;
    this.buy = buy;
  }
}
