import ADT.*;
import arraybased.HeapAdaptablePriorityQueue;
import arraybased.ProbeHashMap;

public class PrimJarnik
{
    public static <V> Map<Vertex<V>, Edge<Integer>> MST(Graph<V,Integer> g, Vertex<V> src)
    {
        // d.get(v) is upper bound on distance from src to v
        Map<Vertex<V>, Integer> d = new ProbeHashMap<>();
        // map reachable v to its d value
        Map<Vertex<V>, Edge<Integer>> tree = new ProbeHashMap<>();
        // pq will have vertices as elements, with d.get(v) as key
        AdaptablePriorityQueue<Integer, Vertex<V>> pq;
        pq = new HeapAdaptablePriorityQueue<>();
        // maps from vertex to its pq locator
        Map<Vertex<V>, Entry<Integer,Vertex<V>>> pqTokens;
        pqTokens = new ProbeHashMap<>();

        for (Vertex<V> v : g.vertices())
        {
            if (v == src)
                d.put(v,0);
            else
                d.put(v, Integer.MAX_VALUE);
            pqTokens.put(v, pq.insert(d.get(v), v));
        }

        while (!pq.isEmpty())
        {
            Entry<Integer, Vertex<V>> entry = pq.removeMin();
            int key = entry.getKey();
            Vertex<V> u = entry.getValue();
            //tree.put(u, key); // this is actual distance to u
            pqTokens.remove(u); // u is no longer in pq
            for (Edge<Integer> e : g.outgoingEdges(u))
            {
                Vertex<V> v = g.opposite(u,e);
                if (tree.get(v) == null)
                {
                    int wgt = e.getElement();
                    if (wgt < d.get(v))
                    { // better path to v?
                        d.put(v, wgt); // update the distance
                        tree.put(u,e);
                        pq.replaceKey(pqTokens.get(v), d.get(v)); // update the pq entry
                    }
                }
            }
        }
        return tree; // this only includes reachable vertices
    }
}
