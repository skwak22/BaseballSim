public class Events {
    Player batter;
    Player pitcher;
    int battingTeam;
    int[] currentTeamEventCount;
    int[] team1EventCount = new int[7];
    int[] team2EventCount = new int[7];
    boolean[] bases;
    int[] Score;
    final int Strikeout = 6;
    final int BB = 0;
    final int Single = 1;
    final int Double = 2;
    final int Triple = 3;
    final int HR = 4;
    final int Out = 5;
    private double adjustedWHIP;
    private double hitChance;
    private double walkChance;
    private double rollVal;
    private double power;
    private double kChance;

    public Events(Player p, Player b, boolean[] bases, int[] Score, int battingTeam) {
        batter = b;
        pitcher = p;
        this.battingTeam = battingTeam;
        if (this.battingTeam == 1) {
            currentTeamEventCount = team1EventCount;
        }
        else currentTeamEventCount = team2EventCount;
        adjustedWHIP = pitcher.histWHIP/1.32;
        hitChance = batter.histBA*adjustedWHIP;
        walkChance = batter.histOBP-batter.histBA+hitChance;
        power = batter.histSLUG/.400;
        rollVal = Math.random();
        kChance = pitcher.histK9/18;
        this.Score = Score;
        this.bases = bases;
    }


    public boolean go() {
        if(rollVal <= hitChance) {
            double hitPower = power*Math.random();
            if (hitPower < .30) currentTeamEventCount[single()]++;
            else if (hitPower < .75) currentTeamEventCount[dbl()]++;
            else if (hitPower < .93) currentTeamEventCount[triple()]++;
            else currentTeamEventCount[homerun()]++;
            return false;
        }
        else if (rollVal <= walkChance) {
            currentTeamEventCount[walk()]++;
            return false;
        }
        else {
            double outOutcome = Math.random();
            if (outOutcome < (kChance-.20))currentTeamEventCount[strikeOut()]++;
            else currentTeamEventCount[regOut()]++;
            return true;
        }
    }

    private int strikeOut() {
        System.out.println(pitcher.name+" has struck out "+batter.name+"!");
        batter.newBatEvent(Strikeout);
        pitcher.pitchedK();
        return Strikeout;
    }


    private int regOut() {
        if (Math.random() < .5) System.out.println(batter.name+" grounded out!");
        else System.out.println(batter.name+" popped out!");
        batter.newBatEvent(Out);
        pitcher.outOccured();
        return Out;
    }

    private int walk() {
        System.out.println(pitcher.name+" has walked "+batter.name+".");
        pitcher.pitchedBB();
        batter.newBatEvent(BB);
        if (!bases[0]) bases[0]=true;
        else {
            if (bases[1]) {
                if (bases[2]){
                    System.out.println("Run Scored!");
                    pitcher.runScored();
                    Score[battingTeam-1]++;
                }
                else {
                    bases[2]=true;
                }
            }
            else {
                bases[1]=true;
            }
        }
        return BB;
    }

    private int single() {
        System.out.println(batter.name+" hit a single!");
        batter.newBatEvent(Single);
        advanceBaserunners(Single);
        return Single;
    }

    private int dbl() {
        System.out.println(batter.name+" hit a double!");
        batter.newBatEvent(Double);
        advanceBaserunners(Double);
        return Double;
    }
    private int triple() {
        System.out.println(batter.name+" hit a triple!");
        batter.newBatEvent(Triple);
        advanceBaserunners(Triple);
        return Triple;
    }
    private int homerun() {
        System.out.println(batter.name+" hit a homerun!");
        batter.newBatEvent(HR);
        advanceBaserunners(HR);
        return HR;
    }

    private void advanceBaserunners(int i) {
        for (int j=0;j<i;j++) {
            if (bases[2]) {
                Score[battingTeam - 1]++;
                System.out.println("Run scored!");
                pitcher.runScored();
                if (bases[1]) {
                    if (!bases[0]){
                        bases[1] = false;
                        if (j == 0) bases[0] = true;
                    }
                } else {
                    bases[2] = false;
                    if (bases[0]){
                        bases[1] = true;
                        if (j!=0) bases[0] = false;
                    }
                    else { if (j==0) bases[0] = true;}
                }
            } else {
                if (bases[1]) {
                    if (!bases[0]) {
                        bases[1] = false;
                        bases[2] = true;
                        if (j==0)bases[0] = true;
                    } else {
                        bases[2] = true;
                        if (j!=0) bases[0] = false;
                    }
                } else {
                    if (bases[0]){
                        bases[1] = true;
                        if (j!=0) bases[0] = false;
                    }
                    else {
                        if (j==0) bases[0] = true;
                    }
                }
            }
        }

    }
}
