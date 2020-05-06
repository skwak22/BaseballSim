import java.util.Scanner;

public class Main {

    private static String pitcherFile = "C:\\Users\\sungh\\Desktop\\CS\\BaseballSimulator\\src\\Pitcher-Stats.csv";
    private static String batterFile = "C:\\Users\\sungh\\Desktop\\CS\\BaseballSimulator\\src\\Batter-Stats.csv";
//Framework
/*
Plan to have a dataset of player's stats -- mostly basic things (ERA, BA, OBP, SLUG, K/9, ETC)
These stats will dictate how they perform in game. Every hit, K, out, walk, will be recorded and be counted towards a player's stats.
Recorded data will include hits, scores, wins, etc.

*/
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        League MLB = new League(pitcherFile, batterFile);
        MLB.createLeague();
        System.out.println("All players added!");
        System.out.println("------------------");
        System.out.println("Welcome to Baseball Simulator 2020!");
        System.out.println("");

//        Player[] temp = new Player[3];
//        temp[2] = new Player();
//        if (temp[2] == null)
//        System.out.println("yee");
        Team boston_red_sox = new Team("Boston Red Sox", MLB);
        Team tampa_bay_rays = new Team("Tampa Bay Rays", MLB);
        Team custom_team1;
        Team custom_team2;
        Team inSlot1 = tampa_bay_rays;
        Team inSlot2 = boston_red_sox;
        int series = 1;
        Games run = new Games(inSlot1,inSlot2,MLB);
        boolean recur = true;
        while (recur) {
            if (series < 4) System.out.println("It is currently game "+series+" of the series between the " +inSlot1.name+" and the "+inSlot2.name+"!");
            else {
                System.out.println("The series has ended between the " +inSlot1.name+" and the "+inSlot2.name+"!");
                System.out.println("The final outcome is "+run.seriesScore[0]+" to "+run.seriesScore[1]);
                System.out.println();
            }
            Menu();
            String input = kb.nextLine();
            switch (input) {
                case "e":
                    System.out.println("Thanks for playing!");
                    recur = false;
                    break;
                case "s":
                    if (series == 4) {
                        System.out.println("The series is over!");
                        System.out.println();
                        break;
                    }
                    run.loadPlayers();
                    run.simulateGame();
                    series++;
                    break;
                case "p":
                    historicLookup(MLB);
//add in player lookup
                    break;
                case "1":
                    if (series != 1){
                        System.out.println("You cannot change teams mid-series!");
                        break;
                    }
                    inSlot1 = changeTeam(MLB);
                    run.team1 = inSlot1;
                    break;
                case "2":
                    if (series != 1){
                        System.out.println("You cannot change teams mid-series!");
                        break;
                    }
                    inSlot2 = changeTeam(MLB);
                    run.team2 = inSlot2;
                    break;
                case "l":
                    currentLookup(MLB);
                    break;
                default:
                    System.out.println("Please enter a valid letter!");
            }
        }
    }

    private static Team changeTeam(League MLB) {
        System.out.println("What is the new team's name?");
        Team temp;
        Scanner kb = new Scanner (System.in);
        String current;
        current = kb.nextLine();
        temp = new Team(current,MLB);
        return temp;
    }

    private static void currentLookup(League MLB) {
        Scanner kb = new Scanner (System.in);
        boolean recur = true;
        String current;
        while (recur) {
            System.out.println("Enter a valid player name to look them up. Enter e to exit.");
            current = kb.nextLine();
            if (current.equals("e")) break;
            if (MLB.allPlayers.exists(current)) {
                MLB.allPlayers.retrieve(current).currentStats();
                recur = false;
            }
            kb.nextLine();
            System.out.println();
        }
    }

    private static void historicLookup(League MLB) {
        Scanner kb = new Scanner (System.in);
        boolean recur = true;
        String current;
        while (recur) {
            System.out.println("Enter a valid player name to look them up. Enter e to exit.");
            current = kb.nextLine();
            if (current.equals("e")) break;
            if (MLB.allPlayers.exists(current)) {
                MLB.allPlayers.retrieve(current).historicStats();
                recur = false;
            }
            kb.nextLine();
            System.out.println();
        }
    }

    private static void Menu(){
        System.out.println("Enter a letter to perform its action");
        System.out.println("s: Simulate game");
        System.out.println("p: Look up a player's historic stats");
        System.out.println("l: Look up player stats from the current series");
        System.out.println("1: Change first team");
        System.out.println("2: Change second team");
        System.out.println("e: Exit");
        System.out.println();
    }
}
