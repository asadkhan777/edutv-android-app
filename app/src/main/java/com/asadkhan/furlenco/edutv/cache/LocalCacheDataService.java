package com.asadkhan.furlenco.edutv.cache;


import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import com.asadkhan.furlenco.edutv.model.VideoDataWrapper;
import io.reactivex.Observable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.net.Uri.parse;
import static android.os.Environment.getExternalStorageDirectory;
import static com.asadkhan.furlenco.edutv.SharedPreferenceUtil.getPreferences;
import static io.reactivex.Observable.error;
import static io.reactivex.Observable.just;
import static io.reactivex.Observable.never;


public class LocalCacheDataService implements LocalCacheService {
  
  public static String TEMP_DIR;
  
  private SharedPreferences preferences;
  
  private Context context;
  
  private File dataDir;
  
  private ContentResolver resolver;
  
  
  public LocalCacheDataService(Context context) {
    this.context = context;
    this.dataDir = context.getFilesDir();
    resolver = context.getContentResolver();
    preferences = getPreferences(context);
    TEMP_DIR = context.getFilesDir().getAbsolutePath();
  }
  
  
  @Override
  public Observable<InputStream> streamBytesOfContent(String videoUri) {
    if (preferences.contains(videoUri)) {
      InputStream inputStream;
      try {
        inputStream = getStreamFromUri(videoUri);
      }
      catch (FileNotFoundException e) {
        e.printStackTrace();
        if (!videoUri.contains("tmp")) {
          videoUri = TEMP_DIR + "/tmp.mp4";
          return streamBytesOfContent(videoUri);
        }
        else {
          return error(e);
        }
      }
      return just(inputStream);
    } else {
      Log.e("Cache", "Cache not available");
      return never();
    }
  }
  
  
  @Override
  public Observable<VideoDataWrapper> streamWrappedContent(String relativePath) {
    if (preferences.contains(relativePath)) {
      System.err.println("Cache present! > " + relativePath);
    } else {
      System.err.println("Cache not present! > " + relativePath);
    }
    return just(relativePath)
      .filter(s -> preferences.contains(s))
      .map(inputStream -> {
        String fullPath = getFullPath(relativePath);
        return new VideoDataWrapper(fullPath);
      });
  }
  
  
  private InputStream getStreamFromUri(String videoUri) throws FileNotFoundException {
    String absPath = getFullPath(videoUri);
    Uri uri = parse(absPath);
    return resolver.openInputStream(uri);
  }
  
  
  @NonNull
  private String getFullPath(String videoUri) {
    String absPath = TEMP_DIR + "/";
    if (!videoUri.contains(TEMP_DIR)) {
      absPath +=  videoUri;
    }
    return absPath;
  }
}
