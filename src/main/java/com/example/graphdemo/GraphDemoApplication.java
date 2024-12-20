package com.example.graphdemo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class GraphDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphDemoApplication.class, args);

		processWithManualGraph();
		processWithJgrapht();
	}

	private static void processWithManualGraph() {
		Graph graph = createGraph();
		log.info("Manual: Depth First Traversal: {}, Breadth First Traversal: {}",
				depthFirstTraversal(graph, "Bob"), breadthFirstTraversal(graph, "Bob"));
	}

	static Graph createGraph() {
		var graph = new Graph();

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

		return graph;
	}

	static Set<String> depthFirstTraversal(Graph graph, String root) {
		Set<String> visited = new LinkedHashSet<>();
		Deque<String> stack = new ArrayDeque<>(); // Used as a stack

		stack.push(root);
		while (!stack.isEmpty()) {
			String label = stack.pop();
			if (!visited.contains(label)) {
				visited.add(label);
				for (Vertex vertex : graph.getAdjVertices(label)) {
					stack.push(vertex.label());
				}
			}
		}

		return visited;
	}

	static Set<String> breadthFirstTraversal(Graph graph, String root) {
		Set<String> visited = new LinkedHashSet<>();
		Queue<String> queue = new LinkedList<>();

		queue.add(root);
		visited.add(root);
		while (!queue.isEmpty()) {
			String label = queue.poll();
			for (Vertex vertex : graph.getAdjVertices(label)) {
				if (!visited.contains(vertex.label())) {
					visited.add(vertex.label());
					queue.add(vertex.label());
				}
			}
		}

		return visited;
	}

	private static void processWithJgrapht() {
		org.jgrapht.Graph<String, DefaultEdge> graph = createJgraphtGraph();
		log.info("JraphT: Depth First Traversal: {}, Breadth First Traversal: {}",
				depthFirstTraversal(graph, "Bob"), breadthFirstTraversal(graph, "Bob"));
	}

	private static org.jgrapht.Graph<String, DefaultEdge> createJgraphtGraph() {
		org.jgrapht.Graph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

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

		return graph;
	}

	private static Set<String> depthFirstTraversal(org.jgrapht.Graph<String, DefaultEdge> graph, String root) {
		Set<String> visited = new LinkedHashSet<>();
		DepthFirstIterator<String, DefaultEdge> iterator = new DepthFirstIterator<>(graph, root);

		while (iterator.hasNext()) {
			visited.add(iterator.next());
		}

		return visited;
	}

	private static Set<String> breadthFirstTraversal(org.jgrapht.Graph<String, DefaultEdge> graph, String root) {
		Set<String> visited = new LinkedHashSet<>();
		BreadthFirstIterator<String, DefaultEdge> iterator = new BreadthFirstIterator<>(graph, root);

		while (iterator.hasNext()) {
			visited.add(iterator.next());
		}

		return visited;
	}
}
