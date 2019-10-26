# Refresh the database
DROP DATABASE IF EXISTS devet;
CREATE DATABASE devet;
USE devet;

# Create the service account and grant it admin permissions on the database
CREATE OR REPLACE USER `devet`@`%` IDENTIFIED BY 'devet';
GRANT ALL PRIVILEGES ON devet.* TO `devet`@`%` WITH GRANT OPTION;

# Create database schema
