package View;

import Model.*;

import javax.swing.*;
import java.lang.reflect.Method;
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

    private boolean userAction = false;

    private JPlayer currentPlayer;

    public GameFrame(ArrayList<String> plumberNames, ArrayList<String> nomadNames) {
        elementTypes.put(Source.class, JSource.class);
        elementTypes.put(Pipe.class, JPipe.class);
        elementTypes.put(Pump.class, JPump.class);
        elementTypes.put(Cistern.class, JCistern.class);
    }

    //TODO Needs mapping for the base position of the elements, pipes can stay at 0,0 , its position gets calculated based on the others
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
    //TODO find better solution for pipe connection loading this is too ghetto even for me
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

        round++;
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

    /**
    The main loop of the game
     */
    public void runGame() {
        //game.startGame(); did these in constructor, otherwise NullPointerException
        //loadElements(plumbers, nomads);

        while (!gameOver) {
            step();

            //TODO wait for player input
            while (!userAction) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            userAction = false;
            draw();
        }
    }

    public void draw(){
        repaint();
    }

    public JGameElement findElement(NetworkElement networkElement) {
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
}
