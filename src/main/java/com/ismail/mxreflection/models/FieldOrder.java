package com.ismail.mxreflection.models;

import com.ismail.mxreflection.exceptions.CycleExpressionDependencyException;
import com.ismail.mxreflection.utilities.GraphUtility;
import com.ismail.mxreflection.core.ReflectionBean;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;
import java.util.stream.Collectors;

public class FieldOrder <T extends AbstractFunction> {

    private Queue<T> orderedFields;

    public FieldOrder(Map<String, T> fieldMap, ReflectionBean reflectionBean){
        this.orderedFields = sortFieldsByExpressionDependency(fieldMap, reflectionBean);
    }

    private Queue<T> sortFieldsByExpressionDependency(Map<String, T> fields, ReflectionBean reflectionBean) {
        Graph<String, DefaultEdge> fieldRelationshipGraph = new DefaultDirectedGraph<>(DefaultEdge.class);

        reflectionBean.getFields()
                .forEach(field -> fieldRelationshipGraph.addVertex(reflectionBean.getArgumentName(field)));

        fields.values().forEach(field -> addGraphEdges(fieldRelationshipGraph, field));

        if(GraphUtility.checkGraphCycle(fieldRelationshipGraph))
            throw new CycleExpressionDependencyException(reflectionBean.getClazz().getName());

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
