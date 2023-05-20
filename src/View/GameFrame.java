package View;

import Model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameFrame extends JFrame {
    ArrayList<JGameElement> gameElements = new ArrayList<>();

    HashMap<Class<?>, Class<?>> elementTypes = new HashMap<>();

    HashMap<Class<?>, Class<?>> playerTypes = new HashMap<>();

    private final Game game = new Game();

    private boolean gameOver = false;

    int nomadPoints = 0, plumberPoints = 0;

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
    }

    public void loadElements() {
        for (NetworkElement networkElement : game.getMap().getElements()) {
            try {
                gameElements.add((JGameElement) elementTypes.get(networkElement.getClass()).getConstructors()[0].newInstance(0, 0));
            } catch (Exception e) {
                System.out.println("Error loading element");
            }
        }
    }

    /*
    this is a function that runs in the main loop of the game
     */
    public void step() {
        for (JGameElement gameElement : gameElements) {
            ((NetworkElement)gameElement.getObject()).tick();
        }
    }

    /*
    The main loop of the game
     */
    public void runGame(){
        game.startGame();
        loadElements();

        while(!gameOver){
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
