#!/usr/bin/bash
docker rmi --force jmp.microservices.fundamental/discovery-service:latest
docker rmi --force jmp.microservices.fundamental/config-service:latest
docker rmi --force jmp.microservices.fundamental/api-gateway-service:latest
docker rmi --force jmp.microservices.fundamental/resource-processor:latest
docker rmi --force jmp.microservices.fundamental/song-service:latest
docker rmi --force jmp.microservices.fundamental/resource-service:latest