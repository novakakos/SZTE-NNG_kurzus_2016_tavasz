import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.Vertex;
import algorithm.Dijkstra;
import reader.GraphReader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author markfodor on 2016.02.25.
 */
public class Main {

    public static void main(String[] args) {

        GraphReader reader = new GraphReader("resource\\graph.graphml");
        Graph graph = reader.read();

        Dijkstra dijkstra = new Dijkstra(graph);
        List<Vertex> vertexes = StreamSupport.stream(graph.getVertices().spliterator(), false).collect(Collectors.toList());


        dijkstra.execute(vertexes.get(0));
        //Miért nem jó a list.get(sokadik elem)?
        dijkstra.printPath(vertexes.get(vertexes.size() - 1));


    }
}
