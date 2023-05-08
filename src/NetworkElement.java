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
	protected int capacity;
	protected boolean hasWater;
	protected boolean damaged;
	protected boolean occupied;
	protected static int nomadPoints;
	protected static int plumberPoints;
	protected ArrayList<NetworkElement> connections;
	protected ArrayList<Player> occupants;

	/** Implements one time slice in the inherited classes */
	public abstract void tick();
	
	/**  */
	public abstract boolean accept(Player p);
	
	/** */
	public abstract void remove(Player p);
	
	/** */
	public abstract void direct(NetworkElement n);
	
	/** */
	public abstract void pickUpPump(Inventory inv);
	
	/** */
	public abstract void addConnection(NetworkElement ne);
	
	/** */
	public abstract void removeConnection(NetworkElement ne);
	
	/** */
	public abstract boolean isConnected(NetworkElement ne);
	
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
	private static void increaseNomadPoint() {
		nomadPoints++;
	}
	
	/** */
	private static void increasePlumberPoint() {
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
}
