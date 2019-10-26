package edu.ucmo.devet.db.dao;

import com.google.inject.AbstractModule;
import edu.ucmo.devet.DeVetConfiguration;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class JdbiModule extends AbstractModule {
    private final Jdbi jdbi;

    public JdbiModule(final Environment environment, final DeVetConfiguration config){
        this.jdbi = new JdbiFactory().build(
            environment,
            config.getDataSourceFactory(),
            "mariadb");
    }

    @Override
    protected void configure() {
        bind(Jdbi.class).toInstance(jdbi);
    }
}
