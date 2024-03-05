#!/usr/bin/bash
cd docker-compose
docker compose --env-file .env up -d
#docker compose config
cd ../