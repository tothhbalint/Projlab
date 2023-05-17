//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Inventory.java
//  @ Date : 16/04/2023
//
//


/**
 * This class is responsible for the players' inventory
 */
public class Inventory {
	/**
	 * Owner of the inventory
	 */
	private Player owner;
	/**
	 * Stores a pump in the player's inventory, null if it has not
	 */
	private Pump pump = null;
	/**
	 * Stores a pipe in the player's inventory, null if it has not
	 */
	private Pipe pipe = null;

	/**
	 * Constructor sets the owner of the inventory
	 * @param player Player
	 */
	public Inventory(Player player){
		owner = player;
	}

	/**
	 * This method adds a pump to the player's inventory if it has not
	 * @param pu Pump, that need to be added
	 */
	public void addPump(Pump pu) {
		Proto.print("Inventory.addPump");
		if (pump == null) {
			pump = pu;
			Proto.log("pump added");
		}else {
			Proto.log("pump not added : inventory full");
		}
		Proto.tab--;

	}

	/**
	 * This method adds a pipe to a player's inventory if it has not
	 * @param pi Pipe, that need to be added
	 */
	public void addPipe(Pipe pi) {
		if (pipe == null) {
			pipe = pi;
			pipe.inInventory = true;
			Proto.log("pipe added");
		}else {
			Proto.log("pipe not added : inventory full");
		}
		Proto.tab--;
	}

	/**
	 * This method get the pump from the player's inventory, and sets the inventory to null
	 * @return Pump from the inventory
	 */
	public Pump removePump() {
		Pump p = pump;
		pump = null;
		return p;
	}

	/**
	 * This method gets the pipe from the player's inventory, and sets the inventory to null
	 * @return Pipe from the inventory
	 */
	public Pipe removePipe() {
		Pipe p = pipe;
		if (p.connections.size() == 0 ) {
			return p;
		}
		pipe = null;
		return p;
	}

	/**
	 * This method gets if the player has a pump in the inventory
	 * @return true if player has a pump, false if it has not
	 */
	public boolean hasPump() {
		return pump != null;
	}

	/**
	 * This method gets if the player has a pipe in the inventory
	 * @return true if the player has pipe, false if it has not
	 */
	public boolean hasPipe() {
		return pipe != null;
	}

	public String toString(){
		String s = "Inventory: ";
		if (pump != null) {
			s += "Pump";
		}
		if (pipe != null) {
			s += "Pipe";
		}
		return s;
	}

	public boolean isEmpty() {
		return pump == null && pipe == null;
	}

	public boolean isFull() {
		return pump != null || pipe != null;
	}

	public boolean contains(Pump p) {
		return pump == p;
	}

	public boolean contains(Pipe p) {
		return pipe == p;
	}
}
