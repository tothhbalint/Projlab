//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Pipe.java
//  @ Date : 16/04/2023
//
//




/** */
public class Pipe extends NetworkElement {
	/** */
	private NetworkElement input;
	/** */
	private NetworkElement output;
	/** */
	private boolean sticky;
	private boolean slippery;
	private int stickyTimeLeft;
	private int slipperyTimeLeft;
	private int repairProtectionTimeLeft;

	/** */
	public void tick() {
		if (isDamaged()){
			increasePlumberPoint();
		}
		if (sticky){
			stickyTimeLeft--;
			if (stickyTimeLeft <= 0){
				sticky = false;
			}
		}
		if (slippery){
			slipperyTimeLeft--;
			if (slipperyTimeLeft <= 0){
				slippery = false;
			}
		}
		if (repairProtectionTimeLeft > 0){
			repairProtectionTimeLeft--;
		}

	}
	
	/** */
	public void setInput(NetworkElement ne) {
		this.input = ne;
	}

	/** */
	public boolean accept(Player p) {
		return false;
	}
	
	/** */
	public void remove(Player p) {
	}

	/** */
	public void recieveWater(NetworkElement ne) {
	}
}
