package im.juan.boilingvulture;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module public final class ApplicationModule {

  private final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides Application provideApplication() {
    return application;
  }
}
