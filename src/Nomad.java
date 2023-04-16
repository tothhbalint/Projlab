//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Nomad.java
//  @ Date : 16/04/2023
//
//

/** */
public class Nomad extends Player {
	/** */
	public void breakElement(NetworkElement ne) {
		System.out.println("Nomad: breakElement()");
	}
	
	/** */
	public void takePump(Inventory inv) {
		System.out.println("Nomad: takePump()");
	}
	
	/** */
	public void placePump() {
		System.out.println("Nomad: placePump()");
	}
	
	/** */
	public void connectPipe() {
		System.out.println("Nomad: connectPipe()");
	}
	
	/** */
	public void disconnectPipe(NetworkElement ne) {
		System.out.println("Nomad: disconnectPipe()");
	}
}
