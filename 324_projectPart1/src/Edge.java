import java.util.*;

public class Edge implements Comparable<Edge> {

    Vertex srcVer;
    Vertex destVer;
    Vertex parent;
    int weight;
    public char[] toString;

    // arraylist of all edges
    static ArrayList<Edge> totalEdges = new ArrayList<Edge>();

    public Edge(Vertex srcVer, Vertex destVer, int weight) {
        this.srcVer = srcVer;
        this.destVer = destVer;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return (destVer.label + "(" + srcVer.label + "," + weight + ") ");
    }

    @Override
    public int compareTo(Edge o) {
        return (this.weight - o.weight);
    }

}
