package View;

import java.awt.*;
import java.io.File;

import Model.*;

public class JPump extends JGameElement {
    private Pump pump;

    public JPump(int _x, int _y) {
        super(_x, _y);
        try {
            setImage(new File("src\\View\\Images\\pump.png"));
        } catch (Exception e) {
            System.out.println("Image not found");
        }
    }

    @Override
    public void setObject(Object o) {
        pump = (Pump) o;
    }

    @Override
    public Pump getObject() {
        return pump;
    }

    public void checkStates(){
        if(pump.isDamaged() && pump.getWaterState()){
            try {
                setImage(new File("src\\View\\Images\\pumpBrokenWater.png"));
            } catch (Exception e) {
                System.out.println("Error loading image");
            }
        }
        else if(pump.isDamaged() && !pump.getWaterState()){
            try {
                setImage(new File("src\\View\\Images\\pumpBroken.png"));
            } catch (Exception e) {
                System.out.println("Error loading image");
            }
        }
        else if(!pump.isDamaged() && pump.getWaterState()){
            try {
                setImage(new File("src\\View\\Images\\pumpWater.png"));
            } catch (Exception e) {
                System.out.println("Error loading image");
            }
        }
        else{
            try {
                setImage(new File("src\\View\\Images\\pump.png"));
            } catch (Exception e) {
                System.out.println("Error loading image");
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        checkStates();
        super.draw(g);
    }
}
