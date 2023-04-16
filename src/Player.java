//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Player.java
//  @ Date : 16/04/2023
//
//

/** */
public abstract class Player {

	/** */
	public abstract void takePump(Inventory inv);
	
	/** */
	public abstract void placePump();
	
	/** */
	public abstract void connectPipe();
	
	/** */
	public abstract void disconnectPipe(NetworkElement ne);

	/** */
	public void move(NetworkElement ne) {
	}

	/** */
	public void directPump() {
	}

	/** */
	public void setPosition(NetworkElement ne) {
	}
}
