# Kafka Graph Processing Application

## Overview

This application demonstrates a simple Kafka-based system for processing graph data to find the cheapest flight path using Dijkstra's algorithm. The producer application sends graph data to a Kafka topic, and the consumer application reads this data, processes it, and calculates the cheapest flight price.

## Project Structure

- `ProducerController.java`: Handles HTTP requests and sends graph data to Kafka.
- `ProducerService.java`: Sends messages to Kafka using `KafkaTemplate`.
- `ConsumerService.java`: Consumes messages from Kafka and processes graph data.
- `Solution.java`: Contains the logic for finding the cheapest flight path using Dijkstra's algorithm.
- `KafkaProducerConfig.java`: Kafka producer configuration.
- `KafkaConsumerConfig.java`: Kafka consumer configuration.
- `ObjectMapperConfig.java`: Configures Jackson's `ObjectMapper`.

## Endpoints

### POST /graph

**Description**: Sends graph data to Kafka for processing.

**Request Body**:
```json
{
  "n": 7,
  "flights": [[0,1,100],[0,2,150],[1,3,100],[2,3,50],[3,4,100],[4,5,50],[5,6,50],[0,6,600]],
  "src": 0,
  "dst": 6,
  "k": 4
}
```

## Response

**Graph data sent to Kafka**

## Kafka Topics

- **graph_topic**: The Kafka topic used to send graph data.

## Configuration

### Kafka Configuration

- **Bootstrap Servers**: `localhost:9092`
- **Consumer Group ID**: `group_id`

### Docker Setup

Ensure that Kafka and Zookeeper are running. If using Docker Compose, the `docker-compose.yml` should configure multiple Kafka brokers to support a replication factor of 3.

### Application Properties

`application.properties` should include Kafka producer and consumer settings.

## Running the Application

1. **Start Kafka and Zookeeper**: Ensure Kafka and Zookeeper are up and running.
2. **Build the Spring Boot Application and Run**:
   ```
    java -jar target/Event-driven-djikstra-0.0.1-SNAPSHOT.jar
   ```

## Dependencies

- **Spring Boot**
- **Kafka**
- **Jackson** (for JSON processing)

## Docker Setup

To set up Kafka and Zookeeper using Docker Compose, use the following `docker-compose.yml` configuration:

```yaml
version: '3'
services:
  zookeeper:
    image: bitnami/zookeeper:3.8.1
    container_name: zookeeper
    ports:
      - "2181:2181"
    environment:
      - ZOO_ENABLE_AUTH=no
      - ALLOW_ANONYMOUS_LOGIN=yes

  kafka:
    image: wurstmeister/kafka:latest
    container_name: kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT
      KAFKA_AUTO_CREATE_TOPICS_ENABLE: 'true'
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
```

To compose the docker file
```
  docker-compose up --build
```
