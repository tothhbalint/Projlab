//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Cistern.java
//  @ Date : 16/04/2023
//
//

/** */
public class Cistern extends NetworkElement {

	Cistern() {
		Skeleton.indentPrint("Cistern : Cistern()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Cistern created");
		Skeleton.INDENT--;
	}

	@Override
	public boolean isConnected(NetworkElement ne) {
		return false;
	}

	/** */
	public void tick() {
	}

	@Override
	void setInput(NetworkElement input) {
		Skeleton.indentPrint("Cistern : setInput()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Input set");
		Skeleton.INDENT--;
	}

	@Override
	void setOutput(NetworkElement output) {
		Skeleton.indentPrint("Cistern : setOutput()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Cistern has no output");
		Skeleton.INDENT--;
	}

	@Override
	void setDamaged(boolean b) {
		Skeleton.indentPrint("Cistern : setDamaged()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Cistern can't be damaged");
		Skeleton.INDENT--;
	}

	@Override
	public boolean accept(Player p){
		Skeleton.indentPrint("Cistern : accept()");
		return super.accept(p);
	}

	/** */
	public void remove(Player p) {
		Skeleton.indentPrint("Cistern : remove()");
	}
	
	/** */
	public void direct(NetworkElement in,NetworkElement out) {
		Skeleton.indentPrint("Cistern : direct()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Can't direct a cistern");
		Skeleton.INDENT--;
	}
	
	/** */
	public void pickUpPump(Inventory inv) {
		Skeleton.indentPrint("Cistern : pickUpPump()");
	}
	
	/** */
	public void addConnection(NetworkElement ne) {
		Skeleton.indentPrint("Cistern : addConnection()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Connection added");
		Skeleton.INDENT--;
	}
	
	/** */
	public void removeConnection(NetworkElement ne) {
		Skeleton.indentPrint("Cistern : removeConnection()");
	}

	/**
	 * Cistern receives water from the parameter NetworkElement
	 * @param ne The NetworkElement that sends water
	 * @author Buzas
	 * */
	public void recieveWater(NetworkElement ne) {
		Skeleton.indentPrint("Cistern : recieveWater()");
		setInput(ne);
		setWaterState(true);
		ne.setWaterState(false);
	}
}
