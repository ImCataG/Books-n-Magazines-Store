FROM postgres:latest

COPY src/sqlcommands.sql /docker-entrypoint-initdb.d/

EXPOSE 5432
