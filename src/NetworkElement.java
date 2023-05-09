//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : NetworkElement.java
//  @ Date : 16/04/2023
//
//


import java.util.ArrayList;

/** */
public abstract class NetworkElement implements ITimer, IMove {
	protected int id;
	protected int capacity; //Do we still need this?
	protected boolean hasWater;
	protected boolean damaged;
	protected boolean occupied;
	protected static int nomadPoints;
	protected static int plumberPoints;
	protected ArrayList<NetworkElement> connections;
	protected ArrayList<Player> occupants;

	public NetworkElement(){
		this.connections = new ArrayList<NetworkElement>();
		this.occupants = new ArrayList<Player>();
		this.hasWater = false;
		this.damaged = false;
		this.occupied = false;
		this.capacity = 0; //The question still stands
		this.id = NetworkMap.generateID();
	}

	/** Implements one time slice in the inherited classes */
	public abstract void tick();
	
	/**  */
	public abstract boolean accept(Player p);
	
	/** */
	public abstract void remove(Player p);
	
	/** */
	public void addConnection(NetworkElement ne){
		this.connections.add(ne);
	}
	
	/** */
	public void removeConnection(NetworkElement ne){
		this.connections.remove(ne);
	}
	
	/** */
	public boolean isConnected(NetworkElement ne){
		return this.connections.contains(ne);
	}
	
	/** */
	public abstract void recieveWater(NetworkElement ne);
	
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
}
