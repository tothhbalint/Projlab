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

	/** */
	public Team(String name) {
		Proto.print("Team.Team(String)");
		this.name = name;
	}

	/** */
	public void updatePoints(int updatedPoints) {
		Proto.print("Team.updatePoints()");
		switch(name.toLowerCase()) {
			case "nomad":
				points = NetworkElement.getNomadPoints();
				break;
			case "plumber":
				points = NetworkElement.getPlumberPoints();
				break;
		}
	}
	
	/** */
	public void addMember(Player p) {
		Proto.print("Team.addMember()");
			members.add(p);
	}

	//TODO figure out an other way to identify a player
	public Player getPlayer(int playerNumber){
		Proto.print("Team.getPlayer()");
		if(playerNumber < members.size()){
			return members.get(playerNumber);
		}else{
			throw new RuntimeException("Player number out of bounds");
		}
	}
}
