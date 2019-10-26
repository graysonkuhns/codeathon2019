package edu.ucmo.devet.handler;

import org.atmosphere.config.service.AtmosphereHandlerService;
import org.atmosphere.cpr.*;
import java.io.IOException;


@AtmosphereHandlerService
public class WebsocketHandler implements AtmosphereHandler {
    private static final String BROADCASTER_NAME = "data";

    @Override
    public void onRequest(AtmosphereResource atmosphereResource) throws IOException {
        BroadcasterFactory broadcasterFactory = atmosphereResource.getAtmosphereConfig().getBroadcasterFactory();
        Broadcaster broadcaster = getBroadcaster(broadcasterFactory);
        atmosphereResource.setBroadcaster(broadcaster);
        atmosphereResource.suspend();
    }

    private boolean isBroadcast(AtmosphereResourceEvent event) {
        return event.getMessage() != null && !event.isCancelled() && !event.isClosedByClient() && !event.isClosedByApplication();
    }

    @Override
    public void onStateChange(AtmosphereResourceEvent event) throws IOException {
        AtmosphereResource resource = event.getResource();

        if (isBroadcast(event)) {
            resource.write(event.getMessage().toString());

            switch (resource.transport()) {
                case WEBSOCKET:
                case STREAMING:
                    resource.getResponse().flushBuffer();
                    break;
                default:
                    resource.resume();
                    break;
            }
        }
    }

    @Override
    public void destroy() {
    }

    private Broadcaster getBroadcaster(BroadcasterFactory broadcasterFactory) {
        return broadcasterFactory.lookup(BROADCASTER_NAME, true);
    }

    public void broadcastData(BroadcasterFactory broadcasterFactory, String data) {
        getBroadcaster(broadcasterFactory).broadcast(data);
    }
}
