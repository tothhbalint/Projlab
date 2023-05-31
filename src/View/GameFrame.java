package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is responsible for the frame of the game.
 */
public class GameFrame extends JFrame {
    /**
     * The list of game elements.
     */
    static ArrayList<JGameElement> gameElements = new ArrayList<>();
    /**
     * The list of nomads.
     */
    ArrayList<JNomad> nomads = new ArrayList<>();
    /**
     * The list of plumbers.
     */
    ArrayList<JPlumber> plumbers = new ArrayList<>();
    /** */
    HashMap<Class<?>, Class<?>> elementTypes = new HashMap<>();
    /**
     * The lock object for the threads.
     */
    private final Object lock = new Object();
    /**
     * The game panel.
     */
    private GamePanel gamePanel;
    /**
     * The controls panel.
     */
    private ControlsPanel controlsPanel;
    /**
     * The game object.
     */
    private static Game game = new Game();
    /**
     * The game over flag.
     */
    private boolean gameOver = false;
    /**
     * Stores the points of the teams
     */
    int nomadPoints = 0, plumberPoints = 0;
    /**
     * The flag for the win of the plumbers.
     */
    private boolean pluwin = false;
    /**
     * Stores the number of rounds.
     */
    private int round = 0;
    /**
     * The flag for the user action.
     */
    private volatile boolean userAction = false;
    /**
     * The current player.
     */
    private JPlayer currentPlayer;
    /**
     * Stores the current indexes of players in both team.
     */
    private int currentNomadIndex = 0, currentPlumberIndex = 0;

    /**
     * Constructor for GameFrame. Loads the elements. Sets the layout and the title. Sets the size and the close operation.
     * Creates the game panel and the controls panel. Adds them to the frame. Sets the visibility and the resizable. Packs the frame.
     * @param plumberNames The list of plumber names.
     * @param nomadNames The list of nomad names.
     */
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

    /**
     * This method loads the elements from the game object.
     * @param plumberNames The list of plumber names.
     * @param nomadNames The list of nomad names.
     */
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
     * This method updates the elements.
     */
    private void updateElements(){
        ArrayList<JGameElement> gameElementsCopy = new ArrayList<>(gameElements);
        for (NetworkElement networkElement : game.getMap().getElements()) {
            if(findElement(networkElement) != null){
                ArrayList<JGameElement> currentConnections = new ArrayList<>();
                for (NetworkElement connectionElement : networkElement.getConnections()) {
                    try {
                        JGameElement connection = findElement(connectionElement);
                        currentConnections.add(connection);
                        findElement(networkElement).updateConnections(currentConnections);
                    } catch (UnsupportedOperationException e) {
                        ;
                    }
                }
            } else {
                ArrayList<JGameElement> currentConnections = new ArrayList<>();
                try {
                    Class<?> target = elementTypes.get(networkElement.getClass());
                    JGameElement element = (JGameElement) target.getConstructors()[0].newInstance(currentPlayer.getX(), 720 - currentPlayer.getY());
                    element.setObject(networkElement);
                    gameElementsCopy.add(element);
                    for (NetworkElement connectionElement : networkElement.getConnections()) {
                        JGameElement connection = findElement(connectionElement);
                        currentConnections.add(connection);
                        findElement(networkElement).updateConnections(currentConnections);
                    }
                } catch (Exception e) {
                    //System.out.println("Error loading element");
                }
            }
        }
        gameElements.clear();
        gameElements.addAll(gameElementsCopy);
        for (NetworkElement networkElement : game.getMap().getElements()) {
            ArrayList<JGameElement> currentConnections = new ArrayList<>();
            for (NetworkElement connectionElement : networkElement.getConnections()) {
                try {
                    JGameElement connection = findElement(connectionElement);
                    currentConnections.add(connection);
                    findElement(networkElement).updateConnections(currentConnections);
                } catch (UnsupportedOperationException e) {
                    ;
                }
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
        gamePanel.setElements(gameElements);
    }

    /** No usage */
    public static void addElementToNetworkMap(NetworkElement element){
        game.getMap().addElement(element);
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

        if (nomadPoints >= 99 || plumberPoints >= 99) {
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
            updateElements();
        }
        gameOverScreen();
    }

    /**
     * This function is called when the game is over
     * It shows a message dialog with the winner and asks if the user wants to play again
     * If the user wants to play again, the app is still closed...
     */
    private void gameOverScreen() {
        String winner = pluwin ? "Plumbers" : "Nomads";
        JOptionPane.showMessageDialog(null, winner + " won the game!");
        int answer = JOptionPane.showConfirmDialog(null, "Do you want to play another game?", "Play again?", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Then restart the game... ;)");
            System.exit(0);
        } else {
            System.exit(0);
        }
    }

    /**
     * This function draws the game
     */
    public void draw(){
        gamePanel.paintComponent(gamePanel.getGraphics());
        controlsPanel.repaint();
    }

    /**
     * This function is called when the user clicks on the game panel
     * @param networkElement the element that was clicked on
     * @return the element that was clicked on
     */
    public static JGameElement findElement(NetworkElement networkElement) {
        for (JGameElement gameElement : gameElements) {
            if (gameElement.getObject().equals(networkElement)) return gameElement;
        }
        return null;
    }

    /**
     * This method sets the userAction boolean to true or false
     * @param userAction the boolean that the userAction boolean is set to
     */
    public void setUserAction(boolean userAction) {
        this.userAction = userAction;
    }

    /**
     * This method returns the current player.
     * @return the current player
     */
    public JPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * This method returns the game.
     * @return the game
     */
    public static Game getGame() {
        return game;
    }
}
