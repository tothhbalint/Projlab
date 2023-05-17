//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Nomad.java
//  @ Date : 16/04/2023
//
//


/**
 * This class is responsible for the nomad team
 */
public class Nomad extends Player {
	/**
	 * This method makes the pipe of the player's position slippery
	 */
	public void makePipeSlippery(){
		Proto.print("Nomad.makePipeSlippery()");
		position.setSlippery();
	}

	/**
	 * This method is not implemented
	 * @throws UnsupportedOperationException
	 */
	public void takePump(Inventory inv) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Nomads cannot take pump");
	}

	/**
	 * This method is not implemented
	 * @throws UnsupportedOperationException
	 */
	public void placePump() throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Nomads cannot place pump");
	}

	/**
	 * This method is not implemented
	 * @throws UnsupportedOperationException
	 */
	public void connectPipe() throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Nomads cannot connect pipes");
	}

	/**
	 * This method is not implemented
	 * @throws UnsupportedOperationException
	 */
	public void disconnectPipe(NetworkElement ne) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Nomads cannot disconnect pipes");
	}
}
