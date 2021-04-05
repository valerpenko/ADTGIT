package listbased;

import ADT.*;
import arraybased.ProbeHashMap;

public class AdjacencyMapGraph<V,E> implements Graph<V,E>
{
    // A vertex of an adjacency map graph representation.
    private class InnerVertex<V> implements Vertex<V>
    {
        private V element;
        private Position<Vertex<V>> pos;
        private Map<Vertex<V>, Edge<E>> outgoing, incoming;
        // Constructs a new InnerVertex instance storing the given element.
        public InnerVertex(V elem, boolean graphIsDirected)
        {
            element = elem;
            outgoing = new ProbeHashMap<>();
            if (graphIsDirected)
                incoming = new ProbeHashMap<>();
            else
            incoming = outgoing; // if undirected, alias outgoing map
        }
        // Returns the element associated with the vertex.
        public V getElement() { return element; }
        // Stores the position of this vertex within the graph's vertex list.
        public void setPosition(Position<Vertex<V>> p) { pos = p; }
        // Returns the position of this vertex within the graph's vertex list.
        public Position<Vertex<V>> getPosition() { return pos; }
        // Returns reference to the underlying map of outgoing edges.
        public Map<Vertex<V>, Edge<E>> getOutgoing() { return outgoing; }
        // Returns reference to the underlying map of incoming edges.
        public Map<Vertex<V>, Edge<E>> getIncoming() { return incoming; }

        /**Validates that this edge instance belongs to the given graph.*/
        public boolean validate(Graph<V, E> graph)

        {
            return AdjacencyMapGraph.this == graph && pos != null;
        }
    } //------------ end of InnerVertex class ------------

    // An edge between two vertices.
    private class InnerEdge<E> implements Edge<E>
    {
        private E element;
        private Position<Edge<E>> pos;
        private Vertex<V>[ ] endpoints;
        // Constructs InnerEdge instance from u to v, storing the given element.
        public InnerEdge(Vertex<V> u, Vertex<V> v, E elem)
        {
            element = elem;
            endpoints = (Vertex<V>[ ]) new Vertex[ ] { u,v } ; // array of length 2
        }
        // Returns the element associated with the edge.
        public E getElement() { return element; }
        // Returns reference to the endpoint array.
        public Vertex<V>[ ] getEndpoints() { return endpoints; }
        // Stores the position of this edge within the graph's vertex list.
        public void setPosition(Position<Edge<E>> p) { pos = p; }
        // Returns the position of this edge within the graph's vertex list.
        public Position<Edge<E>> getPosition() { return pos; }

        /**Validates that this edge instance belongs to the given graph.*/
        public boolean validate(Graph<V, E> graph)
        {
            return AdjacencyMapGraph.this == graph && pos != null;
        }
    } //------------ end of InnerEdge class ------------


    private boolean isDirected;
    private PositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
    private PositionalList<Edge<E>> edges = new LinkedPositionalList<>();
    // Constructs an empty graph (either undirected or directed).
    public AdjacencyMapGraph(boolean directed) { isDirected = directed; }
    // Returns the number of vertices of the graph
    public int numVertices() { return vertices.size(); }
    // Returns the vertices of the graph as an iterable collection
    public Iterable<Vertex<V>> vertices() { return (Iterable<Vertex<V>>) vertices; }
    // Returns the number of edges of the graph
    public int numEdges() { return edges.size(); }
    // Returns the edges of the graph as an iterable collection
    public Iterable<Edge<E>> edges() { return (Iterable<Edge<E>>) edges; }
    // Returns the number of edges for which vertex v is the origin.
    public int outDegree(Vertex<V> v)
    {
        InnerVertex<V> vert = validate(v);
        return vert.getOutgoing().size();
    }
    // Returns an iterable collection of edges for which vertex v is the origin.
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v)
    {
        InnerVertex<V> vert = validate(v);
        return vert.getOutgoing().values(); // edges are the values in the adjacency map
    }
    // Returns the number of edges for which vertex v is the destination.
    public int inDegree(Vertex<V> v)
    {
        InnerVertex<V> vert = validate(v);
        return vert.getIncoming().size();
    }
    // Returns an iterable collection of edges for which vertex v is the destination.
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v)
    {
        InnerVertex<V> vert = validate(v);
        return vert.getIncoming().values(); // edges are the values in the adjacency map
    }
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)
    {
        // Returns the edge from u to v, or null if they are not adjacent.
        InnerVertex<V> origin = validate(u);
        return origin.getOutgoing().get(v); // will be null if no edge from u to v
    }
    // Returns the vertices of edge e as an array of length two.
    public Vertex<V>[ ] endVertices(Edge<E> e)
    {
        InnerEdge<E> edge = validate(e);
        return edge.getEndpoints();
    }

    // Returns the vertex that is opposite vertex v on edge e.
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException
    {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[ ] endpoints = edge.getEndpoints();
        if (endpoints[0] == v)
            return endpoints[1];
        else if (endpoints[1] == v)
            return endpoints[0];
        else
        throw new IllegalArgumentException("v is not incident to this edge");
    }
    // Inserts and returns a new vertex with the given element.
    public Vertex<V> insertVertex(V element)
    {
        InnerVertex<V> v = new InnerVertex<>(element, isDirected);
        v.setPosition(vertices.addLast(v));
        return v;
    }
    // Inserts and returns a new edge between u and v, storing given element.
    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException
    {
        if (getEdge(u,v) == null)
        {
            InnerEdge<E> e = new InnerEdge<>(u, v, element);
            e.setPosition(edges.addLast(e));
            InnerVertex<V> origin = validate(u);
            InnerVertex<V> dest = validate(v);
            origin.getOutgoing().put(v, e);
            dest.getIncoming().put(u, e);
            return e;
        }
        else
            throw new IllegalArgumentException("Edge from u to v exists");
    }
    // Removes a vertex and all its incident edges from the graph.
    public void removeVertex(Vertex<V> v)
    {
        InnerVertex<V> vert = validate(v);
        // remove all incident edges from the graph
        for (Edge<E> e : vert.getOutgoing().values())
            removeEdge(e);
        for (Edge<E> e : vert.getIncoming().values())
            removeEdge(e);
        // remove this vertex from the list of vertices
        vertices.remove(vert.getPosition());
    }

    @Override
    public void removeEdge(Edge<E> e) throws IllegalArgumentException
    {
        //To change body of generated methods, choose Tools | Templates.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @SuppressWarnings({"unchecked"})
    private InnerVertex<V> validate(Vertex<V> v)
    {
        if (!(v instanceof InnerVertex))
        {
            throw new IllegalArgumentException("Invalid vertex");
        }
        InnerVertex<V> vert = (InnerVertex<V>) v;     // safe cast
        if (!vert.validate(this))
        {
            throw new IllegalArgumentException("Invalid vertex");
        }
        return vert;
    }

    @SuppressWarnings({"unchecked"})
    private InnerEdge<E> validate(Edge<E> e)
    {
        if (!(e instanceof InnerEdge))
        {
            throw new IllegalArgumentException("Invalid edge");
        }
        InnerEdge<E> edge = (InnerEdge<E>) e;     // safe cast
        if (!edge.validate(this))
        {
            throw new IllegalArgumentException("Invalid edge");
        }
        return edge;
    }
}
