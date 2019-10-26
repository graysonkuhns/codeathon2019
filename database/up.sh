#!/usr/bin/env bash

# Build the mariadb image with a custom timezone
docker build -t devetdb:10.3 .

# Stop mariadb
docker stop mariadb
docker rm -f mariadb

# Run the mariadb container
docker run \
  --name mariadb \
  -e MYSQL_ROOT_PASSWORD="devet" \
  -v "/tmp/devetdb":/var/lib/mysql \
  --network host \
  -d \
  devetdb:10.3
