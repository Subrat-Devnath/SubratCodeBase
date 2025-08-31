#!/bin/bash

mvn -T 20 clean install -DskipTests

services=("api-gateway" "kafka" "service-registry" "user-mgmt" "workflow")

serviceCount=0

for service in "${services[@]}"; do
  echo "🚀 Building $service..."

  if [ "$serviceCount" -eq 0 ]; then
    cd "$service" 
  else
    cd "../$service"
  fi

  echo "Current directory: $(pwd)"
  docker build -t "$service" .
  serviceCount=$((serviceCount + 1))
  echo "Build success $service..."
done
