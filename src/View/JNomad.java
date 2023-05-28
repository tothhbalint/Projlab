package View;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import Model.*;

public class JNomad extends JPlayer{
    private Nomad nomad;
    public JNomad(int _x, int _y) {
        super(_x, _y);
        try{
            setImage(new File("src\\View\\Images\\nomad.png"));
        } catch (Exception e) {
            System.out.println("Error loading nomad.png");
        }
    }
    @Override
    public void setObject(Object o) {
        nomad = (Nomad) o;
    }
    @Override
    public Object getObject() {
        return nomad;
    }

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

    @Override
    public void draw(Graphics g) {
        super.paint(g);
        checkStuck();
        g.drawImage(elementImage, x, y, null);
        g.drawString(name, x + 20, y + 20);
    }

    public void updateConnections(ArrayList<JGameElement> newConnections) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("JNomads have no connections");
    }
}
