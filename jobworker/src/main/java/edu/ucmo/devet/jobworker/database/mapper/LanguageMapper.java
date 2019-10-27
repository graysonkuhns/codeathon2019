package edu.ucmo.devet.jobworker.database.mapper;

import edu.ucmo.devet.model.Language;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.mapper.RowMappers;
import org.jdbi.v3.core.statement.StatementContext;

public class LanguageMapper implements RowMapper<Language> {

  @Override
  public Language map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new Language(rs.getInt("id"), rs.getString("name"));
  }
}
