package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private JList moveToList;
    private JLabel moveToLabel;
    private JButton breakPipeButton;
    private JButton takePumpButton;
    private JButton placePumpButton;
    private JLabel playerTurnLabel;
    private JButton pipeSlipperyButton;
    private JButton pipeStickyButton;
    private JLabel inventoryLabel;
    private JLabel pipeDisconnectLabel;
    private JList pipeDisconnectList;
    private JButton connectPipeButton;
    private JLabel directPumpLabel;
    private JList directPumpList;
    private JList inventoryList;
    private JButton fixButton;
    private JLabel plumberPoints;
    private JLabel nomadPoints;
    private ArrayList<String> plumberNames;
    private ArrayList<String> nomadNames;

    public GamePanel(ArrayList<String> plumberNames, ArrayList<String> nomadNames) {
        //construct preComponents
        String[] moveToListItems = {"pos.connections1", "pos.connections2", "pos.connections3"};
        String[] pipeDisconnectListItems = {"pipe 1", "pipe2"};
        String[] directPumpListItems = {"Item 1", "Item 2", "Item 3"};
        String[] inventoryListItems = {"Item 1", "Item 2", "Item 3"};

        this.plumberNames = plumberNames;
        this.nomadNames = nomadNames;

        //construct components
        moveToList = new JList (moveToListItems);
        moveToLabel = new JLabel ("Move to:");
        breakPipeButton = new JButton ("Break pipe");
        takePumpButton = new JButton ("Take pump");
        placePumpButton = new JButton ("Place pump");
        playerTurnLabel = new JLabel ("XY player's turn!");
        pipeSlipperyButton = new JButton ("Pipe slippery");
        pipeStickyButton = new JButton ("Pipe sticky");
        inventoryLabel = new JLabel ("Inventory:");
        pipeDisconnectLabel = new JLabel ("Pipe disconnect:");
        pipeDisconnectList = new JList (pipeDisconnectListItems);
        connectPipeButton = new JButton ("Connect pipe");
        directPumpLabel = new JLabel ("Direct pump:");
        directPumpList = new JList (directPumpListItems);
        inventoryList = new JList (inventoryListItems);
        fixButton = new JButton ("Fix");
        plumberPoints = new JLabel ("Plumber points: 0");
        nomadPoints = new JLabel ("Nomad points: 0");

        //adjust size and set layout
        setPreferredSize (new Dimension (1276, 684));
        setLayout (null);
        //make playerTurnLabel font bigger
        playerTurnLabel.setFont(new Font("Arial", Font.BOLD, 28));

        //add components
        add (moveToList);
        add (moveToLabel);
        add (breakPipeButton);
        add (takePumpButton);
        add (placePumpButton);
        add (playerTurnLabel);
        add (pipeSlipperyButton);
        add (pipeStickyButton);
        add (inventoryLabel);
        add (pipeDisconnectLabel);
        add (pipeDisconnectList);
        add (connectPipeButton);
        add (directPumpLabel);
        add (directPumpList);
        add (inventoryList);
        add (fixButton);
        add (plumberPoints);
        add (nomadPoints);

        //set component bounds (only needed by Absolute Positioning)
        moveToList.setBounds (10, 70, 115, 150);
        moveToLabel.setBounds (10, 50, 100, 25);
        breakPipeButton.setBounds (135, 70, 135, 40);
        takePumpButton.setBounds (135, 120, 135, 40);
        placePumpButton.setBounds (135, 170, 135, 40);
        playerTurnLabel.setBounds (10, 5, 240, 45);
        pipeSlipperyButton.setBounds (135, 250, 135, 40);
        pipeStickyButton.setBounds (135, 300, 135, 40);
        inventoryLabel.setBounds (10, 550, 80, 30);
        pipeDisconnectLabel.setBounds (10, 225, 115, 25);
        pipeDisconnectList.setBounds (10, 245, 115, 125);
        connectPipeButton.setBounds (140, 400, 135, 40);
        directPumpLabel.setBounds (10, 380, 100, 25);
        directPumpList.setBounds (10, 400, 115, 135);
        inventoryList.setBounds (10, 575, 100, 75);
        fixButton.setBounds (140, 450, 135, 40);
        plumberPoints.setBounds (150, 570, 125, 25);
        nomadPoints.setBounds (150, 600, 125, 25);
    }


    public static void main (String[] args) {
        GameFrame frame = new GameFrame ();
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new GamePanel(new ArrayList<String>(), new ArrayList<String>()));
        frame.pack();
        frame.setVisible (true);
    }
}
