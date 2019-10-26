package edu.ucmo.devet;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: application initialization
    }

    @Override
    public void run(final DeVetConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
