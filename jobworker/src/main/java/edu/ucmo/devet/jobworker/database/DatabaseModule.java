package edu.ucmo.devet.jobworker.database;

import com.google.inject.AbstractModule;
import org.jdbi.v3.core.Jdbi;

public class DatabaseModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Jdbi.class).toProvider(JdbiProvider.class);
  }
}
