package com.spring.kafka.eventdrivendjikstra;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("hello_topic", message);
    }

    public void sendGraphData(Graph graph) {
        try {
            // Convert graph object to JSON string
            String graphJson = objectMapper.writeValueAsString(graph);
            kafkaTemplate.send("graph_topic", graphJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}