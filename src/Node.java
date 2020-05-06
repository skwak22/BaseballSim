public class Node {
    Player value;
    Node next, previous;

    public Node(Player v) {
        this.value = v;
    }

    public boolean exists(String s) {
        if (value.name.equals(s)) return true;
        else if (next == null) return false;
        else {
            return next.exists(s);
        }
    }

    public Player retrieve(String s) {
        if (value.name.equals(s)) return value;
        else if (next == null) return null;
        else {
            return next.retrieve(s);
        }
    }
}
