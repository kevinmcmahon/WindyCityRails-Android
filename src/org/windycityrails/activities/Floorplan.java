package org.windycityrails.activities;

import org.windycityrails.Constants;
import org.windycityrails.R;
import com.github.droidfu.widgets.WebImageView;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class Floorplan extends RoboActivity {
	private static final String CLASSTAG = Floorplan.class.getSimpleName();
	@InjectView(R.id.floorplan_text) private TextView textView;
	@InjectView(R.id.floorplan_image) private WebImageView imageView;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, Floorplan.CLASSTAG + " onCreate");
        
        setContentView(R.layout.floorplan);
        
        String url = getIntent().getStringExtra(Constants.FLOOR_PLAN_URL_EXTRA);
        
        if(url != null && url != "") {
	        imageView.setImageUrl(url);
	        imageView.loadImage();
	        imageView.setVisibility(View.VISIBLE);
	        textView.setVisibility(View.INVISIBLE);
        } else {
        	imageView.setVisibility(View.INVISIBLE);
        	textView.setVisibility(View.VISIBLE);
        	textView.setGravity(Gravity.CENTER);
        }
	}
}