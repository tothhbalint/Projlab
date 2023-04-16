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
	private int input;
	
	/** */
	private int output;
	
	/** */
	public void tick() {
	}
	
	/** */
	public void setInput() {
	}
	

	@Override
	public boolean isConnected(NetworkElement ne) {
		return false;
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
