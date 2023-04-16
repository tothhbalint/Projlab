//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : NetworkElement.java
//  @ Date : 16/04/2023
//
//




/** */
public abstract class NetworkElement implements ITimer, IMove {
	/** */
	protected int capacity;
	
	/** */
	protected boolean hasWater;
	
	/** */
	protected boolean damaged;
	
	/** */
	private static int nomadPoints;
	
	/** */
	private static int plumberPoints;
	
	/** */
	protected Player occupants;
	
	/** */
	public NetworkElement connections;
	
	/** */
	public abstract void tick();
	
	/** */
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
	}
	
	/** */
	public static int getNomadPoints() {
		return 0;
	}
	
	/** */
	public static int getPlumberPoints() {
		return 0;
	}
	
	/** */
	private void increaseNomadPoint() {
	}
	
	/** */
	private void increasePlumberPoint() {
	}
	
	/** */
	public boolean isOccupied() {
		return false;
	}
	
	/** */
	public void setOccupied(boolean b) {
	}
}
