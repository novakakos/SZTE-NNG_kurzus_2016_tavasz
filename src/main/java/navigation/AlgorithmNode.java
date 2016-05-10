package navigation;

import util.NumberUtil;

/**
 * Created by novak on 2016.04.28..
 */
public class AlgorithmNode  {
    private final GraphNode graphNode;
    private final double graphNodeDistance;
    private final double graphNodeTime;
    private AlgorithmNode parentNode;

    public AlgorithmNode(GraphNode graphNode) {
        this.graphNode = graphNode;
        this.graphNodeDistance = NumberUtil.ZEROPOINTZERO;
        this.graphNodeTime = NumberUtil.ZEROPOINTZERO;
    }

    public AlgorithmNode(GraphNode graphNode, GraphEdge edgeFromParent, AlgorithmNode parentAlgorithmNode) {
        this.parentNode = parentAlgorithmNode;
        this.graphNode = graphNode;
        this.graphNodeDistance = parentAlgorithmNode.getGraphNodeDistance() + edgeFromParent.getDistance();
        this.graphNodeTime = parentAlgorithmNode.getGraphNodeTime() + edgeFromParent.getTime();
    }

    public AlgorithmNode getParentNode() {
        return parentNode;
    }

    public GraphNode getGraphNode() {
        return graphNode;
    }

    public double getGraphNodeDistance() {
        return graphNodeDistance;
    }

    public double getGraphNodeTime() {
        return graphNodeTime;
    }

}
