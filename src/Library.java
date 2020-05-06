public interface Library {

    boolean exists(String s);
    Player retrieve(String s);
    void add(Player toAdd);
}
