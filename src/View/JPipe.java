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

    @Override
    public void draw(Graphics g) {

    }
}
