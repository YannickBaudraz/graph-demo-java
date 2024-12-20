package com.example.graphdemo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {

	private Graph graph;

	@BeforeEach
	public void setUp() {
		graph = new Graph();
		graph.addVertex("Bob");
		graph.addVertex("Alice");
		graph.addVertex("Mark");
		graph.addVertex("Rob");
		graph.addVertex("Maria");
		graph.addEdge("Bob", "Alice");
		graph.addEdge("Bob", "Rob");
		graph.addEdge("Alice", "Mark");
		graph.addEdge("Rob", "Mark");
		graph.addEdge("Alice", "Maria");
		graph.addEdge("Rob", "Maria");
	}

	@Test
	void addVertex() {
		graph.addVertex("NewVertex");
		assertThat(graph.getAdjVertices("NewVertex")).isEmpty();
	}

	@Test
	void removeVertex() {
		graph.removeVertex("Alice");
		assertThat(graph.getAdjVertices("Alice")).isNull();
		assertThat(graph.getAdjVertices("Bob")).doesNotContain(new Vertex("Alice"));
	}

	@Test
	void addEdge() {
		graph.addEdge("Mark", "Maria");
		assertThat(graph.getAdjVertices("Mark")).contains(new Vertex("Maria"));
		assertThat(graph.getAdjVertices("Maria")).contains(new Vertex("Mark"));
	}

	@Test
	void removeEdge() {
		graph.removeEdge("Bob", "Alice");
		assertThat(graph.getAdjVertices("Bob")).doesNotContain(new Vertex("Alice"));
		assertThat(graph.getAdjVertices("Alice")).doesNotContain(new Vertex("Bob"));
	}

	@Test
	void getAdjVertices() {
		List<Vertex> adjVertices = graph.getAdjVertices("Bob");
		assertThat(adjVertices).contains(new Vertex("Alice"), new Vertex("Rob"));
	}

}