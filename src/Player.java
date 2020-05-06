public class Player {
    String name, position, team;
    boolean isPitcher;
    int AB, Hits, Singles, Doubles, Triples, HRs, BB, SO = 0;
    int K, PBB, W, L, outsPitched, Runs = 0;
    double IP = 0;
    double histBA, histOBP, histSLUG, histOPS;
    double histERA, histK, histWHIP, histInnP, histK9;

    public Player() {
        isPitcher = false;
    }

    public double[] getBatStats(){
        if (!isPitcher)
            return new double[]{histBA,histOBP,histSLUG,histOPS};
        else
            return new double[] {0.150, 0.300, 0.400, 0.500};
    }

    public double[] getPitcherStats(){
        if (isPitcher) {
            return new double[]{histInnP, histERA, histK9, histWHIP};
        }
        else return new double[]{35,2.5,1.9,13};
    }

    public void newBatEvent(int i){
        switch (i) {
            case 6:
                SO++;
                break;
            case 0:
                BB++;
                break;
            case 1:
                Singles++;
                Hits++;
                break;
            case 2:
                Doubles++;
                Hits++;
                break;
            case 3:
                Triples++;
                Hits++;
            case 4:
                HRs++;
                Hits++;
                break;
            case 5:
                break;
        }
        AB++;
    }

    public void pitchedK() {
        K++;
        outsPitched++;
        updateIP();
    }

    public void pitchedBB() {
        PBB++;
    }

    public void runScored(){
        Runs++;
    }

    public void outOccured() {
        outsPitched++;
        updateIP();
    }

    public void pitcherOutcome(boolean won){
        if (won) W++;
        else L++;
    }

    public void updateIP() {
        int Innings = outsPitched/3;
        if (outsPitched%3 == 0) {
            IP = Innings;
        }
        else IP = ((double)Innings + (outsPitched%3 * 0.1));
    }


    public void updatePitcherStats() {
        isPitcher = true;
        histK9 = (histK/(histInnP/9));
    }

    public void historicStats() {
        if (this.isPitcher) {
            System.out.println(this.name+ " is a pitcher. Here are their historic stats:");
            System.out.println("ERA: "+this.histERA);
            System.out.println("Strikeouts: "+this.histK);
            System.out.println("WHIP: "+this.histWHIP);
            System.out.println("K/9: "+this.histK9);
        }
        else {
            System.out.println(this.name+ " is a batter. Here are their historic stats:");
            System.out.println("BA: "+this.histBA);
            System.out.println("OBP: "+this.histOBP);
            System.out.println("SLUG: "+this.histSLUG);
            System.out.println("OPS: "+this.histOPS);
        }
    }
    public void currentStats() {
        if (this.isPitcher) {
            //int K, PBB, W, L, outsPitched, Runs = 0;
            //double IP = 0;
            System.out.println(this.name+ " is a pitcher. Here are their current series stats:");
            System.out.println("Innings pitched: "+this.IP);
            System.out.println("Strikeouts: "+this.K);
            System.out.println("Pitched base on balls: "+this.PBB);
            System.out.println("Wins: "+this.W);
            System.out.println("Losses: "+this.L);
            System.out.println("Runs allowed: "+this.Runs);
            System.out.println("Outs pitched: "+this.outsPitched);
        }
        else {
            System.out.println(this.name+ " is a batter. Here are their current series stats:");
            //int AB, Hits, Singles, Doubles, Triples, HRs, BB, SO = 0;

            System.out.println("At bats: "+this.AB);
            System.out.println("Hits: "+this.Hits);
            System.out.println("Singles: "+this.Singles);
            System.out.println("Doubles: "+this.Doubles);
            System.out.println("Triples: "+this.Triples);
            System.out.println("Homeruns: "+this.HRs);
            System.out.println("Base on balls: "+this.BB);
            System.out.println("Strikeouts: "+this.SO);
        }
    }
}