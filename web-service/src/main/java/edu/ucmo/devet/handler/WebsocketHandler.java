package edu.ucmo.devet.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.ucmo.devet.db.dao.RepositoryDAO;
import edu.ucmo.devet.model.GithubAnalysis;
import org.atmosphere.config.service.AtmosphereHandlerService;
import org.atmosphere.cpr.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@Singleton
public class WebsocketHandler implements AtmosphereHandler {
    private static final String BROADCASTER_NAME = "data";
    private BroadcasterFactory broadcasterFactory;
        
    @Inject 
    private RepositoryDAO repositoryDAO;

    public WebsocketHandler(RepositoryDAO repositoryDAO) {
        this.repositoryDAO = repositoryDAO;
    }

    private String data = "";
    private Timer timer;

    @Override
    public void onRequest(AtmosphereResource atmosphereResource) throws IOException {
        broadcasterFactory = atmosphereResource.getAtmosphereConfig().getBroadcasterFactory();
        Broadcaster broadcaster = getBroadcaster(broadcasterFactory);
        atmosphereResource.setBroadcaster(broadcaster);
        atmosphereResource.suspend();
        
        broadcastData(broadcasterFactory, data);
        startDataUpdate();
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

    private void startDataUpdate() {
        if(timer != null){
            return;
        }
        
        TimerTask schedulerTask = new TimerTask() {
            @Override
            public void run()
            {
                try {
                    String newData = new ObjectMapper().writeValueAsString(new GithubAnalysis(repositoryDAO.listLanguageCount()));

                    if(!newData.equals(data) && broadcasterFactory != null && broadcasterFactory.lookupAll().size() > 0) {
                        broadcastData(broadcasterFactory, newData);
                        data = newData;
                    }
                } catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }
        };

        timer = new Timer("Data update timer");

        long intervalMilli = 1000L;
        timer.scheduleAtFixedRate(schedulerTask, intervalMilli, intervalMilli);
    }
}
