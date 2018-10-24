package com.asadkhan.furlenco.edutv.retrofit;


import android.content.Context;
import android.content.SharedPreferences;
import com.asadkhan.furlenco.edutv.model.VideoDataWrapper;
import com.asadkhan.furlenco.edutv.utils.FileUtils;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.io.InputStream;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

import static com.asadkhan.furlenco.edutv.SharedPreferenceUtil.getPreferences;
import static com.asadkhan.furlenco.edutv.di.MainModule.BASE_URL;
import static io.reactivex.schedulers.Schedulers.io;


public class RemoteNetworkDataService implements RemoteNetworkService {
  
  private VideoRestService restService;
  
  private Context context;
  
  private SharedPreferences preferences;
  
  
  public RemoteNetworkDataService(Context context, Retrofit retrofit) {
    restService = retrofit.create(VideoRestService.class);
    this.context = context;
    this.preferences = getPreferences(context);
  }
  
  //@Override
  //public Observable<ResponseBody> streamFileFromNetwork(String videoUrl) {
  //  return restService
  //    .streamFileWithDynamicUrlAsync(videoUrl)
  //    .subscribeOn(io());
  //}
  
  
  @Override
  public Observable<InputStream> streamBytesOfContent(String videoUrl) {
    String remoteUrl = getFullPath(videoUrl);
    return restService
      .streamFileWithDynamicUrlAsync(remoteUrl)
      .filter(s -> !preferences.contains(videoUrl))
      .subscribeOn(io())
      .doOnNext(responseBody -> cacheFile(videoUrl, responseBody.byteStream()))
      .map(ResponseBody::byteStream);
  }
  
  
  private void cacheFile(String videoUrl, InputStream inputStream) {
    Disposable subscribe = Observable
      .just(videoUrl)
      .map(s -> {
        String cacheFilePath = FileUtils.getFilePathFromStream(context, inputStream, videoUrl);
        VideoDataWrapper wrapper = new VideoDataWrapper(cacheFilePath, inputStream);
        preferences.edit().putString(videoUrl, cacheFilePath).apply();
        return wrapper;
      })
      .subscribeOn(io())
      .subscribe(System.err::println, Throwable::printStackTrace);
  }
  
  
  @Override
  public Observable<VideoDataWrapper> streamWrappedContent(String videoUrl) {
    return Observable.just(videoUrl)
      .filter(s -> !preferences.contains(s))
      .switchMap(s -> streamBytesOfContent(videoUrl))
      .map(inputStream -> {
        String fullPath = getFullPath(videoUrl);
        return new VideoDataWrapper(fullPath, inputStream);
      });
  }
  
  
  private String getFullPath(String relativePath) {
    String path = BASE_URL;
    if (!relativePath.contains(BASE_URL)) {
      path += relativePath;
    }
    return path;
  }
}
