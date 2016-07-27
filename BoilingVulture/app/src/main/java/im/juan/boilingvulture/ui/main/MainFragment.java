package im.juan.boilingvulture.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import im.juan.boilingvulture.R;

public class MainFragment extends Fragment implements MainContract.View {

  private MainContract.Presenter presenter;

  public MainFragment() {
  }

  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Override public void onResume() {
    super.onResume();

    presenter.start();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override public void setPresenter(MainContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
