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
		System.out.println("NetworkElement : setWaterState()");
	}
	
	/** */
	public static void getNomadPoints() {
		System.out.println("NetworkElement : getNomadPoints()");
	}
	
	/** */
	public static void getPlumberPoints() {
		System.out.println("NetworkElement : getPlumberPoints()");
	}
	
	/** */
	private void increaseNomadPoint() {
		System.out.println("NetworkElement : increaseNomadPoint()");
	}
	
	/** */
	private void increasePlumberPoint() {
		System.out.println("NetworkElement : increasePlumberPoint()");
	}
	
	/** */
	public void isOccupied() {
		System.out.println("NetworkElement : isOccupied() : boolean");
	}
	
	/** */
	public void setOccupied() {
		System.out.println("NetworkElement : setOccupied()");
	}
}
