package im.juan.boilingvulture;

import android.app.Application;
import im.juan.boilingvulture.data.CurrencyRepositoryComponent;
import im.juan.boilingvulture.data.CurrencyRepositoryModule;
import im.juan.boilingvulture.data.DaggerCurrencyRepositoryComponent;

public class BoilingVultureApplication extends Application {

  private CurrencyRepositoryComponent currencyRepositoryComponent;

  @Override public void onCreate() {
    super.onCreate();

    currencyRepositoryComponent = DaggerCurrencyRepositoryComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .networkingModule(new NetworkingModule(BuildConfig.API_BASE_URL))
        .currencyRepositoryModule(new CurrencyRepositoryModule())
        .build();
  }

  public CurrencyRepositoryComponent currencyRepositoryComponent() {
    return currencyRepositoryComponent;
  }
}
