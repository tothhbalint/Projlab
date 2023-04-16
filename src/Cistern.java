//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Cistern.java
//  @ Date : 16/04/2023
//
//

/** */
public class Cistern extends NetworkElement {
	/** */

	@Override
	public boolean isConnected(NetworkElement ne) {
		return false;
	}

	/** */
	public void tick() {
	}
	
	/** */
	public boolean accept(Player p) {
		return false;
	}
	
	/** */
	public void remove(Player p) {
		Skeleton.indentPrint("Cistern : remove()");
	}
	
	/** */
	public void direct(NetworkElement n) {
		Skeleton.indentPrint("Cistern : direct()");
	}
	
	/** */
	public void pickUpPump(Inventory inv) {
		Skeleton.indentPrint("Cistern : pickUpPump()");
	}
	
	/** */
	public void addConnection(NetworkElement ne) {
		Skeleton.indentPrint("Cistern : addConnection()");
	}
	
	/** */
	public void removeConnection(NetworkElement ne) {
		Skeleton.indentPrint("Cistern : removeConnection()");
	}
	
	/** */
	public void recieveWater(NetworkElement ne) {
		Skeleton.indentPrint("Cistern : recieveWater()");
	}
}
