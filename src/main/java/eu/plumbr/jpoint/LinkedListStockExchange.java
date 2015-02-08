package eu.plumbr.jpoint;

import java.util.LinkedList;
import java.util.List;

public class LinkedListStockExchange implements StockExchange {

  private List<Trade> trades = getTrades();

  protected List<Trade> getTrades() {
    return new LinkedList<>();
  }

  @Override
  public void order(int ticket, int amount, int price, boolean buy) {
    trades.add(new Trade(ticket, amount, price, buy));
  }

  @Override
  public double dayBalance() {
    double balance = 0;
    for (Trade trade : trades) {
      balance += trade.amount * trade.price * (trade.buy ? 1 : -1);
    }
    return balance;
  }
}
