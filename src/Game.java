//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Game.java
//  @ Date : 16/04/2023
//
//

/** */
public class Game {
	private Team plumberTeam = new Team("Plumber");
	private Team nomadTeam = new Team("Nomad");
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

	public Team getPlumberTeam() {
		return plumberTeam;
	}

	public Team getNomadTeam() {
		return nomadTeam;
	}

	public NetworkMap getMap() {
		return map;
	}
}
