package navigation;

import util.NumberUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Implement your navigation algorithm here. This class will be instantiated
 * during the unit tests.
 */
public class AlgorithmImpl implements Algorithm {
    private GraphImpl graph;

    @Override
        public void preprocess(Graph graph) {
        // TODO Auto-generated method stub
        this.graph = (GraphImpl) graph;
        this.graph.getGraphNodes().stream().flatMap(graphNode -> graphNode.getEdges().stream())
                .forEach(graphEdge -> graphEdge.calculateDistTime());

        /*for (GraphNode graphNode : this.graph.getGraphNodes()) {
            for (GraphEdge graphEdge : graphNode.getEdges()) {
                graphEdge.calculateDistTime();
            }
        }*/
    }

    private DistanceTimeResultImpl findOptimalPath(final int startNodeId, final int destinationNodeId, final Comparator<AlgorithmNode> comparator) {
        GraphNode startNode = graph.getGraphNodes().get(startNodeId - NumberUtil.ONE);
        GraphNode destinationNode = graph.getGraphNodes().get(destinationNodeId - NumberUtil.ONE);
        PriorityQueue<AlgorithmNode> openSet = new PriorityQueue<>(comparator);
        ArrayList<AlgorithmNode> closedSet = new ArrayList<>();
        AlgorithmNode algorithmNode = new AlgorithmNode(startNode);
        openSet.add(algorithmNode);

        while (!openSet.isEmpty()) {
            AlgorithmNode q = openSet.poll();
            if (q.getGraphNode() == destinationNode) {
                return new DistanceTimeResultImpl(q);
            }
            for (GraphEdge edge : q.getGraphNode().getEdges()) {
                AlgorithmNode newNode = new AlgorithmNode(edge.getEndNode(), edge, q);
                AlgorithmNode searchedNode = searchForGraphNode(openSet, newNode);
                if (searchedNode != null) {
                    if (comparator.compare(newNode,searchedNode) < NumberUtil.ZERO){
                        openSet.remove(searchedNode);
                        openSet.add(newNode);
                    }
                } else {
                    searchedNode = searchForGraphNode(closedSet, newNode);
                    if (searchedNode == null) {
                        openSet.add(newNode);
                    }
                }
            }
            closedSet.add(q);
        }
        return null;
    }

    private AlgorithmNode searchForGraphNode(final Collection<AlgorithmNode> collection, final AlgorithmNode algorithmNode) {
        for (AlgorithmNode node : collection) {
            //TODO equals
            if (node.getGraphNode() == algorithmNode.getGraphNode()) {
                return node;
            }
        }
        return null;
    }

    @Override
    public DistanceResult findShortestPath(int startNodeId, int destinationNodeId) {
        // TODO Auto-generated method stub
        Comparator<AlgorithmNode> comparatorDistance = (node1,node2) ->
                Double.compare(node1.getGraphNodeDistance(), node2.getGraphNodeDistance());
        return findOptimalPath(startNodeId, destinationNodeId, comparatorDistance);
    }

    @Override
    public TimeResult findFastestPath(int startNodeId, int destinationNodeId) {
        // TODO Auto-generated method stub
        Comparator<AlgorithmNode> comparatorTime = (node1,node2) ->
                Double.compare(node1.getGraphNodeTime(), node2.getGraphNodeTime());
        return findOptimalPath(startNodeId, destinationNodeId, comparatorTime);
    }

    @Override
    public boolean hasPath(int startNodeId, int destinationNodeId) {
        // TODO Auto-generated method stub
        if (startNodeId == destinationNodeId) {
            return true;
        }
        GraphNode startNode = graph.getGraphNodes().get(startNodeId - NumberUtil.ONE);
        GraphNode destinationNode = graph.getGraphNodes().get(destinationNodeId - NumberUtil.ONE);
        ArrayList<GraphNode> openSet = new ArrayList<>();
        ArrayList<GraphNode> closedSet = new ArrayList<>();
        openSet.add(startNode);
        while (!openSet.isEmpty()) {
            GraphNode q = openSet.remove(NumberUtil.ZERO);
            for (GraphEdge graphEdge : q.getEdges()) {
                GraphNode neighbourNode = graphEdge.getEndNode();
                if (neighbourNode == destinationNode) {
                    return true;
                }
                if (!openSet.contains(neighbourNode) && !closedSet.contains(neighbourNode)) {
                    openSet.add(neighbourNode);
                }
            }
            closedSet.add(q);
        }
        return false;
    }
}
