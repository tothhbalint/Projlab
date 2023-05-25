package View;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import Model.*;

public class ControlsPanel extends JPanel {

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
    private GameFrame gameFrame;

    private ArrayList<String> moveToListItems = new ArrayList<>();
    private ArrayList<String> pipeDisconnectListItems = new ArrayList<>();
    private ArrayList<String> directPumpListItems = new ArrayList<>();
    private ArrayList<String> inventoryListItems = new ArrayList<>();
    private DefaultListModel<String> inventoryListModel = new DefaultListModel<>();

    public ControlsPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;

        this.updateLists();
        this.constructComponents();
        this.disableButtons();

        //adjust size and set layout
        setPreferredSize(new Dimension(400, 720));
        setLayout(null);
        //make playerTurnLabel font bigger
        playerTurnLabel.setFont(new Font("Arial", Font.BOLD, 28));

        this.addComponents();

        //set component bounds (only needed by Absolute Positioning)
        moveToList.setBounds(10, 70, 115, 150);
        moveToLabel.setBounds(10, 50, 100, 25);
        breakPipeButton.setBounds(135, 70, 135, 40);
        takePumpButton.setBounds(135, 120, 135, 40);
        placePumpButton.setBounds(135, 170, 135, 40);
        playerTurnLabel.setBounds(10, 5, 240, 45);
        pipeSlipperyButton.setBounds(135, 250, 135, 40);
        pipeStickyButton.setBounds(135, 300, 135, 40);
        inventoryLabel.setBounds(10, 550, 80, 30);
        pipeDisconnectLabel.setBounds(10, 225, 115, 25);
        pipeDisconnectList.setBounds(10, 245, 115, 125);
        connectPipeButton.setBounds(140, 400, 135, 40);
        directPumpLabel.setBounds(10, 380, 100, 25);
        directPumpList.setBounds(10, 400, 115, 135);
        inventoryList.setBounds(10, 575, 100, 75);
        fixButton.setBounds(140, 450, 135, 40);
        plumberPoints.setBounds(150, 570, 125, 25);
        nomadPoints.setBounds(150, 600, 125, 25);

        //Add action listeners
        breakPipeButton.addActionListener(e ->

        {
            Player player = (Player) gameFrame.getCurrentPlayer().getObject();
            player.breakPipe();
            gameFrame.setUserAction(true);
        });
        //HANDLE NOMAD CAST
        takePumpButton.addActionListener(e ->

        {
            Plumber plumber = (Plumber) gameFrame.getCurrentPlayer().getObject();
            plumber.takePump();
            gameFrame.setUserAction(true);
        });

        placePumpButton.addActionListener(e ->

        {
            Plumber plumber = (Plumber) gameFrame.getCurrentPlayer().getObject();
            plumber.placePump();
            gameFrame.setUserAction(true);
        });

        //TODO debug for Plumbers and Nomads
        pipeSlipperyButton.addActionListener(e ->
        {
            Nomad nomad = (Nomad) gameFrame.getCurrentPlayer().getObject();
            nomad.makePipeSlippery();
            gameFrame.setUserAction(true);
        });

        pipeStickyButton.addActionListener(e ->

        {
            Player player = (Player) gameFrame.getCurrentPlayer().getObject();
            player.makePipeSticky();
            gameFrame.setUserAction(true);
        });

        connectPipeButton.addActionListener(e ->

        {
            Plumber plumber = (Plumber) gameFrame.getCurrentPlayer().getObject();
            plumber.connectPipe();
            gameFrame.setUserAction(true);
        });

        fixButton.addActionListener(e ->

        {
            Plumber plumber = (Plumber) gameFrame.getCurrentPlayer().getObject();
            plumber.repair();
            gameFrame.setUserAction(true);
        });


