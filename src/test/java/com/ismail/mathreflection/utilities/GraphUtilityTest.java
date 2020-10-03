package com.ismail.mathreflection.utilities;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphUtilityTest {

    @Test
    public void checkGraphCycleTest(){

        Graph graphWithCycle = new DefaultDirectedGraph(DefaultEdge.class);

        graphWithCycle.addVertex("Vertex_1");
        graphWithCycle.addVertex("Vertex_2");
        graphWithCycle.addVertex("Vertex_3");

        graphWithCycle.addEdge("Vertex_1", "Vertex_2");
        graphWithCycle.addEdge("Vertex_2", "Vertex_3");
        graphWithCycle.addEdge("Vertex_3", "Vertex_1");

        assertTrue(GraphUtility.checkGraphCycle(graphWithCycle));

        Graph graphWithoutCycle = new DefaultDirectedGraph(DefaultEdge.class);

        graphWithCycle.addVertex("Vertex_1");
        graphWithCycle.addVertex("Vertex_2");
        graphWithCycle.addVertex("Vertex_3");

        graphWithCycle.addEdge("Vertex_1", "Vertex_2");
        graphWithCycle.addEdge("Vertex_2", "Vertex_3");

        assertFalse(GraphUtility.checkGraphCycle(graphWithoutCycle));
    }

    @Test
    public void getVerticesWithoutSuccessorsTest(){

    }

    @Test
    public void removeVerticesWithoutSuccessors(){

    }
}
