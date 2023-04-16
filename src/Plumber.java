//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Plumber.java
//  @ Date : 16/04/2023
//
//

/** */
public class Plumber extends Player {
	/** */
	public void repair(NetworkElement ne) {
		System.out.println("Plumber: repair()");
	}
	
	/** */
	public void takePump(Inventory inv) {
		System.out.println("Plumber: takePump()");
	}
	
	/** */
	public void placePump() {
		System.out.println("Plumber: placePump()");
	}
	
	/** */
	public void connectPipe() {
		System.out.println("Plumber: connectPipe()");
	}
	
	/** */
	public void disconnectPipe(NetworkElement ne) {
		System.out.println("Plumber: disconnectPipe()");
	}
}
