package org.windycityrails.activities;

import java.util.ArrayList;

import org.windycityrails.Constants;
import org.windycityrails.R;
import org.windycityrails.model.Session;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SessionAdapter extends ArrayAdapter<Session> {
	
	private static final String CLASSTAG = SessionAdapter.class.getSimpleName();
	   
	private ArrayList<Session> sessions;
	
	public SessionAdapter(Context context, int textViewResourceId, ArrayList<Session> items) {
		super(context, textViewResourceId, items);
		this.sessions = items;
		Log.v(Constants.LOGTAG, " " + SessionAdapter.CLASSTAG + " sessions size - " + items.size());
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
		
		if (v == null) {
            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.session_row, null);
        }
        
		Session s = sessions.get(position);
        if (s != null) {
            TextView tt = (TextView) v.findViewById(R.id.toptext);
            TextView bt = (TextView) v.findViewById(R.id.bottomtext);
            if (tt != null) {
                  tt.setText(s.title);                            }
            if(bt != null){
            	String btext = s.speaker.name;
            	if(s.speaker.company != null && s.speaker.company != "")
            		btext += ", " + s.speaker.company;
            	bt.setText(btext);
            }
        }
        return v;
	}
}
