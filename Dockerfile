FROM postgres:latest

ENV POSTGRES_DB=intecadb
ENV POSTGRES_USER=familydb
ENV POSTGRES_PASSWORD=password

COPY docker-entrypoint-initdb.d /docker-entrypoint-initdb.d

