package im.juan.boilingvulture.data;

import java.util.Map;

public class LatestBaseUsd {

  public String base;
  public String date;
  public Map<String, Double> rates;

  @Override public String toString() {
    return "LatestBaseUsd{" +
        "base='" + base + '\'' +
        ", date='" + date + '\'' +
        ", rates=" + rates +
        '}';
  }
}
