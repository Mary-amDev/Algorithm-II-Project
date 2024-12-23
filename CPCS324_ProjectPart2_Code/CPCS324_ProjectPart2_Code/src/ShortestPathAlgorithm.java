public class ShortestPathAlgorithm {

    long totalTime;

    public ShortestPathAlgorithm(){

    }

    public void DisplayMatrix(Edge[][] adjMatrix) {
        for (int i = 0; i < adjMatrix.length; i++) {
            System.out.println("\nVertex " + i + "= ");
            for (int j = 0; j < adjMatrix[i].length; j++) {
                if (adjMatrix[i][j].srcVer.labelIsValid == true) {
                    System.out.println(adjMatrix[i][j].toString());
                } else {
                    System.out.println(adjMatrix[i][j].toString(i,j));
                }// END IF ELSE
            }// END FOR LOOP j
        } // END FOR LOOP i
    }// END METHOD

        //     0  1  2  3
        // 0 [ 0 inf inf int ]
        // 1 [ inf 0 inf int ]
        // 0 [ inf inf 0 int ]
        // 0 [ inf inf inf 0 ]
    public void printMatrix(Edge[][] adjMatrix) {
        // PRINT FIRST ROW
        for (int i = 0; i < adjMatrix.length ; i++) {
            System.out.printf("\t%-1d", i);
        }// END FOR LOOP
        System.out.println();

        for (int i = 0; i < adjMatrix.length; i++) {
            System.out.printf("%-2d [\t" , i);
            for (int j = 0; j < adjMatrix[i].length; j++) {
                if(adjMatrix[i][j].weight == 999999){
                    System.out.printf("%-1s\t" , "INF");
                } else {
                    System.out.printf("%-1d\t" , adjMatrix[i][j].weight);
                }// END IF ELSE
            }// END FOR LOOP
            System.out.printf(" ]\n");
        }// END FOR LOOP
    }// END METHOD
}//END CLASS
