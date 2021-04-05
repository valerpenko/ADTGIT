import ADT.Vertex;
import listbased.AdjacencyMapGraph;

public class FirstGraphTest
{
    public static void main(String[] args)
    {
        AdjacencyMapGraph<String,Integer> graph = new AdjacencyMapGraph<>(true);

        Vertex<String> cityA = graph.insertVertex("city A");
        Vertex<String> cityB = graph.insertVertex("city B");
        Vertex<String> cityC = graph.insertVertex("city C");
        Vertex<String> cityD = graph.insertVertex("city D");

        graph.insertEdge(cityA, cityB, 153);
        graph.insertEdge(cityB, cityC, 335);
        graph.insertEdge(cityC, cityD, 671);
        graph.insertEdge(cityD, cityA, 560);
        graph.insertEdge(cityD, cityB, 263);


        System.out.println(graph.numVertices());
        System.out.println(graph.numEdges());

        System.out.println(graph.incomingEdges(cityD));
        System.out.println(graph.outgoingEdges(cityD));
    }
}
