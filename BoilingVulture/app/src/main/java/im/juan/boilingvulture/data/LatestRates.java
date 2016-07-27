package im.juan.boilingvulture.data;

import java.util.Map;

public class LatestRates {

  public String base;
  public String date;
  public Map<String, Double> rates;

  @Override public String toString() {
    return "LatestRates{" +
        "base='" + base + '\'' +
        ", date='" + date + '\'' +
        ", rates=" + rates +
        '}';
  }
}
