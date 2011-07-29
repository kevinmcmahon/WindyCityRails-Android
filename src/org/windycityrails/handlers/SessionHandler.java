package org.windycityrails.handlers;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.windycityrails.model.Links;
import org.windycityrails.model.Session;
import org.windycityrails.model.SessionCategory;
import org.windycityrails.model.Speaker;

public class SessionHandler extends WcgHandler {
	
	private StringBuffer buffer = new StringBuffer();
    
    private ArrayList<SessionCategory> sessionList;
    private Session session;
    private SessionCategory sessionCategory;
    private Speaker speaker;
    private Links links;
    
    @Override
    public void startElement(String namespaceURI, String localName,
            String qName, Attributes atts) throws SAXException {
        
        buffer.setLength(0);
        
        if (localName.equals("sessions")) {
            sessionList = new ArrayList<SessionCategory>();
        }
        else if (localName.equals("category")) {
        	sessionCategory = new SessionCategory();
        	sessionCategory.name = atts.getValue("name");
        	sessionCategory.sessions = new ArrayList<Session>();
        }
        else if (localName.equals("session")) {
            session = new Session();
        }
        else if (localName.equals("speaker")) {
            speaker = new Speaker();
        }
        else if (localName.equals("links")) {
            links = new Links();
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)throws SAXException {
        
        if (localName.equals("session")) {
            sessionCategory.sessions.add(session);
        }
        else if (localName.equals("title")) {
            session.title = buffer.toString();
        }
        else if (localName.equals("name")) {
            speaker.name = buffer.toString();
        }
        else if (localName.equals("company")) {
            speaker.company = buffer.toString();
        }
        else if (localName.equals("bio")) {
            speaker.bio = buffer.toString();
        }
        else if (localName.equals("headshot")) {
            speaker.headshot = buffer.toString();
        }
        else if (localName.equals("description")) {
            session.description = buffer.toString();
        }
        else if (localName.equals("start_time")) {
            session.startTime = buffer.toString();
        }
        else if (localName.equals("end_time")) {
            session.endTime = buffer.toString();
        }    
        else if (localName.equals("speaker_website")) {
            links.speakerWebsite = buffer.toString();
        }    
        else if (localName.equals("speaker_twitter")) {
            links.speakerTwitter = buffer.toString();
        }
        else if (localName.equals("slides_url")) {
            links.slidesUrl = buffer.toString();
        }
        else if (localName.equals("rate_url")) {
            links.rateUrl = buffer.toString();
        }
        else if (localName.equals("video_url")) {
            links.videoUrl = buffer.toString();
        }
        else if (localName.equals("speaker")) {
            session.speaker = speaker;
        }
        else if (localName.equals("links")) {
            session.links = links;
        }
        else if (localName.equals("category")){
        	sessionList.add(sessionCategory);
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }
            
	public ArrayList<SessionCategory> retrieve() {
		return sessionList;
	}
}