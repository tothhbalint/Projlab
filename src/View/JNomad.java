package View;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import Model.*;

/**
 * This class is the graphical representation of a nomad.
 */
public class JNomad extends JPlayer{
    /**
     * The nomad object that this JNomad represents.
     */
    private Nomad nomad;

    /**
     * Constructor for JNomad.
     * @param _x The x coordinate of the nomad.
     * @param _y The y coordinate of the nomad.
     */
    public JNomad(int _x, int _y) {
        super(_x, _y);
        try{
            setImage(new File("src\\View\\Images\\nomad.png"));
        } catch (Exception e) {
            System.out.println("Error loading nomad.png");
        }
    }

    /**
     * Sets the nomad object that this JNomad represents.
     * @param o The nomad object that this JNomad represents.
     */
    @Override
    public void setObject(Object o) {
        nomad = (Nomad) o;
    }

    /**
     *  Gets the nomad object that this JNomad represents.
     * @return The nomad object that this JNomad represents.
     */
    @Override
    public Object getObject() {
        return nomad;
    }

    /**
     * Checks if the nomad is stuck and changes the image accordingly.
     */
    public void checkStuck(){
        nomad.tick(); //maybe we need this, so that the nomad can move again
        if(nomad.getStuck()){
            try {
                setImage(new File("src\\View\\Images\\nomadStuck.png"));
            } catch (Exception e) {
                System.out.println("Error loading nomadStuck.png");
            }
        }
        else{
            try {
                setImage(new File("src\\View\\Images\\nomad.png"));
            } catch (Exception e) {
                System.out.println("Error loading nomad.png");
            }
        }
    }

    /**
     * Draws the nomad.
     * @param g The graphics object to draw on.
     */
    @Override
    public void draw(Graphics g) {
        super.paint(g);
        checkStuck();
        g.drawImage(elementImage, x, y, null);
        g.drawString(name, x + 20, y + 20);
    }

    /**
     * Updates the connections of the nomad.
     * @param newConnections The new connections of the nomad.
     * @throws UnsupportedOperationException JNomads have no connections.
     */
    public void updateConnections(ArrayList<JGameElement> newConnections) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("JNomads have no connections");
    }
}
