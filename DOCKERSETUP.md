# Docker Setup for Concurrent Flight Reservation System

## Prerequisites
- Docker installed
- Docker Compose installed

## Building and Running

### Option 1: Using Docker Compose (Recommended)
```bash
# Build and run the container
docker-compose up --build

# Run in background
docker-compose up -d --build

# View logs
docker-compose logs -f

# Stop the container
docker-compose down
```

### Option 2: Using Docker directly
```bash
# Build the image
docker build -t flight-reservation-system .

# Run the container (one time)
docker run -p 8080:8080 -v $(pwd)/LOG.txt:/app/LOG.txt flight-reservation-system

# Run interactively to see output
docker run -it -p 8080:8080 -v $(pwd)/LOG.txt:/app/LOG.txt flight-reservation-system
```

## Useful Commands

### Container Management
```bash
# View running containers
docker ps

# View all containers (including stopped)
docker ps -a

# Enter the container (if running)
docker exec -it concurrent-flight-reservation bash

# View container logs
docker logs concurrent-flight-reservation

# Stop the container
docker stop concurrent-flight-reservation
```

## Application Execution
This application appears to be a simulation system that:
- Runs once and terminates
- Generates logs in `LOG.txt`
- Simulates concurrent flight reservation processes

**Note**: If the application terminates quickly, this is normal behavior. The container will stop when execution finishes.

## Troubleshooting

### Issue: "Permission denied" on gradlew
```bash
# Give execution permissions
chmod +x gradlew
```

### Issue: Port already in use
```bash
# Change the port in docker-compose.yml
ports:
  - "8081:8080" # Use port 8081 instead of 8080
```

### View complete application output
```bash
# Run interactively
docker run -it --rm -v $(pwd)/LOG.txt:/app/LOG.txt flight-reservation-system
```