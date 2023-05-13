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
	public void makePipeSlippery(Pipe pipe){
		Proto.print("Nomad.makePipeSlippery()");
		if (!pipe.isSticky()){
			pipe.setSlippery(true);
		}
	}

	public void takePump(Inventory inv) {
		;
	}

	public void placePump() {
		;
	}

	public void connectPipe() {
		;
	}

	public void disconnectPipe(NetworkElement ne) {
		;
	}
}
