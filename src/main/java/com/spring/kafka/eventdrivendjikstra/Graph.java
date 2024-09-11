package com.spring.kafka.eventdrivendjikstra;

import java.util.List;

public class Graph {

    private int n;
    private List<List<Integer>> flights;
    private int src;
    private int dst;
    private int k;

    // Getters and Setters

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<List<Integer>> getFlights() {
        return flights;
    }

    public void setFlights(List<List<Integer>> flights) {
        this.flights = flights;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDst() {
        return dst;
    }

    public void setDst(int dst) {
        this.dst = dst;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
}
