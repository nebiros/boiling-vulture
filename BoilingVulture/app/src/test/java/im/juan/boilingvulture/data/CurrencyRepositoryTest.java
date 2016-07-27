package im.juan.boilingvulture.data;

import android.support.v4.util.ArrayMap;
import im.juan.boilingvulture.data.services.FixerService;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.when;

public class CurrencyRepositoryTest {

  @Mock FixerService fixerService;

  private CurrencyRepository repository;

  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    repository = new CurrencyRepository(fixerService);
  }

  @Test public void testLatestRatesBaseUsd() throws Exception {
    final Map<String, Double> rates = new ArrayMap<>();
    rates.put("BRL", 3.2537);
    rates.put("GBP", 0.75429);
    rates.put("JPY", 105.69);
    rates.put("EUR", 0.90473);

    final LatestRates latestRates = new LatestRates();
    latestRates.base = "USD";
    latestRates.date = "2016-07-18";
    latestRates.rates = rates;

    final Response<LatestRates> response = Response.success(latestRates);
    final Result<LatestRates> result = Result.response(response);
    final Observable<Result<LatestRates>> resultObservable = Observable.just(result);

    when(fixerService.latestRatesBaseUsd()).thenReturn(resultObservable);

    final Observable<Result<LatestRates>> latestObservable = repository.latestRatesBaseUsd();
    final TestSubscriber<Result<LatestRates>> testSubscriber = new TestSubscriber<>();
    latestObservable.toBlocking().subscribe(testSubscriber);

    testSubscriber.assertNoErrors();
    testSubscriber.assertValueCount(1);
  }
}
