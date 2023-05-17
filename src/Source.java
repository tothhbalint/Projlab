//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Source.java
//  @ Date : 16/04/2023
//
//

import java.util.LinkedList;

/**
 * This class represents a source in the game.
 * */
public class Source extends NetworkElement {
	private class FlowTree {
		private LinkedList<NetworkElement> path;

		public FlowTree(){
			path = new LinkedList<NetworkElement>();
		}

		public void nextDepth(){
			Proto.print("FlowTree.nextDepth");
			Proto.tab++;
			LinkedList<NetworkElement> newPath = new LinkedList<NetworkElement>();
			if(path.size() == 0) {
				path.add(Source.this);
				Proto.tab--;
				return;
			}
			for (NetworkElement ne : path) {
				if(ne.hasWater) {
					if (!ne.damaged) {
						if(newPath.size() == 0)
							newPath.add(ne);
						newPath.add(ne.output);
					}else break;
				}else break;
			}
			path = newPath;
			Proto.tab--;
		}

		public void flow(){
			for (NetworkElement networkElement : path) {
				if(networkElement.output == null) break;
				networkElement.output.receiveWater(networkElement);
			}
		}
	}

	private FlowTree flowTree;
	/**
	 * Constructor
	 * Sets that source has water
	 */
	Source() {
		hasWater = true;
		flowTree = new FlowTree();
	}
	@Override
	public void setInput(NetworkElement ne) {
		hasWater = true;
	}

	/**
	 * This method is called every turn.
	 * The source will send water to all of its connections.
	 * */
	public void tick() {
		Proto.print("source.tick");
		Proto.tab++;
		flowTree.nextDepth();
		flowTree.flow();
		Proto.tab--;
	}

	/**
	 * This method is called when a player steps on the source.
	 * The player will be accepted if the source is connected to the player's current position.
	 * */
	public boolean accept(Player p) {
		Proto.print("source.accept");
		Proto.tab++;
		NetworkElement ne = p.getPosition();
		if (this.isConnected(ne) || ne == null) {
			if (ne != null)	ne.remove(p);
			this.occupants.add(p);
			p.setPosition(this);
			Proto.log("player accepted");
			Proto.tab--;
			return true;
		}
		Proto.log("player not accepted");
		Proto.tab--;
		return false;
	}

	/**
	 * This method is called when a player leaves the source.
	 * */
	public void remove(Player p) {
		Proto.print("source.remove");
		Proto.tab++;
		this.occupants.remove(p);
		Proto.log("player removed");
		Proto.tab--;
	}

	/**
	 * This isn't implemented in the source.
	 * The source cannot receive water.
	 * */
	public void receiveWater(NetworkElement ne) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Source cannot recieve water");
	}

	/**
	 * This isn't implemented in the source.
	 * You cannot pick up a pump from a source.
	 * */
	public void pickUpPump(Inventory inv) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Pump cannot be picked up from Source");
	}

	/**
	 * This isn't implemented in the source.
	 * The source cannot be directed, because it sends water to all of its connections.
	 * */
	public void direct(NetworkElement in, NetworkElement out) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Source cannot be direct");
	}

	/**
	 * This method is called when a pipe is placed next to the source.
	 * The source will connect to the pipe.
	 * @param ne The pipe that is placed next to the source. The source will connect to this pipe.
	 * */
	public void connectPipe(NetworkElement ne) {
		Proto.print("source.connectPipe");
		Proto.tab++;
		NetworkMap.connect(ne, this);
		if (ne.connections.size() == 2)
			NetworkMap.setInAndOutput(ne, this);
		Proto.log("pipe connected");
		Proto.tab--;
	}

	/**
	 * This method is called when a pipe is removed from next to the source.
	 * The source will disconnect from the pipe.
	 * @param ne The pipe that is removed from next to the source. The source will disconnect from this pipe.
	 * */
	public void disconnectPipe(NetworkElement ne) {
		Proto.print("source.disconnectPipe");
		Proto.tab++;
		this.removeConnection(ne);
		ne.removeConnection(this);
		Proto.log("pipe disconnected");
		Proto.tab--;
	}


	/**
	 * This method is not implemented
	 * Cannot place pump by cistern
	 * @throws UnsupportedOperationException
	 */
	public boolean placePump() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot place Pump next to a Source");
	}

	/**
	 * This method is not implemented
	 * @throws UnsupportedOperationException
	 */
	public NetworkElement getPipeOutput() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("");
	}


	/**
	 * This method creates and returns the values of the source in a string
	 * @return values of cistern in string
	 */
	public String toString(){
		return "Source" + super.toString();
	}

	/**
	 * This method is not implemented
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void breakPipe() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Source cannot be broken");
	}

	/**
	 * This method is not implemented
	 * Source cannot break so don't need to be repaired
	 * @throws UnsupportedOperationException
	 */
	public void repair() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Source cannot be repaired");

	}

	/**
	 * This method is not implemented
	 * Source cannot be slippery
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void setSlippery() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Source cannot be slippery");
	}

	/**
	 * This method is not implemented
	 * Source cannot be sticky
	 * @throws UnsupportedOperationException
	 */
	public void setSticky() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Source cannot be sticky");
	}
}
