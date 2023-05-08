//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Pipe.java
//  @ Date : 16/04/2023
//
//

/** */
public class Pipe extends NetworkElement {
	private NetworkElement input;
	private NetworkElement output;
	private boolean sticky = false;
	private boolean slippery = false;
	private int stickyTimeLeft = 0;
	private int slipperyTimeLeft = 0;
	private int repairProtectionTimeLeft = 0;

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
	
	/** TODO */
	public void setInput(NetworkElement ne) {
		this.input = ne;
	}

	/** TODO */
	public boolean accept(Player p) {
		return false;
	}
	
	/** TODO */
	public void remove(Player p) {
	}

	/** TODO */
	public void recieveWater(NetworkElement ne) {
	}

	public void breakPipe(){
		if (this.repairProtectionTimeLeft <= 0)
			this.damaged = true;
	}

	public void repairPipe(){
		this.damaged = false;
		this.repairProtectionTimeLeft = 5;
	}

	public void setSticky(boolean b){
		if (!slippery){
			sticky = b;
			stickyTimeLeft = 5;
		}
	}

	public void setSlippery(boolean b){
		if (!sticky){
			slippery = b;
			slipperyTimeLeft = 5;
		}
	}

	public boolean isSticky(){
		return sticky;
	}

	public boolean isSlippery(){
		return slippery;
	}

	public boolean isRepairProtected(){
		return repairProtectionTimeLeft > 0;
	}
}
