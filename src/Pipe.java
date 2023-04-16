//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Pipe.java
//  @ Date : 16/04/2023
//
//

/** */
public class Pipe extends NetworkElement {
	/** */
	public void tick() {
	}
	
	/** */
	public void setInput() {
	}
	
	@Override
	public boolean isConnected(NetworkElement ne) {
		System.out.println("Pipe : isConnected()");
		return false;
	}

	/** */
	public boolean accept(Player p) {
		System.out.println("Pipe : accept()");
		return false;
	}
	
	/** */
	public void remove(Player p) {
		System.out.println("Pipe : remove()");
	}
	
	/** */
	public void direct(NetworkElement n) {
		System.out.println("Pipe : direct()");
	}
	
	/** */
	public void pickUpPump(Inventory inv) {
		System.out.println("Pipe : pickUpPump()");
	}
	
	/** */
	public void addConnection(NetworkElement ne) {
		System.out.println("Pipe : addConnection()");
	}
	
	/** */
	public void removeConnection(NetworkElement ne) {
		System.out.println("Pipe : removeConnection()");
	}
	
	/** */
	public void recieveWater(NetworkElement ne) {
		System.out.println("Pipe : recieveWater()");
	}
}
