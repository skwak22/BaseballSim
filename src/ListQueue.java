public class ListQueue implements Queue {
    private Node head;
    private Node tail;
    private int size;
    public ListQueue(){
        head = new Node(null);
        tail = head;
        size = 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void add(Player toAdd) {
        tail.next = new Node(toAdd);
        tail.next.previous = tail;
        tail = tail.next;
    }
    @Override
    public Player remove() {
        if (head.next == null) return null;
        Player temp = head.next.value;
        head.next = head.next.next;
        head.next.previous = head;
        return temp;
    }
    @Override
    public void print() {

    }

    @Override
    public Player peek() {
        if (head.next == null) return null;
        return head.next.value;
    }

}
