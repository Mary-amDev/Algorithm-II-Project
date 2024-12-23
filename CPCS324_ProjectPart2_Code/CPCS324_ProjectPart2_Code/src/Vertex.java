public class Vertex {

    char label;
    int position;
    boolean isVisited;
    boolean labelIsValid = false;


    public Vertex(){

    }
    public Vertex(int position){
        this.position = position;
    }
    public Vertex(int position, char label){
        this.position = position;
        this.label = label;
        labelIsValid = true;
    }


    public int getVertPos() {
        return position;
    }
}
