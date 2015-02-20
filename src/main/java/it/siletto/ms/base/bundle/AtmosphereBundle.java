package it.siletto.ms.base.bundle;

import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.servlet.ServletRegistration;

import org.atmosphere.cpr.AtmosphereServlet;

public class AtmosphereBundle implements ConfiguredBundle<Configuration> {

	protected String packageName;
	protected String maxProgessingThread;
	protected String mapping;
	
	public AtmosphereBundle(String packageName, String maxProgessingThread, String mapping) {
		this.packageName = packageName;
		this.maxProgessingThread = maxProgessingThread;
		this.mapping = mapping;
	}

	@Override
	public void run(final Configuration configuration, final Environment environment) throws Exception {
		AtmosphereServlet atmosphereServlet = new AtmosphereServlet();
		final ServletRegistration.Dynamic websocket = environment.servlets().addServlet("socket", atmosphereServlet);
		websocket.setAsyncSupported(true);
		websocket.addMapping(mapping);
		websocket.setInitParameter("com.sun.jersey.config.property.packages", packageName);
		atmosphereServlet.framework().addInitParameter("org.atmosphere.cpr.broadcaster.maxProcessingThreads", maxProgessingThread);
		
	}

	@Override
	public void initialize(final Bootstrap<?> bootstrap) {

	}

}
