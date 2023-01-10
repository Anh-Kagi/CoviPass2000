#!/bin/bash

cd "$(dirname "$0")" || exit 1

docker compose up db -d || exit 2

echo Copying sources...  || exit 3

mkdir -p ./build/ || exit 4

rsync -a --del ./backend/ ./build/backend/ || exit 5
rsync -a --del ./frontend/ ./build/frontend/ || exit 6
rsync -a --del ./.env ./build/.env || exit 7

echo Building frontend... || exit 8

cd ./build/frontend/ || exit 9

npm install && npm run build || exit 10

echo Integrating frontend into backend... || exit 11

cd .. || exit 12

mkdir -p ./backend/src/main/resources/static/ || exit 13

find ./backend/src/main/resources/static/ -type f -delete || exit 14
rsync -a --del ./frontend/dist/ ./backend/src/main/resources/static/ || exit 15

echo Running server... || exit 16

source .env || exit 17
export DB_DATABASE DB_USERNAME DB_PASSWORD APP_PORT || exit 18
export DB_HOST=localhost:5432 || exit 19

cd ./backend/ || exit 20

./gradlew bootRun --args="--spring.profiles.active=dev" || exit 21

echo Done || exit 22
