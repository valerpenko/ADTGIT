import ADT.*;
import arraybased.HeapAdaptablePriorityQueue;
import arraybased.ProbeHashMap;

public class Dijkstra
{
    // Computes shortest-path distances from src vertex to all reachable vertices of g.
    public static <V> Map<Vertex<V>, Integer> shortestPathLengths(Graph<V,Integer> g, Vertex<V> src)
    {
        // d.get(v) is upper bound on distance from src to v
        Map<Vertex<V>, Integer> d = new ProbeHashMap<>();
        // map reachable v to its d value
        Map<Vertex<V>, Integer> cloud = new ProbeHashMap<>();
        // pq will have vertices as elements, with d.get(v) as key
        AdaptablePriorityQueue<Integer, Vertex<V>> pq;
        pq = new HeapAdaptablePriorityQueue<>();
        // maps from vertex to its pq locator
        Map<Vertex<V>, Entry<Integer,Vertex<V>>> pqTokens;
        pqTokens = new ProbeHashMap<>();

        // for each vertex v of the graph, add an entry to the priority queue, with
        // the source having distance 0 and all others having infinite distance
        for (Vertex<V> v : g.vertices())
        {
            if (v == src)
                d.put(v,0);
            else
            d.put(v, Integer.MAX_VALUE);
            pqTokens.put(v, pq.insert(d.get(v), v)); // save entry for future updates
        }
        // now begin adding reachable vertices to the cloud
        while (!pq.isEmpty())
        {
            Entry<Integer, Vertex<V>> entry = pq.removeMin();
            int key = entry.getKey();
            Vertex<V> u = entry.getValue();
            cloud.put(u, key); // this is actual distance to u
            pqTokens.remove(u); // u is no longer in pq
            for (Edge<Integer> e : g.outgoingEdges(u))
            {
                Vertex<V> v = g.opposite(u,e);
                if (cloud.get(v) == null)
                {
                    // perform relaxation step on edge (u,v)
                    int wgt = e.getElement();
                    if (d.get(u) + wgt < d.get(v))
                    { // better path to v?
                        d.put(v, d.get(u) + wgt); // update the distance
                        pq.replaceKey(pqTokens.get(v), d.get(v)); // update the pq entry
                    }
                }
            }
        }
        return cloud; // this only includes reachable vertices
    }
}
