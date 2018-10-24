package com.asadkhan.furlenco.edutv.viewmodels;


import android.content.Context;
import android.support.annotation.NonNull;
import com.asadkhan.furlenco.edutv.cache.LocalCacheDataService;
import com.asadkhan.furlenco.edutv.cache.LocalCacheService;
import com.asadkhan.furlenco.edutv.data.DataRepository;
import com.asadkhan.furlenco.edutv.di.MainModule;
import com.asadkhan.furlenco.edutv.model.VideoDataWrapper;
import com.asadkhan.furlenco.edutv.retrofit.RemoteNetworkDataService;
import io.reactivex.Observable;
import retrofit2.Retrofit;


public class VideStreamingViewModel {
  
  Retrofit retrofit;
  
  RemoteNetworkDataService networkDataService;
  
  LocalCacheDataService localCacheService;
  
  MainModule mainModule = new MainModule();
  
  private DataRepository repository;
  
  private Context context;
  
  
  public VideStreamingViewModel(Context context) {
    this.context = context;
    this.retrofit = mainModule.getRetrofit();
    
    networkDataService = getNetworkDataService();
    localCacheService = getLocalCacheService();
    this.repository = getRepository();
  }
  
  
  @NonNull
  private DataRepository getRepository() {
    if (repository == null) {
      repository = new DataRepository(getLocalCacheService(), getNetworkDataService());
    }
    return repository;
  }
  
  
  @NonNull
  private RemoteNetworkDataService getNetworkDataService() {
    if (networkDataService == null) {
      networkDataService = new RemoteNetworkDataService(context, retrofit);
    }
    return networkDataService;
  }
  
  
  @NonNull
  private LocalCacheDataService getLocalCacheService() {
    if (localCacheService == null) {
      localCacheService = new LocalCacheDataService(this.context);
    }
    return localCacheService;
  }
  
  
  public Observable<VideoDataWrapper> getVideoStream(String uri) {
    Observable<VideoDataWrapper> observable = repository.getVideoContentWrapped(uri);
    return observable
      .doOnNext(System.err::println);
  }
}
