package org.windycityrails.activities;

import org.windycityrails.Constants;
import org.windycityrails.R;
import org.windycityrails.model.Location;

import com.github.droidfu.widgets.WebImageView;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import roboguice.util.Ln;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LocationDetail extends RoboActivity {
	
	private static final String CLASSTAG = LocationDetail.class.getSimpleName();
	
	@InjectView(R.id.picture_location_detail) private WebImageView photoView;
	@InjectView(R.id.venue_location_detail) private TextView venueView;
	@InjectView(R.id.address_location_detail) private TextView addressView;
	@InjectView(R.id.directions_location_detail) private TextView directionsView;
	@InjectView(R.id.location_details_container) private LinearLayout container;
	
	private Location location;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Ln.v(LocationDetail.CLASSTAG + " onCreate");
        
        setContentView(R.layout.location_detail);
        location = (Location) getIntent().getSerializableExtra(Constants.LOCATION_EXTRA);
        
        venueView.setText(location.venueLong);
        
        String addressText = formatAddressText(location.address);
        addressView.setText(addressText);
        
        if(location.photo != null && !location.photo.trim().equals("")) {
        	Ln.v(LocationDetail.CLASSTAG + " photo text: "+ location.photo);
        	photoView.setImageUrl(location.photo);
        	photoView.loadImage();
        } else {
        	photoView.setVisibility(View.INVISIBLE);
        	container.removeView(photoView);
        }
        
        directionsView.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
						Uri.parse("http://maps.google.com/maps?daddr="+ location.latitude + "," + location.longitude));
						startActivity(intent);
			}
		});
	}
	
	private String formatAddressText(String address) {
        String formatted = "";
        
        String[] parts = address.split(",");
        if(parts.length == 3) {
	        StringBuilder sb = new StringBuilder();
	        sb.append(parts[0].trim() + "\n");
	        sb.append(parts[1].trim() +"," + parts[2]+ "\n");
	        formatted = sb.toString();
        }
        else {
        	formatted = address;
        }
        return formatted;
	}
}