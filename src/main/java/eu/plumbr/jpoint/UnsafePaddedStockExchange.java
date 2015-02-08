package eu.plumbr.jpoint;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class UnsafePaddedStockExchange implements StockExchange {

  @Override
  public synchronized void order(int ticket, int amount, int price, boolean buy) {
    UnsafeTrade trade = get(recordsCount++);
    trade.setTicket(ticket);
    trade.setAmount(amount);
    trade.setPrice(price);
    trade.setBuy(buy);
  }

  @Override
  public synchronized double dayBalance() {
    double balance = 0;
    for (int i = 0; i < recordsCount; i++) {
      UnsafeTrade trade = get(i);
      balance += trade.getAmount() * trade.getPrice() * (trade.isBuy() ? 1 : -1);
    }
    return balance;
  }

  private static final Unsafe unsafe;

  static {
    try {
      Field field = Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      unsafe = (Unsafe) field.get(null);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private long address;
  private final UnsafeTrade flyweight = new UnsafeTrade();

  private UnsafeTrade get(final int index) {
    final long offset = address + (index << 4);
    flyweight.setObjectOffset(offset);
    return flyweight;
  }

  public void destroy() {
    unsafe.freeMemory(address);
  }

  private int recordsCount = 0;

  public UnsafePaddedStockExchange() {
    address = unsafe.allocateMemory(TRADES_PER_DAY * UnsafeTrade.getObjectSize());
  }

  private static class UnsafeTrade {
    private static long offset = 0;

    private static final long ticketOffset = offset += 0;
    private static final long amountOffset = offset += 4;
    private static final long priceOffset = offset += 4;
    private static final long buyOffset = offset += 4;

    private long objectOffset;

    public static long getObjectSize() {
      return 16;
    }

    void setObjectOffset(final long objectOffset) {
      this.objectOffset = objectOffset;
    }


    public void setTicket(final int ticket) {
      unsafe.putInt(objectOffset + ticketOffset, ticket);
    }

    public long getPrice() {
      return unsafe.getInt(objectOffset + priceOffset);
    }

    public void setPrice(final int price) {
      unsafe.putInt(objectOffset + priceOffset, price);
    }

    public long getAmount() {
      return unsafe.getInt(objectOffset + amountOffset);
    }

    public void setAmount(final int quantity) {
      unsafe.putInt(objectOffset + amountOffset, quantity);
    }

    public boolean isBuy() {
      return unsafe.getByte(objectOffset + buyOffset) == 1;
    }

    public void setBuy(final boolean buy) {
      unsafe.putByte(objectOffset + buyOffset, (byte) (buy ? 1 : 0));
    }
  }
}
