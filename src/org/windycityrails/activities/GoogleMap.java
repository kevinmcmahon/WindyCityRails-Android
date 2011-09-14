package org.windycityrails.activities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.windycityrails.Constants;
import org.windycityrails.R;
import org.windycityrails.model.Location;
import org.windycityrails.util.MapHelper;
import org.windycityrails.util.XmlParser;

import roboguice.activity.RoboMapActivity;
import roboguice.util.Ln;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;

public class GoogleMap extends RoboMapActivity {

	private static final String CLASSTAG = GoogleMap.class.getSimpleName();

	private MapView mapView;
	private ArrayList<Location> locations = new ArrayList<Location>();
	private ArrayList<GeoPoint> points = new ArrayList<GeoPoint>();
	private ArrayList<SiteOverlay> sites = new ArrayList<GoogleMap.SiteOverlay>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Ln.v(GoogleMap.CLASSTAG + " onCreate");

		setContentView(R.layout.google_map);

		Drawable defaultmarker = getResources().getDrawable(R.drawable.marker);
		defaultmarker.setBounds(0, 0, defaultmarker.getIntrinsicWidth(),
				defaultmarker.getIntrinsicHeight());
		
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		locations = getLocations();

		for (Location location : locations) {
			GeoPoint curPoint = MapHelper.getPoint(
					Double.parseDouble(location.latitude),
					Double.parseDouble(location.longitude));
			points.add(curPoint);
			sites.add(new SiteOverlay(location, curPoint, defaultmarker, mapView));
		}

		mapView.getOverlays().addAll(sites);
		MapController mc = mapView.getController();
		mc.setZoom(17);

		if (points.size() > 0) {
			mc.setCenter(points.get(0));
			sites.get(0).setFocus(sites.get(0).createItem(0));
		}
	}

	private ArrayList<Location> getLocations() {
		Ln.v(GoogleMap.CLASSTAG + " getLocation");
		InputStream stream = null;
		
		String filename = getResources().getString(R.string.data_filename_locations);
        try {
        	File cachedFile = new File(getCacheDir(), filename);
    		
    		if(cachedFile.exists()) {
    			Ln.i("Reading location data from cached copy.");
    			stream = new FileInputStream(cachedFile);
    		} else {
    			Ln.i("Reading sessions data from assets.");
    	        stream = getAssets().open(filename);
    		}	
        } catch (IOException e) {
            // handle
        	Ln.e(e,"SessionsActivity : "+ e.getMessage());
        }
        return new XmlParser().parseLocationResponse(stream);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	private class SiteOverlay extends BalloonItemizedOverlay<OverlayItem> {

		private ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
		private Location mLocation;

		public SiteOverlay(Location location, GeoPoint point, Drawable marker, MapView mapView) {
			super(boundCenterBottom(marker), mapView);
			
			mLocation = location;
			setBalloonBottomOffset(marker.getIntrinsicHeight());
			addOverlay(new OverlayItem(point, mLocation.name, mLocation.venueLong));
		}

		public void addOverlay(OverlayItem overlay) {
			items.add(overlay);
			populate();
		}

		@Override
		public int size() {
			return items.size();
		}

		@Override
		protected OverlayItem createItem(int i) {
			return items.get(i);
		}

		@Override
		protected boolean onBalloonTap(int i, OverlayItem item) {

			Intent intent = new Intent(
								Constants.INTENT_ACTION_VIEW_LOCATION_DETAIL);
						intent.putExtra(Constants.LOCATION_EXTRA, mLocation);
						startActivity(intent);
			
			return true;
		}
	}
}