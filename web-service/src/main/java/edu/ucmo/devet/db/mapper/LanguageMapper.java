package edu.ucmo.devet.db.mapper;

import com.google.inject.Singleton;
import edu.ucmo.devet.model.Language;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
public class LanguageMapper implements RowMapper<Language> {
    @Override
    public Language map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Language(rs.getInt("id"), rs.getString("name"));
    }
}
