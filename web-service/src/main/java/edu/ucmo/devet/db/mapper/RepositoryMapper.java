package edu.ucmo.devet.db.mapper;

import com.google.inject.Singleton;
import edu.ucmo.devet.model.Language;
import edu.ucmo.devet.model.Repository;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
public class RepositoryMapper implements RowMapper<Repository>{
    @Override
    public Repository map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Repository(
            rs.getInt("id"), 
            rs.getString("owner"), 
            rs.getString("name"), 
            rs.getInt("stars"));
    }
}
