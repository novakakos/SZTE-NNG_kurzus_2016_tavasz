package navigation;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by novak on 2016.04.28..
 */
public class DistanceResultImpl implements DistanceResult{
    private LinkedList<Integer> resultPath;
    private double travelDistanceOfResultPath;

    public DistanceResultImpl(AlgorithmNode destinationNode) {
        this.travelDistanceOfResultPath = destinationNode.getGraphNodeDistance();
        this.resultPath = new LinkedList<>();
        AlgorithmNode currentNode = destinationNode;
        while(currentNode != null){
            resultPath.addFirst(currentNode.getGraphNode().getId());
            currentNode = currentNode.getParentNode();
        }
        System.out.println(travelDistanceOfResultPath);
        System.out.println(resultPath);
        //System.out.println(destinationNode.getGraphNodeTime());
    }

    @Override
    public List<Integer> getResultPath() {
        return resultPath;
    }

    @Override
    public double getTravelDistanceOfResultPath() {
        return travelDistanceOfResultPath;
    }
}
