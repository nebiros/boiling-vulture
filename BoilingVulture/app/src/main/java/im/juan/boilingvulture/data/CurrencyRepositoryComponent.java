package im.juan.boilingvulture.data;

import dagger.Component;
import im.juan.boilingvulture.ApplicationModule;
import im.juan.boilingvulture.NetworkingModule;
import javax.inject.Singleton;

@Singleton @Component(modules = {
    ApplicationModule.class, NetworkingModule.class, CurrencyRepositoryModule.class
}) public interface CurrencyRepositoryComponent {

  CurrencyRepository currencyRepository();
}
