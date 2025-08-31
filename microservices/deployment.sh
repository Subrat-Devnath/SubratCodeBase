#!/bin/bash

#Down all runnig containers
bash stop-containers.sh

echo "Starting all contailers"

#Start all runnig containers
docker compose -f docker-compose.yml up -d