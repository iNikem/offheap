package eu.plumbr.jpoint;

import java.util.ArrayList;
import java.util.List;

public class ArrayListStockExchange extends LinkedListStockExchange {
  @Override
  protected List<Trade> getTrades() {
    return new ArrayList<>();
  }
}
