package org.windycityrails.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network {
	
	 public static boolean isNetworkAvailable(Context context) {
	     ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	     if (connectivity != null) {
	        NetworkInfo[] info = connectivity.getAllNetworkInfo();
	        if (info != null) {
	           for (int i = 0; i < info.length; i++) {
	              if (info[i].getState() == NetworkInfo.State.CONNECTED) {
	                 return true;
	              }
	           }
	        }
	     }
	     return false;
	  }
	 
	  public static Bitmap downloadBitmap(String fileUrl) {
    	  URL myFileUrl = null;   
          Bitmap bmImg = null;
          
          try {
               myFileUrl= new URL(fileUrl);
          } catch (MalformedURLException e) {
               e.printStackTrace();
          }
          
          try {
        	  HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
        	  conn.setDoInput(true);

        	  conn.connect();
        	  
        	  InputStream is = conn.getInputStream();
              bmImg = BitmapFactory.decodeStream(new FlushedInputStream(is));
          } catch (IOException e) {
               e.printStackTrace();
          }
		return bmImg;
     }

	public static Bitmap downloadRoundedBitmap(String fileUrl) {
		Bitmap bitmap = downloadBitmap(fileUrl);
		if (bitmap != null) {
			return ImageHelper.getRoundedCornerBitmap(bitmap, 12);
		}
		return null;
	}
}
