package edu.ucmo.devet.db.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.ucmo.devet.db.mapper.LanguageMapper;
import edu.ucmo.devet.model.Language;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMappers;

import java.util.List;

@Singleton
public class LanguageDAO {
    
    private final Jdbi jdbi;
    private final LanguageMapper languageMapper;

    @Inject
    public LanguageDAO(Jdbi jdbi, LanguageMapper languageMapper) {
        this.jdbi = jdbi;
        this.languageMapper = languageMapper;

        // Register the mapper if it has not been already
        if (!jdbi.getConfig().get(RowMappers.class).findFor(Language.class).isPresent()) {
            jdbi.registerRowMapper(languageMapper);
        }
    }

    public List<Language> list(){
        return jdbi.withHandle(handle -> handle
            .createQuery("SELECT * FROM language")
            .mapTo(Language.class)
            .list());
    }
}
