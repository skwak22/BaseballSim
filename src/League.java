import java.io.*;
import java.util.Scanner;

public class League {
    public Library allPlayers;
    private String pitcherFile;
    private String batterFile;

    public League(String pitcherFile, String batterFile) {
        this.pitcherFile = pitcherFile;
        this.batterFile = batterFile;
        //allPlayers = new Table(300);
        allPlayers = new ListQueue();
    }

    void createLeague() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(batterFile));
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
                //allPlayers2.add(cb);
            }
            csvReader = new BufferedReader(new FileReader(pitcherFile));
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
