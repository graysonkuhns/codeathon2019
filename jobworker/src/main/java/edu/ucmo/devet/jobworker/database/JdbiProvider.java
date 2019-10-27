package edu.ucmo.devet.jobworker.database;

import com.google.inject.Provider;
import org.jdbi.v3.core.Jdbi;

public class JdbiProvider implements Provider<Jdbi> {

  @Override
  public Jdbi get() {
    return Jdbi.create("jdbc:mariadb://127.0.0.1:3306/devet", "devet", "devet");
  }
}
