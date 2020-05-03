import java.util.Scanner;

public class Main {
    Scanner kb = new Scanner(System.in);
//Framework
/*
Plan to have a dataset of player's stats -- mostly basic things (ERA, BA, OBP, SLUG, K/9, ETC)
These stats will dictate how they perform in game. Every hit, K, out, walk, will be recorded and be counted towards a player's stats.
These player stats will also dictate the team's stats.
Every single game will be recorded in a dataset (Using different data structures).
Recorded data will include hits, scores, wins, etc.
Standings will update as games are played.

*/
    public static void main(String[] args) {
        League MLB = new League();
        MLB.createLeague();
        System.out.println(MLB.allPlayers.retrieve("Charlie Morton").histK9);
        Menu();
//        Player[] temp = new Player[3];
//        temp[2] = new Player();
//        if (temp[2] == null)
//        System.out.println("yee");
        Team boston_red_sox = new Team("Boston Red Sox", MLB);
        Team tampa_bay_rays = new Team("Tampa Bay Rays", MLB);
        Team los_angeles_dodgers = new Team("Los Angeles Dodgers", MLB);
        Team custom_team1 = new Team("Aroldises", MLB);
        Games run = new Games(custom_team1,boston_red_sox,MLB);
        run.loadPlayers();
        run.simulateGame();
        MLB.allPlayers.retrieve("Charlie Chaplin");
    }

    public static void Menu(){
        System.out.println("Enter a letter to perform its action");
        System.out.println("s: Start simulation");
        System.out.println("p: Look up a player's historic stats");
        System.out.println("e: Exit");
        System.out.println("c: Change data structures");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println();
    }
}
