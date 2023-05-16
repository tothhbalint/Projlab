//
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
	protected NetworkElement output;
	protected NetworkElement input;

	/**
	 * Sets the starting values
	 */
	public NetworkElement(){
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
	
	/** */
	public abstract boolean accept(Player p);
	
	/** */
	public abstract void remove(Player p);
	
	/** */
	public void addConnection(NetworkElement ne){
		this.connections.add(ne);
	}

	/**
	 * This method removes a NetworkElement for the connection list
	 * @param ne NetworkElement, which need to be removed
	 */
	public void removeConnection(NetworkElement ne){
		this.connections.remove(ne);
	}

	/**
	 * This method checks if a NetworkElement is in the connection list
	 * @param ne NetworkElement, which need to be checked
	 * @return true, if the connection list contains the NetworkElement, false if it is not
	 */
	public boolean isConnected(NetworkElement ne){
		return this.connections.contains(ne);
	}

	/**
	 * This method is implemented in the inharited classes
	 * @param ne NetworkElement
	 */
	public abstract void recieveWater(NetworkElement ne);

	/**
	 *
	 * @param ne
	 */
	public void setOutput(NetworkElement ne){
		if (connections.contains(ne)){
			this.output = ne;
		}  else {
			throw new IllegalArgumentException("NetworkElement is not connected");
		}
	}

	/** */
	public NetworkElement getOutput(){
		return this.output;
	}

	/** */
	public void setInput(NetworkElement ne){
		if (connections.contains(ne)){
			this.input = ne;
		} else {
			throw new IllegalArgumentException("NetworkElement is not connected");
		}
	}

	/** */
	public NetworkElement getInput(){
		return this.input;
	}

	/** */
	public void setWaterState(boolean b) {
		this.hasWater = b;
	}
	
	/** */
	protected static int getNomadPoints() {
		return nomadPoints;
	}
	
	/** */
	protected static int getPlumberPoints() {
		return plumberPoints;
	}
	
	/** */
	protected static void increaseNomadPoint() {
		nomadPoints++;
	}
	
	/** */
	protected static void increasePlumberPoint() {
		plumberPoints++;
	}
	
	/** */
	public boolean isOccupied() {
		return occupied;
	}
	
	/** */
	public void setOccupied(boolean b) {
		this.occupied = b;
	}

	/** */
	public boolean isDamaged() {
		return damaged;
	}

	public  void setDamaged(boolean b) {
		this.damaged = b;
	}

	/** */
	public int getID() {
		return id;
	}

	/**
	 * NOTE: based on 7.0.1 class diagram, should be boolean, not void
	 * - consider how accept() works
	 * */
	public abstract void pickUpPump(Inventory inv);

	/**
	 * - consider how accept() works
	 * should this method be boolean instead of void?
	 * */
	public abstract void direct(NetworkElement in, NetworkElement out);

	/** */
	public abstract void connectPipe(NetworkElement ne);

	/** */
	public abstract void disconnectPipe(NetworkElement ne);

	public abstract boolean placePump();

	public abstract NetworkElement getPipeOutput();

	public abstract void removePipeOutput(NetworkElement ne);

	public abstract void addPipeOutput(NetworkElement ne);

	public  abstract void addPipeInput(NetworkElement ne);

	/** Draw element only verbose mode
	 * TODO tweak looks
	 */

	public void printMatrix(){
		Proto.print(this.toString() + " hasWater:" + this.hasWater + " damaged: " + this.damaged + " occupied: " + this.occupied);
		int x = 0;
		Proto.tab++;
		for (NetworkElement ne : this.connections){
			Proto.print("\t" + x + ": " + ne.toString() + " ");
			x++;
		}
		Proto.tab--;
	}

	public String toString(){
		return Integer.toString(id);
	}

	public ArrayList<NetworkElement> getConnections() {
		return connections;
	}

	public abstract void breakPipe();

	public abstract void repair();

	public abstract void setSlippery();

	public abstract void setSticky();
}
