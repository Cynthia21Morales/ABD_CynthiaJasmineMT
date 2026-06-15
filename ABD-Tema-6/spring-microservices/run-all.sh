#!/bin/bash

echo "Starting Eureka..."
cd eureka-server
mvn spring-boot:run &
sleep 15
cd ..

echo "Starting Config..."
cd config-server
mvn spring-boot:run &
sleep 15
cd ..

echo "Starting Courses..."
cd courses-service
mvn spring-boot:run &
sleep 10
cd ..

echo "Starting Students..."
cd students-service
mvn spring-boot:run &
sleep 10
cd ..

echo "Starting Gateway..."
cd gateway-service
mvn spring-boot:run &
cd ..