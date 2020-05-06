public class ListQueue implements Queue, Library{
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
    public boolean exists(String s) {
        return head.next.exists(s);
    }

    public Player retrieve(String s) {
        return head.next.retrieve(s);
    }

        @Override
    public Player peek() {
        if (head.next == null) return null;
        return head.next.value;
    }

}
