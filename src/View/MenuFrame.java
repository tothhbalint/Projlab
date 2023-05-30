package View;
import javax.swing.*;

/**
 * This class is the graphical representation of the menu.
 */
public class MenuFrame extends JFrame{
    /**
     * Constructor for MenuFrame.
     * Shows the main menu to set the starting9 values.
     */
    public MenuFrame(){
        super("Drukkmakori Sivatag - Menu");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
