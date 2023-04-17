//
//  @ Project : ProjlabSkeleton
//  @ File Name : Pump.java
//  @ Date : 16/04/2023
//
//

/** */
public class Pump extends NetworkElement {
	Pump() {
		Skeleton.indentPrint("Pump : Pump()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Pump created.");
		Skeleton.INDENT--;
	}
	@Override
	public boolean isConnected(NetworkElement ne) {
		Skeleton.indentPrint("Pump : isConnected()");
		return false;
	}

	/** */
	public void tick() {
		Skeleton.indentPrint("Pump : tick()");
	}

	@Override
	void setInput(NetworkElement input) {
		Skeleton.indentPrint("Pump : setInput()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Input set");
		Skeleton.INDENT--;
	}

	@Override
	void setOutput(NetworkElement output) {
		Skeleton.indentPrint("Pump : setOutput()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Output set");
		Skeleton.INDENT--;
	}

	/** */
	public void remove(Player p) {
		Skeleton.indentPrint("Pump : remove()");
	}
	
	/** */
	public void direct(NetworkElement in,NetworkElement out) {
		Skeleton.indentPrint("Pump : direct()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Do the two pipes connect to the pump?"); Skeleton.indentPrint("0 - Yes"); Skeleton.indentPrint("1 - No");
		switch(Integer.parseInt(Skeleton.scanner.nextLine())) {
			case 0:
				Skeleton.indentPrint("Pump directed through the pipes.");
				break;
			case 1:
				Skeleton.indentPrint("Cant direct the pump through these lines.");
				break;
			default:
				Skeleton.indentPrint("Invalid input.");
				break;
		}
		Skeleton.INDENT-=2;
	}
	
	/** */
	public void pickUpPump(Inventory inv) {
		Skeleton.indentPrint("Pump : pickUpPump()");
	}
	
	/** */
	public void addConnection(NetworkElement ne) {
		Skeleton.indentPrint("Pump : addConnection()");
	}
	
	/** */
	public void removeConnection(NetworkElement ne) {
		Skeleton.indentPrint("Pump : removeConnection()");
	}
	
	/** */
	public void recieveWater(NetworkElement ne) {
		Skeleton.indentPrint("Pump : recieveWater()");
	}

	public void setDamaged(boolean b) {
		Skeleton.indentPrint("Pump : setDamaged()");
	}
}
