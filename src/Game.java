//
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
        map.build();
        createNomadTeam();
        createPlumberTeam();
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
     * This method retruns the map
     * @return Map
     */
    public NetworkMap getMap() {
        return map;
    }

    /**
     * This method creates the Plumber team with 2 Players, and set the players' position to random position
     */
    public void createPlumberTeam() {
        Proto.print("Game.createPlumberTeam()");
        Plumber p1 = new Plumber();
        Plumber p2 = new Plumber();

        plumberTeam.addMember(p1);
        plumberTeam.addMember(p2);

        ArrayList<Source> ne = map.getSources();

        Random rand = new Random();

        int randomNum = rand.nextInt(ne.size());

        p1.setPosition(ne.get(randomNum));

        randomNum = rand.nextInt(ne.size());

        p2.setPosition(ne.get(randomNum));
    }

    /**
     * This method creates the Nomad team with 2 Players, and set the players' position to random position
     */
    public void createNomadTeam() {
        Proto.print("Game.createNomadTeam()");
        Nomad n1 = new Nomad();
        Nomad n2 = new Nomad();

        nomadTeam.addMember(n1);
        nomadTeam.addMember(n2);

        ArrayList<NetworkElement> ne = map.getElements();

        Random rand = new Random();

        int randomNum = rand.nextInt(ne.size());

        n1.setPosition(ne.get(randomNum));

        randomNum = rand.nextInt(ne.size());

        n2.setPosition(ne.get(randomNum));
    }
}
