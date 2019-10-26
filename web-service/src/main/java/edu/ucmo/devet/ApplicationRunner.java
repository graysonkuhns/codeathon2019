package edu.ucmo.devet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.ucmo.devet.controller.Controller;
import io.dropwizard.setup.Environment;

import java.util.Set;

@Singleton
public class ApplicationRunner {
    private final Set<Controller> controllers;
    
    @Inject
    public ApplicationRunner(final Set<Controller> controllers){
        this.controllers = controllers;
    }   
    
    public void run(final Environment environment, final DeVetConfiguration configuration){
        controllers.forEach(controller -> environment.jersey().register(controller));
    }
}
