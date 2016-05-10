package navigation;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by novak on 2016.05.04..
 */
public class TimeResultImpl implements TimeResult {

    private LinkedList<Integer> resultPath;
    private double travelTimeOfResultPath;

    public TimeResultImpl(AlgorithmNode destinationNode) {
        this.travelTimeOfResultPath = destinationNode.getGraphNodeTime();
        this.resultPath = new LinkedList<>();
        AlgorithmNode currentNode = destinationNode;
        while(currentNode != null){
            resultPath.addFirst(currentNode.getGraphNode().getId());
            currentNode = currentNode.getParentNode();
        }
        System.out.println(travelTimeOfResultPath);
        System.out.println(resultPath);
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
