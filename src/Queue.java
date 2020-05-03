public interface Queue {
    public int size();
    public void add (Player toAdd);
    public Player remove();
    public void print();
    public Player peek ();
}
