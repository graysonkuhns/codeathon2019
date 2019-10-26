package edu.ucmo.fightingmules.github;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GithubAPI {
    public void getUserContributions(String username){

        RepositoryService repositoryService = new RepositoryService();
        IssueService issueService = new IssueService();

        try {
            List<Repository> repos = repositoryService.getRepositories(username);
            for(Repository r : repos){
                System.out.println(r.getName() + " - ");
                List<Contributor> contributors = repositoryService.getContributors(() ->  r.getOwner().getLogin() + "/" + r.getName(), false);
                List<Issue> issues = issueService.getIssues(username, r.getName(), new HashMap<>());

                for(Contributor c : contributors){
                    if(c.getLogin().equals(username)){
                        System.out.println(c.getContributions() + " " + r.getLanguage() + " contributions and " + issues.size() + " issues");
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
