package edu.ucmo.devet.db.dao;

import org.jdbi.v3.core.Jdbi;

public class ReposDAO {
    
    private final Jdbi jdbi;
    
    public ReposDAO(final Jdbi jdbi){
        this.jdbi = jdbi;
    }
}
