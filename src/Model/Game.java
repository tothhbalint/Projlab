package Model;//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Game.java
//  @ Date : 16/04/2023
//
//

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is responsible for the game to run
 */
public class Game {
    /**
     * Plumber Team
     */
    private final Team plumberTeam = new Team("Plumber");
    /**
     * Nomad team
     */
    private final Team nomadTeam = new Team("Nomad");
    /**
     * Map
     */
    private NetworkMap map = new NetworkMap();

    /**
     *
     */
    public Game() {
        Proto.print("Game.Game()");
    }

    /**
     * This method starts the game,
     * Create the teams and builds the map
     * TODO
     */
    public void startGame() {
        boolean tempV = Proto.verbose;
        if(Proto.test){
            Proto.verbose = false;
        }
        map.build();
        createNomadTeam(2);
        createPlumberTeam(2);
        Proto.verbose = tempV;
    }

    public void startGame(ArrayList<String> plumberNames, ArrayList<String> nomadNames) {
        boolean tempV = Proto.verbose;
        if(Proto.test){
            Proto.verbose = false;
        }
        map.build();
        createNomadTeam(nomadNames.size());
        createPlumberTeam(plumberNames.size());
        Proto.verbose = tempV;
    }

    /**
     * TODO
     */
    public void endGame() {
    }

    /**
     * This method calls all the NetworkElements tick() in the map
     */
    public void tick() {
        map.tick();
    }

    /**
     *  This method return the Plumber Team
     * @return Plumber Team
     */
    public Team getPlumberTeam() {
        return plumberTeam;
    }

    /**
     * This method retruns the Nomad Team
     * @return Nomad Team
     */
    public Team getNomadTeam() {
        return nomadTeam;
    }

    /**
     * This method returns the map
     * @return Map
     */
    public NetworkMap getMap() {
        return map;
    }

    /**
     * This method creates the Plumber team with 2 Players, and set the players' position to a random NetworkElement
     */
    public void createPlumberTeam(int sizeOfTeam) {
        Proto.print("Game.createPlumberTeam()");
        for (int i = 0; i < sizeOfTeam; i++) {
            Plumber p = new Plumber();
            plumberTeam.addMember(p);
            ArrayList<NetworkElement> ne = map.getElements();
            Random rand = new Random();

            if (Proto.verbose || Proto.test)
                rand.setSeed(3);

            boolean placed = false;
            int randomNum = rand.nextInt(ne.size());
            while (!placed) {
                if (ne.get(randomNum).accept(p)) {
                    placed = true;
                } else {
                    randomNum = rand.nextInt(ne.size());
                }
            }
        }
    }

    /**
     * This method creates the Nomad team with 2 Players, and set the players' position to a random NetworkElement
     */
    public void createNomadTeam(int sizeOfTeam) {
        Proto.print("Game.createNomadTeam()");
        for (int i = 0; i < sizeOfTeam; i++) {
            Nomad n = new Nomad();
            nomadTeam.addMember(n);
            ArrayList<NetworkElement> ne = map.getElements();
            Random rand = new Random();

            if (Proto.verbose || Proto.test)
                rand.setSeed(3);

            boolean placed = false;
            int randomNum = rand.nextInt(ne.size());
            while (!placed) {
                if (ne.get(randomNum).accept(n)) {
                    placed = true;
                } else {
                    randomNum = rand.nextInt(ne.size());
                }
            }
        }
    }
}
