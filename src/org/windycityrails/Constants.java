package org.windycityrails;

import android.view.Menu;

public class Constants {

    public static final String INTENT_ACTION_VIEW_SESSION_DETAIL = "org.windycityrails.VIEW_SESSION_DETAIL";
    public static final String INTENT_ACTION_VIEW_SESSION_LIST = "org.windycityrails.VIEW_SESSION_LIST";

    public static final String INTENT_ACTION_VIEW_SPONSOR_DETAIL = "org.windycityrails.VIEW_SPONSOR_DETAIL";
    public static final String INTENT_ACTION_VIEW_SPONSOR_LIST = "org.windycityrails.VIEW_SPONSOR_LIST";
    
    public static final String INTENT_ACTION_VIEW_LOCATION_DETAIL = "org.windycityrails.VIEW_LOCATION_DETAIL";
    
    public static final String INTENT_ACTION_VIEW_ABOUT = "org.windycityrails.VIEW_ABOUT_DETAIL";
    
    public static final String LOGTAG = "windycityrails";

    // Extras 
    public static final String STARTFROM_EXTRA = "org.windycityrails.StartFrom";
    public static final String FLOOR_PLAN_URL_EXTRA = "org.windycityrails.FloorPlanUrl";
    public static final String LOCATION_LAT_EXTRA = "org.windycityrails.LocationLat";
    public static final String LOCATION_LONG_EXTRA = "org.windycityrails.LocationLong";
	public static final String LOCATION_NAME_EXTRA = "org.windycityrails.LocationName";
	public static final String LOCATION_VENUE_LONG_EXTRA = "org.windycityrails.LocationVenueLong";
	public static final String LOCATION_VENUE_SHORT_EXTRA = "org.windycityrails.LocationVenueShort";
	public static final String LOCATION_ADDRESS_EXTRA = "org.windycityrails.LocationAddress";
	public static final String LOCATION_EXTRA = "org.windycityrails.Location";
	
	public static final int MENU_ID_WEBSITE = 0;
	public static final int MENU_ID_ABOUT = 1;
	public static final int MENU_ORDER_WEBSITE = Menu.FIRST;
	public static final int MENU_ORDER_ABOUT = Menu.FIRST+1;
}