package org.windycityrails;

import org.windycityrails.model.Location;
import org.windycityrails.model.Session;
import org.windycityrails.model.Sponsor;
import roboguice.application.RoboApplication;

import com.google.inject.Module;

import java.util.List;

public class WindyCityRailsApplication extends RoboApplication {

	private Session session;
	private Sponsor sponsor;
	private Location location;

	public WindyCityRailsApplication() {
		super();
	}

	public Session getCurrentSession() {
		return session;
	}

	public void setCurrentSession(Session currentSession) {
		this.session = currentSession;
	}

	public void setCurrentSponsor(Sponsor currentSponsor) {
		this.sponsor = currentSponsor;
	}

	public Sponsor getCurrentSponsor() {
		return this.sponsor;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.location = currentLocation;
	}

	public Location getCurrentLocation() {
		return this.location;
	}

	@Override
	protected void addApplicationModules(List<Module> modules) {
		modules.add(new WindyCityRailsModule());
	}

}
