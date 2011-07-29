package org.windycityrails.handlers;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.windycityrails.model.Sponsor;
import org.windycityrails.model.SponsorLevel;

public class SponsorHandler extends WcgHandler {

	private StringBuffer buffer = new StringBuffer();
    ArrayList<SponsorLevel> sponsorLevels;
    SponsorLevel level;
    Sponsor sponsor;
    
    @Override
    public void startElement(String namespaceURI, String localName,
            String qName, Attributes atts) throws SAXException {
    	
        buffer.setLength(0);
        
        if (localName.equals("sponsors")) {
        	sponsorLevels = new ArrayList<SponsorLevel>();
        }
        
        else if (localName.equals("level")) {
        	level = new SponsorLevel();
        	level.name = atts.getValue("name");
        	level.sponsors = new ArrayList<Sponsor>();
        }
        else if (localName.equals("sponsor")) {
            sponsor = new Sponsor();
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)throws SAXException {
        
    	if (localName.equals("level")) {
    		sponsorLevels.add(level);
    	}
    	else if (localName.equals("sponsor")) {
            level.sponsors.add(sponsor);
        }
        else if (localName.equals("name")) {
            sponsor.name = buffer.toString();
        }
        else if (localName.equals("url")) {
            sponsor.url = buffer.toString();
        }
        else if (localName.equals("logo")) {
            sponsor.logo = buffer.toString();
        }
        else if (localName.equals("description")) {
            sponsor.description = buffer.toString();
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }
        
	public ArrayList<SponsorLevel> retrieve() {
		return sponsorLevels;
	}
}