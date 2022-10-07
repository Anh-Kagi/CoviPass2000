#!/bin/sh
docker run -it --rm -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=covid-api -p 5432:5432 postgres:alpine
