//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Player.java
//  @ Date : 16/04/2023
//  @ Author : 
//
//


public abstract class Player {
	protected Inventory inventory;
	protected NetworkElement position;
	protected boolean stuck;
	protected int stuckTimeLeft;

	/** */
	public Player() {
		Proto.print("Player.Player()");
		position = null;
		stuck = false;
		stuckTimeLeft = 0;
		inventory = new Inventory(this);
	}

	/** */
	public Player(NetworkElement ne) {
		Proto.print("Player.Player(NetworkElement)");
		position = ne;
		stuck = false;
		stuckTimeLeft = 0;
		inventory = new Inventory(this);
	}

	/** */
	public NetworkElement getPosition() {
		Proto.print("Player.getPosition()");
		return this.position;
	}

	/** */
	public void setPosition(NetworkElement ne) {
		Proto.print("Player.setPosition()");
		this.position = ne;
	}

	/** */
	public boolean getStuck() {
		Proto.print("Player.getStuck()");
		return this.stuck;
	}

	/** */
	public void setStuck(boolean b) {
		Proto.print("Player.setStuck()");
		this.stuck  = b;
	}

	/** */
	public int getStuckTimeLeft() {
		Proto.print("Player.getStuckTimeLeft()");
		return this.stuckTimeLeft;
	}

	/** */
	public void setStuckTimeLeft(int i) {
		Proto.print("Player.setStuckTimeLeft()");
		this.stuckTimeLeft = i;
	}

	/** */
	public Inventory getInventory() {
		Proto.print("Player.getInventory()");
		return this.inventory;
	}

	/** */
	public void setInventory(Inventory inv) {
		Proto.print("Player.setInventory()");
		this.inventory = inv;
	}

	/** */
	public abstract void takePump(Inventory inv);

	/** */
	public abstract void placePump();

	/** */
	public abstract void connectPipe();

	/** */
	public abstract void disconnectPipe(NetworkElement ne);

	public void tick(){
		Proto.print("Player.tick()");
		if (stuck){
			stuckTimeLeft--;
			if (stuckTimeLeft <= 0){
				stuck = false;
			}
		}
	}

	//TODO
	public void move(NetworkElement ne) {
		Proto.print("Player.move()");
		if (ne.accept(this)){
			this.setPosition(ne);
		}
	}
	
	/** TODO if position is an instance of pump,
	 * we can choose from the pumps' connections an output, and set it as an output
	 * NOTE: maybe we should rather use the connection as the parameter, not the pump itself
	 * RE - NOTE: maybe we rather use the pump, and the desired in- and output as parameters,
	 * 			  because the pump should be given in position (which is a NetworkElement though)
	 * */
	public void directPump(Pump pump, NetworkElement in, NetworkElement out) {
		Proto.print("Player.directPump()");
		position.direct(in, out);
	}

	public void makePipeSticky(){
		Proto.print("Player.makePipeSticky()");
		position.setSticky();
	}

	public void breakPipe(){
		Proto.print("Player.breakPipe()");
		position.breakPipe();
	}

	public void takePipe(NetworkElement pipeToDisconnect){
		Proto.print("Player.takePipe()");
		try {
			position.disconnectPipe(pipeToDisconnect);
			inventory.addPipe((Pipe) pipeToDisconnect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void takePump(){
		Proto.print("Player.takePump()");
		try {
			inventory.addPump((Pump) position);
			position.pickUpPump(inventory);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toString(){
		return "Player position: " + position.toString() + " stuck: " + stuck + " " + stuckTimeLeft + " " + inventory.toString();
	}
}
