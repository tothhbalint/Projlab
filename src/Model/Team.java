package Model;//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Team.java
//  @ Date : 16/04/2023
//
//


import java.util.ArrayList;

/**
 * This class represents a team in the game.
 * */
public class Team {
	/**
	 * Stores the players in the team
	 */
	private ArrayList<Player> members = new ArrayList<Player>();
	/**
	 * Stores the points of the team
	 */
	private int points = 0;
	/**
	 * Stores the number of players in the team
	 */
	private int noPlayers = 0;
	/**
	 * Stores the name of the team
	 */
	private String name;

	/**
	 * The constructor of the class.
	 * @param name The name of the team.
	 */
	public Team(String name) {
		this.name = name;
	}

	/**
	 * Adds a player to the team.
	 * @param p The player to be added.
	 * */
	public void addMember(Player p) {
		Proto.print("Team.addMember()");
		Proto.tab++;
			members.add(p);
			noPlayers++;
		Proto.tab--;
	}

	//TODO figure out an other way to identify a player
	/**
	 * A getter for a player.
	 * @param playerNumber The number of the player.
	 * @return The player identified by the number.
	 */
	public Player getPlayer(int playerNumber){
		if(playerNumber < members.size()){
			return members.get(playerNumber);
		}else{
			throw new RuntimeException("Player number out of bounds");
		}
	}

	/**
	 * A getter for the number of players in the team.
	 * @return The number of players in the team.
	 * */
	public int getNoPlayers() {
		return noPlayers;
	}
}
