package im.juan.boilingvulture.data;

import im.juan.boilingvulture.data.services.FixerService;
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
}
