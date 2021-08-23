import ADT.*;
import Exceptions.SetFullException;
import Exceptions.StackEmptyException;
import Exceptions.StackFullException;
import arraybased.ProbeHashMap;
import listbased.AdjacencyMapGraph;
import listbased.GraphTraversals;

import java.util.HashSet;
import java.util.Set;

public class FirstGraphTest
{
    public static void main(String[] args) throws SetFullException, StackFullException, StackEmptyException
    {
        AdjacencyMapGraph<String,Integer> graph = new AdjacencyMapGraph<>(false);

        Vertex<String> cityA = graph.insertVertex("city A");
        Vertex<String> cityB = graph.insertVertex("city B");
        Vertex<String> cityC = graph.insertVertex("city C");
        Vertex<String> cityD = graph.insertVertex("city D");
        Vertex<String> cityE = graph.insertVertex("city E");
        Vertex<String> cityF = graph.insertVertex("city F");

//        Edge<String> AB = graph.insertEdge(cityA, cityB, "AB");
//        Edge<String> BC = graph.insertEdge(cityB, cityC, "BC");
//        Edge<String> AC = graph.insertEdge(cityA, cityC, "AC");
//        Edge<String> CD = graph.insertEdge(cityC, cityD, "DA");
//        Edge<String> DE = graph.insertEdge(cityD, cityE, "DE");
//        Edge<String> DF = graph.insertEdge(cityD, cityF, "DF");
//        Edge<String> BD = graph.insertEdge(cityB, cityD, "BD");
//        Edge<String> BF = graph.insertEdge(cityB, cityF, "BF");
        
        Edge<Integer> AB = graph.insertEdge(cityA, cityB, 25);
        Edge<Integer> BC = graph.insertEdge(cityB, cityC, 10);
        Edge<Integer> AC = graph.insertEdge(cityA, cityC, 50);
        Edge<Integer> CD = graph.insertEdge(cityC, cityD, 20);
        Edge<Integer> DE = graph.insertEdge(cityD, cityE, 30);
        Edge<Integer> DF = graph.insertEdge(cityD, cityF, 40);
        Edge<Integer> BD = graph.insertEdge(cityB, cityD, 5);
        Edge<Integer> BF = graph.insertEdge(cityB, cityF, 15);



        Set <Vertex<String>> known = new HashSet<>();
        //known.add(cityA);
        Map<Vertex<String>, Edge<Integer>> forest = new ProbeHashMap<>();
        //forest.put(cityA, AB);
        GraphTraversals.BFS(graph, cityA, known, forest);
        //GraphTraversals.DFS(graph, cityD, known, forest);

//        for(Vertex<String> v : known)
//        {
//            try {
//                System.out.println(forest.get(v).getElement());
//            } catch (Exception e){System.out.println("Null");}
//        }
//
//        Map<Vertex<String>, Edge<Integer>> result = GraphTraversals.DFSComplete(graph);
//        int connectedComp = graph.numVertices() - result.size();
//        System.out.println(connectedComp);
//
//        PositionalList<Edge<Integer>>  path = GraphTraversals.constructPath(graph, cityA, cityC, forest);
//        System.out.println(path);

        PositionalList<Vertex<String>> sort = topoSort.topologicalSort(graph);
        System.out.println(sort);

//        Map<Vertex<String>, Integer> sp = Dijkstra.shortestPathLengths(graph, cityA);
//        for(Entry<Vertex<String>, Integer> obj : sp.entrySet())
//            System.out.format("%s - %d%n", obj.getKey().getElement(),obj.getValue());

        Map<Vertex<String>, Integer> mst = PrimJarnik.MST(graph, cityA);
        for(Entry<Vertex<String>, Integer> obj : mst.entrySet())
            System.out.format("%s - %d%n", obj.getKey().getElement(),obj.getValue());
    }
}
