package edu.ucmo.devet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.ucmo.devet.controller.Controller;
import edu.ucmo.devet.handler.WebsocketHandler;
import io.dropwizard.setup.Environment;
import org.atmosphere.cpr.AtmosphereServlet;

import java.util.Set;

@Singleton
public class ApplicationRunner {
    private final Set<Controller> controllers;
    private final WebsocketHandler websocketHandler;
    
    @Inject
    public ApplicationRunner(Set<Controller> controllers, WebsocketHandler websocketHandler) {
        this.controllers = controllers;
        this.websocketHandler = websocketHandler;
    }

    public void run(final Environment environment, final DeVetConfiguration configuration, final AtmosphereServlet servlet){
        controllers.forEach(controller -> environment.jersey().register(controller));

        servlet.framework().addAtmosphereHandler("/", websocketHandler);
    }
}
