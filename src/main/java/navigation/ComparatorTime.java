package navigation;

import java.util.Comparator;

/**
 * Created by novak on 2016.05.04..
 */
public class ComparatorTime implements Comparator<AlgorithmNode> {

    @Override
    public int compare(AlgorithmNode node1, AlgorithmNode node2) {
        return Double.compare(node1.getGraphNodeTime(), node2.getGraphNodeTime());
    }
}
