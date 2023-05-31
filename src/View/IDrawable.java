package View;

import java.awt.*;

/**
 * This interface is used to draw the graphical representation of an object.
 */
public interface IDrawable {
    /**
     * Sets the object that this IDrawable represents.
     * @param g The graphics object to draw with.
     */
    public void draw(Graphics g);
}
