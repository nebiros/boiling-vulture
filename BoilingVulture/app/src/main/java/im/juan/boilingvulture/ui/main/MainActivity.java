package im.juan.boilingvulture.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import im.juan.boilingvulture.BoilingVultureApplication;
import im.juan.boilingvulture.R;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  @Inject MainPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    MainFragment fragment =
        (MainFragment) getSupportFragmentManager().findFragmentById(R.id.content_main);

    if (fragment == null) {
      fragment = MainFragment.newInstance();
      final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
      transaction.add(R.id.content_main, fragment);
      transaction.commit();
    }

    DaggerMainComponent.builder()
        .currencyRepositoryComponent(
            ((BoilingVultureApplication) getApplication()).currencyRepositoryComponent())
        .mainPresenterModule(new MainPresenterModule(fragment))
        .build()
        .inject(this);
  }
}
