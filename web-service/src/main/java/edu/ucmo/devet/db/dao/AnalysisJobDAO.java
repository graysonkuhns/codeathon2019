package edu.ucmo.devet.db.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.jdbi.v3.core.Jdbi;

@Singleton
public class AnalysisJobDAO {
    private final Jdbi jdbi;

    @Inject
    public AnalysisJobDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public void start(){
        
    }
}
