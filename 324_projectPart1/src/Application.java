/**
 * CPCS324 PROJECT PART 1
 * 7 APR 2022
 */
import java.util.Scanner;
public class Application {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("\t -------Test and compare Different Minimum Spanning Tree Algorithms-------\n");
        System.out.println("\t1- Kruskal's Algorithm & Prim's Algorithm (based on Priority Queue)\n"
                + "\t2- Prim's Algorithm (based on Min Heap) & Prim's Algorithm (based on Priority Queue)");
        System.out.println("----------------------------------------------------------------\n");
        System.out.println(">>Test  cases : \n(where n is the number of vertices "
                + "and m is the number of edges)");
        System.out.print("Enter number of Vertices: ");
        int verNo = scan.nextInt();
        System.out.print("Enter number of Edges: ");
        int edgeNo = scan.nextInt();

        // CREATE GRAPH 
        new Graph().makeGraph(verNo, edgeNo);
       
        System.out.print("\n>>Enter your choice: ");
        int choice = scan.nextInt();

        switch (choice) {
            // 1- Kruskal's Algorithm & Prim's Algorithm (based on Priority Queue)
            case 1:
                System.out.println("\nKruskal's Algorithm: ");
                new KruskalAlg().Kruskal();
                new KruskalAlg().displayResultingMST();

                System.out.println("\nPrim's Algorithm(based on Priority Queue): ");
                new PQPrimAlg().PQPrim();
                new PQPrimAlg().displayResultingMST();

                break;

            // 2- Prim's Algorithm (based on Min Heap) & Prim's Algorithm (based on Priority Queue)
            case 2:
                System.out.println("\nPrim's Algorithm (based on Min Heap): ");
                new MHPrimAlg().MHprim();
                new MHPrimAlg().displayResultingMST();

                System.out.println("\nPrim's Algorithm (based on Priority Queue): ");
                new PQPrimAlg().PQPrim();
                new PQPrimAlg().displayResultingMST();
                break;

            default:
                System.out.println("Invalid input!");
                break;
        }// END SWITCH

        // CLOSE SCANNER
        scan.close();

    }// END MAIN
}// END CLASS