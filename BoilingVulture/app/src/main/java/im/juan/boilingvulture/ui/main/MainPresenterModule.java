package im.juan.boilingvulture.ui.main;

import dagger.Module;
import dagger.Provides;

@Module public class MainPresenterModule {

  private final MainContract.View view;

  public MainPresenterModule(MainContract.View view) {
    this.view = view;
  }

  @Provides MainContract.View provideMainContractView() {
    return view;
  }
}
