package Model;//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : NetworkElement.java
//  @ Date : 16/04/2023
//
//


import java.util.ArrayList;

/**
 * This class is responsible for all the NetworkElements
 */
public abstract class NetworkElement implements ITimer, IMove {
    /**
     * Stores the id of the NetworkElement
     */
    protected int id;
    /**
     * Stores if the NetworkElement is in the inventory of any players
      */
    protected boolean inInventory = false;
    /**
     * Stores the capacity of the NetworkElement
     */
    protected int capacity; //Do we still need this?
    /**
     * Stores if the NetworkElement has water in it
     */
    protected boolean hasWater;
    /**
     * Stores if the NetworkElement is damaged
     */
    protected boolean damaged;
    /**
     * Stores if the NetworkElement is occupied
     */
    protected boolean occupied;
    /**
     * Stores the point of nomads
     */
    protected static int nomadPoints;
    /**
     * Stores the point of plumbers
     */
    protected static int plumberPoints;
    /**
     * Stores the connections of the NetworkElement in a list
     */
    protected ArrayList<NetworkElement> connections;
    /**
     * Stores the players in a list, which stand on the NetworkElement
     */
    protected ArrayList<Player> occupants;
    /**
     * Stores the output of the NetworkElement
     */
    protected NetworkElement output;
    /**
     * Stores the input of the NetworkElement
     */
    protected NetworkElement input;

    /**
     * Constructor,
     * Sets the starting values
     */
    public NetworkElement() {
        this.connections = new ArrayList<NetworkElement>();
        this.occupants = new ArrayList<Player>();
        this.hasWater = false;
        this.damaged = false;
        this.occupied = false;
        this.capacity = 0; //The question still stands
        this.id = NetworkMap.generateID();
    }

    /**
     * Implements one time slice in the inherited classes
     */
    public abstract void tick();

    /**
     * Implemented in inherited classes
     */
    public abstract boolean accept(Player p);

    /**
     * Implemented in inherited classes
     */
    public abstract void remove(Player p);

    /**
     * This method adds a NetworkElement into the connection list
     * @param ne NetworkElement, that need to be added
     */
    public void addConnection(NetworkElement ne) {
        this.connections.add(ne);
    }

    /**
     * This method removes a NetworkElement for the connection list
     * @param ne NetworkElement, which need to be removed
     */
    public void removeConnection(NetworkElement ne) {
        this.connections.remove(ne);
    }

    /**
     * This method checks if a NetworkElement is in the connection list
     * @param ne NetworkElement, which need to be checked
     * @return true, if the connection list contains the NetworkElement, false if it is not
     */
    public boolean isConnected(NetworkElement ne) {
        return this.connections.contains(ne);
    }

    /**
     * This method is implemented in the inharited classes
     * @param ne NetworkElement
     */
    public abstract void receiveWater(NetworkElement ne);

    /**
     * This method sets the output of the NetworkElement, if it is connected
     * @param ne NetworkElement, which need to be set as output
     */
    public void setOutput(NetworkElement ne) {
        if (connections.contains(ne) || ne == null) {
            this.output = ne;
            if(this.output == null) {
                this.hasWater = false;
            }
        } else {
            throw new IllegalArgumentException("NetworkElement is not connected");
        }
    }

    /**
     * This method gets the output of the NetworkElement
     * @return NetworkElement, the output
     */
    public NetworkElement getOutput() {
        return this.output;
    }

    /**
     * This method sets the input of the NetworkElement if it is connected
     * @param ne NetworkElement, that need to be set as input
     */
    public void setInput(NetworkElement ne) {
        if (connections.contains(ne) || ne == null) {
            this.input = ne;
            if (this.input == null) {
                this.hasWater = false;
            }
        } else {
            throw new IllegalArgumentException("NetworkElement is not connected");
        }
    }

    /**
     * This method gets the input of the NetworkElement
     * @return NetworkElement, the input
     */
    public NetworkElement getInput() {
        return this.input;
    }

    /**
     * This method sets if the NetworkElement has water in it
     * @param b true/false
     */
    public void setWaterState(boolean b) {
        this.hasWater = b;
    }

    /**
     * This method gets the points of nomad team
     * @return points of nomad team
     */
    public static int getNomadPoints() {
        return nomadPoints;
    }

    /**
     * This method gets the points of plumber team
     * @return points of plumber team
     */
    public static int getPlumberPoints() {
        return plumberPoints;
    }

    /**
     * This method increases the points of nomad team
     */
    protected static void increaseNomadPoint() {
        nomadPoints++;
    }

    /**
     * This method increases the point of plumber team
     */
    protected static void increasePlumberPoint() {
        plumberPoints++;
    }

    /**
     * This method checks if the NetworkElement is occupied
     * @return true, if NetworkElement is occupied, false if it is not
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * This method sets if the NetworkElement is occupied
     * @param b true/false
     */
    public void setOccupied(boolean b) {
        this.occupied = b;
    }

    /**
     * This method checks if the NetworkElement is damaged
     * @return true, if the NetworkElement is damaged, false if it is not
     */
    public boolean isDamaged() {
        return damaged;
    }

    /**
     * This method sets if the NetworkElement is damaged
     * @param b true/false
     */
    public void setDamaged(boolean b) {
        this.damaged = b;
    }

    /**
     * This method gets the ID of the NetworkElement
     * @return ID
     */
    public int getID() {
        return id;
    }

    /**
     * NOTE: based on 7.0.1 class diagram, should be boolean, not void
     * - consider how accept() works
     */
    public abstract void pickUpPump(Inventory inv);

    /**
     * - consider how accept() works
     * should this method be boolean instead of void?
     */
    public abstract void direct(NetworkElement in, NetworkElement out);

    /**
     * This method is implemented in inherited classes
     */
    public abstract void connectPipe(NetworkElement ne);

    /**
     * This method is implemented in inherited classes
     */
    public abstract void disconnectPipe(NetworkElement ne);

    /**
     * This method is implemented in inherited classes
     */
    public abstract boolean placePump();

    /**
     * This method is implemented in inherited classes
     */
    public abstract NetworkElement getPipeOutput();


    /**
     * Draw element only verbose mode
     * TODO tweak looks, comment
     */
    public void printMatrix() {
        Proto.print(this.toString() + " hasWater:" + this.hasWater + " damaged: " + this.damaged +
                " occupied: " + this.occupied + " input:" + this.input + " output: " + this.output + " connections: ");
        int x = 0;
        Proto.tab++;
        for (NetworkElement ne : this.connections) {
            Proto.print("\t" + x + ": " + ne.toString() + " hasWater:" + ne.hasWater + " damaged: " + ne.damaged +
                    " occupied: " + ne.occupied + " input:" + ne.input + " output: " + ne.output);
            x++;
        }
        Proto.tab--;
    }

    /**
     * This method returns the ID of the NetworkElement in a string
     * @return id of NetworkElement in string
     */
    public String toString() {
        return Integer.toString(id);
    }

    /**
     * This method gets the connection list of the NetworkElement
     * @return connection list
     */
    public ArrayList<NetworkElement> getConnections() {
        return connections;
    }

    /**
     * This method is implemented in inherited classes
     */
    public abstract void breakPipe();

    /**
     * This method is implemented in inherited classes
     */
    public abstract void repair();

    /**
     * This method is implemented in inherited classes
     */
    public abstract void setSlippery();

    /**
     * This method is implemented in inherited classes
     */
    public abstract void setSticky();

    public boolean getWaterState(){
        return this.hasWater;
    }
}
