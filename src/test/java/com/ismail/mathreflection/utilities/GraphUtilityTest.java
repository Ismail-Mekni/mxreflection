package com.ismail.mathreflection.utilities;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GraphUtilityTest {

    @Test
    public void checkGraphWithCycleTest(){

        Graph graphWithCycle = new DefaultDirectedGraph(DefaultEdge.class);

        graphWithCycle.addVertex("Vertex_1");
        graphWithCycle.addVertex("Vertex_2");
        graphWithCycle.addVertex("Vertex_3");

        graphWithCycle.addEdge("Vertex_1", "Vertex_2");
        graphWithCycle.addEdge("Vertex_2", "Vertex_3");
        graphWithCycle.addEdge("Vertex_3", "Vertex_1");

        assertTrue(GraphUtility.checkGraphCycle(graphWithCycle));

    }

    @Test
    public void checkGraphWithoutCycle() {

        Graph graphWithoutCycle = new DefaultDirectedGraph(DefaultEdge.class);

        graphWithoutCycle.addVertex("Vertex_1");
        graphWithoutCycle.addVertex("Vertex_2");
        graphWithoutCycle.addVertex("Vertex_3");

        graphWithoutCycle.addEdge("Vertex_1", "Vertex_2");
        graphWithoutCycle.addEdge("Vertex_2", "Vertex_3");

        assertFalse(GraphUtility.checkGraphCycle(graphWithoutCycle));
    }

    @Test
    public void getVerticesWithoutSuccessorsTest(){
        Graph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        graph.addVertex("Vertex_1");
        graph.addVertex("Vertex_2");
        graph.addVertex("Vertex_3");
        graph.addVertex("Vertex_4");

        graph.addEdge("Vertex_1", "Vertex_2");
        graph.addEdge("Vertex_1", "Vertex_3");
        graph.addEdge("Vertex_2", "Vertex_4");
        graph.addEdge("Vertex_3", "Vertex_4");

        List<String> vertices = GraphUtility.getVerticesWithoutSuccessors(graph);

        assertEquals(1, vertices.size());
        assertEquals("Vertex_4", vertices.get(0));
    }

    @Test
    public void removeVerticesWithoutSuccessors(){

    }
}
