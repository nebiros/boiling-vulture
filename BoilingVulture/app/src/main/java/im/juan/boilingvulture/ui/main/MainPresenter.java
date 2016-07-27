package im.juan.boilingvulture.ui.main;

import im.juan.boilingvulture.data.CurrencyRepository;
import im.juan.boilingvulture.data.LatestBaseUsd;
import javax.inject.Inject;
import retrofit2.adapter.rxjava.Result;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public final class MainPresenter implements MainContract.Presenter {

  private final CurrencyRepository currencyRepository;
  private final MainContract.View view;

  @Inject public MainPresenter(CurrencyRepository currencyRepository, MainContract.View view) {
    this.currencyRepository = currencyRepository;
    this.view = view;
  }

  @Inject void setupListeners() {
    view.setPresenter(this);
  }

  @Override public void start() {
    loadLatest();
  }

  public void loadLatest() {
    currencyRepository.latest()
        .subscribeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Result<LatestBaseUsd>>() {
          @Override public void call(Result<LatestBaseUsd> latestBaseUsdResult) {
            final LatestBaseUsd latestBaseUsd = latestBaseUsdResult.response().body();
            Timber.d("latestBaseUsd, %s", latestBaseUsd);
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            Timber.d(throwable, "throwable");
          }
        });
  }
}
