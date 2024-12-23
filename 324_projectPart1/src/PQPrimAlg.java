import java.util.*;

public class PQPrimAlg extends MSTAlgorithm {

    // CREATE AN EMPTY PRIORITY QUEUE
    // Map (key - value) -> key = key(weight), value = vertex label
    PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(Graph.verList.size(),
            new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Map.Entry<Integer, Integer> m1, Map.Entry<Integer, Integer> m2) {
                    int key1 = m1.getKey();
                    int key2 = m2.getKey();
                    return key1 - key2;
                }// END COMPARE
            });

    boolean inMST[] = new boolean[Graph.verList.size()];
    int key[] = new int[Graph.verList.size()];

    public void PQPrim() {
        // START TIME
        long start = System.currentTimeMillis();

        // INITIALIZE KEYS OF ALL VERTICES AS INFINITE, PARENT OF EVERY VERTEX AS -1 AND
        // NOT PART OF MST YET.
        for (int i = 0; i < Graph.verList.size(); i++) {
            key[i] = Integer.MAX_VALUE;
            Graph.verList.get(i).parent = -1;
            inMST[i] = false;
        } // END FOR LOOP

        // INSERT AN ENTRY REPRESENTING A MAPPING INTO PQ AND MAKE ITS KEY AS 0
        key[0] = 0;
        // Map.Entry<Integer, Integer> temp = new AbstractMap.SimpleEntry<Integer,
        // Integer>(key[0],0);
        priorityQueue.add(new AbstractMap.SimpleEntry<Integer, Integer>(key[0], 0));

        // LOOP WHILE QUEUE IS NOT EMPTY 
        while (!priorityQueue.isEmpty()) {

            // EXTRACT MINIMUM KEY VERTEX FROM PQ.
            Map.Entry<Integer, Integer> tempVer = priorityQueue.poll(); // u

            // INCLUDE IT IN MST
            inMST[tempVer.getValue()] = true;

            // ITERATE THROUGH ALL THE ADJACENT VERTICES OF ABOVE VERTEX u
            for (int i = 0; i < Vertex.adjList.get(tempVer.getValue()).size(); i++) {

                Vertex destVer = Vertex.adjList.get(tempVer.getValue()).get(i).destVer; // v
                int tempWeight = Vertex.adjList.get(tempVer.getValue()).get(i).weight; // v - u weight

                // IF VERTEX v IS NOT IN MST 
                if (inMST[destVer.label] == false && key[destVer.label] > tempWeight) {

                    // UPDATE KEY
                    key[destVer.label] = tempWeight;

                    // CREATE A MAP OBJECT WITH VERTEX V AND KEY = U-V WEIGHT AND INSERT IT INTO
                    // PRIORITY QUEUE.
                    priorityQueue.add(new AbstractMap.SimpleEntry<Integer, Integer>(key[destVer.label], destVer.label));

                    // UPDATE PARENT
                    destVer.parent = tempVer.getValue();
                } // END IF
            } // END FOR LOOP
        } // END WHILE LOOP

        // FINISH TIME
        long finish = System.currentTimeMillis();
        totalTime = finish - start;

        for (int i = 0; i < Vertex.adjList.size(); i++) {
            Vertex tempVer = Graph.verList.get(i);
            int parentVer = tempVer.parent;
            // IF PARENT -1 THEN VERTEX IS ROOT
            if (parentVer == -1) parentVer = tempVer.label;
            for (int j = 0; j < Vertex.adjList.get(i).size(); j++) {
                Edge temp = Vertex.adjList.get(i).get(j);
                if (temp.destVer.label == parentVer) {
                    MSTResultList.add(temp);
                }// END IF
            }// END FOR LOOP j
        }// END FOR LOOP i
    }// END METHOD
}// END CLASS
