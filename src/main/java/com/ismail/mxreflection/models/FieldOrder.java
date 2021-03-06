package com.ismail.mxreflection.models;

import com.ismail.mxreflection.exceptions.CycleExpressionDependencyException;
import com.ismail.mxreflection.utilities.GraphUtility;
import com.ismail.mxreflection.utilities.ReflectionUtility;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;
import java.util.stream.Collectors;

public class FieldOrder <T extends AbstractFunction> {

    private Queue<T> orderedFields;

    public FieldOrder(Map<String, T> fieldMap, Class clazz){
        this.orderedFields = sortFieldsByExpressionDependency(fieldMap, clazz);
    }

    private Queue<T> sortFieldsByExpressionDependency(Map<String, T> fields, Class clazz) {
        Graph<String, DefaultEdge> fieldRelationshipGraph = new DefaultDirectedGraph<>(DefaultEdge.class);

        ReflectionUtility.getClassFields(clazz).forEach(field -> fieldRelationshipGraph.addVertex(ReflectionUtility.getArgumentName(field)));

        fields.values().forEach(field -> addGraphEdges(fieldRelationshipGraph, field));

        if(GraphUtility.checkGraphCycle(fieldRelationshipGraph))
            throw new CycleExpressionDependencyException(clazz.getName());

        return sortFields(fieldRelationshipGraph, fields);
    }

    private Queue<T> sortFields(Graph<String, DefaultEdge> fieldGraph, Map<String, T> fields) {

        if(fieldGraph.vertexSet().isEmpty())
            return new LinkedList<>();

        Queue<T> orderedFields = GraphUtility.getVerticesWithoutSuccessors(fieldGraph).stream().filter(fields::containsKey)
                .map(fields::get).collect(Collectors.toCollection(LinkedList::new));

        GraphUtility.removeVerticesWithoutSuccessors(fieldGraph);

        orderedFields.addAll(sortFields(fieldGraph, fields));

        return orderedFields;
    }

    private void addGraphEdges(Graph<String, DefaultEdge> graph, T field) {
        field.getArguments().forEach(f -> graph.addEdge(field.argumentName, (String) f));
    }

    public Queue<T> getOrderedFields() {
        return orderedFields;
    }

}
