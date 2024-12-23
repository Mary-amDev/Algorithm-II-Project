public class MHPrimAlg extends MSTAlgorithm {

    boolean inHeap[] = new boolean[Graph.verList.size()];
    int key[] = new int[Graph.verList.size()];

    public void MHprim() {
        // START TIME
        long start = System.currentTimeMillis();

        // INITIALIZE KEY AS MAX_VAL EXCEPT THE FIRST VERTEX FOR WHICH KEY WILL 0
        for (int i = 0; i < Graph.verList.size() ; i++) {
            Graph.verList.get(i).parent = -1;
            inHeap[i] = true;
            key[i]= Integer.MAX_VALUE;
        }// END FOR LOOP

        // SET INDEX 0 WITH ELEMENT 0
        key[0] = 0;

        while (!isEmpty()) {
            // PICK THE VERTEX THAT HAS MINIMUM KEY
            int tempVer = pickMin(); 

            // EXTRACT THE MIN VERTEX FROM THE HEAP
            inHeap[tempVer] = false;

            for (int i = 0; i < Vertex.adjList.get(tempVer).size(); i++) {
                int destVer = Vertex.adjList.get(tempVer).get(i).destVer.label;
                int tempWeight = Vertex.adjList.get(tempVer).get(i).weight;
                if (inHeap[destVer] && tempWeight < key[destVer]) { 
                    // UPDATE KEY
                    key[destVer] = tempWeight;
                    // UPDATE PARENT
                    Vertex.adjList.get(tempVer).get(i).destVer.parent = tempVer;
                }// END IF
            }// END FOR LOOP i
        }// END WHILE LOOP

        // FINISH TIME
        long finish = System.currentTimeMillis();
        totalTime = finish - start;

        for (int i = 0; i < Vertex.adjList.size(); i++) {
            Vertex tempVer = Graph.verList.get(i);
            int parentVer = tempVer.parent;
            if(parentVer == -1) parentVer = tempVer.label;
            for (int j = 0; j < Vertex.adjList.get(i).size(); j++) {
                Edge temp = Vertex.adjList.get(i).get(j);
                if (temp.destVer.label == parentVer) {
                    MSTResultList.add(temp);
                }// END IF
            }// END FOR LOOP j
        }// END FOR LOOP i
    }// END METHOD

    // PICK SMALLEST VARTEX IN HEAP
    public int pickMin() {
        int min = Integer.MAX_VALUE;
        int label = 0;
        for (int i = 0; i < key.length ; i++) {
            if ( inHeap[i] == true && key[i] < min ){
                min = key[i];
                label = i;
            }// END IF
        }// END FOR LOOP
        return label;
    }// END pickMin METHOD

    // CHECK IF HEAP IS EMPTY
    public boolean isEmpty() {
        for (int i = 0; i < inHeap.length; i++) {
            if (inHeap[i] == true) {
                return false;
            }// END IF
        }// END FOR LOOP
        return true;
    }// END isEmpty METHOD

}// END CLASS
