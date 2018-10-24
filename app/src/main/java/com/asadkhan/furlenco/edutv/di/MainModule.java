package com.asadkhan.furlenco.edutv.di;


import com.asadkhan.furlenco.edutv.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;


public class MainModule {
  
  public static final String BASE_URL = "http://clips.vorwaerts-gmbh.de/";
  
  private HttpLoggingInterceptor interceptor;
  
  private OkHttpClient client;
  
  private Retrofit retrofit;
  
  
  public Retrofit getRetrofit() {
    Interceptor interceptor = provideInterceptor();
    OkHttpClient client = provideOkHttpClient(interceptor);
    return provideRetrofit(client);
  }
  
  
  public Interceptor provideInterceptor() {
    if (interceptor == null) {
      interceptor = new HttpLoggingInterceptor();
      if (BuildConfig.DEBUG) {
        interceptor.setLevel(BODY);
      }
      else {
        interceptor.setLevel(HEADERS);
      }
    }
    return interceptor;
  }
  
  
  public OkHttpClient provideOkHttpClient(Interceptor interceptor) {
    if (client == null) {
      client = new OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(240, SECONDS)
        .readTimeout(240, SECONDS)
        .writeTimeout(240, SECONDS)
        .build();
    }
    return client;
  }
  
  
  public Retrofit provideRetrofit(OkHttpClient client) {
    if (retrofit == null) {
      retrofit = new Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build();
    }
    return retrofit;
  }
}
