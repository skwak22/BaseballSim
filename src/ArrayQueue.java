public class ArrayQueue implements Queue {
    private int size;
    private Player[] players;
    private int first;
    private int last;

    public ArrayQueue(){
        players = new Player[10];
        first = 0;
        last = 0;
        size = 0;
    }

    public int size(){
        return size;
    }

    public void add (Player toAdd) {
        if (size == players.length-1) {
            Player[] temp = new Player[size*2];
            for (int i=0;i<size;i++) {
                temp[i] = players[i];
            }
            players = temp;
        }
        players[last] = toAdd;
        last++;
        last = last%players.length;
        size++;
    }

    public Player remove() {
        if (size == 0) {
            return null;
        }
        Player temp = players[first];
        first++;
        first = first%players.length;
        size--;
        return temp;
    }
    public void print() {

    }
    public Player peek () {
        return players[first];
    }

}

