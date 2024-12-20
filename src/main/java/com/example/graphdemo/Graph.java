package com.example.graphdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record Graph(Map<Vertex, List<Vertex>> adjacencyVertices) {

	public Graph() {
		this(new HashMap<>());
	}

	public void addVertex(String label) {
		adjacencyVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
	}

	public void removeVertex(String label) {
		var vertex = new Vertex(label);
		adjacencyVertices.values().forEach(e -> e.remove(vertex));
		adjacencyVertices.remove(vertex);
	}

	public void addEdge(String label1, String label2) {
		var v1 = new Vertex(label1);
		var v2 = new Vertex(label2);

		adjacencyVertices.get(v1).add(v2);
		adjacencyVertices.get(v2).add(v1);
	}

	public void removeEdge(String label1, String label2) {
		Vertex v1 = new Vertex(label1);
		Vertex v2 = new Vertex(label2);

		List<Vertex> vertices1 = adjacencyVertices.get(v1);
		List<Vertex> vertices2 = adjacencyVertices.get(v2);

		if (vertices1 != null) {
			vertices1.remove(v2);
		}

		if (vertices2 != null) {
			vertices2.remove(v1);
		}
	}

	List<Vertex> getAdjVertices(String label) {
		return adjacencyVertices.get(new Vertex(label));
	}

}
