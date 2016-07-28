package im.juan.boilingvulture.ui.main;

import im.juan.boilingvulture.data.CurrencyRepository;
import im.juan.boilingvulture.data.LatestRates;
import java.util.Map;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public final class MainPresenter implements MainContract.Presenter {

  private final CurrencyRepository currencyRepository;
  private final MainContract.View view;

  private final CompositeSubscription subscriptions;

  @Inject public MainPresenter(CurrencyRepository currencyRepository, MainContract.View view) {
    this.currencyRepository = currencyRepository;
    this.view = view;
    this.subscriptions = new CompositeSubscription();
  }

  @Inject void setupListeners() {
    view.setPresenter(this);
  }

  @Override public void loadLatestRatesBaseUsd() {
    final Subscription subscription = currencyRepository.latestRatesBaseUsd()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(latestBaseUsdResult -> {
          if (latestBaseUsdResult.isError()) {
            view.showError(latestBaseUsdResult.error());
            return;
          }

          final LatestRates latestRates = latestBaseUsdResult.response().body();
          view.latestRates(latestRates);
        }, view::showError);

    subscriptions.add(subscription);
  }

  @Override public void calculateRates(String amount, Map<String, Double> rates) {
    amount = amount.trim();
    if (amount.isEmpty()) {
      view.showError(new Throwable());
      return;
    }

    final int value = Integer.parseInt(amount);
    final Map<String, Integer> calculatedRates = currencyRepository.calculateRates(value, rates);
  }

  @Override public void subscribe() {
    loadLatestRatesBaseUsd();
  }

  @Override public void unsubscribe() {
    subscriptions.clear();
  }
}
