package org.windycityrails.activities;

import org.windycityrails.Constants;
import org.windycityrails.R;
import org.windycityrails.ui.WebImageView;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class Floorplan extends RoboActivity {
	private static final String CLASSTAG = Floorplan.class.getSimpleName();
	@InjectView(R.id.floorplan_text) private TextView textView;
	@InjectView(R.id.floorplan_image) private WebImageView imageView;
    @InjectView(R.id.switcher) private ViewSwitcher switcher;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, Floorplan.CLASSTAG + " onCreate");
        
        setContentView(R.layout.floorplan);
        
        textView.setGravity(Gravity.CENTER);
        
        String url = getIntent().getStringExtra(Constants.FLOOR_PLAN_URL_EXTRA).trim();
        
        if(url == null || url.equals("")) {
        	switcher.setDisplayedChild(0);
        } else {
        	switcher.setDisplayedChild(1); 
	        imageView.setImageUrl(url);
	        imageView.loadImage();
        }
	}
}
