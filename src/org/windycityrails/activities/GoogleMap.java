package org.windycityrails.activities;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.windycityrails.Constants;
import org.windycityrails.R;
import org.windycityrails.model.Location;
import org.windycityrails.util.MapHelper;
import org.windycityrails.util.XmlParser;

import roboguice.activity.RoboMapActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;


public class GoogleMap extends RoboMapActivity {
	
	private static final String CLASSTAG = GoogleMap.class.getSimpleName();
	
	private MapView mapView;
	private ArrayList<Location> locations = new ArrayList<Location>();
	private ArrayList<GeoPoint> points = new ArrayList<GeoPoint>();
	private ArrayList<SiteOverlay> sites = new ArrayList<GoogleMap.SiteOverlay>();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, GoogleMap.CLASSTAG + " onCreate");
        
        setContentView(R.layout.google_map);
        
        Drawable defaultmarker = getResources().getDrawable(R.drawable.marker);     
	    defaultmarker.setBounds(0,0,defaultmarker.getIntrinsicWidth(),defaultmarker.getIntrinsicHeight());
	    
        locations = getLocations();
        
        for(Location location : locations) {
        	 GeoPoint curPoint = MapHelper.getPoint(Double.parseDouble(location.latitude),
               		Double.parseDouble(location.longitude));
        	 points.add(curPoint);
        	 sites.add(new SiteOverlay(location, curPoint, defaultmarker, getParent()));    
        }
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapView.getOverlays().addAll(sites);
        
        MapController mc = mapView.getController();
        mc.setZoom(17);
        
        if(points.size() > 0) {
        	mc.setCenter(points.get(0));
        }
	}
    
    private ArrayList<Location> getLocations() {
        Log.v(Constants.LOGTAG, GoogleMap.CLASSTAG + " getLocation");
        InputStream stream = null;
    	try {
			stream = getAssets().open("locations.xml");
		} catch (IOException e) {
            // handle
        	android.util.Log.e(Constants.LOGTAG, GoogleMap.CLASSTAG + " " + e.getMessage(), e);
        }
    	return new XmlParser().parseLocationResponse(stream);
    }
    
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	private class SiteOverlay extends ItemizedOverlay<OverlayItem> {
		
		private List<OverlayItem> items = new ArrayList<OverlayItem>();
		private Context mContext;
		private Location mLocation;
		
		public SiteOverlay(Location location, GeoPoint point, Drawable marker, Context context) {
			super(marker);
			mContext=context;
			mLocation=location;
			
			boundCenterBottom(marker);
			
			items.add(new OverlayItem(point,mLocation.name,mLocation.venueLong));

			populate();
		}

		@Override
		protected OverlayItem createItem(int i) {
			return items.get(i);
		}

		@Override
		protected boolean onTap(int i) {
			  OverlayItem item = items.get(i);
			  try {
				  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
				  dialog.setTitle(item.getTitle());
				  dialog.setMessage(item.getSnippet());
				  dialog.setNeutralButton("Details",new OnClickListener() {
				  	
					public void onClick(DialogInterface dialog, int which) {
				        Intent intent = new Intent(Constants.INTENT_ACTION_VIEW_LOCATION_DETAIL);
				        intent.putExtra(Constants.LOCATION_EXTRA, mLocation);
				        startActivity(intent);
					}
				  });
				  dialog.setNegativeButton( "Close", null);
				  dialog.show();
			  } 
			  catch (Exception e){
				  Log.e(Constants.LOGTAG, GoogleMap.CLASSTAG + " Exception showing alert", e);
			  }
			  
			  return true;
		}

		@Override
		public int size() {
			return items.size();
		}
	}
}