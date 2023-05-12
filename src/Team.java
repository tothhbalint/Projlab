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
	private String name;
	private int maxTeamSize = 2;

	
	/** */
	public void updatePoints(int updatedPoints) {
		points = updatedPoints; //Will be easier to just ask for the static points of the team from the NetworkMap -> NetworkElement
	}
	
	/** */
	public void addMember(Player p) {
		if (members.size() < maxTeamSize) {
			members.add(p);
		}
	}
	
	/** */
	public Team(String name, int nop) {
		this.name = name;
		maxTeamSize = nop;
	}
	//TODO figure out an other way to identify a player
	public Player getPlayer(int playerNumber){
		if(playerNumber < members.size()){
			return members.get(playerNumber);
		}else{
			throw new RuntimeException("Player number out of bounds");
		}
	}

}
