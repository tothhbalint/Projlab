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
	public void repair(Pump pump) {
		pump.repairPump();
	}

	public void repair(Pipe pipe){
		pipe.repairPipe();
	}

	/**
	 * DONE check if position is Cistern
	 * only Cistern implements adding a new Pump
	 * */
	public void takePump(Inventory inv) {
		position.pickUpPump(this.inventory);
	}

	/** TODO */
	public void placePump() {
	}
	
	/**  */
	public void connectPipe() {
		position.connectPipe(this.inventory.removePipe());
	}
	
	/** TODO */
	public void disconnectPipe(NetworkElement ne) {
		Pipe pipe = (Pipe) position;
		if (pipe.isConnected(ne)){
			pipe.removeConnection(ne);
			ne.removeConnection(pipe);
			inventory.addPipe(pipe);
		}
	}
}
