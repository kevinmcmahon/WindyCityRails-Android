package org.windycityrails.activities;

import org.windycityrails.Constants;
import org.windycityrails.R;
import org.windycityrails.service.ConferenceDataService;

import roboguice.activity.RoboTabActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends RoboTabActivity {
	
	private static final String CLASSTAG = MainActivity.class.getSimpleName();
	private TabHost tabHost = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, MainActivity.CLASSTAG + " onCreate");
        
        setContentView(R.layout.main);
        buildTabs();
        
        Intent intent = new Intent().setClass(this, ConferenceDataService.class);
		startService(intent);
    }
    
    private void buildTabs() {
        Resources res = getResources();
        tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, SessionList.class);

        spec = tabHost.newTabSpec("sessions").setIndicator("Sessions",
                          res.getDrawable(R.drawable.ic_tab_clock))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, MapTab.class);
        spec = tabHost.newTabSpec("maps").setIndicator("Maps",
                          res.getDrawable(R.drawable.ic_tab_globe))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, SponsorsList.class);
        spec = tabHost.newTabSpec("sponsors").setIndicator("Sponsors",
                          res.getDrawable(R.drawable.ic_tab_seal))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

      menu.add(0,Constants.MENU_ID_WEBSITE, Constants.MENU_ORDER_WEBSITE, "Official Website").setIcon(R.drawable.ic_menu_laptop);
      menu.add(0,Constants.MENU_ID_ABOUT, Constants.MENU_ORDER_ABOUT, "About").setIcon(R.drawable.ic_menu_gear);
      
      return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

		switch(item.getItemId())
	    {
			case Constants.MENU_ID_WEBSITE:
				Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.official_website_url)));
				startActivity(web);
			break;
			case Constants.MENU_ID_ABOUT:
				Intent about = new Intent(Constants.INTENT_ACTION_VIEW_ABOUT);
				startActivity(about);
			break;
	    }
		return super.onOptionsItemSelected(item);

    }
    
}