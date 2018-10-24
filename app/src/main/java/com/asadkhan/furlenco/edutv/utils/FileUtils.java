package com.asadkhan.furlenco.edutv.utils;


import android.content.Context;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class FileUtils {
  
  public static String getFilePathFromStream(Context ctx, InputStream inputStream, String name) {
    
    File file = new File("");
    
    byte buffer[] = new byte[1024 * 6];
    InputStream bis = new BufferedInputStream(inputStream, 1024 * 8);
    
    try {
      // read inputStream
      Log.e("InputStream", "Name: " + bis);
      int size = bis.available();
      
      // Write output stream
      file = new File(ctx.getFilesDir(), name);
      FileOutputStream outputStream = new FileOutputStream(file);
      
      int read;
      while ((read = bis.read(buffer)) != -1) {
        outputStream.write(buffer, 0, read);
        
        if (size == 0) {
          return "";
        }
      }
      
      try {
        bis.close();
        inputStream.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      try {
        outputStream.flush();
        outputStream.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return file.getAbsolutePath();
  }
}
