package edu.ucmo.devet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.ucmo.devet.db.dao.AnalysisJobDAO;
import edu.ucmo.devet.db.dao.LanguageDAO;
import edu.ucmo.devet.db.dao.RepositoryDAO;
import edu.ucmo.devet.model.GithubAnalysis;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/analysis")
@Produces(MediaType.APPLICATION_JSON)
public class GithubAnalysisController implements Controller {
    
    private final AnalysisJobDAO analysisJobDAO;
    private final LanguageDAO languageDAO;
    private final RepositoryDAO repositoryDAO;

    @Inject
    public GithubAnalysisController(AnalysisJobDAO analysisJobDAO, LanguageDAO languageDAO, RepositoryDAO repositoryDAO) {
        this.analysisJobDAO = analysisJobDAO;
        this.languageDAO = languageDAO;
        this.repositoryDAO = repositoryDAO;
    }
    
    @POST
    @Path("/{username}")
    public void startAnylysis(@PathParam("username") String username){
        analysisJobDAO.start(username);
    }
    
    @GET
    public GithubAnalysis getAnalysis() {
        return new GithubAnalysis(repositoryDAO.listLanguageCount());
    }
}
