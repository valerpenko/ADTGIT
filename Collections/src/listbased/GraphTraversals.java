package listbased;

import ADT.*;
import Exceptions.SetFullException;
import arraybased.ProbeHashMap;
import java.util.HashSet;

public class GraphTraversals
{
    // Performs depth-first search of Graph g starting at Vertex u.
    public static <V,E> void DFS(Graph<V,E> g, Vertex<V> u, java.util.Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) throws SetFullException
    {
        known.add(u); // u has been discovered
        for (Edge<E> e : g.outgoingEdges(u))
        { // for every outgoing edge from u
            Vertex<V> v = g.opposite(u, e);
            if (!known.contains(v))
            {
                forest.put(v, e); // e is the tree edge that discovered v
                DFS(g, v, known, forest); // recursively explore from v
            }
        }
    }

    // Returns an ordered list of edges comprising the directed path from u to v.
    public static <V,E> PositionalList<Edge<E>> constructPath(Graph<V,E> g, Vertex<V> u, Vertex<V> v, Map<Vertex<V>,Edge<E>> forest)
    {
        PositionalList<Edge<E>> path = new LinkedPositionalList<>();
        if (forest.get(v) != null)
        { // v was discovered during the search
            Vertex<V> walk = v; // we construct the path from back to front
            while (walk != u)
            {
                Edge<E> edge = forest.get(walk);
                path.addFirst(edge); // add edge to *front* of path
                walk = g.opposite(walk, edge); // repeat with opposite endpoint
            }
        }
        return path;
    }

    // Performs DFS for the entire graph and returns the DFS forest as a map.
    public static <V,E> Map<Vertex<V>,Edge<E>> DFSComplete(Graph<V,E> g) throws SetFullException
    {
        java.util.Set<Vertex<V>> known = new HashSet<>();
        Map<Vertex<V>,Edge<E>> forest = new ProbeHashMap<>();
        for (Vertex<V> u : g.vertices())
            if (!known.contains(u))
            DFS(g, u, known, forest); // (re)start the DFS process at u
        return forest;
    }
}
