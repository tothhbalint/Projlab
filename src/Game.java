//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Game.java
//  @ Date : 16/04/2023
//
//

/** */
public class Game {
	private Team plumberTeam = new Team();
	private Team nomadTeam = new Team();
	private NetworkMap map = new NetworkMap();

	/** */
	public void startGame() {
	}
	
	/** */
	public void endGame() {
	}

	public void tick(){
		map.tick();
	}
}
