//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Plumber.java
//  @ Date : 16/04/2023
//
//


/**
 * This class is responsible for plumbers
 */
public class Plumber extends Player {
	/**
	 * This method calls the repair() method of the NetworkElement, where the players stands
	 */
	public void repair() {
		Proto.print("Plumber.repair()");
		position.repair();
	}

	/**
	 * TODO check
	 * This method tries to take pump into player's inventory
	 * @param inv
	 */
	public void takePump(Inventory inv) {
		Proto.print("Plumber.takePump()");
		position.pickUpPump(this.inventory);
	}

	/**
	 * This method places a pump if it is possible
	 * Plumbers can only place pumps on a pipe by splitting the pipe
	 */
	public void placePump() {
		Proto.print("Plumber.placePump()");
		if(position.placePump()) {
			Pump tempPump = inventory.removePump();
			Pipe tempPipe = new Pipe();
			NetworkElement nextPump = position.getPipeOutput();
			position.removePipeOutput(nextPump);
			nextPump.removeConnection(position);
			NetworkMap.add(tempPipe);
			NetworkMap.connect(tempPump, tempPipe);
			position.addPipeOutput(tempPump);
			tempPump.addConnection(position);
			tempPump.addConnection(tempPipe);
			tempPipe.addPipeInput(tempPump);
			nextPump.addConnection(tempPipe);
			tempPipe.addPipeOutput(nextPump);
			tempPump.direct(position, tempPipe);
		}
	}

	/**
	 * This method connect the NetworkElement where the player stands with the pipe, which is in its inventory
	 * If there is no pipe in its inventory the method returns
	 */
	public void connectPipe() {
		Proto.print("Plumber.connectPipe()");
		Proto.tab++;
		if(inventory.isEmpty()) {
			Proto.log("inventory empty");
			Proto.log("pipe cant be placed");
			return;
		}
		position.connectPipe(this.inventory.removePipe());
		Proto.tab--;
	}

	/**
	 * This method is responsible for disconnecting the NetworkElement, where player stands from the NetworkElement, which the method gets as parameter
	 * @param ne NetworkElement, which need to be disconnected from position
	 */
	public void disconnectPipe(NetworkElement ne) {
		Proto.print("Plumber.disconnectPipe()");
		position.disconnectPipe(ne);
	}

	/**
	 * This method is responsible for making the NetworkElement slippery if it is possible
	 */
	public void makePipeSlippery(){
		position.setSlippery();
	}
}

