package com.myapp.miskaainternshipassignment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class NetworkListenerReceiver extends BroadcastReceiver {
  public boolean isNetworkConnected;

  @Override
  public void onReceive(Context context, Intent intent) {
    try {
      if (isOnline(context)) {
        Toast.makeText(context, "network connected", Toast.LENGTH_SHORT).show();
        isNetworkConnected = true;
      }else{
        Toast.makeText(context, "network disconnected", Toast.LENGTH_SHORT).show();
        isNetworkConnected = false;
      }

    } catch (NullPointerException e) {
      Log.i("--receiver--", e.toString());
    }
  }

  public boolean isOnline(Context context) {
    try {
      ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
      return (networkInfo != null && networkInfo.isConnected());
    } catch (NullPointerException e) {
      e.printStackTrace();
      return false;
    }
  }
}