        //TODO VALAKI OLDJA MEG HELYETTEM KISZALLTAM
        directPumpList.addListSelectionListener(e ->

        {
            Plumber plumber = (Plumber) gameFrame.getCurrentPlayer().getObject();
            //plumber.directPump();
            gameFrame.setUserAction(true);
        });
    }


    private void updateLists(){
        //Delete the previous lists
        moveToListItems.clear();
        pipeDisconnectListItems.clear();
        directPumpListItems.clear();
        inventoryListModel.clear();

        //inventoryListItems
        if (gameFrame.getCurrentPlayer().getObject() instanceof Plumber){
            for (String item : ((Plumber) gameFrame.getCurrentPlayer().getObject()).getInventory().toString().split(" ")) {
                if (item.contains("Pump") || item.contains("Pipe")) {
                    inventoryListModel.addElement(item.trim());
                }
            }
        }
        else{
            for (String item : ((Nomad) gameFrame.getCurrentPlayer().getObject()).getInventory().toString().split(" ")) {
                inventoryListModel.addElement(item.trim());
            }
        }


        //moveToListItems
        for (NetworkElement neighbour : ((Player) gameFrame.getCurrentPlayer().getObject()).getPosition().getConnections()){
            moveToListItems.add(neighbour.toString());
        }

        //pipeDisconnectListItems
        for (NetworkElement neighbour : ((Player) gameFrame.getCurrentPlayer().getObject()).getPosition().getConnections()){
            if (neighbour instanceof Pipe){
                pipeDisconnectListItems.add(neighbour.toString());
            }
        }

        //TODO directPumpListItems, rosszul mukodik
        if (((Player) gameFrame.getCurrentPlayer().getObject()).getPosition() instanceof Pump){
            for (NetworkElement neighbour : ((Player) gameFrame.getCurrentPlayer().getObject()).getPosition().getConnections()){
                if (neighbour instanceof Pipe && neighbour.getOutput() != ((Player) gameFrame.getCurrentPlayer().getObject()).getPosition()){
                    directPumpListItems.add(neighbour.toString());
                }
            }
        }
    }
    private void addComponents() {

        //add components
        add(moveToList);

        add(moveToLabel);

        add(breakPipeButton);

        add(takePumpButton);

        add(placePumpButton);

        add(playerTurnLabel);

        add(pipeSlipperyButton);

        add(pipeStickyButton);

        add(inventoryLabel);

        add(pipeDisconnectLabel);

        add(pipeDisconnectList);

        add(connectPipeButton);

        add(directPumpLabel);

        add(directPumpList);

        add(inventoryList);

        add(fixButton);

        add(plumberPoints);

        add(nomadPoints);
    }
    private void constructComponents(){
        //construct components
        moveToList = new JList(moveToListItems.toArray());
        directPumpList = new JList(directPumpListItems.toArray());
        pipeDisconnectList = new JList(pipeDisconnectListItems.toArray());
        inventoryList = new JList(inventoryListModel.toArray());
        moveToLabel = new JLabel("Move to:");
        breakPipeButton = new JButton("Break pipe");
        takePumpButton = new JButton("Take pump");
        placePumpButton = new JButton("Place pump");
        playerTurnLabel = new JLabel(gameFrame.getCurrentPlayer().getName() + " player's turn!");
        pipeSlipperyButton = new JButton("Pipe slippery");
        pipeStickyButton = new JButton("Pipe sticky");
        inventoryLabel = new JLabel("Inventory:");
        pipeDisconnectLabel = new JLabel("Pipe disconnect:");
        connectPipeButton = new JButton("Connect pipe");
        directPumpLabel = new JLabel("Direct pump:");
        fixButton = new JButton("Fix");
        plumberPoints = new JLabel("Plumber points: 0");
        nomadPoints = new JLabel("Nomad points: 0");
    }

    public void disableButtons() {
        if (!GameFrame.isAbstractMethodImplemented(((Player) gameFrame.getCurrentPlayer().getObject()).getPosition(), "pickupPump")) {
            takePumpButton.setEnabled(false);
        } else {
            takePumpButton.setEnabled(true);
        }

        if (!GameFrame.isAbstractMethodImplemented(((Player) gameFrame.getCurrentPlayer().getObject()).getPosition(), "placePump")) {
            placePumpButton.setEnabled(false);
        } else {
            if (!((Player) gameFrame.getCurrentPlayer().getObject()).getInventory().hasPump()) {
                placePumpButton.setEnabled(false);
            } else
                placePumpButton.setEnabled(true);
        }

        if (!GameFrame.isAbstractMethodImplemented(((Player) gameFrame.getCurrentPlayer().getObject()).getPosition(), "breakPipe")) {
            breakPipeButton.setEnabled(false);
        } else {
            if (((Player) gameFrame.getCurrentPlayer().getObject()).getPosition().isDamaged()) {
                breakPipeButton.setEnabled(false);
            } else {
                breakPipeButton.setEnabled(true);
            }
        }

        if (!GameFrame.isAbstractMethodImplemented(((Player) gameFrame.getCurrentPlayer().getObject()), "connectPipe")) {
            connectPipeButton.setEnabled(false);
        } else {
            if (!GameFrame.isAbstractMethodImplemented(((Player) gameFrame.getCurrentPlayer().getObject()).getPosition(), "connectPipe")) {
                connectPipeButton.setEnabled(false);
            }else if (!((Player) gameFrame.getCurrentPlayer().getObject()).getInventory().hasPipe()) {
                connectPipeButton.setEnabled(false);
            } else {
                connectPipeButton.setEnabled(true);
            }
        }

        if (!GameFrame.isAbstractMethodImplemented(((Player) (gameFrame.getCurrentPlayer().getObject())).getPosition(), "repair")) {
            fixButton.setEnabled(false);
        } else {
            if (!((Player) gameFrame.getCurrentPlayer().getObject()).getPosition().isDamaged())
                fixButton.setEnabled(false);
            else
                fixButton.setEnabled(true);
        }

        if (!GameFrame.isAbstractMethodImplemented((gameFrame.getCurrentPlayer().getObject()), "makePipeSlippery")) {
            pipeSlipperyButton.setEnabled(false);
        } else {
            if(!GameFrame.isAbstractMethodImplemented(((Player) gameFrame.getCurrentPlayer().getObject()).getPosition(), "setSlippery"))
                pipeSlipperyButton.setEnabled(false);
            else
                pipeSlipperyButton.setEnabled(true);
        }

        if (!GameFrame.isAbstractMethodImplemented(((Player) gameFrame.getCurrentPlayer().getObject()).getPosition(), "setSticky")) {
            pipeStickyButton.setEnabled(false);
        } else {
            pipeStickyButton.setEnabled(true);
        }
    }

    public void repaint() {
        super.repaint();
        if (this.gameFrame != null) {
            disableButtons();
            updateLists();
        }
    }
}
