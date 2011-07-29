package org.windycityrails;

import roboguice.config.AbstractAndroidModule;
import roboguice.inject.SharedPreferencesName;


public class WindyCityRailsModule extends AbstractAndroidModule {

    @Override
    protected void configure() {
    	bindConstant().annotatedWith(SharedPreferencesName.class).to("windycityrails.app");
    }

}
