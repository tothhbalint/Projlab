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

	abstract void setInput(NetworkElement input);

	abstract void setOutput(NetworkElement output);
	
	/** */
	public abstract boolean accept(Player p);
	
	/** */
	public abstract void remove(Player p);
	
	/** */
	public abstract void direct(NetworkElement ne1,NetworkElement ne2);
	
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
		Skeleton.indentPrint("NetworkElement : setWaterState()");
	}
	
	/** */
	public static void getNomadPoints() {
		Skeleton.indentPrint("NetworkElement : getNomadPoints()");
	}
	
	/** */
	public static void getPlumberPoints() {
		Skeleton.indentPrint("NetworkElement : getPlumberPoints()");
	}
	
	/** */
	private void increaseNomadPoint() {
		Skeleton.indentPrint("NetworkElement : increaseNomadPoint()");
	}
	
	/** */
	private void increasePlumberPoint() {
		Skeleton.indentPrint("NetworkElement : increasePlumberPoint()");
	}
	
	/** */
	public void isOccupied() {
		Skeleton.indentPrint("NetworkElement : isOccupied() : boolean");
	}
	
	/** */
	public void setOccupied() {
		Skeleton.indentPrint("NetworkElement : setOccupied()");
	}
}
