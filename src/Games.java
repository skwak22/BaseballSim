public class Games {
    public int[] Score = new int[]{0,0};
    public int[] seriesScore = new int[]{0,0};
    public int Inning = 1;
    public boolean top = true;
    public int outs = 0;
    public int series = 1;
    public boolean[] bases = new boolean[3];
    public Team team1;
    public Team team2;
    public Queue t1B;
    public Queue t2B;
    public Player[] t1P = new Player[4];
    public Player[] t2P = new Player[4];
    public League league;
    public boolean ListQueue = false;
    public Events event;

    public Games(Team one, Team two, League currentLeague){
        team1 = one;
        team2 = two;
        league = currentLeague;
    }

    public void loadPlayers(){
        if (ListQueue) {
            t1B = new ListQueue();
            t2B = new ListQueue();
        }
        else {
            t1B = new ArrayQueue();
            t2B = new ArrayQueue();
        }
        for (int i=0; i<9; i++) {
            t1B.add(league.allPlayers.retrieve(team1.lineup[i]));
 //           System.out.println(team1.lineup[i]+ " retrieved!");
            t2B.add(league.allPlayers.retrieve(team2.lineup[i]));
 //           System.out.println(team2.lineup[i]+ " retrieved!");
        }
        for (int i=0;i<3;i++){
            t1P[i] = league.allPlayers.retrieve(team1.SP[i]);
   //         System.out.println(team1.SP[i]+ " retrieved!");
            t2P[i] = league.allPlayers.retrieve(team2.SP[i]);
  //          System.out.println(team2.SP[i]+ " retrieved!");
        }
        t1P[3] = league.allPlayers.retrieve(team1.CP);
        t2P[3] = league.allPlayers.retrieve(team2.CP);
    }

    public void simulateGame() {
        Player SP1 = t1P[series-1];
        Player SP2 = t2P[series-1];
        System.out.println("It's game "+ series +" of the series between the " +team1.name +" and the "+team2.name+"!");
        System.out.println("Pitching for " +team1.name+" is "+SP1.name+".");
        System.out.println("Pitching for "+team2.name+" is "+SP2.name+".");
        while (Inning <= 9 || Score[0]==Score[1]) {
            if(Inning<8) simulateInning(SP1, SP2);
            else simulateInning(t1P[3],t2P[3]);
        }
        System.out.println();
        if (Score[0] > Score[1]) {
            SP1.W++;
            SP2.L++;
            System.out.println(team1.name + " wins over " + team2.name + " with a score of " + Score[0] + " to " + Score[1] + ".");
            seriesScore[0]++;
        }
        else {
            SP1.L++;
            SP2.W++;
            System.out.println(team2.name + " wins over " + team1.name + " with a score of " + Score[1] + " to " + Score[0] + ".");
            seriesScore[1]++;
        }
        series++;
        System.out.println();
        reset();
    }

    private void reset() {
        Score = new int[]{0,0};
        Inning = 1;
        top = true;
        outs = 0;
        bases = new boolean[3];
    }

    private void simulateInning(Player SP1, Player SP2) {
        outs = 0;
        for (int i=0;i<3;i++) bases[i] = false;
        if (top) {
            System.out.println("It's the top of inning number "+Inning+".");
            System.out.println(SP2.name+" is pitching.");
            while (outs<3)if (atBat(SP2, 1)) outs++;
            top = false;
        }
        else {
            System.out.println("It's the bottom of inning number "+Inning+".");
            System.out.println(SP1.name+" is pitching.");
            while (outs<3)if (atBat(SP1, 2)) outs++;
            Inning++;
            top = true;
        }
    }

    private boolean atBat(Player pitcher, int battingTeam) {
        Player CB;
        if (battingTeam == 1) CB = t1B.remove();
        else CB = t2B.remove();
        if (battingTeam == 1) t1B.add(CB);
        else t2B.add(CB);
        event = new Events(pitcher, CB, bases, Score, battingTeam);
        return event.go();
    }

}
