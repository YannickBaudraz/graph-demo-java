package com.example.graphdemo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GraphDemoApplicationTests {

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
	void depthFirstTraversal() {
		Set<String> traversal = GraphDemoApplication.depthFirstTraversal(graph, "Bob");
		assertThat(traversal).containsExactlyInAnyOrder("Bob", "Alice", "Maria", "Mark", "Rob");
	}

	@Test
	void breadthFirstTraversal() {
		Set<String> traversal = GraphDemoApplication.breadthFirstTraversal(graph, "Bob");
		assertThat(traversal).containsExactly("Bob", "Alice", "Rob", "Mark", "Maria");
	}

}
