//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Inventory.java
//  @ Date : 16/04/2023
//
//




/** */
public class Inventory {
	private Player owner;
	private Pump pump = null;
	private Pipe pipe = null;

	public Inventory(Player player){
		owner = player;
	}

	public void addPump(Pump pu) {
		if (pump == null) {
			pump = pu;
		}
	}

	public void addPipe(Pipe pi) {
		if (pipe == null) {
			pipe = pi;
		}
	}
	
	/** */
	public Pump removePump() {
		Pump p = pump;
		pump = null;
		return p;
	}

	/** */
	public Pipe removePipe() {
		Pipe p = pipe;
		pipe = null;
		return p;
	}

	/** */
	public boolean hasPump() {
		return pump != null;
	}

	/** */
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
}
