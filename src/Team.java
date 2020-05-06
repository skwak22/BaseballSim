import java.util.Scanner;

public class Team {
    public League MLB;
    public String name;
    public String[] lineup = new String[9];
    public String[] SP = new String[3];
    public String CP;
    Scanner kb = new Scanner(System.in);

    public Team(String name, League MLB) {
        this.name = name;
        this.MLB = MLB;
        if (name.equals("Tampa Bay Rays")) {
            lineup[0] = "Brandon Lowe";
            lineup[1] = "Austin Meadows";
            lineup[2] = "Yandy Diaz";
            lineup[3] = "Ji-Man Choi";
            lineup[4] = "Hunter Renfroe";
            lineup[5] = "Michael Perez";
            lineup[6] = "Willy Adames";
            lineup[7] = "Kevin Kiermaier";
            lineup[8] = "Mike Zunino";
            SP[0] = "Charlie Morton";
            SP[1] = "Blake Snell";
            SP[2] = "Tyler Glasnow";
            CP = "Nick Anderson";

        }
        else if (name.equals("Los Angeles Dodgers")) {
            lineup[0] = "Mookie Betts";
            lineup[1] = "Max Muncy";
            lineup[2] = "Justin Turner";
            lineup[3] = "Cody Bellinger";
            lineup[4] = "Joc Pederson";
            lineup[5] = "Corey Seager";
            lineup[6] = "Will Smith";
            lineup[7] = "Gavin Lux";
            lineup[8] = "Austin Barnes";
            SP[0] = "Clayton Kershaw";
            SP[1] = "Walker Buehler";
            SP[2] = "David Price";
            CP = "Kenley Jansen";
        }
        else if (name.equals("Boston Red Sox")) {
            lineup[0] = "Andrew Benintendi";
            lineup[1] = "Rafael Devers";
            lineup[2] = "Xander Bogaerts";
            lineup[3] = "J.D. Martinez";
            lineup[4] = "Mitch Moreland";
            lineup[5] = "Michael Chavis";
            lineup[6] = "Alex Verdugo";
            lineup[7] = "Christian Vazquez";
            lineup[8] = "Jackie Bradley";
            SP[0] = "Eduardo Rodriguez";
            SP[1] = "Nathan Eovaldi";
            SP[2] = "Martin Perez";
            CP = "Brandon Workman";
        }

        else createTeamRoster();

    }

    private void createTeamRoster() {
        String current;
        System.out.println("This team does not exist in the current database! Manually insert players below:");
        for (int i=0;i<9;i++) {
            boolean recur = true;
            while (recur) {
                int j = i+1;
                System.out.println("Enter a name for batter number " + j + ".");
                current = kb.nextLine();
                if (MLB.allPlayers.exists(current)) {
                    lineup[i] = current;
                    recur = false;
                }
                else {
                    System.out.println("This player does not exist. Try again.");
                }
            }
        }
        for (int i=0;i<3;i++) {
            boolean recur = true;
            while (recur) {
                int j = i+1;
                System.out.println("Enter a name for starting pitcher number " + j + ".");
                current = kb.nextLine();
                if (MLB.allPlayers.exists(current)) {
                    SP[i] = current;
                    recur = false;
                }
                else {
                    System.out.println("This player does not exist. Try again.");
                }
            }
        }
        boolean recur = true;
        while (recur) {
            System.out.println("Enter a name for your closing pitcher.");
            current = kb.nextLine();
            if (MLB.allPlayers.exists(current)) {
                SP[2] = current;
                recur = false;
            }
            else {
                System.out.println("This player does not exist. Try again.");
            }
        }

    }


}
