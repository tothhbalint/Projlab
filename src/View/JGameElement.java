package View;

import Model.NetworkElement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * This class is the graphical representation of a game element.
 */
public abstract class JGameElement extends JComponent implements IDrawable{
    /**
     * The x and y coordinates of the element.
     */
    protected int x,y;
    /**
     * The image of the element.
     */
    protected Image elementImage;

    /**
     * Constructor for JGameElement.
     * @param _x The x coordinate of the element.
     * @param _y The y coordinate of the element.
     */
    public JGameElement(int _x, int _y){
        x = _x;
        y = 720 - _y;
    }

    /**
     * Initializes the position of the element.
     * @param _x The x coordinate of the element.
     * @param _y The y coordinate of the element.
     */
    public void initPosition(int _x, int _y){
        x = _x;
        y = 720 - _y;
    }

    /**
     * Moves this component to a new location. The top-left corner of
     * @param X the x coordinate of the new location's
     *           top-left corner in the parent's coordinate space
     * @param Y the y coordinate of the new location's
     *           top-left corner in the parent's coordinate space
     *
     */
    public void move(int X, int Y){
       x = X;
       y = Y;
    }

    /**
     * Returns the x coordinate of the element.
     * @return The x coordinate of the element.
     */
    public int getX(){
        return x;
    }

    /**
     * Returns the y coordinate of the element.
     * @return The y coordinate of the element.
     */
    public int getY(){
        return y;
    }

    /**
     * Returns the image of the element.
     * @return The image of the element.
     */
    public Image getImage(){
        return elementImage;
    }

    /**
     * Sets the image of the element.
     * @param image The image of the element that need to be set.
     */
    public void setImage(File image){
        try {
            elementImage = ImageIO.read(image);
        } catch (Exception e) {
            System.out.println("Error loading image in JGameElement");
        }
    }

    /**
     * Draws the element.
     * @param g The graphics object.
     */
    public void draw(Graphics g){
        super.paint(g);

        g.drawImage(elementImage, x, y - 20, null);
        g.drawString(((NetworkElement)getObject()).toString(), x, y - 20);
    }

    /**
     * Returns the object that this JGameElement represents. Implemented in inherited classes
     * @return The object that this JGameElement represents.
     */
    public abstract Object getObject();

    /**
     * Sets the object that this JGameElement represents. Implemented in inherited classes
     * @param o The object that this JGameElement represents.
     */
    public abstract void setObject(Object o);

    /**
     * Updates the connections of the element. Implemented in inherited classes
     * @param newConnections The new connections of the element.
     */
    public abstract void updateConnections(ArrayList<JGameElement> newConnections);
}
