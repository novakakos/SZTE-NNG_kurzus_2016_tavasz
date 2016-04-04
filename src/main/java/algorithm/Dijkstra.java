package algorithm;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.Vertex;

import java.util.*;

/**
 * @author markfodor on 2016.02.25.
 */
public class Dijkstra implements IAlgorithm {
    private final Graph graph;
    private final Set<Vertex> settledNodes;
    private final Set<Vertex> unSettledNodes;
    private final Map<Vertex, Vertex> predecessors;
    private final Map<Vertex, Integer> distances;

    public Dijkstra(Graph graph) {
        this.graph = graph;
        this.settledNodes = new HashSet<>();
        this.unSettledNodes = new HashSet<>();
        this.distances = new HashMap<>();
        this.predecessors = new HashMap<>();
    }

    @Override
    public void execute(Vertex source) {
        distances.put(source, 0);
        unSettledNodes.add(source);

        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findClosestNeighbor(node);
        }
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;

        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                minimum = vertex;
            }
        }

        return minimum;
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distances.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    public Deque<Vertex> getPath(Vertex target) {
        Deque<Vertex> path = new ArrayDeque<>();
        Vertex step = target;

        if (predecessors.get(step) == null) {
            return path;
        }

        path.addFirst(step);

        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.addFirst(step);
        }

        return path;
    }

    public void printPath(Vertex target) {
        StringJoiner joiner = new StringJoiner(" ");
        joiner.add("[");

        for (Vertex vertex : getPath(target)) {
            String name = (String) vertex.getProperty("name");
            joiner.add(name);
        }

        joiner.add("]")
                .add("Distance:")
                .add(Integer.toString(getShortestDistance(target)));

        System.out.println(joiner.toString());
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    private boolean areReachableNeighbors(Edge edge, Vertex node, Vertex target){
        return edge.getOutVertex().equals(node)
                && edge.getInVertex().equals(target);
    }

    public int getDistance(Vertex node, Vertex target) {
        for (Edge edge : graph.getEdges()) {
            if (areReachableNeighbors(edge, node, target)) {

                return (int) edge.getProperty("weight");
            }
        }

        throw new IllegalStateException("Should not happen");
    }


    private void findClosestNeighbor(Vertex node) {
        List<Vertex> neighbors = getUnsettledNeighbors(node);

        for (Vertex vertex : neighbors) {
            int distance = getShortestDistance(node) + getDistance(node, vertex);

            if (getShortestDistance(vertex) > distance) {
                distances.put(vertex, distance);
                predecessors.put(vertex, node);
                unSettledNodes.add(vertex);
            }
        }

    }

    private List<Vertex> getUnsettledNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<>();

        for (Edge edge : graph.getEdges()) {
            if (edge.getOutVertex().equals(node)
                    && !isSettled(edge.getInVertex())) {
                neighbors.add(edge.getInVertex());
            }
        }

        return neighbors;
    }
}
