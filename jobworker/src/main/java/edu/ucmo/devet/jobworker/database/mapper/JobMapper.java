package edu.ucmo.devet.jobworker.database.mapper;

import edu.ucmo.devet.jobworker.job.Job;
import edu.ucmo.devet.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class JobMapper implements RowMapper<Job> {

  @Override
  public Job map(final ResultSet rs, final StatementContext ctx) throws SQLException {
    final User user = new User(rs.getString("username"));
    return new Job(rs.getInt("id"), user);
  }
}
