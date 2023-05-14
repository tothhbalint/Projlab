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
 *
 */
public class Game {
    private final Team plumberTeam = new Team("Plumber");
    private final Team nomadTeam = new Team("Nomad");
    private NetworkMap map = new NetworkMap();

    public Game() {
        Proto.print("Game.Game()");
    }

    /**
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

    public void tick() {
        map.tick();
    }

    public Team getPlumberTeam() {
        return plumberTeam;
    }

    public Team getNomadTeam() {
        return nomadTeam;
    }

    public NetworkMap getMap() {
        return map;
    }

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
