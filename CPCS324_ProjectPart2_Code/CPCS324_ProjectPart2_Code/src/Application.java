/**
 * CPCS324  DAR
 * PROJECT PART 2
 * 10 MAY 2022
 * BADOR RAKKAN ALGHALIB            1905253
 * MARYAM ABDULRAHMAN BAMBEROOK     1906316  
 */
import java.io.*;
import java.util.*;

public class Application {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(System.in);
        System.out.println("\n\t ------- Test and compare Different Shortest Path Algorithms -------\n"
                + "comparing between the Floyd-Warshall algorithm and Dijkstra algorithm for the shortest path general problem.\n");
        System.out.println(">> Requirement 1: read graph from file");
        System.out.println(">> Requirement 2: enter number of vertices and number of edges");

        System.out.print("Choose Requirement 1 or 2: ");
        int choose = scan.nextInt();
        System.out.println("----------------------------------------------------------------\n");

        switch (choose) {

            // -------------------------------- REQUIREMENT 1 --------------------------------
            // 1.1 IMPLEMENT THE FLOYD-WARSHALL ALGORITHM TO COMPUTE THE DISTANCE MATRIX
            // THAT INDICATES THE LENGTH OF THE SHORTEST PATHS BETWEEN EVERY PAIR IN THE
            // GRAPH.

            // 1.2 CHOOSE A SOURCE VERTEX AND IMPLEMENT THE DIJKSTRA ALGORITHM TO COMPUTE
            // THE LENGTH OF THE SHORTEST PATHS FROM THE CHOSEN SOURCE TO THE REST OF THE
            // VERTICES. MAKE SURE TO PRINT THE SHORTEST PATHS WITH THEIR LENGTHS.
            case 1:
                Graph g1 = new Graph();
                g1 = g1.readGraphFromFile(new File("requirement1.txt"));

                // ------------------------------ 1.1 ------------------------------
                System.out.println("-------------------------------- Floyd-Warshall algorithm  --------------------------------");
                AllSourceSPAlg req1FW = new AllSourceSPAlg();
                req1FW.computeFloyedWarshalAlg();
                System.out.println();
                System.out.println("\tTotal runtime = " + req1FW.totalTime + " msec\n");

                // ------------------------------ 1.2 ------------------------------
                System.out.println("-------------------------------- Dijkstra algorithm  --------------------------------\n");
                SingleSourceSPAlg req1Dij = new SingleSourceSPAlg();
                req1Dij.computeDijkstraAlg();
                System.out.println();
                System.out.println("\tTotal runtime = " + req1Dij.totalTime + " msec\n");
                break;

            // -------------------------------- REQUIREMENT 2 --------------------------------
            // 2.1 USE THE FLOYD-WARSHALL ALGORITHM TO COMPUTE THE DISTANCE MATRIX THAT
            // INDICATES THE LENGTH OF THE SHORTEST PATHS BETWEEN EVERY PAIR IN A GRAPH.
            // COMPUTE THE RUN DURATION AND COMPARE THE ALGORITHM’S TIME EFFICIENCY COMPUTED
            // FROM THE EXPERIMENT’S RESULTS WITH THE ONE DETERMINED BY THE FORMULA STATED
            // IN THE BOOK.

            // 2.2 CHOOSE A SOURCE VERTEX AND USE THE DIJKSTRA ALGORITHM TO COMPUTE THE
            // LENGTH
            // OF THE SHORTEST PATHS FROM THE CHOSEN SOURCE TO THE REST OF THE VERTICES.
            // COMPUTE THE RUN DURATION AND COMPARE THE ALGORITHM’S TIME EFFICIENCY COMPUTED
            // FROM THE EXPERIMENT’S RESULTS WITH THE ONE DETERMINED BY THE FORMULA STATED
            // IN THE BOOK.
            case 2:
                Graph g2 = new Graph();
                System.out.print("Enter number of Vertices: ");
                int verNo = scan.nextInt();
                System.out.print("Enter number of Edges: ");
                int edgeNo = scan.nextInt();
                g2 = g2.makeGraph(verNo, edgeNo);

                // ------------------------------ 2.1 ------------------------------
                System.out.println("-------------------------------- Floyd-Warshall algorithm  --------------------------------");
                AllSourceSPAlg req2FW = new AllSourceSPAlg();
                req2FW.computeFloyedWarshalAlg();
                System.out.println();
                System.out.println("\tTotal runtime = " + req2FW.totalTime + " msec\n");

                // ------------------------------ 2.2 ------------------------------
                System.out.println("-------------------------------- Dijkstra algorithm  --------------------------------\n");
                SingleSourceSPAlg req2Dij = new SingleSourceSPAlg();
                req2Dij.computeDijkstraAlg();
                System.out.println();
                System.out.println("\tTotal runtime = " + req2Dij.totalTime + " msec\n");
                break;
        }// END SWITCH

        scan.close();
    }// END MAIN

}// END CLASS
