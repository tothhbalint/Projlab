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
	public void makePipeSlippery(){
		Proto.print("Nomad.makePipeSlippery()");
		position.setSlippery();
	}

	public void takePump(Inventory inv) {
		Proto.print("Nomad.takePump()");
	}

	public void placePump() {
		Proto.print("Nomad.placePump()");
	}

	public void connectPipe() {
		Proto.print("Nomad.connectPipe()");
	}

	public void disconnectPipe(NetworkElement ne) {
		Proto.print("Nomad.disconnectPipe()");
	}
}
