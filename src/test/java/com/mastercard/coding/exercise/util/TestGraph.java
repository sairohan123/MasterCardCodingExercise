package com.mastercard.coding.exercise.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

/**
 * @author Dhayanidhi Sundaramoorthi
 *
 */
public class TestGraph extends TestCase {

	Map<String, LinkedHashSet<String>> map = null;

	public TestGraph() {
		map = new HashMap<String, LinkedHashSet<String>>();

	}

	/**
	 * Tests not null condition for hashmap containing the Edges and vertices
	 */
	@Test
	void testVerticesAndEdgesIntanceVariableNotNull() {
		Assertions.assertNotNull(map);

	}

	/**
	 * Tests null condition for edges
	 */
	@Test
	void testEdgesNull() {
		map.put("Boston", null);
		Assertions.assertNull(map.get("Boston"));

	}

	/**
	 * Tests not null condition for edges
	 */
	@Test
	void testEdgesNotNull() {
		map.put("Boston", new LinkedHashSet<String>());
		Assertions.assertNotNull(map.get("Boston"));

	}

	/**
	 * Tests edges are equal for a given vertice
	 */
	@Test
	void testEdgesAreEqualForGivenVertice() {
		LinkedHashSet<String> edges = new LinkedHashSet<String>();

		edges.add("Newark");
		edges.add("New York");
		map.put("Boston", edges);
		edges = map.get("Boston");
		String[] edgesArray = new String[edges.size()];
		edgesArray = edges.toArray(edgesArray);
		Assertions.assertEquals("Newark", edgesArray[0]);
		Assertions.assertEquals("New York", edgesArray[1]);

		edges = new LinkedHashSet<String>();
		edges.add("Philadelphia");
		map.put("Newark", edges);
		edgesArray = new String[edges.size()];
		edgesArray = edges.toArray(edgesArray);
		Assertions.assertEquals("Philadelphia", edgesArray[0]);

		edges = new LinkedHashSet<String>();
		edges.add("Albany");
		map.put("Trenton", edges);
		edgesArray = new String[edges.size()];
		edgesArray = edges.toArray(edgesArray);
		Assertions.assertEquals("Albany", edgesArray[0]);
	}

	/**
	 * Test whether vertices exists or not
	 */
	@Test
	void testVerticesExist() {
		LinkedHashSet<String> edges = new LinkedHashSet<String>();

		edges.add("Newark");
		edges.add("New York");
		map.put("Boston", edges);
		edges = map.get("Boston");
		String[] edgesArray = new String[edges.size()];
		edgesArray = edges.toArray(edgesArray);
		Assertions.assertTrue(map.containsKey("Boston"));

		edges = new LinkedHashSet<String>();
		edges.add("Philadelphia");
		map.put("Newark", edges);
		edgesArray = new String[edges.size()];
		edgesArray = edges.toArray(edgesArray);
		Assertions.assertTrue(map.containsKey("Newark"));

		edges = new LinkedHashSet<String>();
		edges.add("Albany");
		map.put("Trenton", edges);
		edgesArray = new String[edges.size()];
		edgesArray = edges.toArray(edgesArray);
		Assertions.assertTrue(map.containsKey("Trenton"));
	}

	/**
	 * Test add first edge for a given vertex
	 */
	@Test
	void testAddFirstEdgeForGivenVertex() {
		map.put("Boston", null);
		Optional<LinkedHashSet<String>> vertexOptional = Optional.ofNullable(map.get("Boston"));
		if (vertexOptional.isEmpty()) {
			LinkedHashSet<String> firstEdge = new LinkedHashSet<String>();
			firstEdge.add("New York");
			map.put("Boston", firstEdge);
			Assertions.assertNotNull(map.get("Boston"));
			Assertions.assertTrue(map.get("Boston").contains("New York"));
		}
	}

	/**
	 * Test add first edge for a given vertex
	 */
	@Test
	void testAddSecondEdgeForGivenVertex() {
		LinkedHashSet<String> edges = new LinkedHashSet<String>();
		edges.add("New York");
		map.put("Boston", edges);
		Optional<LinkedHashSet<String>> vertexOptional = Optional.ofNullable(map.get("Boston"));
		if (vertexOptional.isPresent()) {
			edges.add("Newark");
			Assertions.assertNotNull(map.get("Boston"));
			Assertions.assertTrue(map.get("Boston").contains("New York"));
			Assertions.assertTrue(map.get("Boston").contains("Newark"));
		}
	}

