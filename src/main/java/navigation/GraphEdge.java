package navigation;

/**
 * Created by novak on 2016.04.19..
 */
public class GraphEdge {
    private int id;
    private GraphNode startNode;
    private GraphNode endNode;
    private int averageSpeed;
    private double distance;
    private double time;

    public GraphEdge() {
    }

    private void calculateDistance(){
        double x = startNode.getxCoord() - endNode.getxCoord();
        double y = startNode.getyCoord() - endNode.getyCoord();
        this.distance = Math.sqrt((x*x) + (y*y));
    }

    private void calculateTime(){
        this.time = this.distance / this.averageSpeed;
        //this.time = this.averageSpeed;
    }

    public void calculateDistTime(){
        calculateDistance();
        calculateTime();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartNode(GraphNode startNode) {
        this.startNode = startNode;
    }

    public void setEndNode(GraphNode endNode) {
        this.endNode = endNode;
    }

    public void setAverageSpeed(int averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public GraphNode getStartNode() {
        return startNode;
    }

    public GraphNode getEndNode() {
        return endNode;
    }

    public double getDistance() {
        return distance;
    }

    public double getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "\n" +
                "\tGraphEdge{" +
                "id=" + id +
                ", startNode=" + startNode.getId() +
                ", endNode=" + endNode.getId() +
                ", averageSpeed=" + averageSpeed +
                ", distance=" + distance +
                ", time=" + time +
                "}";
    }

}
