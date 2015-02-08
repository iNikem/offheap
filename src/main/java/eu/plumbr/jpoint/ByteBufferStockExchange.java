package eu.plumbr.jpoint;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteBufferStockExchange implements StockExchange {

  @Override
  public void order(int ticket, int amount, int price, boolean buy) {
    BufferTrade trade = get(recordsCount++);
    trade.setTicket(ticket);
    trade.setAmount(amount);
    trade.setPrice(price);
    trade.setBuy(buy);
  }

  @Override
  public double dayBalance() {
    double balance = 0;
    for (int i = 0; i < recordsCount; i++) {
      BufferTrade trade = get(i);
      balance += trade.getAmount() * trade.getPrice() * (trade.isBuy() ? 1 : -1);
    }
    return balance;
  }

  private ByteBuffer buffer;
  private final BufferTrade flyweight = new BufferTrade();

  private BufferTrade get(final int index) {
    final int offset = index * flyweight.getObjectSize();
    flyweight.setObjectOffset(offset);
    return flyweight;
  }

  public void destroy() {
    buffer.clear();
  }

  private int recordsCount = 0;

  public ByteBufferStockExchange(boolean direct) {
    buffer = direct ?
        ByteBuffer.allocateDirect(TRADES_PER_DAY * flyweight.getObjectSize()) :
        ByteBuffer.allocate(TRADES_PER_DAY * flyweight.getObjectSize());
    buffer.order(ByteOrder.nativeOrder());
  }

  private class BufferTrade {
    private int offset = 0;

    private final int ticketOffset = offset += 0;
    private final int amountOffset = offset += 4;
    private final int priceOffset = offset += 4;
    private final int buyOffset = offset += 4;

    private final int objectSize = offset += 1;

    private int objectOffset;

    public int getObjectSize() {
      return objectSize;
    }

    void setObjectOffset(final int objectOffset) {
      this.objectOffset = objectOffset;
    }

    public void setTicket(final int ticket) {
      buffer.putInt(objectOffset + ticketOffset, ticket);
    }

    public int getPrice() {
      return buffer.getInt(objectOffset + priceOffset);
    }

    public void setPrice(final int price) {
      buffer.putInt(objectOffset + priceOffset, price);
    }

    public int getAmount() {
      return buffer.getInt(objectOffset + amountOffset);
    }

    public void setAmount(final int quantity) {
      buffer.putInt(objectOffset + amountOffset, quantity);
    }

    public boolean isBuy() {
      return buffer.get(objectOffset + buyOffset) == 1;
    }

    public void setBuy(final boolean buy) {
      buffer.put(objectOffset + buyOffset, (byte) (buy ? 1 : 0));
    }
  }
}
