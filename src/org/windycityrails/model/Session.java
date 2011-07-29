package org.windycityrails.model;

public class Session {

	public String title;
	public Speaker speaker;
	public String description;
	public String startTime;
	public String endTime;
	public Links links;
	
	@Override
	public String toString() {
		return title;
	}
}
