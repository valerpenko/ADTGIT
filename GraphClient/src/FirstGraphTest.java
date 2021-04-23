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
        AdjacencyMapGraph<String,Integer> graph = new AdjacencyMapGraph<>(true);

        Vertex<String> cityA = graph.insertVertex("city A");
        Vertex<String> cityB = graph.insertVertex("city B");
        Vertex<String> cityC = graph.insertVertex("city C");
        Vertex<String> cityD = graph.insertVertex("city D");

        Edge<Integer> AB = graph.insertEdge(cityA, cityB, 153);
        Edge<Integer> BC = graph.insertEdge(cityB, cityC, 335);
        Edge<Integer> CD = graph.insertEdge(cityC, cityD, 671);
        Edge<Integer> DA = graph.insertEdge(cityD, cityA, 560);
        Edge<Integer> DB = graph.insertEdge(cityD, cityB, 263);



        Set <Vertex<String>> known = new HashSet<>();
        known.add(cityA);
        Map<Vertex<String>, Edge<Integer>> forest = new ProbeHashMap<>();
        forest.put(cityA, AB);
        GraphTraversals.DFS(graph, cityA, known, forest);

        Map<Vertex<String>, Edge<Integer>> result = GraphTraversals.DFSComplete(graph);

        System.out.println(result.entrySet());
    }
}
