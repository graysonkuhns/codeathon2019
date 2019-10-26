#!/usr/bin/env bash

# Run the database initialization script
mysql -u root -pdevet < /opt/devet/database/init.sql
