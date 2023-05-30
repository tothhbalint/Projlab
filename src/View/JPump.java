package View;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import Model.*;

/**
 * This class is the graphical representation of a pump.
 */
public class JPump extends JGameElement {
    /**
     * The pump object that this JPump represents.
     */
    private Pump pump;

    /**
     * Constructor for JPump.
     * @param _x The x coordinate of the pump.
     * @param _y The y coordinate of the pump.
     */
    public JPump(int _x, int _y) {
        super(_x, _y);
        try {
            setImage(new File("src\\View\\Images\\pump.png"));
        } catch (Exception e) {
            System.out.println("Error loading pump.png");
        }
    }

    /**
     * Sets the object that this JGameElement represents.
     * @param o The object that this JGameElement represents.
     */
    @Override
    public void setObject(Object o) {
        pump = (Pump) o;
    }

    /**
     * Gets the object that this JGameElement represents.
     * @return
     */
    @Override
    public Pump getObject() {
        return pump;
    }

    /**
     * Checks the state of the pump and changes the image accordingly.
     */
    public void checkStates(){
        if(pump.isDamaged() && pump.getWaterState()){
            try {
                setImage(new File("src\\View\\Images\\pumpBrokenWater.png"));
            } catch (Exception e) {
                System.out.println("Error loading pumpBrokenWater.png");
            }
        }
        else if(pump.isDamaged() && !pump.getWaterState()){
            try {
                setImage(new File("src\\View\\Images\\pumpBroken.png"));
            } catch (Exception e) {
                System.out.println("Error loading pumpBroken.png");
            }
        }
        else if(!pump.isDamaged() && pump.getWaterState()){
            try {
                setImage(new File("src\\View\\Images\\pumpWater.png"));
            } catch (Exception e) {
                System.out.println("Error loading pumpWater.png");
            }
        }
        else{
            try {
                setImage(new File("src\\View\\Images\\pump.png"));
            } catch (Exception e) {
                System.out.println("Error loading pump.png");
            }
        }
    }

    /**
     * Draws the pump.
     * @param g The graphics object.
     */
    @Override
    public void draw(Graphics g) {
        checkStates();
        super.draw(g);
    }

    /**
     * Updates the connections of the element.
     * @param newConnections The new connections of the element.
     * @throws UnsupportedOperationException
     */
    public void updateConnections(ArrayList<JGameElement> newConnections) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("JPumps have no connections");
    }
}
