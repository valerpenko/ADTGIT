import ADT.Edge;
import ADT.Entry;
import ADT.Map;
import ADT.Vertex;
import Exceptions.SetFullException;
import arraybased.ProbeHashMap;
import listbased.AdjacencyMapGraph;
import listbased.GraphTraversals;

import java.util.HashSet;
import java.util.Set;

public class FirstGraphTest
{
    public static void main(String[] args) throws SetFullException
    {
        AdjacencyMapGraph<String,String> graph = new AdjacencyMapGraph<>(true);

        Vertex<String> cityA = graph.insertVertex("city A");
        Vertex<String> cityB = graph.insertVertex("city B");
        Vertex<String> cityC = graph.insertVertex("city C");
        Vertex<String> cityD = graph.insertVertex("city D");

        Edge<String> AB = graph.insertEdge(cityA, cityB, "AB");
        Edge<String> BC = graph.insertEdge(cityB, cityC, "BC");
        Edge<String> CD = graph.insertEdge(cityC, cityD, "CD");
        Edge<String> DA = graph.insertEdge(cityD, cityA, "DA");
        Edge<String> DB = graph.insertEdge(cityD, cityB, "DB");



        Set <Vertex<String>> known = new HashSet<>();
        //known.add(cityA);
        Map<Vertex<String>, Edge<String>> forest = new ProbeHashMap<>();
        //forest.put(cityA, AB);
        GraphTraversals.DFS(graph, cityD, known, forest);

        for(Vertex<String> v : known)
        {
            try {
                System.out.println(forest.get(v).getElement());
            } catch (Exception e){System.out.println("Null");}
        }

        Map<Vertex<String>, Edge<String>> result = GraphTraversals.DFSComplete(graph);

        //System.out.println(result.entrySet());
    }
}
