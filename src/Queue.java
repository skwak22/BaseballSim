public interface Queue {
    public int size();
    public void add (Player toAdd);
    public Player remove();
    public Player peek ();
}
