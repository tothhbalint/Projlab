package View;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import Model.*;

/**
 * This class is the graphical representation of a cistern.
 */
public class JCistern extends JGameElement {
    /**
     * The cistern object that this JCistern represents.
     */
    private Cistern cistern;

    /**
     * Constructor for JCistern.
     * @param _x The x coordinate of the cistern.
     * @param _y The y coordinate of the cistern.
     */
    public JCistern(int _x, int _y) {
        super(_x, _y);
        try {
            setImage(new File(".\\src\\View\\Images\\cistern.png"));
        } catch (Exception e) {
            System.out.println("Error loading cistern.png");
        }
    }

    /**
     * Sets the cistern object that this JCistern represents.
     * @param o The object that this JGameElement represents.
     */
    @Override
    public void setObject(Object o) {
        cistern = (Cistern) o;
    }

    /**
     * Returns the cistern object that this JCistern represents.
     * @return The cistern object that this JCistern represents.
     */
    @Override
    public Object getObject() {
        return cistern;
    }

    /**
     * Draws the cistern.
     * @param g The graphics object.
     */
    @Override
    public void draw(Graphics g) {
        super.paint(g);

        g.drawImage(elementImage, x, y - 55, null);
        g.drawString(((NetworkElement)getObject()).toString(), x, y - 40);
    }

    /**
     * Updates the connections of the element.
     * @param newConnections The new connections of the element.
     * @throws UnsupportedOperationException
     */
    public void updateConnections(ArrayList<JGameElement> newConnections) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("JCisterns have no connections");
    }
}
