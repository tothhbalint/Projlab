package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import Model.*;

/**
 * This class is the graphical representation of a source.
 */
public class JSource extends JGameElement {
    /**
     * The source object that this JSource represents.
     */
    private Source source;

    /**
     * Constructor for JSource.
     * @param _x The x coordinate of the source.
     * @param _y The y coordinate of the source.
     */
    public JSource(int _x, int _y) {
        super(_x, _y);
        try {
            setImage(new File("src\\View\\Images\\source.png"));
        } catch (Exception e) {
            System.out.println("Error loading source.png");
        }
    }

    /**
     * Sets the object that this JGameElement represents.
     * @param o The object that this JGameElement represents.
     */
    @Override
    public void setObject(Object o) {
        source = (Source) o;
    }

    /**
     * Gets the object that this JGameElement represents.
     * @return
     */
    @Override
    public Object getObject() {
        return source;
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
