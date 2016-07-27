package im.juan.boilingvulture.ui.main;

import im.juan.boilingvulture.ui.BasePresenter;
import im.juan.boilingvulture.ui.BaseView;

public interface MainContract {

  interface View extends BaseView<Presenter> {

  }

  interface Presenter extends BasePresenter {
  }
}
