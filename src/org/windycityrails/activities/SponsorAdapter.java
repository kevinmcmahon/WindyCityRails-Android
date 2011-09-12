package org.windycityrails.activities;

import java.util.ArrayList;

import org.windycityrails.Constants;
import org.windycityrails.R;
import org.windycityrails.model.Sponsor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SponsorAdapter extends ArrayAdapter<Sponsor> {
	private static final String CLASSTAG = SponsorAdapter.class.getSimpleName();
	   
	private ArrayList<Sponsor> sponsors;
	
	public SponsorAdapter(Context context, int textViewResourceId, ArrayList<Sponsor> items) {
		super(context, textViewResourceId, items);
		this.sponsors = items;
		Log.v(Constants.LOGTAG, " " + SponsorAdapter.CLASSTAG + " sponsors size - " + items.size());
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.v(Constants.LOGTAG, " " + SponsorAdapter.CLASSTAG + " getView");
		
		View v = convertView;
		
		if (v == null) {
		    LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    v = vi.inflate(R.layout.sponsor_row, null);
		}
		
		Sponsor s = sponsors.get(position);
		
		if (s != null) {
			TextView sponsorName = (TextView) v.findViewById(R.id.sponsor_name_list);
			if (sponsorName != null) {
				sponsorName.setText(s.name);
			}
		}
		
		return v;
	}
}