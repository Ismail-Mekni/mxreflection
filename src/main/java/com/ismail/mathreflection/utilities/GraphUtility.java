package com.ismail.mathreflection.utilities;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;
import java.util.stream.Collectors;

public class GraphUtility {

    public static boolean checkGraphCycle(Graph graph) {
        CycleDetector<String, DefaultEdge> cycleDetector = new CycleDetector<>(graph);
        return cycleDetector.detectCycles();
    }

    public static List<String> getVerticesWithoutSuccessors(Graph<String, DefaultEdge> graph) {
        return graph.vertexSet().stream().filter(v -> !Graphs.vertexHasSuccessors(graph, v)).collect(Collectors.toList());
    }

    public static void removeVerticesWithoutSuccessors (Graph<String, DefaultEdge> graph){
        Graphs.removeVerticesAndPreserveConnectivity(graph, s -> !Graphs.vertexHasSuccessors(graph, s));
    }

}
