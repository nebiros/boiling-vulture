package im.juan.boilingvulture.ui.main;

import im.juan.boilingvulture.data.LatestRates;
import im.juan.boilingvulture.ui.BasePresenter;
import im.juan.boilingvulture.ui.BaseView;
import java.util.Map;

public interface MainContract {

  interface View extends BaseView<Presenter> {

    void showError(Throwable error);

    void latestRates(LatestRates latestRates);
  }

  interface Presenter extends BasePresenter {

    void loadLatestRatesBaseUsd();

    void calculateRates(String amount, Map<String, Double> rates);
  }
}
