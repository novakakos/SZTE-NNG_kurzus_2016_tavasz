package navigation;

import reader.InputFileHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Implement your graph representation here. This class will be instantiated
 * during the unit tests.
 */
public class GraphImpl implements Graph {
	private final ArrayList <GraphNode> graphNodes;

	public GraphImpl() {
		this.graphNodes = new ArrayList<>();
	}

	@Override
	public void initializeFromFile(File inputXmlFile) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			InputFileHandler inputFileHandler = new InputFileHandler(graphNodes);
			saxParser.parse(inputXmlFile, inputFileHandler);
		} catch (Exception e) {
			System.out.println("Error during the XML parsing: " + e.toString());
		}
	}

	public ArrayList<GraphNode> getGraphNodes() {
		return graphNodes;
	}
}
