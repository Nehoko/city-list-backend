#!/bin/bash
set -e
echo "importing data"
export PGPASSWORD=$POSTGRES_PASSWORD;
psql --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  COPY CITY FROM '/docker-entrypoint-initdb.d/cities.csv' DELIMITER ',' CSV HEADER;
EOSQL