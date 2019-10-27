package edu.ucmo.devet.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.ucmo.devet.db.dao.RepositoryDAO;
import edu.ucmo.devet.model.GithubAnalysis;
import org.atmosphere.cpr.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Singleton
public class WebsocketHandler implements AtmosphereHandler {
    private static final String BROADCASTER_NAME = "data";
    private BroadcasterFactory broadcasterFactory;
        
    private RepositoryDAO repositoryDAO;

    @Inject
    public WebsocketHandler(RepositoryDAO repositoryDAO) {
        this.repositoryDAO = repositoryDAO;
    }

//    private String data = "";
    private int broadcasterCounter = 0;
    
    private List<Timer> timers = new ArrayList<>();
    private List<Broadcaster> broadcasters = new ArrayList<>();
    private List<String> data = new ArrayList<>();

    @Override
    public void onRequest(AtmosphereResource atmosphereResource) throws IOException {
        broadcasterFactory = atmosphereResource.getAtmosphereConfig().getBroadcasterFactory();
        Broadcaster broadcaster = getBroadcaster(String.valueOf(broadcasterCounter++));
        broadcasters.add(broadcaster);
        data.add("");
        
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

    private Broadcaster getBroadcaster(String id) {
        return broadcasterFactory.lookup(id, true);
    }

    public void broadcastData(String id, String data) {
        getBroadcaster(id).broadcast(data);
    }

    public void startDataUpdate(String username) {
        int id = broadcasterCounter - 1;
        
        TimerTask updateTask = new TimerTask() {
            @Override
            public void run()
            {
                try {
                    String newData = new ObjectMapper().writeValueAsString(new GithubAnalysis(repositoryDAO.listLanguageCount(username)));
                    if(!newData.equals(data.get(id))) {
                        broadcastData(String.valueOf(id), newData);
                    }
                } catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }
        };

        Timer timer = new Timer(username);
        timer.scheduleAtFixedRate(updateTask, 0, 1000L);
        
        if(broadcasters.size() > timers.size()) {
            // New page has been opened, start a new timer
            timers.add(timer);
        } else {
            // Same page, delete old and make new
            timers.get(id).cancel();
            timers.set(id, timer);
        }
    }
}


