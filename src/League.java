import java.io.*;
import java.util.Scanner;

public class League {
    public Table allPlayers;

    public League() {
        allPlayers = new Table(300);
    }

    void createLeague() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\sungh\\Desktop\\CS\\BaseballSim\\src\\Batter-Stats.csv"));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Player cb = new Player();
                cb.name=data[0];
                cb.position=data[2];
                cb.team=data[1];
                if (data[3].charAt(0) == '0')
                    cb.histBA = Double.parseDouble(data[3]);
                else
                    cb.histBA=0.150;
                if (data[4].charAt(0) == '0')
                    cb.histOBP=Double.parseDouble(data[4]);
                else
                    cb.histOBP=0.250;
                if (data[5].charAt(0) == '0')
                    cb.histSLUG=Double.parseDouble(data[5]);
                else
                    cb.histSLUG=0.300;
                if (data[6].charAt(0) == '0' || data[6].charAt(0)=='1')
                    cb.histOPS=Double.parseDouble(data[6]);
                else
                    cb.histOPS=0.500;
                allPlayers.add(cb);
            }
            csvReader = new BufferedReader(new FileReader("C:\\Users\\sungh\\Desktop\\CS\\BaseballSim\\src\\Pitcher-Stats.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Player cp = new Player();
                cp.name=data[0];
                cp.position="P";
                cp.team=data[1];
                if (Character.isDigit(data[2].charAt(0)))
                    cp.histInnP = Double.parseDouble(data[2]);
                else
                    cp.histInnP=1.0;
                if (Character.isDigit(data[3].charAt(0)))
                    cp.histK=Double.parseDouble(data[3]);
                else
                    cp.histK=0;
                if (Character.isDigit(data[4].charAt(0)))
                    cp.histERA=Double.parseDouble(data[4]);
                else
                    cp.histERA=36.00;
                if (Character.isDigit(data[5].charAt(0)))
                    cp.histWHIP=Double.parseDouble(data[5]);
                else
                    cp.histWHIP=3.00;
                cp.updatePitcherStats();
                allPlayers.add(cp);
                System.out.println(cp.name+" added.");
            }

        //    System.out.println("All players added to the hash table.");
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Table {
    private int size;
    private List[] table;

    public Table(int tableSize) {
        size = tableSize;
        table = new List[size];
        for (int i = 0; i < size; i++)
            table[i] = new List();
    }

    private int hash(String s) {
        int val=1;
        char[] c = s.toCharArray();
        for (char z : c) {
            val = val+((int)z-48);
        }
        val = val+c.length;
        return Math.abs(val%size);
    }

    public void add(Player s) {
        if (find(s)) return;
        int temp = hash(s.name);
        table[temp].add(s);
        //System.out.println(s.name + " added!");
    }

    public boolean find(Player s) {
        int temp = hash(s.name);
        return table[temp].find(s) != null;
    }

    public boolean exists(String s) {
        try {
            int temp = hash(s);
            Player foundPlayer = table[temp].retrieve(s).value;
            if (foundPlayer != null) return true;
        }
        catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public Player retrieve(String s){
        try {
            int temp = hash(s);

//            if (table[temp].retrieve(s).value == null) {
//                System.out.println("This player does not exist.");
//                return null;
//            }
            Player foundPlayer = table[temp].retrieve(s).value;
            return foundPlayer;
        }
        catch(NullPointerException e) {
            System.out.println("Can't find player.");
            int temp = hash("Joey Votto");
            return table[temp].retrieve("Joey Votto").value;
        }
    }

    public void remove(Player s) {
        if (!find(s)) return;
        int temp = hash(s.name);
        table[temp].remove(s);
    }

    public int maxInSlot() {
        int temp=0;
        for (int i=0; i<size; i++){
            int current = table[i].size();
            if (temp < current) {
                temp = current;
            }
        }
        return temp;
    }
}


class List {
    private Node first;
    private Node last;
    private int count;

    public List() {
        count = 0;
        first = null;
        last = null;
    }

    public int size() {
        return count;
    }

    public void add(Player toAdd) {
        if (first == null && last == null) {
            first = new Node(toAdd);
            last = first;
        }
        else {
            last.next = new Node(toAdd);
            last.next.previous = last;
            last = last.next;
        }
        count++;
    }

    public Node find(Player toFind) {
        Node temp = first;
        return helperFind(temp, toFind);
    }

    public Node retrieve(String toRetrieve) {
        Node temp = first;

        return helperRetrieve(temp, toRetrieve);
    }

    private Node helperRetrieve(Node temp, String toRetrieve){
        if (temp == null) {
            System.out.println(toRetrieve+" not found.");
            return null;}
        else if (temp.value.name.equals(toRetrieve))
            return temp;
        else return helperRetrieve(temp.next, toRetrieve);
    }

    private Node helperFind(Node temp, Player toFind){
        if (temp == null) return null;
        else if (temp.value.name.equals(toFind.name))
            return temp;
        else return helperFind(temp.next, toFind);
    }

    public void remove(Player toRemove) {
        //Must account for three special cases: first node, single node, last node
        Node tR = find(toRemove);
        if (tR.next == null && tR.previous == null) {
            first = null;
            last = null;
        }
        else if (tR.previous == null) {
            first = first.next;
            first.previous = null;
        }
        else if (tR.next == null) {
            last = last.previous;
            last.next = null;
        }
        else {
            Node prev = tR.previous;
            Node nex = tR.next;
            prev.next = nex;
            nex.previous = prev;
        }
        count--;
    }
}