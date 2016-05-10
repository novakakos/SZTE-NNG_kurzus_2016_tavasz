package navigation;

import util.NumberUtil;

import java.util.ArrayList;

/**
 * Created by novak on 2016.04.17..
 */
public class GraphNode  {
    private int id;
    private double yCoord;
    private double xCoord;
    private final ArrayList<GraphEdge> edges;

    public GraphNode() {
        this.id = NumberUtil.ZERO;
        this.yCoord = NumberUtil.ZEROPOINTZERO;
        this.xCoord = NumberUtil.ZEROPOINTZERO;
        this.edges = new ArrayList<>();
    }

    public void addEdge(GraphEdge edge){
        edges.add(edge);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(double yCoord) {
        this.yCoord = yCoord;
    }

    public int getId() {
        return id;
    }

    public ArrayList<GraphEdge> getEdges() {
        return edges;
    }

    public double getyCoord() {
        return yCoord;
    }

    public double getxCoord() {
        return xCoord;
    }

    @Override
    public String toString() {
        return "GraphNode{" +
                "id=" + id +
                ", yCoord=" + yCoord +
                ", xCoord=" + xCoord +
                ", Edges=" + edges +
                "}\n";
    }
}
