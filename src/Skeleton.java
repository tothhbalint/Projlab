import java.util.HashMap;
import java.util.Scanner;

public class Skeleton {

    static Team plumberTeam;

    static Team nomadTeam;


    static NetworkMap nMap = new NetworkMap();

    static Scanner scanner = new Scanner(System.in);

    static int INDENT = 0;
    static int noOfPlayers = 0;
    static int noOfTeams = 0;
    static int noOfElements = 0;
    static int noOfInventories = 0;

    public static void main(String[] args) {
        while(true){
            resetSkeleton();

            System.out.println("Choose a situation to simulate:");
            System.out.println("""
                                0. Init
                                1. Create Team
                                2. Move player
                                3. Direct pump
                                4. Fix element
                                5. Pick up pump
                                6. Place pump
                                7. Destroy pipe
                                8. Water flows
                                9. Pipe spawns
                                10. Disconnect pipe
                                11. Connect pipe
                                12. End of round
                                13. Exit""");

            switch (scanner.nextLine()){
                case "0":
                    init();
                    break;
                case "1":
                    createTeam();
                    break;
                case "2":
                    movePlayer();
                    break;
                case "3":
                    directPump();
                    break;
                case "4":
                    fixElement();
                    break;
                case "5":
                    pickUpPump();
                    break;
                case "6":
                    placePump();
                    break;
                case "7":
                    destroyPipe();
                    break;
                case "8":
                    waterFlows();
                    break;
                case "9":
                    pipeSpawns();
                    break;
                case "10":
                    disconnectPipe();
                    break;
                case "11":
                    connectPipe();
                    break;
                case "12":
                    endOfRound();
                    break;
                case "13":
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
            }
        }
    }



    public static void resetSkeleton(){
        noOfPlayers = 0;
        noOfTeams = 0;
        noOfElements = 0;
        noOfInventories = 0;

        plumberTeam = null;
        nomadTeam = null;
        nMap = null;
    }

    public static void init(){
        //TODO Make it work on the inside too
        createTeam();
        createTeam();

        nMap.build();

        System.out.println("Finished initializing \n Press enter to continue");
        scanner.nextLine();
    }

    public static void createTeam(){
        //TODO Make it work on the inside too
        System.out.println("What kind of team do you want to create?");
        System.out.println("""
                                0. Plumber
                                1. Nomad""");
        String teamType = scanner.nextLine();
        System.out.println("How many players do you want to create?");
        int noOfPlayers = Integer.parseInt(scanner.nextLine());

        if(teamType.equals("0")){
            plumberTeam.createPlumberTeam("Plumbers", noOfPlayers);
        } else if(teamType.equals("1")){
            nomadTeam.createNomadTeam("Nomads", noOfPlayers);
        } else {
            System.out.println("Invalid input");
        }
    }

    public static void movePlayer(){
        System.out.println("What kind of player do you want to move?");
        System.out.println("""
                                0. Plumber
                                1. Nomad""");
        String playerType = scanner.nextLine();

        System.out.println("Where is the player standing?");
        System.out.println("""
                                0. Cistern
                                1. Pipe
                                2. Pump
                                """);
        String playerPosition = scanner.nextLine();

        if(playerType.equals("0")){
        } else if(playerType.equals("1")){
        } else {
            System.out.println("Invalid input");
        }

        switch (playerPosition){
            case "0":
                //TODO
                break;
            case "1":
                //TODO
                break;
            case "2":
                //TODO
                break;
        }

        //TODO
    }

    public static void directPump(){
        System.out.println("What kind of player do you want to direct the pump with?");
        System.out.println("""
                                0. Plumber
                                1. Nomad""");
        String playerType = scanner.nextLine();

        System.out.println("Where is the player standing?");
        System.out.println("""
                                0. Cistern
                                1. Pipe
                                2. Pump
                                """);
        String playerPosition = scanner.nextLine();

        if(playerType.equals("0")){
        } else if(playerType.equals("1")){
        } else {
            System.out.println("Invalid input");
        }

        switch (playerPosition){
            case "0":
                System.out.println("Can't direct pump from cistern");
                break;
            case "1":
                System.out.println("Can't direct pump from pipe");
                break;
            case "2":
                System.out.println("Where do you want to direct the pump?");
                //TODO
                break;
        }

        //TODO
    }

    public static void fixElement(){
        //TODO
    }

    public static void pickUpPump(){
        //TODO
    }

    public static void placePump(){
        //TODO
    }

    public static void destroyPipe(){
        //TODO
    }

    public static void waterFlows(){
        //TODO
    }

    public static void pipeSpawns(){
        //TODO
    }

    public static void disconnectPipe(){
        //TODO
    }

    public static void connectPipe(){
        //TODO
    }

    public static void endOfRound() {
        //TODO
    }

    public static void indentPrint(String string){
        for(int i = 0; i < INDENT; i++){
            System.out.print("\t");
        }
        System.out.println(string);
        INDENT++;
    }
}