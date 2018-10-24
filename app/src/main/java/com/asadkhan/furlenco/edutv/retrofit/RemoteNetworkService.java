package com.asadkhan.furlenco.edutv.retrofit;


import com.asadkhan.furlenco.edutv.data.DataSource;
import io.reactivex.Observable;
import okhttp3.ResponseBody;


public interface RemoteNetworkService extends DataSource {
  
  // Observable<ResponseBody> streamFileFromNetwork(String videoUrl);
}
