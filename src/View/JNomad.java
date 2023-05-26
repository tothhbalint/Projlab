package View;
import java.awt.*;
import java.io.File;
import Model.*;

public class JNomad extends JPlayer{
    private Nomad nomad;
    public JNomad(int _x, int _y) {
        super(_x, _y);
        try{
            setImage(new File("src\\View\\Images\\nomad.png"));
        } catch (Exception e) {
            System.out.println("Image not found");
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
        if(nomad.getStuck()){
            try {
                setImage(new File("src\\View\\Images\\nomadStuck.png"));
            } catch (Exception e) {
                System.out.println("Error loading image");
            }
        }
        else{
            try {
                setImage(new File("src\\View\\Images\\nomad.png"));
            } catch (Exception e) {
                System.out.println("Error loading image");
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        super.paint(g);
        checkStuck();
        g.drawImage(elementImage, x, y, null);
        //g.drawString(name, x, y - 40);
    }
}
