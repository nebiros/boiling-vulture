package im.juan.boilingvulture.data;

import android.support.v4.util.ArrayMap;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import im.juan.boilingvulture.data.services.FixerService;
import java.util.ArrayList;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;

@Singleton public class CurrencyRepository {

  private final FixerService fixerService;

  @Inject public CurrencyRepository(FixerService fixerService) {
    this.fixerService = fixerService;
  }

  public Observable<Result<LatestRates>> latestRatesBaseUsd() {
    return fixerService.latestRatesBaseUsd();
  }

  public Map<String, Integer> calculateRates(int amount, Map<String, Double> rates) {
    final Map<String, Integer> calculated = new ArrayMap<>();

    for (Map.Entry<String, Double> entry : rates.entrySet()) {
      final Double value = entry.getValue();
      final double rate = amount * value;

      calculated.put(entry.getKey(), ((Double) Math.abs(rate)).intValue());
    }

    return calculated;
  }

  public BarData prepareDataForChart(Map<String, Integer> calculatedRates) {
    final ArrayList<BarEntry> yVals = new ArrayList<>();
    final Object[] values = calculatedRates.values().toArray();

    for (int x = 0, l = values.length; x < l; x++) {
      final BarEntry entry = new BarEntry(x, (int) values[x]);
      yVals.add(entry);
    }

    final BarDataSet dataSet = new BarDataSet(yVals, "");
    dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

    final ArrayList<IBarDataSet> dataSets = new ArrayList<>();
    dataSets.add(dataSet);

    return new BarData(dataSets);
  }
}
