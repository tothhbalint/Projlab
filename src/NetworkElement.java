//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : NetworkElement.java
//  @ Date : 16/04/2023
//
//

/** */
public abstract class NetworkElement implements ITimer, IMove {
	/** */
	public abstract void tick();

	abstract void setInput(NetworkElement input);

	abstract void setOutput(NetworkElement output);

	public void setDamaged(boolean b){
		if (b){
			Skeleton.INDENT++;
			Skeleton.indentPrint("NetworkElement : setDamaged()");
			Skeleton.indentPrint("NetworkElement damaged");
			Skeleton.INDENT--;
		}
		else{
			Skeleton.INDENT++;
			Skeleton.indentPrint("NetworkElement : setDamaged()");
			Skeleton.indentPrint("NetworkElement repaired");
			Skeleton.INDENT--;
		}

	}
	
	/** */
	public boolean accept(Player p) {
		Skeleton.indentPrint("Cistern : accept()");
		Skeleton.INDENT++;
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
	}

	/** */
	public abstract void remove(Player p);
	
	/** */
	public abstract void direct(NetworkElement ne1,NetworkElement ne2);
	
	/** */
	public abstract void pickUpPump(Inventory inv);
	
	/** */
	public abstract void addConnection(NetworkElement ne);
	
	/** */
	public abstract void removeConnection(NetworkElement ne);
	
	/** */
	public boolean isConnected(NetworkElement ne){
		Skeleton.indentPrint("NetworkElement : isConnected()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Is this connected to the player's position?");
		Skeleton.indentPrint("0 - Yes");
		Skeleton.indentPrint("1 - No");
		String connected = Skeleton.scanner.nextLine();

		Skeleton.INDENT--;

		if (connected.equals("0")) {
			return true;
		} else {
			return false;
		}
	}
	
	/** */
	public abstract void recieveWater(NetworkElement ne);
	
	/** */
	public void setWaterState(boolean b) {
		Skeleton.indentPrint("NetworkElement : setWaterState()");
	}
	
	/** */
	public static int getNomadPoints() {
		Skeleton.INDENT++;
		Skeleton.indentPrint("NetworkElement : getNomadPoints()");
		Skeleton.INDENT--;
		return 0;
	}
	
	/** */
	public static int getPlumberPoints() {
		Skeleton.INDENT++;
		Skeleton.indentPrint("NetworkElement : getPlumberPoints()");
		Skeleton.INDENT--;
		return 0;
	}
	
	/** */
	private void increaseNomadPoint() {
		Skeleton.indentPrint("NetworkElement : increaseNomadPoint()");
	}
	
	/** */
	private void increasePlumberPoint() {
		Skeleton.indentPrint("NetworkElement : increasePlumberPoint()");
	}
	
	/** */
	public boolean isOccupied() {
		Skeleton.indentPrint("NetworkElement : isOccupied()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Is the network element occupied?");
		Skeleton.indentPrint("0 - No");
		Skeleton.indentPrint("1 - Yes");
		String occupied = Skeleton.scanner.nextLine();

		switch(Integer.parseInt(occupied)) {
			case 0:
				Skeleton.INDENT--;
				return false;
			case 1:
				Skeleton.INDENT--;
				return true;
			default:
				Skeleton.indentPrint("Invalid input");
				Skeleton.INDENT--;
				return false;
		}
	}
	
	/** */
	public void setOccupied() {
		Skeleton.indentPrint("NetworkElement : setOccupied()");
	}
}
