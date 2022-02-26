// --== CS400 File Header Information ==--
// Name:Yating Tian
// Email:ytian83@wisc.edu
// Team: MF
// TA: Harit
// Lecturer: Gary
// Notes to Grader: NA

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

	private CS400Graph<Integer> graph;

	@BeforeEach
	/**
	 * Instantiate graph from last week's shortest path activity.
	 */
	public void createGraph() {
		graph = new CS400Graph<>();
		// insert verticies 0-9
		for (int i = 0; i < 10; i++)
			graph.insertVertex(i);
//        graph.insertVertex(15);
		// insert edges from Week 08. Dijkstra's Activity
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 7, 2);
		graph.insertEdge(1, 8, 4);
		graph.insertEdge(2, 4, 4);
		graph.insertEdge(2, 6, 3);
		graph.insertEdge(3, 1, 6);
		graph.insertEdge(3, 7, 2);
		graph.insertEdge(4, 5, 4);
		graph.insertEdge(5, 0, 2);
		graph.insertEdge(5, 1, 4);
		graph.insertEdge(5, 9, 1);
		graph.insertEdge(6, 3, 1);
		graph.insertEdge(7, 0, 3);
		graph.insertEdge(7, 6, 1);
		graph.insertEdge(8, 9, 3);
		graph.insertEdge(9, 4, 5);
	}

	/**
	 * Checks the distance/total weight cost from the vertex labelled 0 to 8 (should
	 * be 15), and from the vertex labelled 9 to 8 (should be 17).
	 */
	@Test
	public void providedTestToCheckPathCosts() {
		//use assertTrue() method to check the distance 
		assertTrue(graph.getPathCost(0, 8) == 15);
		assertTrue(graph.getPathCost(9, 8) == 17);
	}

	/**
	 * Checks the ordered sequence of data within vertices from the vertex labelled
	 * 0 to 8, and from the vertex labelled 9 to 8.
	 */
	@Test
	public void providedTestToCheckPathContents() {
		//use assertTrue() method to check all the vertices in the path
		assertTrue(graph.shortestPath(0, 8).toString().equals("[0, 2, 6, 3, 1, 8]"));
		assertTrue(graph.shortestPath(9, 8).toString().equals("[9, 4, 5, 1, 8]"));
	}

	/**
	 * Checks the distance/total weight cost from the vertex labelled 5 to 8, 8 is
	 * the longest distance from 5, the distance should be 8. In the Module 08:
	 * Dijkstra's Shortest Path Activity.
	 */
	@Test
	public void testToCheckPathCosts5To8() {
		//use assertTrue() method to check the distance from 5 to 8 
		assertTrue(graph.getPathCost(5, 8) == 8);
	}

	/**
	 * Checks the ordered sequence of data within vertices from the vertex labelled
	 * 5 to 8, 8 is the longest distance from 5, the path content should be [5,1,8]
	 * In the Module 08: Dijkstra's Shortest Path Activity.
	 */
	@Test
	public void testToCheckPathContents5To8() {
		//use assertTrue() method to check all the vertices in the path from 5 to 8
		assertTrue(graph.shortestPath(5, 8).toString().equals("[5, 1, 8]"));
	}

	/**
	 * Checks the distance/total weight cost from the vertex labelled 5 to all the
	 * other vertex
	 */
	@Test
	public void testToCheckPathCosts() {
		//use assertTrue() method to check the distance from 5 to all other vertices
		assertTrue(graph.getPathCost(5, 9) == 1);
		assertTrue(graph.getPathCost(5, 4) == 6);
		assertTrue(graph.getPathCost(5, 1) == 4);
		assertTrue(graph.getPathCost(5, 7) == 6);
		assertTrue(graph.getPathCost(5, 0) == 2);
		assertTrue(graph.getPathCost(5, 2) == 3);
		assertTrue(graph.getPathCost(5, 6) == 6);
		assertTrue(graph.getPathCost(5, 3) == 7);
	}

	/**
	 * Checks the ordered sequence of data within vertices from the vertex labelled
	 * 5 to all the other vertex
	 */
	@Test
	public void testToCheckPathContents() {
		//use assertTrue() method to check the path from 5 to all other vertices
		assertTrue(graph.shortestPath(5, 9).toString().equals("[5, 9]"));
		assertTrue(graph.shortestPath(5, 4).toString().equals("[5, 9, 4]"));
		assertTrue(graph.shortestPath(5, 1).toString().equals("[5, 1]"));
		assertTrue(graph.shortestPath(5, 7).toString().equals("[5, 1, 7]"));
		assertTrue(graph.shortestPath(5, 0).toString().equals("[5, 0]"));
		assertTrue(graph.shortestPath(5, 2).toString().equals("[5, 0, 2]"));
		assertTrue(graph.shortestPath(5, 6).toString().equals("[5, 0, 2, 6]"));
		assertTrue(graph.shortestPath(5, 3).toString().equals("[5, 0, 2, 6, 3]"));

	}

}
