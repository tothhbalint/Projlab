//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Source.java
//  @ Date : 16/04/2023
//
//

/** */
public class Source extends NetworkElement {
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

	@Override
	public boolean isConnected(NetworkElement ne) {
		return false;
	}

	/** */
	public void recieveWater(NetworkElement ne) {
	}
}
