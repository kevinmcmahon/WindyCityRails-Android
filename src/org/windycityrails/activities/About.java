package org.windycityrails.activities;

import org.windycityrails.Constants;
import org.windycityrails.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class About extends RoboActivity {

	private static final String CLASSTAG =  About.class.getSimpleName();

	@InjectView(R.id.about_dev) private TextView devTextView;
	
	@InjectView(R.id.about_wcgteam) private TextView wcgTeamTextView;
	
	@InjectResource(R.string.about_app_dev_text) private String appDevText;
	
	@InjectView(R.id.cr_image) private ImageView cr;
	
	@InjectResource(R.drawable.image_chicagoruby) private Drawable chiRubyImage;
	
	@InjectView(R.id.about_cr_button) private Button crButton;
	
	@InjectView(R.id.wg_image) private ImageView wg;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, About.CLASSTAG + " onCreate");
        
        setContentView(R.layout.about);
        
        devTextView.setText(appDevText);
        
        wcgTeamTextView.setText(getDevTeamText());
        
        cr.setImageDrawable(chiRubyImage);
        
        crButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent crWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://chicagoruby.org/"));
				startActivity(crWeb);
			}
		});
        
         
        wg.setImageDrawable(getResources().getDrawable(R.drawable.image_wisdomgroup));
        wg.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				Intent wgWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.wisdomgroup.com/"));
				startActivity(wgWeb);
			}
		});
	}

	private String getDevTeamText() {
		String[] devTeamArray = getResources().getStringArray(R.array.dev_team);
        StringBuilder sb = new StringBuilder();
        for(String teamMember : devTeamArray) {
        	sb.append(teamMember+"\n");
        }
        return sb.toString().trim();
	}
}
