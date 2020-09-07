package com.ismail.mathreflection.utilities;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultEdge;

public class GraphUtility {

    public static boolean checkGraphCycle(Graph graph){
        CycleDetector<String, DefaultEdge> cycleDetector = new CycleDetector<>(graph);
        return cycleDetector.detectCycles();
    }
}
