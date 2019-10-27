package edu.ucmo.devet.jobworker.collection;

import edu.ucmo.devet.jobworker.database.dao.CommitDAO;
import edu.ucmo.devet.jobworker.database.dao.RepositoryDAO;
import edu.ucmo.devet.jobworker.github.api.CommitRetriever;
import edu.ucmo.devet.model.Repository;
import edu.ucmo.devet.model.User;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import javax.inject.Inject;
import java.io.IOException;

public class CommitsCollector {

    // Dependencies
    private final GitHub gitHub;
    private final CommitDAO commitDAO;
    private final RepositoryDAO repositoryDAO;
    private final CommitLanguageCollector commitLanguageCollector;
    private final CommitRetriever commitRetriever;

    @Inject
    public CommitsCollector(GitHub gitHub, CommitDAO commitDAO, RepositoryDAO repositoryDAO, CommitLanguageCollector commitLanguageCollector, CommitRetriever commitRetriever) {
        this.gitHub = gitHub;
        this.commitDAO = commitDAO;
        this.repositoryDAO = repositoryDAO;
        this.commitLanguageCollector = commitLanguageCollector;
        this.commitRetriever = commitRetriever;
    }

    public void collect(User user){
        try {
            commitDAO.delete(user);

            gitHub
                    .getUser(user.getUsername())
                    .getRepositories()
                    .values()
                    .stream()
//                    .map(repo -> {
//                        try {
//                            if (repo.isFork()) {
//                                GHRepository parent = repo.getParent();
//                                if(parent == null){
//                                    throw new RuntimeException("Parent of the repo " + repo.getName() + " is null");
//                                }
//                                return parent;
//                            }
//
//                            return repo;
//                        } catch(IOException e){
//                            throw new RuntimeException(e);
//                        }
//                    })
                    .map(GHRepo -> new Repository(0, GHRepo.getOwnerName(), GHRepo.getName(), 0))
                    .forEach(repo -> {
                        // Get commits on that repo
                        commitRetriever.getCommits(repo, user)
                            .forEach(commit -> {
                                // Create the commit in the db
                                commitDAO.create(commit);

                                // Call commit language collector on that commit and repo
                                commitLanguageCollector.collect(repo, commit);
                            });
                    });
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
