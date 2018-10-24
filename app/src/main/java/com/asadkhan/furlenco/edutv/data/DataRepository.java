package com.asadkhan.furlenco.edutv.data;


import com.asadkhan.furlenco.edutv.cache.LocalCacheService;
import com.asadkhan.furlenco.edutv.model.VideoDataWrapper;
import com.asadkhan.furlenco.edutv.retrofit.RemoteNetworkService;
import io.reactivex.Observable;
import java.io.InputStream;

import static io.reactivex.Observable.concat;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;


public class DataRepository {
  
  private LocalCacheService cacheService;
  
  private RemoteNetworkService networkService;
  
  
  public DataRepository() {
  }
  
  
  public DataRepository(LocalCacheService cacheService, RemoteNetworkService networkService) {
    this.cacheService = cacheService;
    this.networkService = networkService;
  }
  
  
  //public Observable<InputStream> getVideoContentStream(String videoUrl) {
  //  Observable<InputStream> local = cacheService.streamBytesOfContent(videoUrl);
  //  Observable<InputStream> remote = networkService.streamBytesOfContent(videoUrl);
  //
  //  return concat(
  //    local,
  //    remote
  //  )
  //    .firstElement()
  //    .toObservable()
  //    .observeOn(mainThread());
  //}
  
  
  public Observable<VideoDataWrapper> getVideoContentWrapped(String videoUrl) {
    Observable<VideoDataWrapper> local = cacheService.streamWrappedContent(videoUrl);
    Observable<VideoDataWrapper> remote = networkService.streamWrappedContent(videoUrl);
  
    Observable<VideoDataWrapper> result = concat(
      local,
      remote
    )
       .firstElement()
       .toObservable()
      .observeOn(mainThread());
    return result;
  }
}