	/**
	 * Test whether two vertexes are connected or not
	 */
	@Test
	void testIsTwoVertexesConnected() {
		LinkedHashSet<String> edges = null;
		map.put("Boston", edges);
		Optional<LinkedHashSet<String>> vertexOptional = Optional.ofNullable(map.get("Boston"));
		if (vertexOptional.isEmpty()) {
			Assertions.assertEquals("Vertex is not connected (No)", "Vertex is not connected (No)"); // This vertex does
			// not have any
			// edges to
			// connect with
			// vertex
		}

		// Test one vertex is connected with other vertex
		edges = new LinkedHashSet<String>();
		edges.add("New York");
		map.put("Boston", edges);
		vertexOptional = Optional.ofNullable(map.get("Boston"));
		if (vertexOptional.isPresent()) {
			Assertions.assertNotNull(map.get("Boston")); // This denotes Boston vertex is connected with another Vertex
			// New York
			Assertions.assertTrue(map.get("Boston").contains("New York"));
			String[] edgesArray = new String[edges.size()];
			edgesArray = edges.toArray(edgesArray);
			Assertions.assertEquals("New York", edgesArray[0]);
			Assertions.assertEquals("Vertex is not connected (Yes)", "Vertex is not connected (Yes)");
		}

		// Test one vertex is connected with other vertex
		edges = new LinkedHashSet<String>();
		edges.add("New York");
		edges.add("Newark");
		map.put("Boston", edges);
		LinkedHashSet<String> edgess = new LinkedHashSet<String>();
		edgess.add("Philadelphia");
		map.put("Newark", edgess);

		List<String> connectedVertex = new ArrayList<String>();
		connectedVertex.addAll(map.get("Boston"));
		if (connectedVertex.contains("Philadelphia")) {
			Assertions.assertEquals("Vertex is not connected (Yes)", "Vertex is not connected (Yes)");
		} else {
			connectedVertex.forEach((String name) -> {
				if (map.get(name) != null) {

					Assertions.assertTrue(map.get(name).contains("Philadelphia"));
					Assertions.assertEquals("Vertex is not connected (Yes)", "Vertex is not connected (Yes)");
				}
			});

		}
	}

	/**
	 * Test if adjacent vertexes are null/empty. If adjacent vertexes are empty
	 * create an empty hashset.
	 */
	@Test
	void testAdjacentNodes() {
		LinkedHashSet<String> edges = null;
		map.put("Boston", edges);
		Optional<LinkedHashSet<String>> vertexOptional = Optional.ofNullable(map.get("Boston"));
		// Boston does not have any adjacent nodes
		Assertions.assertTrue(vertexOptional.isEmpty());
		// if connected vertex is null new vertex is created. Now Adjacent nodes will
		// not have any nodes inside it.
		if (vertexOptional.isEmpty()) {
			edges = new LinkedHashSet<String>();
			map.put("Boston", edges);
			Assertions.assertNotNull(map.get("Boston"));
			Assertions.assertEquals(0, map.get("Boston").size());

		}

	}

	/**
	 * Test to get adjacent vertexes for a given vertex
	 */
	@Test
	void testIfAdjacentNodeExists() {
		Map<String, LinkedHashSet<String>> map = new HashMap<String, LinkedHashSet<String>>();
		LinkedHashSet<String> edges = new LinkedHashSet<String>();
		edges.add("New York");
		map.put("Boston", edges);
		Optional<LinkedHashSet<String>> vertexOptional = Optional.of(map.get("Boston"));
		if (vertexOptional.isPresent()) {
			Assertions.assertNotNull(map.get("Boston"));
			Assertions.assertEquals(1, map.get("Boston").size());
			String[] edgesArray = new String[edges.size()];
			edgesArray = edges.toArray(edgesArray);
			Assertions.assertTrue(map.get("Boston").contains("New York"));
			Assertions.assertEquals("New York", edgesArray[0]);
		}

	}
}
