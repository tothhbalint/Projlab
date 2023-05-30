package View;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * This class is the graphical representation of a player.
 */
public abstract class JPlayer extends JGameElement{
    /**
     * The name of the player.
     */
    String name;

    /**
     * Constructor for JPlayer.
     * @param _x The x coordinate of the player.
     * @param _y The y coordinate of the player.
     */
    public JPlayer(int _x, int _y) {
        super(_x, _y);
    }

    /**
     * Constructor for JPlayer.
     * @param _x The x coordinate of the player.
     * @param _y The y coordinate of the player.
     * @param name The name of the player.
     */
    public JPlayer(int _x, int _y, String name) {
        super(_x, _y);
        this.name = name;
    }

    /**
     * Checks if the player is stuck. Implemented in inherited classes.
     */
    public abstract void checkStuck();

    /**
     * Sets the name of this component to the specified string.
     * @param name  the string that is to be this
     *           component's name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Returns the name of the player.
     * @return The name of the player.
     */
    public String getName(){
        return name;
    }

    /**
     * Draws the player.
     * @param g The graphics object.
     */
    @Override
    public void draw(Graphics g) {

    }

    /**
     * This method updates the connections of the player.
     * @param newConnections The new connections of the player.
     * @throws UnsupportedOperationException
     */
    public void updateConnections(ArrayList<JGameElement> newConnections) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("JPlayers have no connections");
    }
}
