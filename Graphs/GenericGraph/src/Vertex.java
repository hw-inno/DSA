public class Vertex{
    private String name;
    private int id;
    //List<Vertex> neighbours;

    public Vertex(String s, int id){
        this.name = s;
        this.id = id;
    }

    public String getName() {return name;}
    public int getId() { return id; }

    @Override
    public boolean equals(Object o) {
        /*if( o instanceof String)
            return o.equals(name);
        else*/
            return (o instanceof Vertex) && (((Vertex) o).getName().equals(name));

    }
    @Override
    public int hashCode() { return name.hashCode(); }
    @Override
    public String toString(){ return name; }

    /*public void addNeighbour(Vertex v){
        this.neighbours.add(v);
    }*/
}

