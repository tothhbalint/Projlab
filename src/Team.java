//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Team.java
//  @ Date : 16/04/2023
//
//


import java.util.ArrayList;

/** */
public class Team {
	private ArrayList<Player> members = new ArrayList<Player>();
	private int points = 0;
	private int noPlayers = 0;
	private String name;

	/** */
	public Team(String name) {
		this.name = name;
	}

	/** */
	public void addMember(Player p) {
		Proto.print("Team.addMember()");
		Proto.tab++;
			members.add(p);
			noPlayers++;
		Proto.tab--;
	}

	//TODO figure out an other way to identify a player
	public Player getPlayer(int playerNumber){
		if(playerNumber < members.size()){
			return members.get(playerNumber);
		}else{
			throw new RuntimeException("Player number out of bounds");
		}
	}

	public int getNoPlayers() {
		return noPlayers;
	}
}
