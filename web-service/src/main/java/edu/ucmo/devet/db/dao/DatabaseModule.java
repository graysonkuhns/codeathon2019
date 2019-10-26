package edu.ucmo.devet.db.dao;

import com.google.inject.AbstractModule;
import edu.ucmo.devet.DeVetConfiguration;
import io.dropwizard.setup.Environment;

public class DatabaseModule extends AbstractModule {
    private final Environment environment;
    private final DeVetConfiguration config;

    public DatabaseModule(final Environment environment, final DeVetConfiguration config){
        this.environment = environment;
        this.config = config;
    }

    @Override
    protected void configure(){
        install(new JdbiModule(environment, config));
    }
}
