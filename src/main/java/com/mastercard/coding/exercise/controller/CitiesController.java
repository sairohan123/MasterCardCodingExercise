package com.mastercard.coding.exercise.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.coding.exercise.util.Graph;

@RestController
public class CitiesController {
	
	/**
	 * @param origin
	 * @param destination
	 * @return This method takes the source and destination and returns yes or no
	 */
	@GetMapping("/connected")
	public String pathExists(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
		Graph graph = null;
		try {
			graph = readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		LinkedList<String> visited = new LinkedList<String>();
		visited.add(origin);
		depthFirst(graph, visited, destination);
		return graph.isConnected(origin, destination);

	}

	/**
	 * @param graph
	 * @param visited This method traverses thenGraph object
	 */
	private void depthFirst(Graph graph, LinkedList<String> visited, String destination) {
		LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());
		for (String node : nodes) {
			if (visited.contains(node)) {
				continue;
			}
			if (node.equals(destination)) {
				visited.add(node);
				visited.removeLast();
				break;
			}
		}
		for (String node : nodes) {
			if (visited.contains(node) || node.equals(destination)) {
				continue;
			}
			visited.addLast(node);
			depthFirst(graph, visited, destination);
			visited.removeLast();
		}
	}

	/**
	 * @return
	 * @throws IOException This utility method reads cities.txt and creates graph
	 *                     object
	 */
	private Graph readFile() throws IOException {
		Graph graph = new Graph();
		List<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("cities.txt")));
		lines.forEach((String name) -> {
			String[] parts = name.split(",");
			graph.addEdge(parts[0].trim(), parts[1].trim());
			graph.addTwoWayVertex(parts[0].trim(), parts[1].trim());
		});
		return graph;

	}

}
