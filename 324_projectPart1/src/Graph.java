
import java.util.*;

public class Graph {

    // DATA FIELDS
    int verticeNo;
    int edgeNo;
    boolean isDigraph = false; // DIRECTED GRAPH
    static ArrayList<Vertex> verList = new ArrayList<Vertex>();

    // CONSTRUCTOR
    public Graph() {

    }

    public Graph(int verticeNo, int edgeNo, boolean isDigraph) {
        this.verticeNo = verticeNo;
        this.edgeNo = edgeNo;
        this.isDigraph = isDigraph;
        verList = new ArrayList<Vertex>(verticeNo);
    }
    
    // ------------------------------------------- makeGraph ---------------------------------------------------
    /**
     * this function takes as parameters the number of vertices and the number
     * of edges.It is responsible for creating a graph object with the specified
     * parameters and randomly initializes the vertices’ labels, creating edges
     * that connects the created vertices randomly and assigning them random
     * weights.Make sure that the resulting graph is connected.
     *
     * @param verticeNo
     * @param edgeNo
     * @return
     */
    public Graph makeGraph(int verticeNo, int edgeNo) {

        // CREATE GRAPH OPJECT
        Graph graphObj = new Graph(); // [1]
        Random random = new Random();

        // ------------------------------------------- ## STEP 1 ## ---------------------------------------------------
        // CREATE VERTICES AND INITIALIZE THE LABLES
        for (int i = 0; i < verticeNo; i++) {
            Vertex ver = new Vertex(i);
            // INCREMENT NUM OF VERTICES IN GRAPH
            graphObj.verticeNo++; // [2]
            Vertex.adjList.add(new LinkedList<>());
            Graph.verList.add(ver);
        }// END FOR LOOP 

        // ------------------------------------------- ## STEP 2 ## ---------------------------------------------------
        // CONNECT ALL VERTICES 
        for (int i = 0; i < verticeNo - 1; i++) {

            // GENERATE RANDOM VALUE FOR WEIGHT
            int weight = random.nextInt(50) + 1;

            // ------------------------------------------- ASCENDING ORDER  ---------------------------------------------------
            if ((i + 1) > verticeNo - 1) {
                // REACHED LAST VERTEX, CONNECT WITH FIRST VERTEX
                addEdge(Graph.verList.get(i), Graph.verList.get(0), weight);

                // INCREMENT NUM OF EDGES IN GRAPH
                graphObj.edgeNo++; // [3]
                continue;
            }// END IF

            // CREATE EDGE BETWEEN VERTICES
            addEdge(Graph.verList.get(i), Graph.verList.get(i + 1), weight);

            // INCREMENT NUM OF EDGES IN GRAPH
            graphObj.edgeNo++;

            if (!graphObj.isDigraph) {

                // CREATE EDGE BETWEEN VERTICES
                addEdge(Graph.verList.get(i + 1), Graph.verList.get(i), weight);

                // INCREMENT NUM OF EDGES IN GRAPH
                graphObj.edgeNo++;
            }// END IF 
        }// END FOR LOOP 

        // ------------------------------------------- ## STEP 3 ## ---------------------------------------------------
        // CREATE REMINING EGDES RANDOMLY BETWEEN VERTICES
        // CALCULATE REMINING EDGES
        int remEdge = edgeNo - (verticeNo - 1);

        // CONNECT VERTICES RENDOMLY 
        for (int i = 0; i < remEdge; i++) {
            
            // GENERATE RANDOM VALUE FOR SRC AND DEST VERTICES
            int srcVert = random.nextInt(graphObj.verticeNo);
            int destVert = random.nextInt(graphObj.verticeNo);

            // CHECK TO AVOID FOR DUPLICATE EDGES
            if (destVert == srcVert || CheckEdge(srcVert, destVert, Vertex.adjList)) { // [5]
                i--;
                continue;
            }// END IF

            // GENERATE RANDOM VALUE FOR WEIGHT
            int weight = random.nextInt(50) + 1;
            // CREATE EDGE BETWEEN VERTICES
            addEdge(Graph.verList.get(srcVert), Graph.verList.get(destVert), weight);

            // INCREMENT NUM OF EDGES IN GRAPH
            graphObj.edgeNo++;

            // IF GRAPH IS UNDIRECTED
            if (!graphObj.isDigraph) {

                // CREATE EDGE BETWEEN VERTICES
                addEdge(Graph.verList.get(destVert), Graph.verList.get(srcVert), weight);

                // INCREMENT NUM OF EDGES IN GRAPH
                graphObj.edgeNo++;
            }// END IF 
        }// END FOR LOOP

        // RETURN GRAPH
        return graphObj;
    }// END METHOD

    // ------------------------------------------- addEdge ---------------------------------------------------
     public void addEdge(Vertex srcVer, Vertex destVer, int weight) {

        Edge edgeObj = new Edge(srcVer, destVer, weight);
        edgeObj.parent = new Vertex(-1);
        Vertex.adjList.get(srcVer.label).add(edgeObj);
        Edge.totalEdges.add(edgeObj);

    }// END addEdge METHOD
     
    // ------------------------------------------- CheckEdge ---------------------------------------------------
    public boolean CheckEdge(int src, int dest, ArrayList<LinkedList<Edge>> totalEdges) {

        for (int i = 0; i < totalEdges.size(); i++) {
            for (int j = 0; j < totalEdges.get(i).size(); j++) {
                Edge temp = totalEdges.get(i).get(j);
                if (temp.destVer.label == dest && temp.srcVer.label == src) {
                    return true;
                }// END IF
            }// END FOR LOOP J
        }// END FOR LOOP I
        
        // IF THERE IN NO MATCH RETURN FALSE
        return false;
    }// END METHOD

    // print graph
    public void printGraph() {

        if (!isDigraph) { // [6] -> MAIN CLASS
            System.out.println("\n *note: number of edges is duplicated because it's an undirected graph*");
            System.out.println("--------------------------------");
            System.out.println("num of edges = " + edgeNo + "(" + (edgeNo / 2) + ")" + "\nnum of ver = " + verticeNo);
        } else {
            System.out.println("--------------------------------");
            System.out.println("num of edges = " + edgeNo + "\nnum of ver = " + verticeNo);
        }// END IF
        
        System.out.println("--------------------------------");

        // ARRAY LIST
        for(int i = 0 ; i < Vertex.adjList.size(); i++){
            System.out.println("Vertex " + verList.get(i).label + "= ");
            
            // Linked list
            for (int j = 0; j < Vertex.adjList.get(i).size(); j++){
                
                Edge temp = Vertex.adjList.get(i).get(j);
                System.out.print(temp.toString());
            }// END FOR LOOP J
            
            System.out.println("");
        }// END FOR LOOP I
   
    }// END METHOD
    
}// END CLASS
