package org.windycityrails.handlers;

import java.util.ArrayList;

import org.xml.sax.helpers.DefaultHandler;

public abstract class WcgHandler extends DefaultHandler {
	public abstract ArrayList<?> retrieve();
	
}