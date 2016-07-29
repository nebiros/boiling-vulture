package im.juan.boilingvulture.ui.main;

import android.support.v4.util.ArrayMap;
import com.github.mikephil.charting.data.BarData;
import im.juan.boilingvulture.BuildConfig;
import im.juan.boilingvulture.data.CurrencyRepository;
import im.juan.boilingvulture.data.LatestRates;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.android.plugins.RxAndroidPlugins;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class) @Config(constants = BuildConfig.class)
public class MainPresenterTest {

  @Mock CurrencyRepository repository;
  @Mock MainContract.View view;

  private MainPresenter presenter;

  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    RxJavaHooks.setOnIOScheduler(scheduler -> Schedulers.immediate());
    RxJavaHooks.setOnComputationScheduler(scheduler -> Schedulers.immediate());
    RxJavaHooks.setOnNewThreadScheduler(scheduler -> Schedulers.immediate());

    presenter = new MainPresenter(repository, view);
  }

  @After public void tearDown() {
    RxAndroidPlugins.getInstance().reset();
  }

  @Test public void testLoadLatestRatesBaseUsd() throws Exception {
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

    when(repository.latestRatesBaseUsd()).thenReturn(resultObservable);

    presenter.loadLatestRatesBaseUsd();

    verify(view, times(1)).latestRates(eq(latestRates));
  }

  @Test public void testCalculateRates() throws Exception {
    final String amountFromEditView = "100";
    final int amountParsed = Integer.parseInt(amountFromEditView);
    final Map<String, Double> rates = new ArrayMap<>();
    rates.put("BRL", 3.2537);
    rates.put("GBP", 0.75429);
    rates.put("JPY", 105.69);
    rates.put("EUR", 0.90473);
    final Map<String, Integer> calculatedRates = new ArrayMap<>();
    calculatedRates.put("BRL", 325);
    calculatedRates.put("GBP", 75);
    calculatedRates.put("JPY", 10569);
    calculatedRates.put("EUR", 90);

    when(repository.calculateRates(amountParsed, rates)).thenReturn(calculatedRates);

    presenter.calculateRates(amountFromEditView, rates);

    verify(repository, times(1)).calculateRates(eq(amountParsed), eq(rates));
    verify(repository, times(1)).prepareDataForChart(eq(calculatedRates));
    verify(view, times(1)).updateBarChart(any(BarData.class));
  }
}
