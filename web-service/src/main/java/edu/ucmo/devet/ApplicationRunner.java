package edu.ucmo.devet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.ucmo.devet.controller.Controller;
import edu.ucmo.devet.db.dao.RepositoryDAO;
import edu.ucmo.devet.handler.WebsocketHandler;
import edu.ucmo.devet.model.GithubAnalysis;
import io.dropwizard.setup.Environment;
import org.atmosphere.cpr.AtmosphereServlet;
import org.atmosphere.cpr.BroadcasterFactory;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

@Singleton
public class ApplicationRunner {
    private final Set<Controller> controllers;
    //private final WebsocketHandler websocketHandler;
    private final RepositoryDAO repositoryDAO;
    
    @Inject
    public ApplicationRunner(Set<Controller> controllers, RepositoryDAO repositoryDAO) {
        this.controllers = controllers;
        this.repositoryDAO = repositoryDAO;
    }

    public void run(final Environment environment, final DeVetConfiguration configuration, final AtmosphereServlet servlet){
        controllers.forEach(controller -> environment.jersey().register(controller));

        WebsocketHandler websocketHandler = new WebsocketHandler(repositoryDAO);
        servlet.framework().addAtmosphereHandler("/", websocketHandler);
    }
}
