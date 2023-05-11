//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Game.java
//  @ Date : 16/04/2023
//
//

/** */
public class Game {
	private Team plumberTeam = new Team("Plumber", 2);
	private Team nomadTeam = new Team("Nomad", 2);
	private NetworkMap map = new NetworkMap();

	/** TODO */
	public void startGame() {
	}
	
	/** TODO */
	public void endGame() {
	}

	public void tick(){
		map.tick();
	}
}
