package com.mastercard.coding.exercise.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class Graph {
	private Map<String, LinkedHashSet<String>> map = new HashMap<String, LinkedHashSet<String>>();

	/**
	 * @param node1
	 * @param node2 This method adds edge
	 */
	public void addEdge(String node1, String node2) {
		LinkedHashSet<String> adjacent = map.get(node1);
		if (adjacent == null) {
			adjacent = new LinkedHashSet<String>();
			map.put(node1, adjacent);
		}
		adjacent.add(node2);
	}

	/**
	 * @param node1
	 * @param node2 This method creates edges
	 */
	public void addTwoWayVertex(String node1, String node2) {
		addEdge(node1, node2);
		addEdge(node2, node1);
	}

	/**
	 * @param node1
	 * @param node2
	 * @return This method reurns whether the origin and destination is connected or
	 *         not.
	 */
	public String isConnected(String node1, String node2) {
		LinkedHashSet<String> adjacent = map.get(node1);
		Optional<LinkedHashSet<String>> optional = Optional.ofNullable(map.get(node1));
		if (optional.isEmpty()) {
			return "No";
		}

		if (optional.isPresent()) {
			for (String node : adjacent) {
				if (map.get(node).contains(node2)) {
					return "Yes";
				}
			}
		}

		return adjacent.contains(node2) ? "Yes" : "No";

	}

	/**
	 * @param last
	 * @return This method gets adjacent nodes
	 */
	public LinkedList<String> adjacentNodes(String last) {
		LinkedHashSet<String> adjacent = map.get(last);
		if (adjacent == null) {
			return new LinkedList<String>();
		}
		return new LinkedList<String>(adjacent);
	}
}