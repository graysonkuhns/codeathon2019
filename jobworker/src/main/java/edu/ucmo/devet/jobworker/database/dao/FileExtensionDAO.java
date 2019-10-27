package edu.ucmo.devet.jobworker.database.dao;

import com.google.inject.Singleton;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class FileExtensionDAO {

    // Dependencies
    private final Jdbi jdbi;

    @Inject
    FileExtensionDAO(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public Map<String, Integer> list(){
        Map<String, Integer> map = new HashMap<>();
        jdbi.useHandle(handle -> handle
            .createQuery("SELECT * FROM file_extension")
            .mapToBean(ExTuple.class)
            .forEach(tuple -> map.put(tuple.extension, tuple.language)));

        return map;
    }

    public static class ExTuple {
        private String extension;
        private int language;

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public int getLanguage() {
            return language;
        }

        public void setLanguage(int language) {
            this.language = language;
        }
    }
}
