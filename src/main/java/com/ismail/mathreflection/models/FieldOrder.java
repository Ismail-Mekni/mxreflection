package com.ismail.mathreflection.models;

import com.ismail.mathreflection.exceptions.CycleFormulaDependencyException;
import com.ismail.mathreflection.utilities.GraphUtility;
import com.ismail.mathreflection.utilities.ReflectionUtility;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;
import java.util.stream.Collectors;

public class FieldOrder <T extends Formula> {

    private Queue<T> orderedFields;

    public FieldOrder(){

    }

    public FieldOrder(Map<String, T> fieldMap, Class clazz){
        this.orderedFields = sortFieldsByFormulaDependency(fieldMap, clazz);
    }

    private Queue<T> sortFieldsByFormulaDependency(Map<String, T> fields, Class clazz) {
        Graph<String, DefaultEdge> fieldRelationshipGraph = new DefaultDirectedGraph<>(DefaultEdge.class);

        ReflectionUtility.getClassFields(clazz).forEach(field -> fieldRelationshipGraph.addVertex(ReflectionUtility.getFieldName(field)));

        fields.values().forEach(field -> addGraphEdges(fieldRelationshipGraph, field));

        if(GraphUtility.checkGraphCycle(fieldRelationshipGraph))
            throw new CycleFormulaDependencyException(clazz.getName());

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
        field.getVariables().forEach(f -> graph.addEdge(field.fieldName, (String) f));
    }

    public Queue<T> getOrderedFields() {
        return orderedFields;
    }

    public void setOrderedFields(Queue<T> orderedFields) {
        this.orderedFields = orderedFields;
    }
}
