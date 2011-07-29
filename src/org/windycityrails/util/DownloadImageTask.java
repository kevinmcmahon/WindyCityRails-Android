package org.windycityrails.util;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;
    private Boolean shouldRoundCorners;
    
	public DownloadImageTask(ImageView imageView) {
    	this(imageView, false);
    }
    
	public DownloadImageTask(ImageView imageView, boolean shouldRoundCorners) {
		this.imageView = imageView;
		this.shouldRoundCorners = shouldRoundCorners;
	}

	protected Bitmap doInBackground(String... urls) {
	     Bitmap bitmap;
	     
	     if (shouldRoundCorners) {
	    	 bitmap = Network.downloadRoundedBitmap(urls[0]);
	     }
	     else {
	    	 bitmap = Network.downloadBitmap(urls[0]);
	     }
	     return bitmap;
	}

	protected void onPostExecute(Bitmap result) {
		if(imageView != null) {
			imageView.setImageBitmap(result);
		}
	}
}
