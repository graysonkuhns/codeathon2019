package edu.ucmo.devet.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.ucmo.devet.db.dao.AnalysisJobDAO;
import edu.ucmo.devet.db.dao.LanguageDAO;
import edu.ucmo.devet.db.dao.RepositoryDAO;
import edu.ucmo.devet.handler.WebsocketHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/analysis")
@Produces(MediaType.APPLICATION_JSON)
public class GithubAnalysisController implements Controller {
    
    private final AnalysisJobDAO analysisJobDAO;
    private final LanguageDAO languageDAO;
    private final RepositoryDAO repositoryDAO;
    private final WebsocketHandler websocketHandler;

    @Inject
    public GithubAnalysisController(AnalysisJobDAO analysisJobDAO, LanguageDAO languageDAO, RepositoryDAO repositoryDAO, WebsocketHandler websocketHandler) {
        this.analysisJobDAO = analysisJobDAO;
        this.languageDAO = languageDAO;
        this.repositoryDAO = repositoryDAO;
        this.websocketHandler = websocketHandler;
    }

    @POST
    @Path("/{username}")
    public void startAnylysis(@PathParam("username") String username){
        websocketHandler.startDataUpdate(username);
        analysisJobDAO.start(username);
    }
}
