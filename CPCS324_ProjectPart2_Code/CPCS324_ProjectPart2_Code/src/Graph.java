import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {

    // DATA FIELDS
    int verticeNo;
    int edgeNo;
    boolean isDigraph = false; // DIRECTED GRAPH
    static Edge[][] adjMatrix;
    static ArrayList<Vertex> verList = new ArrayList<Vertex>();
    static char[] verLabels = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Z' };

    // ------------------------------------------- Constructor ---------------------------------------------------
    public Graph() {

    }// END CONSTRUCTOR

    /**
     * takes number of vertices and number of edges of the graph
     * and creates the object of the adjMatrix.
     * It is responsible for initializing all attributes of the Graph object
     * and it must initialize all elements of the adjMatrix with null.
     */
    public Graph(int verticeNo, int edgeNo) { 

        // CREATE adjMatrix OBJECT WITH ATTRIBUTES OF GRAPH OBJECT
        adjMatrix = new Edge[verticeNo][verticeNo];
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {
                adjMatrix[i][j] = null;
            } // END FOR LOOP J
        } // END FOR LOOP I
    }// END CONSTRUCTOR
    

    // ------------------------------------------- makeGraph ---------------------------------------------------
    /**
     * this method takes as parameters the number of vertices and the number of
     * edges.
     * It is responsible for creating a graph object with the specified parameters
     * and randomly initializes the vertices’ position (make sure they are within
     * the adjMatrix size),
     * creating edges that connects the created vertices randomly and assigning them
     * random weights.
     * Make sure that the resulting graph is connected. It should use the method
     * addEdge().
     * To check that the edge exists or not, check the value stored in the adjMatrix
     * at the determined
     * coordinates, if it is null, then the determined edge is new.
     * 
     * 1. CREATE GRAPH OBJECT
     * 2. RANDOMLY INITIALIZE VERTICES POSITION
     * 3. CREATE EDGES RANDOMLY AND ASSIGNING THEM RANDOM WEIGHTS // addEdge()
     *
     * @param verticeNo
     * @param edgeNo
     */
    public Graph makeGraph(int verticeNo, int edgeNo) {

        // CREATE GRAPH OPJECT
        Graph graphObj = new Graph(verticeNo, edgeNo);
        Random random = new Random();

        // 0 1 2 3
        // 0 [ 0 inf inf int ]
        // 1 [ inf 0 inf int ]
        // 0 [ inf inf 0 int ]
        // 0 [ inf inf inf 0 ]

        // INITIALIZE VERTICES POSITION
        for (int i = 0; i < verticeNo; i++) {
            Vertex ver = new Vertex(i);
            // INCREMENT NUM OF VERTICES IN GRAPH
            graphObj.verticeNo++;
            Graph.verList.add(ver);
        } // END FOR LOOP

        for (int i = 0; i < Graph.adjMatrix.length; i++) {
            for (int j = 0; j < Graph.adjMatrix[i].length; j++) {
                if (i == j) {
                    Graph.adjMatrix[i][j] = new Edge(verList.get(i), verList.get(j), 0);
                } else {
                    Graph.adjMatrix[i][j] = new Edge(verList.get(i), verList.get(j), 999999 );
                } // END IF
            } // END FOR LOOP j
        } // END FOR LOOP i

        // CONNECT ALL VERTICES
        for (int i = 0; i < verticeNo - 1; i++) {

            // GENERATE RANDOM VALUE FOR WEIGHT
            int weight = random.nextInt(50) + 1;

            // ------------------------------------------- ASCENDING ORDER ---------------------------------------------------

            // CREATE EDGE BETWEEN VERTICES
            addEdge(Graph.verList.get(i), Graph.verList.get(i + 1), weight);
            Graph.adjMatrix[i][i + 1].weight = weight;

            // INCREMENT NUM OF EDGES IN GRAPH
            graphObj.edgeNo++;

            if (!graphObj.isDigraph) {

                // CREATE EDGE BETWEEN VERTICES
                addEdge(Graph.verList.get(i + 1), Graph.verList.get(i), weight);
                Graph.adjMatrix[i + 1][i].weight = weight;

                // INCREMENT NUM OF EDGES IN GRAPH
                graphObj.edgeNo++;
            } // END IF
        } // END FOR LOOP

        // ------------------------------------------- RANDOMLY ---------------------------------------------------
        // CREATE REMINING EGDES RANDOMLY BETWEEN VERTICES
        // CALCULATE REMINING EDGES
        int remEdge = edgeNo - (verticeNo - 1);

        // CONNECT VERTICES RENDOMLY
        for (int i = 0; i < remEdge; i++) {

            // GENERATE RANDOM VALUE FOR SRC AND DEST VERTICES
            int srcVert = random.nextInt(graphObj.verticeNo);
            int destVert = random.nextInt(graphObj.verticeNo);

            // CHECK TO AVOID FOR DUPLICATE EDGES
            if (destVert == srcVert || CheckEdge(srcVert, destVert, Edge.totalEdges)) {
                i--;
                continue;
            } // END IF

            // GENERATE RANDOM VALUE FOR WEIGHT
            int weight = random.nextInt(50) + 1;
            // CREATE EDGE BETWEEN VERTICES
            addEdge(Graph.verList.get(srcVert), Graph.verList.get(destVert), weight);
            Graph.adjMatrix[srcVert][destVert].weight = weight;

            // INCREMENT NUM OF EDGES IN GRAPH
            graphObj.edgeNo++;

            // IF GRAPH IS UNDIRECTED
            if (!graphObj.isDigraph) {

                // CREATE EDGE BETWEEN VERTICES
                addEdge(Graph.verList.get(destVert), Graph.verList.get(srcVert), weight);
                Graph.adjMatrix[destVert][srcVert].weight = weight;

                // INCREMENT NUM OF EDGES IN GRAPH
                graphObj.edgeNo++;
            } // END IF
        } // END FOR LOOP

        // RETURN GRAPH
        return graphObj;

    }// END makeGraph method

    // ------------------------------------------- CheckEdge ---------------------------------------------------
    public boolean CheckEdge(int src, int dest, ArrayList<Edge> totalEdges) {

        for (int i = 0; i < totalEdges.size(); i++) {
            Edge temp = totalEdges.get(i);
            if (temp.destVer.position == dest && temp.srcVer.position == src) {
                return true;
            } // END IF
        } // END FOR LOOP I

        // IF THERE IN NO MATCH RETURN FALSE
        return false;
    }// END METHOD

    // ------------------------------------------- readGraphFromFile ---------------------------------------------------
    /**
     * reads the edges and vertices from the text file whose name is specified by
     * the parameter filename
     * and place data in a Graph object. In this project, you need to create a text
     * file that contains
     * the graph presented in requirement 1. The file format is shown in Appendix
     * II. It is responsible
     * for doing some preprocessing then call the addEdge() method to determine the
     * position of the Edge
     * and then processing the returned Edge object to set the label attribute of
     * the source and target
     * attributes that are of the type Vertex.
     * 
     * 1. READS THE EDGES AND VERTICES FROM THE TEXT FILE
     * 2. CREATE GRAPH OBJECT AND PLACE DATA
     * 3. READ EDGES AND WEIGHTS FROM FILE AND ADD THEM TO AdjMatrix
     * 
     * @param fileName
     * @throws FileNotFoundException
     */
    public Graph readGraphFromFile(File fileName) throws FileNotFoundException {

        Scanner input = new Scanner(fileName);
        int isDigraph = Integer.parseInt(input.nextLine().substring(8));
        verticeNo = input.nextInt();
        edgeNo = input.nextInt();

        Graph graphObj = new Graph(verticeNo, edgeNo);

        if (isDigraph == 1) {
            graphObj.isDigraph = true;
        } // END IF

        for (int i = 0; i < verticeNo; i++) {
            Vertex ver = new Vertex(i, verLabels[i]);
            // INCREMENT NUM OF VERTICES IN GRAPH
            graphObj.verticeNo++;
            verList.add(ver);
        }// END FOR LOOP i

        for (int i = 0; i < Graph.adjMatrix.length; i++) {
            for (int j = 0; j < Graph.adjMatrix[i].length; j++) {
                if (i == j) {
                    Graph.adjMatrix[i][j] = new Edge(verList.get(i), verList.get(j), 0);
                } else {
                    Graph.adjMatrix[i][j] = new Edge(verList.get(i), verList.get(j), 999999 );
                } // END IF
            } // END FOR LOOP j
        } // END FOR LOOP i

        while (input.hasNext()) {

            // READ SRC AND DEST LABELS AND EDGE WEIGHT FROM FILE
            char srcLabel = input.next().trim().charAt(0);
            char destLabel = input.next().trim().charAt(0);
            int weight = Integer.parseInt(input.next().trim());

            // CREATE VERTICES OBJECTS
            Vertex tempSrc = new Vertex();
            Vertex tempDest = new Vertex();

            for (int i = 0; i < verList.size(); i++) {
                if (verList.get(i).label == srcLabel) {
                    tempSrc = verList.get(i);
                } else if (verList.get(i).label == destLabel) {
                    tempDest = verList.get(i);
                }// END IF ELSE
            }// END FOR LOOP

            // CREATE EDGE BETWEEN VERTICES
            addEdge(tempSrc, tempDest, weight);
            Graph.adjMatrix[tempSrc.position][tempDest.position].weight = weight;

            // INCREMENT NUM OF EDGES IN GRAPH
            graphObj.edgeNo++;

            if (!graphObj.isDigraph) {

                // CREATE EDGE BETWEEN VERTICES
                addEdge(tempDest, tempSrc, weight);
                Graph.adjMatrix[tempDest.position][tempSrc.position].weight = weight;

                // INCREMENT NUM OF EDGES IN GRAPH
                graphObj.edgeNo++;
            } // END IF
        } // END WHILE LOOP

        input.close();
        return graphObj;

    }// END readGraphFromFile METHOD

    // ------------------------------------------- addEdge ---------------------------------------------------
    public void addEdge(Vertex srcVer, Vertex destVer, int weight) {

        Edge edgeObj = new Edge(srcVer, destVer, weight);
        Edge.totalEdges.add(edgeObj);

    }// END addEdge METHOD

    // ------------------------------------------- printGraph ---------------------------------------------------
    public void printGraph() {

        if (!isDigraph) {
            System.out.println("\n *note: number of edges is duplicated because it's an undirected graph*");
            System.out.println("--------------------------------");
            System.out.println("num of edges = " + edgeNo + "(" + (edgeNo / 2) + ")" + "\nnum of ver = " + verticeNo);
        } else {
            System.out.println("--------------------------------");
            System.out.println("num of edges = " + edgeNo + "\nnum of ver = " + verticeNo);
        } // END IF

        System.out.println("--------------------------------");

        // 0 1 2 3
        // 0 [ 0 inf inf int ]
        // 1 [ inf 0 inf int ]
        // 0 [ inf inf 0 int ]
        // 0 [ inf inf inf 0 ]
        for (int i = 0; i < adjMatrix.length; i++) {
            System.out.println("\nVertex " + i + "= ");
            for (int j = 0; j < adjMatrix[i].length; j++) {
                System.out.println(adjMatrix[i][j].toString(i,j));
            }// END FOR LOOP j
        } // END FOR LOOP i
    }// END METHOD

}// END CLASS
