package org.windycityrails.activities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.windycityrails.Constants;
import org.windycityrails.R;
import org.windycityrails.WindyCityRailsApplication;
import org.windycityrails.model.Session;
import org.windycityrails.model.SessionCategory;
import org.windycityrails.util.XmlParser;

import roboguice.activity.RoboListActivity;
import roboguice.util.Ln;

import com.commonsware.cwac.merge.MergeAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class SessionList extends RoboListActivity {
	private static final String CLASSTAG = SessionList.class.getSimpleName();
	private ArrayList<Session> sessions;
	private MergeAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Ln.v(SessionList.CLASSTAG + " onCreate");
        
        AssetManager assetManager = getAssets();
        InputStream stream = null;
        ArrayList<SessionCategory> sessionCategories = null;
        adapter = new MergeAdapter();
        String filename = getResources().getString(R.string.data_filename_sessions);
        try {
        	File cachedFile = new File(getCacheDir(), filename);
    		
    		if(cachedFile.exists()) {
    			Ln.i("Reading sessions data from cached copy.");
    			stream = new FileInputStream(cachedFile);
    		} else {
    			Ln.i("Reading sessions data from assets.");
    	        stream =  assetManager.open(filename);
    		}
        	
        	sessionCategories = new XmlParser().parseSessionResponse(stream);
            sessions = sessionCategories.get(0).sessions;
        } catch (IOException e) {
            // handle
        	Ln.e(e,"SessionsActivity : "+ e.getMessage());
        }
        
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        for (Session s : sessions) {
        	
        	View sessionRow = (View) vi.inflate(R.layout.session_row,null);
        	TextView header = (TextView) sessionRow.findViewById(R.id.session_time_list);
        	header.setText(s.startTime + " - " + s.endTime);
        	
        	TextView top = (TextView) sessionRow.findViewById(R.id.toptext);
        	TextView bot = (TextView) sessionRow.findViewById(R.id.bottomtext);
        	top.setText(s.title);
        	bot.setText(s.speaker.name);
        	if(s.speaker.company != "")
        		bot.append(", "+s.speaker.company);
        	adapter.addView(sessionRow, true); 
        }
        
        this.setListAdapter(adapter);
    } 
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	Log.v(Constants.LOGTAG, " " + SessionList.CLASSTAG + " onListItemClick");
    	
    	// set the current review to the Application (global state placed there)
        WindyCityRailsApplication application = (WindyCityRailsApplication) getApplication();
        application.setCurrentSession(sessions.get(position));

        // startFrom page is not stored in application, for example purposes it's a simple "extra"
        Intent intent = new Intent(Constants.INTENT_ACTION_VIEW_SESSION_DETAIL);
        intent.putExtra(Constants.STARTFROM_EXTRA, getIntent().getIntExtra(Constants.STARTFROM_EXTRA, 1));
        startActivity(intent);
    }
}
