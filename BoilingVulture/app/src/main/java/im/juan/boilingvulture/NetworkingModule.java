package im.juan.boilingvulture;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module public final class NetworkingModule {

  final String baseUrl;

  public NetworkingModule(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  @Provides @Singleton HttpLoggingInterceptor provideOkHttpLoggingInterceptor() {
    return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
  }

  @Provides @Singleton Cache provideOkHttpCache(Application application) {
    int cacheSize = 10 * 1024 * 1024; // 10 MiB
    return new Cache(application.getCacheDir(), cacheSize);
  }

  @Provides @Singleton OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
      Cache cache) {
    return new OkHttpClient.Builder().cache(cache).addInterceptor(loggingInterceptor).build();
  }

  @Provides @Singleton Retrofit provideRetrofit(OkHttpClient client) {
    return new Retrofit.Builder().baseUrl(baseUrl)
        .client(client)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
  }
}
