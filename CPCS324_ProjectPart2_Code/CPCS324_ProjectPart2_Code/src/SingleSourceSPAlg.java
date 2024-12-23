public class SingleSourceSPAlg extends ShortestPathAlgorithm {

    public SingleSourceSPAlg(){

    }

    // GET THE VERTEX WITH MINIMUM DISTANCE WHICH IS NOT INCLUDED IN SPT
    public int getMinimumVertex(boolean [] mst, int [] key){
        int minKey = Integer.MAX_VALUE;
        int vertex = -1;
        for (int i = 0; i < Graph.verList.size() ; i++) {
            if(mst[i]==false && minKey>key[i]){
                minKey = key[i];
                vertex = i;
            }// END IF 
        }// END FOR LOOP
        return vertex;
    }// END METHOD

    // ------------------------------------------- Dijkstra ---------------------------------------------------
    /**
     * 1.	START WITH THE EMPTY SHORTEST PATH TREE (SPT).
     * 2.	MAINTAIN A SET SPT[] TO KEEP TRACK TO VERTICES INCLUDED IN SPT.
     * 3.	ASSIGN A DISTANCE VALUE TO ALL THE VERTICES, 
     *      (SAY DISTANCE []) AND INITIALIZE ALL THE DISTANCES WITH INFINITY
     *      EXCEPT THE SOURCE VERTEX. THIS WILL BE USED TO KEEP TRACK OF 
     *      DISTANCE OF VERTICES FROM THE SOURCE VERTEX. DISTANCE OF SOURCE 
     *      VERTEX TO SOURCE VERTEX WILL BE 0.
     * 4.	REPEAT THE FOLLOWING STEPS UNTIL ALL VERTICES ARE PROCESSED. 
     *      -	PICK THE VERTEX U WHICH IS NOT IN SPT[] AND HAS MINIMUM DISTANCE. 
     *          HERE WE WILL LOOP THROUGH THE VERTICES AND FIND THE VERTEX WITH MINIMUM DISTANCE.
     *      -	ADD VERTEX U TO SPT[].
     *      -	LOOP OVER ALL THE ADJACENT VERTICES OF
     *      -	FOR ADJACENT VERTEX V, 
     *          IF V IS NOT IN SPT[] AND DISTANCE[V] > DISTANCE[U] + EDGE U-V WEIGHT 
     *          THEN UPDATE DISTANCE[V] = DISTANCE[U] + EDGE U-V WEIGHT 
     */
    public void computeDijkstraAlg() {

        // START TIME
        long start = System.currentTimeMillis();

        boolean[] SPT = new boolean[Graph.verList.size()];
        int[] distance = new int[Graph.verList.size()];

        int INF = 999999;
        // INITIALIZE ALL THE DISTANCE TO INFINITY
        for (int i = 0; i < Graph.verList.size(); i++) {
            distance[i] = INF;
        }// END FOR LOOP i

        // START FROM VERTEX 0
        int sourceVertex = 0;
        distance[sourceVertex] = 0;

        // CREATE SPT
        for (int i = 0; i < Graph.verList.size(); i++) {
            // GET THE VERTEX WITH THE MINIMUM DISTANCE
            int vertex_U = getMinimumVertex(SPT, distance);

            // INCLUDE VERTEX U IN SPT
            SPT[vertex_U] = true;

            // ITERATE THROUGH ALL THE ADJACENT Graph.verList.size() OF ABOVE VERTEX AND UPDATE THE KEYS
            for (int vertex_V = 0; vertex_V < Graph.verList.size(); vertex_V++) {

                // CHECK OF THE EDGE BETWEEN U AND V
                if (Graph.adjMatrix[vertex_U][vertex_V].weight > 0) {
                    // CHECK IF THIS VERTEX 'V' ALREADY IN SPT AND IF DISTANCE != INFINITY
                    if (SPT[vertex_V] == false && Graph.adjMatrix[vertex_U][vertex_V].weight != INF) {
                        // CHECK TOTAL WEIGHT FROM SOURCE TO V IS LESS THAN THE CURRENT DISTANCE VALUE,
                        // IF YES THEN UPDATE THE DISTANCE
                        int newKey = Graph.adjMatrix[vertex_U][vertex_V].weight + distance[vertex_U];
                        if (newKey < distance[vertex_V])
                            distance[vertex_V] = newKey;
                    }// END IF
                }// END IF
            }// END FOR LOOP VERTEX V
        }// END FOR LOOP i
        
        // FINISH TIME
        long finish = System.currentTimeMillis();
        totalTime = finish - start;

        // PRINT SHORTEST PATH TREE
        printDijkstra(sourceVertex, distance);
    }// END METHOD

    public void printDijkstra(int sourceVertex, int[] key) {
        System.out.println("Dijkstra Algorithm: (Adjacency Matrix)");
        for (int i = 0; i < Graph.verList.size(); i++) {
            System.out.println("Source Vertex: " + sourceVertex + " to vertex " + i +
                    " distance: " + key[i]);
        }// END FOR LOOP i
    }// END METHOD

}// END CLASS