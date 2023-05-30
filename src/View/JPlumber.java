package View;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import Model.*;

/**
 * This class represents a plumber in the game.
 */
public class JPlumber extends JPlayer {
    /**
     * The plumber object that this JPlumber represents.
     */
    private Plumber plumber;

    /**
     * Constructor for JPlumber.
     * @param _x The x coordinate of the plumber.
     * @param _y The y coordinate of the plumber.
     */
    public JPlumber(int _x, int _y) {
        super(_x, _y);
        try {
            setImage(new File("src\\View\\Images\\plumber.png"));
        } catch (Exception e) {
            System.out.println("Error loading plumber.png");
        }
    }

    /**
     * Sets the object that this JGameElement represents.
     * @param o The object that this JGameElement represents.
     */
    @Override
    public void setObject(Object o) {
        plumber = (Plumber) o;
    }

    /**
     * Gets the object that this JGameElement represents.
     * @return The object that this JGameElement represents.
     */
    @Override
    public Object getObject() {
        return plumber;
    }

    /**
     * Checks if the plumber is stuck and changes the image accordingly.
     */
    public void checkStuck(){
        plumber.tick(); //maybe we need this, so that the nomad can move again
        if(plumber.getStuck()){
            try {
                setImage(new File("src\\View\\Images\\plumberStuck.png"));
            } catch (Exception e) {
                System.out.println("Error loading plumberStuck.png");
            }
        }
        else{
            try {
                setImage(new File("src\\View\\Images\\plumber.png"));
            } catch (Exception e) {
                System.out.println("Error loading plumber.png");
            }
        }
    }

    /**
     * Draws the plumber.
     * @param g The graphics object.
     */
    @Override
    public void draw(Graphics g) {
        super.paint(g);
        checkStuck();
        g.drawImage(elementImage, x, y, null);
        g.drawString(name, x + 5, y + 20);
    }

    /**
     * Updates the connections of the player.
     * @param newConnections The new connections of the player.
     * @throws UnsupportedOperationException
     */
    public void updateConnections(ArrayList<JGameElement> newConnections) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("JPlumbers have no connections");
    }
}
