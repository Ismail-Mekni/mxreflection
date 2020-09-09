package com.ismail.mathreflection.utilities;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public class GraphUtility {

    public static boolean checkGraphCycle(Graph graph){
        CycleDetector<String, DefaultEdge> cycleDetector = new CycleDetector<>(graph);
        return cycleDetector.detectCycles();
    }

    public static List<String> getVerticesWithoutChildren(Graph graph){
        //TODO
    }
}
