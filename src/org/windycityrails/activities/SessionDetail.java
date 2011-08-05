package org.windycityrails.activities;

import org.windycityrails.Constants;
import org.windycityrails.R;
import org.windycityrails.WindyCityRailsApplication;
import org.windycityrails.model.Session;
import org.windycityrails.util.Network;

import org.windycityrails.ui.WebImageView;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SessionDetail extends RoboActivity {
	private static final String CLASSTAG = SessionDetail.class.getSimpleName();
	
	@InjectView(R.id.speaker_icon) private WebImageView imgView;
	@InjectView(R.id.title) private TextView titleTextView;
	@InjectView(R.id.presenter) private TextView presenterTextView;
	@InjectView(R.id.description) private TextView descriptionTextView;
	@InjectView(R.id.bio) private TextView speakerBioTextView;
	@InjectView(R.id.session_website_button) private Button websiteButton;
	@InjectView(R.id.session_twitter_button) private Button twitterButton;
	@InjectView(R.id.bio_header) private TextView bioHeader;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG," " + SessionDetail.CLASSTAG + " onCreate");
        
        WindyCityRailsApplication app = (WindyCityRailsApplication) getApplication();
        final Session session = app.getCurrentSession();
        
        setContentView(R.layout.session_detail);

        titleTextView.setText(session.title);
        presenterTextView.setText(session.speaker.name);
        if(session.speaker.company != null && session.speaker.company != "")
        	presenterTextView.append(", "+session.speaker.company);
        descriptionTextView.setText(session.description);
        
        if(session.speaker.bio != null && session.speaker.bio != "") {
        	speakerBioTextView.setText(session.speaker.bio);
        }
        else {
        	bioHeader.setVisibility(View.INVISIBLE);
        	speakerBioTextView.setVisibility(View.INVISIBLE);
        }
        
        if(Network.isNetworkAvailable(this) && session.speaker.headshot != null && session.speaker.headshot != "") {
        	imgView.setImageUrl(session.speaker.headshot);
        	imgView.loadImage();
        }
        
        if(session.links.speakerWebsite != null && session.links.speakerWebsite != "") {
        	websiteButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(session.links.speakerWebsite));
					startActivity(i);
				}
			});
        }
        else {
        	websiteButton.setVisibility(View.INVISIBLE);
        }
        
    	if(session.links.speakerTwitter != null && session.links.speakerTwitter != "") {
    		twitterButton.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(session.links.speakerTwitter));
					startActivity(i);
				}
			});
        }
        else {
        	twitterButton.setVisibility(View.INVISIBLE);
        }
    }
}