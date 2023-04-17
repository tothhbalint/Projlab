//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Pipe.java
//  @ Date : 16/04/2023
//
//

/** */
public class Pipe extends NetworkElement {
	Pipe() {
		Skeleton.indentPrint("Pipe : Pipe()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Created a pipe");
		Skeleton.INDENT--;
	}
	/** */
	public void tick() {
	}

	
	/** */
	public void setInput(NetworkElement input) {
		Skeleton.indentPrint("Pipe : setInput()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Input set");
		Skeleton.INDENT--;
	}

	public void setOutput(NetworkElement output) {
		Skeleton.indentPrint("Pipe : setOutput()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Output set");
		Skeleton.INDENT--;
	}
	
	@Override
	public boolean isConnected(NetworkElement ne) {
		Skeleton.indentPrint("Pipe : isConnected()");
		return false;
	}

	/** */
	public boolean accept(Player p) {
		Skeleton.indentPrint("Pipe : accept()");
		Skeleton.INDENT++;
		if(!isOccupied()){
			NetworkElement oldPos = p.getPosition();
			if(oldPos.isConnected(this)) {
				oldPos.remove(p);
				oldPos.setOccupied();
				setOccupied();
				Skeleton.indentPrint("Player accepted");
				Skeleton.INDENT--;
				return true;
			}else {
				Skeleton.indentPrint("Player rejected");
				Skeleton.INDENT--;
				return false;
			}
		}else{
			return false;
		}
	}
	
	/** */
	public void remove(Player p) {
		Skeleton.indentPrint("Pipe : remove()");
	}
	
	/** */
	public void direct(NetworkElement in,NetworkElement out) {
		Skeleton.indentPrint("Pipe : direct()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Can't direct a pipe");
		Skeleton.INDENT--;
	}
	
	/** */
	public void pickUpPump(Inventory inv) {
		Skeleton.indentPrint("Pipe : pickUpPump()");
	}
	
	/** */
	public void addConnection(NetworkElement ne) {
		Skeleton.indentPrint("Pipe : addConnection()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Connection added");
		Skeleton.INDENT--;
	}
	
	/** */
	public void removeConnection(NetworkElement ne) {
		Skeleton.indentPrint("Pipe : removeConnection()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Connection removed");
		Skeleton.INDENT--;
	}

	/**
	 * Pipe receives water from the parameter NetworkElement
	 * @param ne The NetworkElement that sends water
	 * @author Buzas
	 * */
	public void recieveWater(NetworkElement ne) {
		Skeleton.indentPrint("Pipe : recieveWater()");
		setInput(ne);
		setWaterState(true);
		ne.setWaterState(false);
	}

	public void setDamaged(boolean b) {
		Skeleton.indentPrint("Pipe : setDamaged()");
		Skeleton.INDENT++;
		if(b){
			Skeleton.indentPrint("Pipe is damaged");
		}else{
			Skeleton.indentPrint("Pipe is fixed");
		}
		Skeleton.INDENT--;
	}
}
