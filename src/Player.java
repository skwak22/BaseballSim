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
}