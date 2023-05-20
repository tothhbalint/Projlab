package View;

import Model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameFrame extends JFrame {
    ArrayList<JGameElement> gameElements = new ArrayList<>();

    ArrayList<JNomad> nomads = new ArrayList<>();

    ArrayList<JPlumber> plumbers = new ArrayList<>();

    HashMap<Class<?>, Class<?>> elementTypes = new HashMap<>();

    HashMap<Class<?>, Class<?>> playerTypes = new HashMap<>();

    private final Game game = new Game();

    private boolean gameOver = false;

    int nomadPoints = 0, plumberPoints = 0;

    private boolean pluwin = false;

    private int round = 0;

    public GameFrame() {
        super("Drukkmakori Sivatag - Game PLUWIN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        elementTypes.put(Source.class, JSource.class);
        elementTypes.put(Pipe.class, JPipe.class);
        elementTypes.put(Pump.class, JPump.class);
        elementTypes.put(Cistern.class, JCistern.class);

        elementTypes.put(Nomad.class, JNomad.class);
        elementTypes.put(Plumber.class, JPlumber.class);

        runGame();
    }

    public void loadElements() {
        for (NetworkElement networkElement : game.getMap().getElements()) {
            try {
                gameElements.add((JGameElement) elementTypes.get(networkElement.getClass()).getConstructor().newInstance(0, 0));
            } catch (Exception e) {
                System.out.println("Error loading element");
            }
        }

        for (int i = 0; i < game.getPlumberTeam().getNoPlayers(); i++) {
            JPlumber plumber = new JPlumber(0, 0);
            plumber.setObject(game.getPlumberTeam().getPlayer(i));
            plumbers.add(plumber);
        }

        for (int i = 0; i < game.getNomadTeam().getNoPlayers(); i++) {
            JNomad nomad = new JNomad(0, 0);
            nomad.setObject(game.getNomadTeam().getPlayer(i));
            nomads.add(nomad);
        }
    }

    /*
    this is a function that runs in the main loop of the game
     */
    public void step() {
        for (JGameElement gameElement : gameElements) {
            ((NetworkElement) gameElement.getObject()).tick();
        }

        JPlayer currentPlayer = null;

        switch (round % 2) {
            case 0:
                currentPlayer = plumbers.get(round % plumbers.size());
            case 1:
                currentPlayer = nomads.get(round % nomads.size());
            default:
        }

        nomadPoints = game.getMap().getNomadPoints();
        plumberPoints = game.getMap().getPlumberPoints();

        if (nomadPoints >= 100 || plumberPoints >= 100) {
            gameOver = true;
            pluwin = plumberPoints > nomadPoints;
        }
    }

    /*
    The main loop of the game
     */
    public void runGame() {
        game.startGame();
        loadElements();

        while (!gameOver) {
            step();
            draw();
        }
    }

    public void draw() {
        for (JGameElement gameElement : gameElements) {
            gameElement.draw(getGraphics());
        }
    }
}
