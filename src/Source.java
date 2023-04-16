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
		System.out.println("Source : accept()");
		return false;
	}
	
	/** */
	public void remove(Player p) {
		System.out.println("Source : remove()");
	}
	
	/** */
	public void direct(NetworkElement n) {
		System.out.println("Source : direct()");
	}
	
	/** */
	public void pickUpPump(Inventory inv) {
		System.out.println("Source : pickUpPump()");
	}
	
	/** */
	public void addConnection(NetworkElement ne) {
		System.out.println("Source : addConnection()");
	}
	
	/** */
	public void removeConnection(NetworkElement ne) {
		System.out.println("Source : removeConnection()");
	}

	@Override
	public boolean isConnected(NetworkElement ne) {
		System.out.println("Source : isConnected()");
		return false;
	}

	/** */
	public void recieveWater(NetworkElement ne) {
		System.out.println("Source : recieveWater()");
	}
}
