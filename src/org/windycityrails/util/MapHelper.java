package org.windycityrails.util;

import com.google.android.maps.GeoPoint;

public class MapHelper {
	public static GeoPoint getPoint(double lat, double lon) {
		return(new GeoPoint((int)(lat*1000000.0), (int)(lon*1000000.0)));
	}
}
