package org.windycityrails.util;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.windycityrails.Constants;
import org.windycityrails.handlers.*;
import org.windycityrails.model.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.util.Log;

public class XmlParser {
	private static final String CLASSTAG = XmlParser.class.getSimpleName();
	
    private XMLReader initializeReader() throws ParserConfigurationException, SAXException {
    	
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
      
        return parser.getXMLReader();
    }
    
    @SuppressWarnings("unchecked")
	public ArrayList<SessionCategory> parseSessionResponse(InputStream xml) {
    	Log.v(Constants.LOGTAG, " " + XmlParser.CLASSTAG + " parseSessionResponse" );
        return (ArrayList<SessionCategory>) retrieveFromHandler(new SessionHandler(), new InputSource(xml));
    }

	@SuppressWarnings("unchecked")
	public ArrayList<SponsorLevel> parseSponsorResponse(InputStream xml) {
		Log.v(Constants.LOGTAG, " " + XmlParser.CLASSTAG + " parseSponsorResponse" );
		return (ArrayList<SponsorLevel>) retrieveFromHandler(new SponsorHandler(),new InputSource(xml));
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Location> parseLocationResponse(InputStream xml) {
		Log.v(Constants.LOGTAG, " " + XmlParser.CLASSTAG + " parseLocationResponse" );	
		return (ArrayList<Location>) retrieveFromHandler(new LocationHandler(), new InputSource(xml));
	}
	
	private ArrayList<?> retrieveFromHandler(WcgHandler handler, InputSource source) {
		try {		
			XMLReader xmlreader = initializeReader();
			xmlreader.setContentHandler(handler);
			xmlreader.parse(source);
			
			return handler.retrieve();            
        } 
        catch (Exception e) {
        	android.util.Log.e(Constants.LOGTAG, e.getMessage(), e.getCause());
            e.printStackTrace();
            return null;
        }   
	}
}