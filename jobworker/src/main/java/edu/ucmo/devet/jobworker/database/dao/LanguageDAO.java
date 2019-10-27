package edu.ucmo.devet.jobworker.database.dao;

import edu.ucmo.devet.jobworker.database.mapper.LanguageMapper;
import edu.ucmo.devet.model.Language;
import java.util.List;
import javax.inject.Inject;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMappers;

public class LanguageDAO {

  // Dependencies
  private final Jdbi jdbi;

  @Inject
  LanguageDAO(
      final Jdbi jdbi,
      final LanguageMapper mapper) {

    this.jdbi = jdbi;

    if (!jdbi.getConfig().get(RowMappers.class).findFor(Language.class).isPresent()) {
      jdbi.registerRowMapper(mapper);
    }
  }

  public List<Language> list() {
    return jdbi.withHandle(handle -> handle
        .createQuery("SELECT * FROM language")
        .mapTo(Language.class)
        .list());
  }
}
