package edu.ucmo.devet.jobworker.collection;

import edu.ucmo.devet.jobworker.database.dao.CommitDAO;
import edu.ucmo.devet.jobworker.database.dao.RepositoryDAO;
import edu.ucmo.devet.model.User;
import org.kohsuke.github.GitHub;

import javax.inject.Inject;
import java.io.IOException;

public class CommitsCollector {

    // Dependencies
    private final GitHub gitHub;
    private final CommitDAO commitDAO;
    private final RepositoryDAO repositoryDAO;
    private final CommitLanguageCollector commitLanguageCollector;

    @Inject
    public CommitsCollector(GitHub gitHub, CommitDAO commitDAO, RepositoryDAO repositoryDAO, CommitLanguageCollector commitLanguageCollector) {
        this.gitHub = gitHub;
        this.commitDAO = commitDAO;
        this.repositoryDAO = repositoryDAO;
        this.commitLanguageCollector = commitLanguageCollector;
    }

    public void collect(User user){
        try {
            gitHub
                    .getUser(user.getUsername())
                    .getRepositories()
                    .values()
                    .stream()
                    .map(repo -> {
                        try {
                            if (repo.isFork()) {
                                return repo.getParent();
                            }

                            return repo;
                        } catch(IOException e){
                            e.printStackTrace();
                        }

                        return null;
                    })
                    .map(repositoryDAO::create)
                    .forEach(repo -> {
                        try {
                            // For each repo, get that repo and get all commits
                            gitHub.getRepository(String.format(
                                    "%s/%s",
                                    repo.getUser(),
                                    repo.getName()));

                            // Get commits on that repo

                            // Call commit language collector on that commit and repo
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
