//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Source.java
//  @ Date : 16/04/2023
//
//

/** */
public class Source extends NetworkElement {
	Source() {
		Skeleton.indentPrint("Source: Source()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Source created");
		Skeleton.INDENT--;
	}
	/** */
	public void tick() {
	}

	@Override
	void setInput(NetworkElement input) {
		Skeleton.indentPrint("Source : setInput()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Source has no input");
		Skeleton.INDENT--;
	}

	@Override
	void setOutput(NetworkElement output) {
		Skeleton.indentPrint("Source : setOutput()");
		Skeleton.INDENT++;
		output.setInput(this);
		Skeleton.indentPrint("Output set");
		Skeleton.INDENT--;
	}

	@Override
	void setDamaged(boolean b) {
		Skeleton.indentPrint("Source : setDamaged()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Source can't be damaged");
		Skeleton.INDENT--;
	}

	/** */
	public boolean accept(Player p) {
		Skeleton.indentPrint("Source : accept()");
		return super.accept(p);
	}
	
	/** */
	public void remove(Player p) {
		Skeleton.indentPrint("Source : remove()");
	}
	
	/** */
	public void direct(NetworkElement in,NetworkElement out) {
		Skeleton.indentPrint("Source : direct()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Can't direct a source");
		Skeleton.INDENT--;
	}
	
	/** */
	public void pickUpPump(Inventory inv) {
		Skeleton.indentPrint("Source : pickUpPump()");
	}
	
	/** */
	public void addConnection(NetworkElement ne) {
		Skeleton.indentPrint("Source : addConnection()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Connection added");
		Skeleton.INDENT--;
	}
	
	/** */
	public void removeConnection(NetworkElement ne) {
		Skeleton.indentPrint("Source : removeConnection()");
	}

	@Override
	public boolean isConnected(NetworkElement ne) {
		Skeleton.indentPrint("Source : isConnected()");
		return false;
	}

	/** */
	public void recieveWater(NetworkElement ne) {
		Skeleton.indentPrint("Source : recieveWater()");
	}
}
