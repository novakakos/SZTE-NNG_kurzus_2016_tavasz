package reader;

import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.pgm.util.io.graphml.GraphMLReader;

import java.io.*;

/**
 * @author markfodor on 2016.02.25.
 */
public class GraphReader {

    private String filePath;

    public GraphReader(String filePath) {
        this.filePath = filePath;
    }

    public Graph read() {
        Graph graph = new TinkerGraph();
        GraphMLReader reader = new GraphMLReader(graph);

        try {
            InputStream is = new BufferedInputStream(new FileInputStream(filePath));
            reader.inputGraph(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

}
