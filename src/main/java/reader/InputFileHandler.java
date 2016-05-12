package reader;

import navigation.GraphEdge;
import navigation.GraphNode;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import util.NumberUtil;

import java.util.ArrayList;

/**
 * Created by novak on 2016.04.19..
 */

public class InputFileHandler extends DefaultHandler {

    private static final String NODE = "node";
    private static final String EDGE = "edge";
    private static final String PROPERTY = "property";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String YCOORD = "yCoord";
    private static final String XCOORD = "xCoord";
    private static final String STARTNODE = "startNode";
    private static final String ENDNODE = "endNode";
    private static final String AVERAGESPEED = "averageSpeed";

    private ArrayList<GraphNode> graphNodes;
    private GraphNode graphNode;
    private GraphEdge graphEdge;

    private boolean isyCoord;
    private boolean isxCoord;
    private boolean isStartNode;
    private boolean isEndNode;
    private boolean isAverageSpeed;

    public InputFileHandler(ArrayList<GraphNode> graphNodes) {
        this.graphNodes = graphNodes;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(NODE)){
            graphNode = new GraphNode();
            int id = Integer.parseInt(attributes.getValue(ID));
            graphNode.setId(id);
        }else if (qName.equalsIgnoreCase(PROPERTY)){
            if (attributes.getValue(NAME).equalsIgnoreCase(YCOORD)){
                isyCoord = true;
            }else if (attributes.getValue(NAME).equalsIgnoreCase(XCOORD)){
                isxCoord = true;
            }else if (attributes.getValue(NAME).equalsIgnoreCase(STARTNODE)){
                isStartNode = true;
            }else if (attributes.getValue(NAME).equalsIgnoreCase(ENDNODE)){
                isEndNode = true;
            }else if (attributes.getValue(NAME).equalsIgnoreCase(AVERAGESPEED)){
                isAverageSpeed = true;
            }
        }else if(qName.equalsIgnoreCase(EDGE)){
            graphEdge = new GraphEdge();
            int id = Integer.parseInt(attributes.getValue(ID));
            graphEdge.setId(id);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase(NODE)){
            graphNodes.add(graphNode);
        }else if (qName.equalsIgnoreCase(EDGE)){
            graphEdge.getStartNode().addEdge(graphEdge);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (isyCoord) {
            double yCoord = Double.parseDouble(new String(ch, start, length));
            graphNode.setyCoord(yCoord);
            isyCoord = false;
        }else if(isxCoord){
            double xCoord = Double.parseDouble(new String(ch, start, length));
            graphNode.setxCoord(xCoord);
            isxCoord = false;
        }else if(isStartNode){
            int id = Integer.parseInt(new String(ch, start, length));
            graphEdge.setStartNode(graphNodes.get(id - NumberUtil.ONE));
            isStartNode = false;
        }else if(isEndNode){
            int id = Integer.parseInt(new String(ch, start, length));
            graphEdge.setEndNode(graphNodes.get(id - NumberUtil.ONE));
            isEndNode = false;
        }else if(isAverageSpeed){
            int averageSpeed = Integer.parseInt(new String(ch, start, length));
            graphEdge.setAverageSpeed(averageSpeed);
            isAverageSpeed = false;
        }
    }

}
