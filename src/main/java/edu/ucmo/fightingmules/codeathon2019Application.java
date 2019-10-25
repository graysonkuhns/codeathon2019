package edu.ucmo.fightingmules;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class codeathon2019Application extends Application<codeathon2019Configuration> {

    public static void main(final String[] args) throws Exception {
        new codeathon2019Application().run(args);
    }

    @Override
    public String getName() {
        return "codeathon2019";
    }

    @Override
    public void initialize(final Bootstrap<codeathon2019Configuration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final codeathon2019Configuration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
