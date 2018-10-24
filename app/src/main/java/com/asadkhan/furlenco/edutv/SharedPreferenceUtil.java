package com.asadkhan.furlenco.edutv;


import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferenceUtil {
  
  public static final String EDU_TV_STORE = "edu_tv_store";
  
  
  public static SharedPreferences getPreferences(Context context) {
    return context.getSharedPreferences(EDU_TV_STORE, Context.MODE_PRIVATE);
  }
}
