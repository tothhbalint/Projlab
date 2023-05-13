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
		Proto.print("Plumber.repair(Pump)");
		pump.repairPump();
	}

	public void repair(Pipe pipe){
		Proto.print("Plumber.repair(Pipe)");
		pipe.repairPipe();
	}

	/** */
	public void takePump(Inventory inv) {
		Proto.print("Plumber.takePump()");
		position.pickUpPump(this.inventory);
	}

	/** TODO */
	public void placePump() {
		Proto.print("Plumber.placePump()");
		if(position.placePump()) {
			Pump tempPump = inventory.removePump();
			Pipe tempPipe = new Pipe();
			NetworkElement nextPump = position.getPipeOutput();
			position.removePipeOutput(nextPump);
			nextPump.removeConnection(position);
			position.addPipeOutput(tempPump);
			tempPump.addConnection(position);
			tempPump.addConnection(tempPipe);
			tempPipe.addPipeInput(tempPump);
			nextPump.addConnection(tempPipe);
			tempPipe.addPipeOutput(nextPump);
			tempPump.direct(position, tempPipe);
		}
	}
	
	/**  */
	public void connectPipe() {
		Proto.print("Plumber.connectPipe()");
		position.connectPipe(this.inventory.removePipe());
	}
	
	/** */
	public void disconnectPipe(NetworkElement ne) {
		Proto.print("Plumber.disconnectPipe()");
		position.disconnectPipe(ne);
	}
}
