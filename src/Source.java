//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Source.java
//  @ Date : 16/04/2023
//
//




/** */
public class Source extends NetworkElement {

	/** */
	public void tick() {
	}

	public boolean accept(Player p) {
		NetworkElement ne = p.getPosition();
		for (NetworkElement n:
				this.connections) {
			if (n == ne){
				ne.remove(p);
				return true;
			}
		}
		return false;
	}
	public void remove(Player p) {
		// Nothing
	}

	/** */
	public void recieveWater(NetworkElement ne) {
	}

	/** */
	public void pickUpPump(Inventory inv) {

	}

	/** */
	public void direct(NetworkElement in, NetworkElement out) {

	}

	public void connectPipe(NetworkElement ne) {
		this.addConnection(ne);
		ne.addConnection(this);
	}

	public void disconnectPipe(NetworkElement ne) {
		this.removeConnection(ne);
		ne.removeConnection(this);
	}

	public String toString(){
		return "Source" + super.toString();
	}

}
