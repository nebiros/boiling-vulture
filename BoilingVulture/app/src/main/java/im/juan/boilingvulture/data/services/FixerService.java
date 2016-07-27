package im.juan.boilingvulture.data.services;

import im.juan.boilingvulture.data.LatestRates;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import rx.Observable;

public interface FixerService {

  @GET("/latest?base=USD&symbols=GBP,EUR,JPY,BRL") Observable<Result<LatestRates>> latestRatesBaseUsd();
}
