public class Vertex{
    private String name;
    private int id; // index number in adjacency matrix
    private boolean marked = false;

    public Vertex(String s, int id){
        this.name = s;
        this.id = id;
    }

    public String getName() {return name;}
    public int getId() { return id; }
    public void setMarked() { this.marked = true; }
    public void setNotMarked() { this.marked = false; }
    public boolean isMarked() { return marked; }

    @Override
    public boolean equals(Object o) {
            return (o instanceof Vertex) && (((Vertex) o).getName().equals(name));
    }
    @Override
    public int hashCode() { return name.hashCode(); }
    @Override
    public String toString(){ return name; }
}

