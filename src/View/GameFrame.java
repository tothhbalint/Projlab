package View;

import Model.*;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class GameFrame extends JFrame {
    ArrayList<JGameElement> gameElements = new ArrayList<>();

    ArrayList<JNomad> nomads = new ArrayList<>();

    ArrayList<JPlumber> plumbers = new ArrayList<>();

    HashMap<Class<?>, Class<?>> elementTypes = new HashMap<>();

    private final Game game = new Game();

    private boolean gameOver = false;

    int nomadPoints = 0, plumberPoints = 0;

    private boolean pluwin = false;

    private int round = 0;

    public GameFrame() {
        super("Drukkmakori Sivatag - Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        elementTypes.put(Source.class, JSource.class);
        elementTypes.put(Pipe.class, JPipe.class);
        elementTypes.put(Pump.class, JPump.class);
        elementTypes.put(Cistern.class, JCistern.class);

        runGame();
    }

    //TODO Needs mapping for the base position of the elements, pipes can stay at 0,0 , its position gets calculated based on the others
    public void loadElements() {
        for (NetworkElement networkElement : game.getMap().getElements()) {
            try {
                Class<?> target = elementTypes.get(networkElement.getClass());
                JGameElement element = (JGameElement) target.getConstructor().newInstance(0, 0);
                element.setObject(networkElement);
                if (target.equals(JPipe.class)) {
                    Pipe object = (Pipe) element.getObject();
                    for (NetworkElement connectionElement : object.getConnections()) {
                        JGameElement connection = findElement(connectionElement);
                        ((JPipe) element).addConnection(connection);
                    }
                    ((JPipe) element).calcMiddle();
                }
                gameElements.add(element);
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

        //TODO

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

        game.getMap().getElements().get(0).getPipeOutput();


        while (!gameOver) {
            step();
            draw();
        }
    }

    public static boolean isAbstractMethodImplemented(Object derived, String methodName) {
        try {
            Method method = derived.getClass().getMethod(methodName);

            Class<?>[] declaredExceptions = method.getExceptionTypes();

            for (Class<?> e : declaredExceptions) {
                if (e.equals(UnsupportedOperationException.class)) return false;
            }

        } catch (NoSuchMethodException e) {
            return false;
        }
        return true;
    }

    public JGameElement findElement(NetworkElement networkElement) {
        for (JGameElement gameElement : gameElements) {
            if (gameElement.getObject().equals(networkElement)) return gameElement;
        }
        return null;
    }

    public void draw() {
        for (JGameElement gameElement : gameElements) {
            //TODO ha nem rajzol ki semmit ide kell mast irni valszeg xd
            gameElement.draw(getGraphics());
        }
    }
}
