package com.ismail.mathreflection.models;

import com.ismail.mathreflection.exceptions.CycleFormulaDependencyException;
import com.ismail.mathreflection.utilities.GraphUtility;
import com.ismail.mathreflection.utilities.ReflectionUtility;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class FieldOrder <T extends Formula> {

    private Queue<T> orderedFields;

    public FieldOrder(){

    }

    public FieldOrder(Map<String, T> fieldMap, Class clazz){
        sortFieldsByFormulaDependency(fieldMap.values(), clazz);
    }

    private Queue<T> sortFieldsByFormulaDependency(Collection<T> fields, Class clazz) {
        Graph<String, DefaultEdge> fieldRelationshipGraph = new DefaultDirectedGraph<>(DefaultEdge.class);

        ReflectionUtility.getClassFields(clazz).forEach(field -> fieldRelationshipGraph.addVertex(ReflectionUtility.getFieldName(field)));

        fields.forEach(field -> addGraphEdges(fieldRelationshipGraph, field));

        if(GraphUtility.checkGraphCycle(fieldRelationshipGraph))
            throw new CycleFormulaDependencyException(clazz.getName());

        return sortFields(fieldRelationshipGraph);
    }

    private Queue<T> sortFields(Graph<String, DefaultEdge> fielGraph) {
        //TODO
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
