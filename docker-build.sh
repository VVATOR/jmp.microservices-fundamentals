#!/usr/bin/bash
#docker build --no-cache -t apollo-release-docker.wolterskluwer.io/apollo/apollo-csb-worker-wkit:<version> .
docker build --force-rm --no-cache -t jmp.microservices.fundamental/resource-processor:latest ./resource-processor/
docker build --force-rm --no-cache -t jmp.microservices.fundamental/song-service:latest ./song-service-parent/song-service/
docker build --force-rm --no-cache -t jmp.microservices.fundamental/resource-service:latest ./resource-service-parent/resource-service/
