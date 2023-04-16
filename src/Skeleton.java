import java.awt.image.ComponentSampleModel;
import java.util.HashMap;
import java.util.Scanner;

public class Skeleton {
    static Team plumberTeam;
    static Team nomadTeam;

    static HashMap<String, NetworkElement> elementHashMap = new HashMap<>();
    static HashMap<String, Player> playerHashMap = new HashMap<>();
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
        //TODO setting up connections and stuff
        plumberTeam = new Team();
        plumberTeam.createPlumberTeam("Plumbers", 2);
        nomadTeam = new Team();
        nomadTeam.createNomadTeam("Nomads", 2);

        nMap = new NetworkMap();

        nMap.build();
        scanner.nextLine();
    }
    public static void createTeam(){
        System.out.println("What kind of team do you want to create?");
        System.out.println("""
                                0. Plumber
                                1. Nomad""");
        String teamType = scanner.nextLine();

        System.out.println("How many players do you want to add to the team?");
        int noOfPlayers = scanner.nextInt();

        if(teamType.equals("0")){
            plumberTeam = new Team();
            plumberTeam.createPlumberTeam("Plumbers", noOfPlayers);
        } else if(teamType.equals("1")){
            nomadTeam = new Team();
            nomadTeam.createNomadTeam("Nomads", noOfPlayers);
        } else {
            System.out.println("Invalid input");
        }
        scanner.nextLine();
    }
    //TODO Toti
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
        scanner.nextLine();
    }
    //DONE
    public static void directPump(){
        init();
        System.out.println("What kind of player do you want to direct the pump with?");
        System.out.println("""
                                0. Plumber
                                1. Nomad""");
        String playerType = scanner.nextLine();

        if(playerType.equals("0")){
            playerHashMap.get("plumber").directPump();
        } else if(playerType.equals("1")){
            playerHashMap.get("nomad").directPump();
        } else {
            System.out.println("Invalid input");
        }
    }
    //DONE - Buzas
    public static void fixElement(){
        init();
        System.out.println("What kind of Element do you want to fix?");
        System.out.println("""
                                0. Pipe
                                1. Pump
                                """);
        String elementType = scanner.nextLine();
        if (elementType.equals("0")){
            System.out.println("Simulating fix pipe:");
            System.out.println("Creating conditions for broken pipe");
            INDENT++;
            Pipe position = (Pipe) elementHashMap.get("pipe1");
            position.setDamaged(true);
            INDENT--;
            Plumber player = (Plumber) playerHashMap.get("plumber");
            player.setPosition(position);
            player.repair(position);

        } else if (elementType.equals("1")){
            System.out.println("Simulating fix pump:");
            System.out.println("Creating conditions for broken pump");
            INDENT++;
            Pump position = (Pump) elementHashMap.get("pump");
            position.setDamaged(true);
            INDENT--;
            Plumber player = (Plumber) playerHashMap.get("plumber");
            player.setPosition(position);
            player.repair(position);

        } else {
            System.out.println("Invalid input");
        }
        System.out.println("\nPress enter to continue");
        scanner.nextLine();

    }
    //TODO Buzas
    public static void pickUpPump(){
        //TODO
        init();
        System.out.println("Is the plumber's inventory full?:");
        System.out.println("""
                                0. Yes
                                1. No""");
        int inventoryFull = scanner.nextInt();
        if (inventoryFull == 0){
            System.out.println("Simulating pick up pump:");
            System.out.println("Creating conditions");
            INDENT += 2;
            Cistern position = (Cistern) elementHashMap.get("cistern");
            Plumber player = (Plumber) playerHashMap.get("plumber");
            player.setPosition(position);
            Inventory inv = new Inventory();
            INDENT -= 2;
            player.takePump(inv);
            position.pickUpPump(inv);
            System.out.println("Inventory is full, cannot pick up pump");
        } else if (inventoryFull == 1){
            System.out.println("Simulating pick up pump:");
            System.out.println("Creating conditions");
            INDENT += 2;
            Cistern position = (Cistern) elementHashMap.get("cistern");
            Plumber player = (Plumber) playerHashMap.get("plumber");
            player.setPosition(position);
            Inventory inv = new Inventory();
            INDENT -= 2;
            player.takePump(inv);
            position.pickUpPump(inv);
            Pump newPump = new Pump();
            inv.addItem(newPump);
            System.out.println("Pump picked up");
        } else {
            System.out.println("Invalid input");
        }
        System.out.println("\nPress enter to continue");
        scanner.nextLine();
    }
    //TODO Marki
    public static void placePump(){
        //TODO
    }
    //TODO Bence
    public static void destroyPipe(){
        //TODO
    }

    //TODO Buzas
    public static void waterFlows(){
        //TODO
    }
    //TODO Bence
    public static void pipeSpawns(){
        //TODO
    }
    //TODO Marki
    public static void disconnectPipe(){
        //TODO
    }
    //TODO Marki
    public static void connectPipe(){
        //TODO
    }
    //TODO Bence
    public static void endOfRound() {
        //TODO
    }

    public static void indentPrint(String string){
        for(int i = 0; i < INDENT; i++){
            System.out.print("\t");
        }
        System.out.println(string);
    }
}