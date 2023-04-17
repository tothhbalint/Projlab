//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Team.java
//  @ Date : 16/04/2023
//
//

/** */
public class Team {
	/** */
	public void updatePoints(int i) {
		Skeleton.indentPrint("Team: updatePoints()");
		Skeleton.INDENT--;
	}
	
	/** */
	public void addMember(Player p) {
		Skeleton.indentPrint("Team: addMember()");
	}
	
	/** */
	public void createPlumberTeam(String name, int nop) {
		Skeleton.indentPrint("Team: createPlumberTeam()");
		Skeleton.INDENT++;
		Skeleton.playerHashMap.put("plumber", new Plumber());
		Skeleton.indentPrint("Created player team with a size of: " + nop + " by the name: " + name);
		Skeleton.INDENT--;
	}
	
	/** */
	public void createNomadTeam(String name, int nop) {
		Skeleton.indentPrint("Team: createNomadTeam()");
		Skeleton.INDENT++;
		Skeleton.playerHashMap.put("nomad", new Nomad());
		Skeleton.indentPrint("Created nomad team with a size of: " + nop + " by the name: " + name);
		Skeleton.INDENT--;
	}
}
