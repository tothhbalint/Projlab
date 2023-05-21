package View;

import java.awt.*;
import java.io.File;

public abstract class JPlayer extends JGameElement{
    public JPlayer(int _x, int _y) {
        super(_x, _y);
    }

    public abstract void checkStuck();

    @Override
    public void draw(Graphics g) {
        checkStuck();
        super.draw(g);
    }

}
