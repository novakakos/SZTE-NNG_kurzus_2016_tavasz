package navigation;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by novak on 2016.05.07..
 */
public class DistanceTimeResultImpl implements DistanceResult, TimeResult {

    private final LinkedList<Integer> resultPath;
    private final double travelDistanceOfResultPath;
    private final double travelTimeOfResultPath;

    public DistanceTimeResultImpl(AlgorithmNode destinationNode) {
        this.travelDistanceOfResultPath = destinationNode.getGraphNodeDistance();
        this.travelTimeOfResultPath = destinationNode.getGraphNodeTime();
        this.resultPath = new LinkedList<>();
        AlgorithmNode currentNode = destinationNode;
        while(currentNode != null){
            resultPath.addFirst(currentNode.getGraphNode().getId());
            currentNode = currentNode.getParentNode();
        }
        System.out.println(travelDistanceOfResultPath);
        System.out.println(resultPath);
        System.out.println(travelTimeOfResultPath);
    }

    @Override
    public double getTravelDistanceOfResultPath() {
        return travelDistanceOfResultPath;
    }

    @Override
    public double getTravelTimeOfResultPath() {
        return travelTimeOfResultPath;
    }

    @Override
    public List<Integer> getResultPath() {
        return resultPath;
    }
}
