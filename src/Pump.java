//
//  @ Project : ProjlabSkeleton
//  @ File Name : Pump.java
//  @ Date : 16/04/2023
//
//


import java.util.Random;

/**
 * This class is responsible for the pumps
 */
public class Pump extends NetworkElement {
	/**
	 * Stores the age of the pump
	 */
	private int age = 0;
	/**
	 * Random generator
	 */
	private Random rand = new Random();

	/**
	 * This method is responsible for the flowing of water in pumps and called in every round
	 * The pump will send water to its output if the pump is not damaged
	 */
	public void tick() {
		Proto.print("pump.tick");
		Random rand = new Random();
		int randomInt = rand.nextInt(this.connections.size());
		if(output == null)
			output = this.connections.get(randomInt);

		if(input == null){
			int temp = rand.nextInt(this.connections.size());
			if(temp == randomInt){
				temp = (temp + 1) % this.connections.size();
			}
			input = this.connections.get(temp);
		}

		Proto.tab++;
		if(!Proto.test) {
			if (rand.nextInt(10) < 2)
				this.breakPump();
		}
		Proto.tab--;
	}

	/**
	 * This method checks if a player can move to the pump
	 * @param p Player, which wants to move
	 * @return true, if the step is possible, false if it is not
	 */
	public boolean accept(Player p) {
		Proto.print("pump.accept");
		Proto.tab++;
		NetworkElement ne = p.getPosition();
		if(this.isConnected(ne) || ne == null){
			this.occupied = true;
			p.setPosition(this);
			if (ne != null)
				ne.remove(p);
			Proto.log("player accepted");
			Proto.tab--;
			return true;
		}
		Proto.log("player rejected");
		Proto.tab--;
		return false;
	}

	/**
	 * This method removes the player from the pump
	 * @param p The player, which needs to be removed
	 */
	public void remove(Player p) {
		Proto.print("pump.remove");
		Proto.tab++;
		this.occupants.remove(p);
		this.occupied = false;
		Proto.log("player removed");
		Proto.tab--;
	}

	/**
	 * This method is responsible for directing the pump to the right way
	 * @param in NetworkElement, that need to be set as input
	 * @param out NetworkElement, that need to be set as output
	 */
	public void direct(NetworkElement in, NetworkElement out) {
		Proto.print("pump.direct");
		Proto.tab++;
		if(this.input != null)
			this.input.setOutput(null);
		if(this.output != null)
			this.output.setInput(null);
		this.input = null;
		this.input = in;
		in.setOutput(this);
		this.output = out;
		out.setInput(this);
		Proto.log("pump direction changed new input:" + input + " new output:" + output);
		Proto.tab--;
	}

	/**
	 * This method creates and returns the values of the pump in a string
	 * @return values of the pump in string
	 */
	public String toString(){
		return "Pump" + super.toString() ;
	}

	/**
	 * This method is not implemented
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void breakPipe() throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Pump is not a Pipe");
	}

	/**
	 * This method is responsible for the flowing of water
	 * @param ne NetworkElement
	 */
	public void receiveWater(NetworkElement ne) {
		Proto.print("pump.receiveWater");
		Proto.tab++;
		if(!damaged){
			hasWater = true;
			Proto.log("pump has water");
		}
		Proto.tab--;
	}

	/**
	 * This method is not implemented
	 * @throws UnsupportedOperationException
	 */
	public void pickUpPump(Inventory inv) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Pump cannot be picked up");
	}

	/**
	 * This method sets the input of the pump
	 * @param ne NetworkElement, that need to be set as input
	 */
	public void setInput(NetworkElement ne) {
		this.input = ne;
	}

	/**
	 * This method sets the output of the pump
	 * @param ne NetworkElement, which need to be set as output
	 */
	public void setOutput(NetworkElement ne) {
		this.output = ne;
	}

	/**
	 * This method set the pump repaired (not damaged)
	 */
	public void repair(){
		Proto.print("pump.repairPump");
		Proto.tab++;
		this.damaged = false;
		age = 0;
		Proto.log("pump repaired");
		Proto.tab--;
	}

	/**
	 * This method is not implemented
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void setSlippery() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Pump cannot be slippery");
	}

	/**
	 * This method is not implemented
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void setSticky() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Pump cannot be sticky");
	}

	/**
	 * This method sets the pump damaged randomly
	 */
	private void breakPump(){
		Proto.print("pump.breakPump");
		Proto.tab++;
		int randNum = rand.nextInt(20 - age);
		if (randNum == 0){
			this.damaged = true;
		}
		age++;
		Proto.log("pump broken");
		Proto.tab--;
	}

	/**
	 * This method connects a pipe with the pump
	 * @param ne pipe, that need to be connected
	 */
	public void connectPipe(NetworkElement ne) {
		Proto.print("pump.connectPipe");
		Proto.tab++;
		NetworkMap.connect(ne, this);
		if (ne.connections.size() == 2)
			NetworkMap.setInAndOutput(ne, this);
		Proto.log("pipe connected");
		Proto.tab--;
	}

	/**
	 * This method disconnects a pipe from the pump
	 * @param ne pipe, that need to be disconnected
	 */
	public void disconnectPipe(NetworkElement ne) {
		Proto.print("pump.disconnectPipe");
		Proto.tab++;
		this.removeConnection(ne);
		ne.removeConnection(this);
		Proto.log("pipe disconnected");
		Proto.tab--;
	}


	/**
	 * This method is not implemented
	 * Cannot place pump on pumps
	 * @throws UnsupportedOperationException
	 */
	public boolean placePump() throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Pump cannot be placed here");
	}

	/**
	 * This method is not implemented
	 * @throws UnsupportedOperationException
	 */
	public NetworkElement getPipeOutput() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Pump is not a Pipe");
	}

}
