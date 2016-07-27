package im.juan.boilingvulture.data;

import dagger.Module;
import dagger.Provides;
import im.juan.boilingvulture.data.services.FixerService;
import javax.inject.Singleton;
import retrofit2.Retrofit;

@Module
public class CurrencyRepositoryModule {

  @Provides @Singleton FixerService provideFixerService(Retrofit retrofit) {
    return retrofit.create(FixerService.class);
  }
}
