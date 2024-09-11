package com.spring.kafka.eventdrivendjikstra;

import java.util.*;

class Flight {
    int city;
    int cost;
    int stop;

    Flight(int city, int cost, int stop) {
        this.city = city;
        this.cost = cost;
        this.stop = stop;
    }
}

public class Solution {
    public Map<Integer, List<Flight>> graph;

    public Solution() {
        graph = new HashMap<>();
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        buildGraph(flights);

        PriorityQueue<Flight> pq = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        pq.offer(new Flight(src, 0, 0));
        int[] stop = new int[n];
        Arrays.fill(stop, Integer.MAX_VALUE);

        while (!pq.isEmpty()) {
            Flight currentFlight = pq.poll();
            int currentCity = currentFlight.city;
            int currentCost = currentFlight.cost;
            int currentStop = currentFlight.stop;

            if (currentCity == dst) {
                return currentCost;
            }

            if (currentStop > k || stop[currentCity] < currentStop || !graph.containsKey(currentCity)) {
                continue;
            }

            stop[currentCity] = currentStop;

            for (Flight neighbor : graph.get(currentCity)) {
                int neighborCity = neighbor.city;
                int neighborCost = neighbor.cost;
                pq.offer(new Flight(neighborCity, currentCost + neighborCost, currentStop + 1));
            }
        }
        return -1;
    }

    public void buildGraph(int[][] flights) {
        for (int[] flight : flights) {
            int source = flight[0];
            int destination = flight[1];
            int cost = flight[2];
            graph.putIfAbsent(source, new ArrayList<>());
            graph.get(source).add(new Flight(destination, cost, 0));
        }
    }
}