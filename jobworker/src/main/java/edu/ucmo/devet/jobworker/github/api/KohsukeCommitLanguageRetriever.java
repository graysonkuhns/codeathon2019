package edu.ucmo.devet.jobworker.github.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.ucmo.devet.jobworker.database.dao.FileExtensionDAO;
import edu.ucmo.devet.jobworker.database.dao.LanguageDAO;
import edu.ucmo.devet.model.Commit;
import edu.ucmo.devet.model.Repository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class KohsukeCommitLanguageRetriever implements CommitLanguageRetriever {

    private final GitHub apiClient;
    private final FileExtensionDAO fileExtensionDAO;
    private final LanguageDAO languageDAO;

    @Inject
    public KohsukeCommitLanguageRetriever(GitHub apiClient, FileExtensionDAO fileExtensionDAO, LanguageDAO languageDAO) {
        this.apiClient = apiClient;
        this.fileExtensionDAO = fileExtensionDAO;
        this.languageDAO = languageDAO;
    }

    @Override
    public Map<String, Integer> getLanguages(Repository repo, Commit commit) throws LanguageRetrievalException {
        try {
            Map<String, Integer> langCount = new HashMap<>();

            apiClient
                    .getRepository(String.format(
                            "%s/%s",
                            repo.getUser(),
                            repo.getName()))
                    .getCommit(commit.getHash())
                    .getFiles()
                    .forEach(file -> {
                        int dotIndex =  file
                                .getFileName()
                                .lastIndexOf('.');

                        String ex = file
                                .getFileName()
                                .substring(dotIndex + 1);

                        String fileType = getFileFromExtenstion(ex);

                        if(langCount.containsKey(fileType)){
                            langCount.put(fileType, langCount.get(fileType) + 1);
                        } else {
                            langCount.put(fileType, 1);
                        }
                    });

            return langCount;
        } catch(IOException e){
            throw new LanguageRetrievalException(e);
        }
    }

    private String getFileFromExtenstion(String ex){
        Integer fileId = fileExtensionDAO.list().get(ex);

        if(fileId != null) {
            return languageDAO.list()
                    .stream()
                    .filter(file -> file.getId() == fileId)
                    .findFirst()
                    .get()
                    .getName();
        } else {
            System.out.printf("File extension not currently supported: .%s\n", ex);
            return null;
        }

    }
}
