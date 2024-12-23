public class AllSourceSPAlg extends ShortestPathAlgorithm {

    public AllSourceSPAlg() {

    }

    public void computeFloyedWarshalAlg() {

        // PRINT THE GRAPH BEFORE APPLYING FLOYD-WARSHALL'S ALGORITHM
        System.out.println("\nk = " + 0 );
        printMatrix(Graph.adjMatrix);

        // START TIME
        long start = System.currentTimeMillis();
        
        for (int k = 0; k < Graph.verList.size(); k++) {
            for (int i = 0; i < Graph.verList.size(); i++) {
                for (int j = 0; j < Graph.verList.size(); j++) {
                    Graph.adjMatrix[i][j].weight = Math.min((Graph.adjMatrix[i][j].weight), (Graph.adjMatrix[i][k].weight) + (Graph.adjMatrix[k][j].weight) );
                } // END FOR LOOP J
            } // END FOR LOOP I

            // PRINT THE GRAPH AFTER APPLYING FLOYD-WARSHALL'S ALGORITHM
            System.out.println("\nk = " + (k+1));
            printMatrix(Graph.adjMatrix);

        }// END FOR LOOP K
        // FINISH TIME
        long finish = System.currentTimeMillis();
        totalTime = finish - start;
    }// END METHOD
}// END CLASS
