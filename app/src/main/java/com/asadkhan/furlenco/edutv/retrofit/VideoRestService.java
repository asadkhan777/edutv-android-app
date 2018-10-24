package com.asadkhan.furlenco.edutv.retrofit;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


public interface VideoRestService {
  
  @GET
  Observable<ResponseBody> streamFileWithDynamicUrlAsync(@Url String fileUrl);
  
}
