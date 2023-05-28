package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import Model.*;

public class JPipe extends JGameElement {
    private Pipe pipe;

    private ArrayList<JGameElement> connections = new ArrayList<>();

    public JPipe(int _x, int _y) {
        super(_x, _y);
    }

    @Override
    public void setObject(Object o) {
        pipe = (Pipe) o;
    }

    @Override
    public Object getObject() {
        return pipe;
    }

    public void addConnection(JGameElement connection) {
        connections.add(connection);
    }

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
        } //TODO else if size 1 (azaz playernel van cso vagy cisternnel van csoveg...)
    }
}
