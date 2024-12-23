import java.util.*;

public class Vertex implements Comparable<Vertex>{
    
    // DATA FIELD
    int label;
    static ArrayList<LinkedList<Edge>> adjList = new ArrayList<>(); // ADJCENCY LIST

    int parent = -1; // LABEL OF PARENT VERTEX -> INITIAL  VALUE -1 
    int rank = 0;
    int key = Integer.MAX_VALUE;

    public Vertex(){
        
    }

    public Vertex(int label){
        this.label = label;
    }

    @Override
    public int compareTo(Vertex o) {
        return (this.key - o.key);
    }
    
}
