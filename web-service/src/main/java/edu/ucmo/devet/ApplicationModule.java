package edu.ucmo.devet;

import com.google.inject.AbstractModule;
import edu.ucmo.devet.controller.ControllersModule;
import edu.ucmo.devet.db.dao.DatabaseModule;
import io.dropwizard.setup.Environment;

public class ApplicationModule extends AbstractModule {
    
    private final Environment environment;
    private final DeVetConfiguration config;
    
    public ApplicationModule(final Environment environment, final DeVetConfiguration config){
        this.environment = environment;
        this.config = config;
    }
    
    @Override
    protected void configure(){
        install(new DatabaseModule(environment, config));
        install(new ControllersModule());
    }
}
