package im.juan.boilingvulture.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import im.juan.boilingvulture.R;
import im.juan.boilingvulture.data.LatestRates;

public class MainFragment extends Fragment implements MainContract.View {

  private MainContract.Presenter presenter;
  private LatestRates latestRates;
  private EditText convertEditView;
  private Button convertButton;

  public MainFragment() {
  }

  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Override public void onResume() {
    super.onResume();
    presenter.subscribe();
  }

  @Override public void onPause() {
    super.onPause();
    presenter.unsubscribe();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View root = inflater.inflate(R.layout.fragment_main, container, false);
    convertEditView = (EditText) root.findViewById(R.id.convert_edit_text);
    convertButton = (Button) root.findViewById(R.id.convert_button);
    convertButton.setOnClickListener(
        view -> presenter.calculateRates(convertEditView.getText().toString(), latestRates.rates));

    return root;
  }

  @Override public void setPresenter(MainContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override public void showError(Throwable error) {

  }

  @Override public void latestRates(LatestRates latestRates) {
    this.latestRates = latestRates;
  }
}
