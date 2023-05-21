package View;

import java.awt.*;
import java.io.File;

public abstract class JPlayer extends JGameElement{
    String name;
    public JPlayer(int _x, int _y) {
        super(_x, _y);
    }

    public JPlayer(int _x, int _y, String name) {
        super(_x, _y);
        this.name = name;
    }

    public abstract void checkStuck();

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public void draw(Graphics g) {
        checkStuck();
        super.draw(g);
    }

}
