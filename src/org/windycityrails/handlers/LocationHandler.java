package org.windycityrails.handlers;

import java.util.ArrayList;

import org.windycityrails.model.Location;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class LocationHandler extends WcgHandler {
			
			private StringBuffer buffer = new StringBuffer();
		    
		    private ArrayList<Location> locationList;
		    private Location location;
		    
		    @Override
		    public void startElement(String namespaceURI, String localName,
		            String qName, Attributes atts) throws SAXException {
		        
		        buffer.setLength(0);
		        
		        if (localName.equals("locations")) {
		        	locationList = new ArrayList<Location>();
		        }
		        else if (localName.equals("location")) {
		        	location = new Location();
		        	location.name = atts.getValue("name");
		        }
		    }
		    
		    @Override
		    public void endElement(String uri, String localName, String qName)throws SAXException {
		        
		        if (localName.equals("location")) {
		            locationList.add(location);
		        }
		        else if (localName.equals("venue_short")) {
		            location.venueShort = buffer.toString();
		        }
		        else if (localName.equals("venue_long")) {
		            location.venueLong = buffer.toString();
		        }
		        else if (localName.equals("address")) {
		        	location.address = buffer.toString();
		        }
		        else if (localName.equals("lat")) {
		        	location.latitude = buffer.toString();
		        }
		        else if (localName.equals("long")) {
		        	location.longitude = buffer.toString();
		        }		    
		        else if (localName.equals("photo")) {
		        	location.photo = buffer.toString();
		        }
		        else if (localName.equals("floor_plan")) {
		        	location.floorPlan = buffer.toString();		        	
		        }
		    }
		    
		    @Override
		    public void characters(char[] ch, int start, int length) {
		        buffer.append(ch, start, length);
		    }
		        
			public ArrayList<Location> retrieve() {
				return locationList;
			}
}
