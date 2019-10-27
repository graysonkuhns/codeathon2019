package edu.ucmo.devet.db.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.jdbi.v3.core.Jdbi;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class CommitDAO {
    private final Jdbi jdbi;

    @Inject
    public CommitDAO(final Jdbi jdbi){
        this.jdbi = jdbi;
    }

    public Map<String, Integer> listCommitLanguageCount(String username){
        Map<String, Integer> languageCount = new HashMap<>();
        jdbi.useHandle(handle -> handle
            .createQuery("SELECT l.name, rl.file_count FROM commit_language AS rl JOIN language AS l " +
                "WHERE l.id = rl.language AND rl.commit IN " +
                "(SELECT hash FROM commit WHERE author = :author)")
            .bind("author", username)
            .mapToBean(LanguageTuple.class)
            .list()
            .forEach(tuple -> {
                if(languageCount.containsKey(tuple.name)){
                    languageCount.put(tuple.name, languageCount.get(tuple.name) + tuple.fileCount);
                } else {
                    languageCount.put(tuple.name, tuple.fileCount);
                }
            }));

        return languageCount;
    }

    public static class LanguageTuple {
        private String name;
        private Integer fileCount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getFileCount() {
            return fileCount;
        }

        public void setFileCount(Integer fileCount) {
            this.fileCount = fileCount;
        }
    }
}
