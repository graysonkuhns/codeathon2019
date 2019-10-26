package edu.ucmo.devet;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;


public class DeVetApplication extends Application<DeVetConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DeVetApplication().run(args);
    }

    @Override
    public String getName() {
        return "DeVet";
    }

    @Override
    public void initialize(final Bootstrap<DeVetConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/dist", "/", "index.html"));
    }

    @Override
    public void run(
        final DeVetConfiguration configuration,
        final Environment environment) {

        // Serve API resources at /api path
        environment.jersey().setUrlPattern("/api/*");
    }
}
