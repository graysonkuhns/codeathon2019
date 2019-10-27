package edu.ucmo.devet;

import com.google.inject.Guice;
import edu.ucmo.devet.handler.WebsocketHandler;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;
import org.atmosphere.cpr.ApplicationConfig;
import org.atmosphere.cpr.AtmosphereServlet;

import javax.servlet.ServletRegistration;

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

        AtmosphereServlet servlet = new AtmosphereServlet();
        servlet.framework().addInitParameter(ApplicationConfig.WEBSOCKET_SUPPORT, "true");

        ServletRegistration.Dynamic registration = environment.servlets().addServlet("atmosphere", servlet);
        registration.addMapping("/websocket/*");
        
        // Serve API resources at /api path
        environment.jersey().setUrlPattern("/api/*");

        Guice
            .createInjector(new ApplicationModule(environment, configuration))
            .getInstance(ApplicationRunner.class)
            .run(environment, configuration, servlet);
    }
}
