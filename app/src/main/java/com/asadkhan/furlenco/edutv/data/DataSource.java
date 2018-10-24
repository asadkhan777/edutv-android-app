package com.asadkhan.furlenco.edutv.data;


import com.asadkhan.furlenco.edutv.model.VideoDataWrapper;
import io.reactivex.Observable;
import java.io.InputStream;


public interface DataSource {
  
  Observable<InputStream> streamBytesOfContent(String videoUrl);
  
  
  Observable<VideoDataWrapper> streamWrappedContent(String videoUrl);
}
