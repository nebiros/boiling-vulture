package im.juan.boilingvulture.ui.main;

import dagger.Component;
import im.juan.boilingvulture.data.CurrencyRepositoryComponent;
import im.juan.boilingvulture.util.FragmentScoped;

@FragmentScoped
@Component(dependencies = CurrencyRepositoryComponent.class, modules = MainPresenterModule.class)
public interface MainComponent {

  void inject(MainActivity activity);
}
