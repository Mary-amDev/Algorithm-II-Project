import java.util.ArrayList;

public class Edge {

    int weight;
    Vertex srcVer;
    Vertex destVer;

    // arraylist of all edges
    static ArrayList<Edge> totalEdges = new ArrayList<Edge>();

    public Edge(Vertex srcVer, Vertex destVer, int weight) {
        this.srcVer = srcVer;
        this.destVer = destVer;
        this.weight = weight;
    }

    @Override
    public String toString() {
        if (weight == 999999) {
            return (destVer.label + "(" + srcVer.label + "," + "INF" + ") ");
        }
        return (destVer.label + "(" + srcVer.label + "," + weight + ") ");
    }

    public String toString(int srcVer, int destVer) {
        if (weight == 999999) {
            return (destVer + "(" + srcVer + "," + "INF" + ") ");
        }
        return (destVer + "(" + srcVer + "," + weight + ") ");
    }

    
}
