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
        try {
            setImage(new File("src\\View\\Images\\pipe.png"));
        } catch (Exception e) {
            System.out.println("Error loading image");
        }
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

    public void calcMiddle(){
        int x = 0;
        int y = 0;
        for (JGameElement connection : connections) {
            x += connection.getX();
            y += connection.getY();
        }
        x /= connections.size();
        y /= connections.size();
        move(x,y);
    }

    @Override
    public void draw(Graphics g) {
        super.paint(g);

        int cR = 0,cG = 0,cB = 0;

        if (pipe.isSlippery()) {
            cG += 120;
        }else if(pipe.isDamaged()){
            cR += 120;
        }else if(pipe.isSticky()){
            cG += 120;
        }else if(pipe.getWaterState()){
            cB = 250;
        }

        g.setColor(new Color(cR,cG,cB));
        g.drawLine(connections.get(0).getX(), connections.get(0).getY(), connections.get(1).getX(), connections.get(1).getY());
    }
}
