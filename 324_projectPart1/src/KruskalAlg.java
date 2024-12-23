import java.util.*;
public class KruskalAlg extends MSTAlgorithm {
    
    public int findParent(Vertex vertex) {
        if (vertex.parent == -1) {
            return vertex.label;
        } // END IF

        //return findParent(Graph.verList.get(vertex.parent));
        return vertex.parent = findParent(Graph.verList.get(vertex.parent));
    }// END FIND PARENT METHOD

    public boolean isCycle(Vertex A, Vertex B) {
        if (findParent(A) == findParent(B)) {
            return true;
        } // END IF
        return false;
    }// END IS CYCLE METHOD

    public void union(Vertex A, Vertex B) {

        // HAVING SAME RANK , RANK OF NEW PARENT DECREASE
        if (A.rank == B.rank) {
            int parent = findParent(A);
            Graph.verList.get(findParent(B)).parent = parent;
            A.rank++;
        }

        // HAVING DIFFERENT RANK, RANK DOESN'T CHANGE
        // LOWER RANK PARENT WILL BE CHILD OF HIGHER RANK PARENT
        else if (A.rank > B.rank) {
            int parent = findParent(B);
            Graph.verList.get(findParent(A)).parent = parent;

        } else if (A.rank < B.rank){
            int parent = findParent(A);
            Graph.verList.get(findParent(B)).parent = parent;
        }// END IF ELSE
        
    }// END UNION METHOD

    public void Kruskal() {
        // START TIME
        long start = System.currentTimeMillis();

        int eCounter = 0; // EDGE COUNTER
        int k = 0;// LOOP COUNTER

        // SORT EDGES
        Collections.sort(Edge.totalEdges);

        while (eCounter < Graph.verList.size() - 1) {
            
            // REACH LAST EDGE IN ARRAY
            if (k == Edge.totalEdges.size()) {
                break;
            }// END IF

            Vertex src = Edge.totalEdges.get(k).srcVer;
            Vertex dest = Edge.totalEdges.get(k).destVer;

            if (isCycle(src, dest)) {
                k++;
                continue;
            }// END IF
            
            union(src, dest); 
            MSTResultList.add(Edge.totalEdges.get(k));
            k++;
            eCounter++;
        }// END WHILE LOOP

        // FINISH TIME
        long finish = System.currentTimeMillis();
        totalTime = finish - start;
    }// END METHOD
}// END CLASS
