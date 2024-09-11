package com.spring.kafka.eventdrivendjikstra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final ObjectMapper objectMapper;

    public ConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "graph_topic", groupId = "group_id")
    public void consume(String message) {
        try {
            // Deserialize the JSON message to a Graph object
            Graph graph = objectMapper.readValue(message, Graph.class);

            // Convert flights list to an int[][] array for the algorithm
            int[][] flights = graph.getFlights().stream()
                    .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                    .toArray(int[][]::new);

            // Call the algorithm to find the cheapest price
            Solution solution = new Solution();
            int cheapestPrice = solution.findCheapestPrice(
                    graph.getN(),
                    flights,
                    graph.getSrc(),
                    graph.getDst(),
                    graph.getK()
            );

            System.out.println("The cheapest price is: " + cheapestPrice);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}