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
	}
	
	/** */
	public void direct(NetworkElement n) {
	}
	
	/** */
	public void pickUpPump(Inventory inv) {
	}
	
	/** */
	public void addConnection(NetworkElement ne) {
	}
	
	/** */
	public void removeConnection(NetworkElement ne) {
	}
	
	/** */
	public void recieveWater(NetworkElement ne) {
	}
}
