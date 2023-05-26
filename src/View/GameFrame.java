package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class GameFrame extends JFrame {
    static ArrayList<JGameElement> gameElements = new ArrayList<>();

    ArrayList<JNomad> nomads = new ArrayList<>();

    ArrayList<JPlumber> plumbers = new ArrayList<>();

    HashMap<Class<?>, Class<?>> elementTypes = new HashMap<>();
    private final Object lock = new Object();
    private GamePanel gamePanel;
    private ControlsPanel controlsPanel;

    private static final Game game = new Game();

    private boolean gameOver = false;

    int nomadPoints = 0, plumberPoints = 0;

    private boolean pluwin = false;

    private int round = 0;

    private volatile boolean userAction = false;



    private JPlayer currentPlayer;
    private int currentNomadIndex = 0, currentPlumberIndex = 0;

    public GameFrame(ArrayList<String> plumberNames, ArrayList<String> nomadNames) {
        elementTypes.put(Source.class, JSource.class);
        elementTypes.put(Pipe.class, JPipe.class);
        elementTypes.put(Pump.class, JPump.class);
        elementTypes.put(Cistern.class, JCistern.class);

        setLayout(new BorderLayout());
        setTitle("Drukkmakori sivatag - Game");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadElements(plumberNames, nomadNames);

        gamePanel = new GamePanel(gameElements, nomads, plumbers);
        controlsPanel = new ControlsPanel(this, lock);

        controlsPanel.setPreferredSize(new Dimension(400, 720));
        gamePanel.setPreferredSize(new Dimension(880, 720));

        getContentPane().add(controlsPanel, BorderLayout.WEST);
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        setVisible(true);
        setResizable(false);
        pack();
    }

    public void loadElements(ArrayList<String> plumberNames, ArrayList<String> nomadNames) {
        game.startGame(plumberNames, nomadNames);
        for (NetworkElement networkElement : game.getMap().getElements()) {
            try {
                Class<?> target = elementTypes.get(networkElement.getClass());
                JGameElement element = (JGameElement) target.getConstructors()[0].newInstance(0, 0);
                element.setObject(networkElement);
                gameElements.add(element);
            } catch (Exception e) {
                System.out.println("Error loading element");
            }
        }

        for (JGameElement gameElement : gameElements) {
            if (gameElement.getClass().equals(JPipe.class)) {
                NetworkElement element = (NetworkElement) gameElement.getObject();
                for (NetworkElement connectionElement : element.getConnections()) {
                    JGameElement connection = findElement(connectionElement);
                    ((JPipe) gameElement).addConnection(connection);
                }
                ((JPipe) gameElement).calcMiddle();
            }
        }

        for (int i = 0; i < game.getPlumberTeam().getNoPlayers(); i++) {
            JPlumber plumber = new JPlumber(0, 0);
            plumber.setObject(game.getPlumberTeam().getPlayer(i));
            plumber.setName(plumberNames.get(i));
            plumbers.add(plumber);
        }

        for (int i = 0; i < game.getNomadTeam().getNoPlayers(); i++) {
            JNomad nomad = new JNomad(0, 0);
            nomad.setObject(game.getNomadTeam().getPlayer(i));
            nomad.setName(nomadNames.get(i));
            nomads.add(nomad);
        }

        currentPlayer = plumbers.get(0);
    }

    /**
    * this is a function that runs in the main loop of the game
    */
    public void step() {
        for (JGameElement gameElement : gameElements) {
            ((NetworkElement) gameElement.getObject()).tick();
        }

        boolean chosen = false;
        while(!chosen){ //added this, so that stuck players can be skipped
            switch (round % 2) {
                case 0 -> {
                    currentPlayer = plumbers.get(currentPlumberIndex);
                    currentPlumberIndex = (currentPlumberIndex + 1) % plumbers.size();
                }
                case 1 -> {
                    currentPlayer = nomads.get(currentNomadIndex);
                    currentNomadIndex = (currentNomadIndex + 1) % nomads.size();
                }
                default -> {
                }
            }
            if(((Player) currentPlayer.getObject()).getStuck()){
                round++;
            } else {
                chosen = true;
            }
        }

        System.out.println("Round: " + round + " Current player: " + currentPlayer.getName() + " Position: " + ((Player)currentPlayer.getObject()).getPosition().toString());

        nomadPoints = game.getMap().getNomadPoints();
        plumberPoints = game.getMap().getPlumberPoints();

        if (nomadPoints >= 100 || plumberPoints >= 100) {
            gameOver = true;
            pluwin = plumberPoints > nomadPoints;
        }

        round++;
    }

    /**
    The main loop of the game
     */
    public void runGame() {
        while (!gameOver) {
            step();
            draw();
            synchronized (lock) {
                while (!userAction) {
                    try {
                        lock.wait(); // Release the lock and wait until notified
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            userAction = false;
        }
    }

    public void draw(){
        gamePanel.paintComponent(gamePanel.getGraphics());
        controlsPanel.repaint();
    }

    public static JGameElement findElement(NetworkElement networkElement) {
        for (JGameElement gameElement : gameElements) {
            if (gameElement.getObject().equals(networkElement)) return gameElement;
        }
        return null;
    }

    public void setUserAction(boolean userAction) {
        this.userAction = userAction;
    }

    public JPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public static Game getGame() {
        return game;
    }
}
