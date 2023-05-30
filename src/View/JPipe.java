package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import Model.*;

/**
 * This class is the graphical representation of a pipe.
 */
public class JPipe extends JGameElement {
    /**
     * The pipe object that this JPipe represents.
     */
    private Pipe pipe;
    /**
     * The connections of this pipe.
     */
    private ArrayList<JGameElement> connections = new ArrayList<>();

    /**
     * Constructor for JPipe.
     * @param _x The x coordinate of the pipe.
     * @param _y The y coordinate of the pipe.
     */
    public JPipe(int _x, int _y) {
        super(_x, _y);
    }

    /**
     * Sets the pipe object that this JPipe represents.
     * @param o The pipe object that this JPipe represents.
     */
    @Override
    public void setObject(Object o) {
        pipe = (Pipe) o;
    }

    /**
     * Returns the pipe object that this JPipe represents.
     * @return The pipe object that this JPipe represents.
     */
    @Override
    public Object getObject() {
        return pipe;
    }

    /**
     * Adds a connection to this pipe.
     * @param connection The connection to be added.
     */
    public void addConnection(JGameElement connection) {
        if (this.connections.size() < 2) {
            connections.add(connection);
        }
    }

    /**
     * This method updates the connections of this pipe.
     * @param newConnections The connections to be removed.
     */
    public void updateConnections(ArrayList<JGameElement> newConnections){
        connections.clear();
        connections = new ArrayList<>(newConnections);
    }

    /**
     * This method calculates the middle of the pipe.
     */
    public void calcMiddle() {
        int x = 0;
        int y = 0;
        for (JGameElement connection : connections) {
            x += connection.getX();
            y += connection.getY();
        }
        x /= connections.size();
        y /= connections.size();
        move(x, y);
    }

    /**
     * This method draws the pipe.
     * @param g The graphics object to be drawn on.
     */
    @Override
    public void draw(Graphics g) {
        super.paint(g);

        int cR = 0, cG = 0, cB = 0;

        // calculating color
        if (pipe.isSlippery()) {
            cG += 120;
        }
        else if (pipe.isSticky()) {
            cG += 60;
            cR += 120;
        }
        else if (pipe.getWaterState()) {
            cG += 120;
            cB = 200;
        }
        // if none of the above, the pipe is grey
        else {
            cR = 150;
            cG = 150;
            cB = 150;
        }
        g.setColor(new Color(cR, cG, cB));

        if (pipe.isDamaged()) {
            g.setColor(new Color(255, 0, 0));
        }

        Graphics2D g2 = (Graphics2D) g;


        g2.setStroke(new BasicStroke(10));
        if (pipe.getConnections().size() >= 2){
            g2.drawLine(connections.get(0).getX(), connections.get(0).getY(), connections.get(1).getX(), connections.get(1).getY());
            g.setFont(new Font("TimesRoman", Font.PLAIN, 9));
            g.setColor(new Color(0, 0, 0));
            g.drawString(((Pipe)getObject()).toString(), x - 10, y - 10);
        }
        else if(pipe.getConnections().size() < 2 && pipe.getJustCreatedByCistern()) {
            g2.drawLine(connections.get(0).getX(), connections.get(0).getY(), connections.get(0).getX(), connections.get(0).getY() + 80);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 9));
            g.setColor(new Color(0, 0, 0));
            g.drawString(((Pipe) getObject()).toString(), connections.get(0).getX() - 80, connections.get(0).getY() + 40);
        }
    }
}
