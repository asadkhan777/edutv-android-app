package com.asadkhan.furlenco.edutv.model;


import android.net.Uri;
import java.io.InputStream;


public class VideoDataWrapper {
  
  public String fullPath;
  
  public Uri contentUri;
  
  public InputStream inputStream;
  
  
  public VideoDataWrapper() {
  }
  
  
  public VideoDataWrapper(String fullPath, Uri contentUri, InputStream inputStream) {
    this.fullPath = fullPath;
    this.contentUri = contentUri;
    this.inputStream = inputStream;
  }
  
  
  public VideoDataWrapper(String fullPath, InputStream inputStream) {
    this.fullPath = fullPath;
    this.inputStream = inputStream;
  }
  
  public VideoDataWrapper(String fullPath) {
    this.fullPath = fullPath;
  }
}
