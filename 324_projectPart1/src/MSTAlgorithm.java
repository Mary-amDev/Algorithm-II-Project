import java.util.ArrayList;

public class MSTAlgorithm {
    
    static ArrayList<Edge> MSTResultList = new ArrayList<Edge>();
    static long totalTime;

    public MSTAlgorithm() {

    }
    
    // DISPLAY MINIMUM SPANNING TREE RESULT AND CLEAR THE ARRAY LIST
    public void displayResultingMST() {
        int minimumCost = 0;
        for (int i = 0; i < MSTResultList.size(); i++) {
            minimumCost += MSTResultList.get(i).weight;
            //System.out.println( (i+1) + " = " + MSTResultList.get(i).toString());
        }// END FOR LOOP
        System.out.println("\tMinimum Cost Spanning Tree = " + minimumCost);
        System.out.println("\tTotal runtime = " + totalTime + " msec");

        // CLEAR ARRAY LIST
        MSTResultList.clear();
    }// END METHOD
}// END CLASS
