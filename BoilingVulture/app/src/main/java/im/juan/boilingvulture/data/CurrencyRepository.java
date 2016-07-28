package im.juan.boilingvulture.data;

import android.support.v4.util.ArrayMap;
import im.juan.boilingvulture.data.services.FixerService;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;

@Singleton public class CurrencyRepository {

  private final FixerService fixerService;

  @Inject public CurrencyRepository(FixerService fixerService) {
    this.fixerService = fixerService;
  }

  public Observable<Result<LatestRates>> latestRatesBaseUsd() {
    return fixerService.latestRatesBaseUsd();
  }

  public Map<String, Integer> calculateRates(int amount, Map<String, Double> rates) {
    final Map<String, Integer> calculated = new ArrayMap<>();

    for (Map.Entry<String, Double> entry : rates.entrySet()) {
      final Double value = entry.getValue();
      final double rate = amount * value;

      calculated.put(entry.getKey(), ((Double) Math.abs(rate)).intValue());
    }

    return calculated;
  }
}
