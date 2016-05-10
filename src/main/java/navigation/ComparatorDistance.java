package navigation;

import java.util.Comparator;

/**
 * Created by novak on 2016.04.28..
 */
public class ComparatorDistance implements Comparator<AlgorithmNode> {

    @Override
    public int compare(AlgorithmNode node1, AlgorithmNode node2) {
        return Double.compare(node1.getGraphNodeDistance(), node2.getGraphNodeDistance());

        /*if (node1.getGraphNodeDistance() > node2.getGraphNodeDistance()) {
            return 1;
        }
        if (node1.getGraphNodeDistance() < node2.getGraphNodeDistance()) {
            return -1;
        }
        return 0;*/
    }
}
