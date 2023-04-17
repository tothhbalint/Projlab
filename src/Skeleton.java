import java.awt.image.ComponentSampleModel;
import java.util.HashMap;
import java.util.Scanner;

public class Skeleton {
    static Team plumberTeam;
    static Team nomadTeam;

    static Game game = new Game();

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

            switch (scanner.nextLine()) {
                case "0" -> init();
                case "1" -> createTeam();
                case "2" -> movePlayer();
                case "3" -> directPump();
                case "4" -> fixElement();
                case "5" -> pickUpPump();
                case "6" -> placePump();
                case "7" -> destroyPipe();
                case "8" -> waterFlows();
                case "9" -> pipeSpawns();
                case "10" -> disconnectPipe();
                case "11" -> connectPipe();
                case "12" -> endOfRound();
                case "13" -> System.exit(0);
                default -> System.out.println("Invalid input");
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
    public static void movePlayer(){
        init();
        System.out.println("What kind of player do you want to move?");
        System.out.println("""
                                0. Plumber
                                1. Nomad""");
        String playerType = scanner.nextLine();

        System.out.println("Where do you want to move the player?");
        System.out.println("""
                                0. Source
                                1. Cistern
                                2. Pipe
                                3. Pump""");
        String elementType = scanner.nextLine();
        String elementName = "";

        if(elementType.equals("0")){
           elementName = "source";
        } else if(elementType.equals("1")){
            elementName = "cistern";
        } else if(elementType.equals("2")){
            elementName = "pipe1";
        } else if(elementType.equals("3")){
            elementName = "pump";
        } else {
            System.out.println("Invalid input");
        }

        if(playerType.equals("0")){
            playerHashMap.get("plumber").move(elementHashMap.get(elementName));
        } else if(playerType.equals("1")){
            playerHashMap.get("nomad").move(elementHashMap.get(elementName));
        } else {
            System.out.println("Invalid input");
        }

        scanner.nextLine();
    }
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
        Plumber plumber = (Plumber) playerHashMap.get("plumber");
        NetworkElement position = plumber.getPosition();

        plumber.repair(position);
        System.out.println("\nPress enter to continue");
        scanner.nextLine();
    }

    //TODO - Buzas
    public static void pickUpPump(){
        init();
        System.out.println("Is the plumber's inventory full?:");
        System.out.println("""
                                0. Yes
                                1. No""");
        int inventoryFull = scanner.nextInt();
        if (inventoryFull == 0){
            System.out.println("Simulating pick up pump:");
            System.out.println("Creating conditions");
            Cistern position = (Cistern) elementHashMap.get("cistern");
            Plumber player = (Plumber) playerHashMap.get("plumber");
            player.setPosition(position);
            Inventory inv = new Inventory();
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
    //DONE Marki
    public static void placePump(){
        init();
        System.out.println("What kind of player do you want to place a pump with?");
        System.out.println("""
                                0. Plumber
                                1. Nomad""");
        String playerType = scanner.nextLine();

        String playerPosition = "0";
        if(playerType.equals("0")) {
            System.out.println("Where is the player standing?");
            System.out.println("""
                    0. Cistern
                    1. Pipe
                    2. Pump
                    """);
            playerPosition = scanner.nextLine();
        }

        System.out.println("Creating conditions for placing pump");
        INDENT++;
        Inventory inventory = new Inventory();
        inventory.addItem(elementHashMap.get("pump"));
        INDENT--;
        System.out.println("Simulating placePump():");
        INDENT++;
        if(playerType.equals("0")) {
            if(playerPosition.equals("0")) {
                playerHashMap.get("plumber").placePump();
                INDENT++;
                indentPrint("Cannot place pipe on position");
                INDENT--;
            } else if(playerPosition.equals("1")) {
                playerHashMap.get("plumber").placePump();
                INDENT++;
                inventory.removeItem(elementHashMap.get("pump"));
                Pipe newPipe = new Pipe();
                elementHashMap.get("pump").direct(elementHashMap.get("pipe1"), newPipe);
                INDENT++;
                elementHashMap.get("pump").setInput(newPipe);
                indentPrint("Pipe : getOutput()");
                INDENT++;
                indentPrint("Got output");
                INDENT--;
                newPipe.setOutput(elementHashMap.get("pump"));
                elementHashMap.get("pump").setInput(elementHashMap.get("pipe1"));
                elementHashMap.get("pipe1").setOutput(elementHashMap.get("pump"));
                INDENT -= 2;
            } else if(playerPosition.equals("2")) {
                playerHashMap.get("plumber").placePump();
                INDENT++;
                indentPrint("Cannot place pipe on position");
                INDENT--;
            } else {
                System.out.println("Invalid input");
            }
        } else if(playerType.equals("1")) {
            playerHashMap.get("nomad").placePump();
        } else {
            System.out.println("Invalid input");
        }
        INDENT--;
        scanner.nextLine();
    }
    //TODO Matyikaa DONE
    public static void destroyPipe(){
        init();
        System.out.println("Simulating destroy pipe:");

        Nomad nomad = (Nomad) playerHashMap.get("nomad");

        nomad.breakElement(nomad.getPosition());

        System.out.println("\nPress enter to continue");
        scanner.nextLine();
    }

    //DONE - Buzas
    public static void waterFlows(){
        init();
        System.out.println("Simulating water flow:");
        System.out.println("Where do you want the water to flow from?");
        System.out.println("""
                0. Source
                1. Pipe
                2. Pump
                3. flow TO cistern from pipe""");
        int waterFlow = scanner.nextInt();
        if (waterFlow == 0){
            Source source = (Source) elementHashMap.get("source");
            Pipe pipe = (Pipe) elementHashMap.get("pipe1");
            System.out.print("for all: ");
            source.getConnections();
            INDENT++;
            pipe.recieveWater(source);
            INDENT--;

        } else if (waterFlow == 1){
            System.out.println("Should the pipe contain water?");
            System.out.println("""
                    0. No
                    1. Yes""");
            int pipeContainsWater = scanner.nextInt();
            if(pipeContainsWater == 0){
                INDENT++;
                Pipe pipe = (Pipe) elementHashMap.get("pipe2");
                pipe.setWaterState(false);
                System.out.println("Pipe is empty, cannot transfer water");
                INDENT--;
            } else if(pipeContainsWater == 1){
                INDENT++;
                Pipe pipe2 = (Pipe) elementHashMap.get("pipe2");
                Pump pump = (Pump) elementHashMap.get("pump");
                pipe2.setWaterState(true);
                System.out.println("Should the pipe be damaged?");
                System.out.println("""
                    0. No
                    1. Yes""");
                int pipeDamaged = scanner.nextInt();
                if(pipeDamaged == 1){
                    INDENT++;
                    pipe2.setDamaged(true);
                    System.out.println("Pipe is damaged, cannot transfer water");
                    INDENT--;
                } else if(pipeDamaged == 0){
                    INDENT++;
                    pipe2.recieveWater(pump);
                    INDENT--;
                } else {
                    System.out.println("Invalid input");
                }
                INDENT--;
            } else {
                System.out.println("Invalid input");
            }
        } else if (waterFlow == 2){
            System.out.println("Should the pump contain water?");
            System.out.println("""
                    0. No
                    1. Yes""");
            int pumpContainsWater = scanner.nextInt();
            if(pumpContainsWater == 0){
                INDENT++;
                Pump pump = (Pump) elementHashMap.get("pump");
                pump.setWaterState(false);
                System.out.println("Pump is empty, cannot transfer water");
                INDENT--;
            } else if(pumpContainsWater == 1){
                INDENT++;
                Pump pump = (Pump) elementHashMap.get("pump");
                Pipe pipe2 = (Pipe) elementHashMap.get("pipe2");
                pump.setWaterState(true);
                System.out.println("Should the pump be damaged?");
                System.out.println("""
                    0. No
                    1. Yes""");
                int pumpDamaged = scanner.nextInt();
                if(pumpDamaged == 1){
                    INDENT++;
                    pump.setDamaged(true);
                    System.out.println("Pump is damaged, cannot transfer water");
                    INDENT--;
                } else if(pumpDamaged == 0){
                    INDENT++;
                    pump.recieveWater(pipe2);
                    INDENT--;
                } else {
                    System.out.println("Invalid input");
                }
                INDENT--;
            } else {
                System.out.println("Invalid input");
            }
        } else if (waterFlow == 3){
            System.out.println("Should the pipe contain water?");
            System.out.println("""
                    0. No
                    1. Yes""");
            int pipeContainsWater = scanner.nextInt();
            if(pipeContainsWater == 0){
                INDENT++;
                Pipe pipe = (Pipe) elementHashMap.get("pipe2");
                pipe.setWaterState(false);
                System.out.println("Pipe is empty, cannot transfer water");
                INDENT--;
            } else if(pipeContainsWater == 1){
                INDENT++;
                Pipe pipe2 = (Pipe) elementHashMap.get("pipe2");
                Cistern cistern = (Cistern) elementHashMap.get("cistern");
                pipe2.setWaterState(true);
                System.out.println("Should the pipe be damaged?");
                System.out.println("""
                    0. No
                    1. Yes""");
                int pipeDamaged = scanner.nextInt();
                if(pipeDamaged == 1){
                    INDENT++;
                    pipe2.setDamaged(true);
                    System.out.println("Pipe is damaged, cannot transfer water");
                    INDENT--;
                } else if(pipeDamaged == 0){
                    INDENT++;
                    cistern.recieveWater(pipe2);
                    INDENT--;
                } else {
                    System.out.println("Invalid input");
                }
                INDENT--;
            } else {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("Invalid input");
        }
        scanner.nextLine();
        scanner.nextLine();

    }
    //TODO Matyikaa
    public static void pipeSpawns(){
        init();


    }
    //TODO Marki
    public static void disconnectPipe(){
        init();
        System.out.println("What kind of player do you want to connect a pipe with?");
        System.out.println("""
                                0. Plumber
                                1. Nomad""");
        String playerType = scanner.nextLine();

        String playerPosition = "0";
        String connectionTarget = "0";
        String inventoryStatus = "0";
        if(playerType.equals("0")) {
            System.out.println("Where is the player standing?");
            System.out.println("""
                    0. Cistern
                    1. Pipe
                    2. Pump
                    3. Source
                    """);
            playerPosition = scanner.nextLine();

            System.out.println("Does the player have a pipe in their inventory?");
            System.out.println("""
                    0. Yes
                    1. No
                    """);
            inventoryStatus = scanner.nextLine();
        }

        System.out.println("Creating conditions for placing pump");
        INDENT++;
        Inventory inventory = new Inventory();
        if(inventoryStatus.equals("0")) {
            inventory.addItem(elementHashMap.get("pipe3"));
        }
        INDENT--;
        System.out.println("Simulating connectPipe():");
        INDENT++;
        if(playerType.equals("0")) {
            if(playerPosition.equals("0")) {
                if(inventoryStatus.equals("0")){
                    playerHashMap.get("plumber").disconnectPipe(elementHashMap.get("pipe1"));
                    INDENT++;
                    indentPrint("Inventory is full");
                    INDENT--;
                } else if(inventoryStatus.equals("1")) {
                    elementHashMap.get("pipe1").isConnected(elementHashMap.get("cistern"));
                    INDENT++;
                    indentPrint("Element is connected");
                    INDENT--;
                    playerHashMap.get("plumber").disconnectPipe(elementHashMap.get("pipe1"));
                    elementHashMap.get("pipe1").removeConnection(elementHashMap.get("cistern"));
                    elementHashMap.get("cistern").removeConnection(elementHashMap.get("pipe1"));
                    inventory.addItem(elementHashMap.get("pipe1"));
                } else {
                    System.out.println("Invalid input");
                }
            } else if(playerPosition.equals("1")) {
                playerHashMap.get("plumber").disconnectPipe(elementHashMap.get("pipe1"));
                INDENT++;
                indentPrint("Cannot disconnect a pipe from a pipe");
                INDENT--;
            } else if(playerPosition.equals("2")) {
                if(inventoryStatus.equals("0")){
                    playerHashMap.get("plumber").disconnectPipe(elementHashMap.get("pipe1"));
                    INDENT++;
                    indentPrint("Inventory is full");
                    INDENT--;
                } else if(inventoryStatus.equals("1")) {
                    elementHashMap.get("pipe1").isConnected(elementHashMap.get("pump"));
                    INDENT++;
                    indentPrint("Element is connected");
                    INDENT--;
                    playerHashMap.get("plumber").disconnectPipe(elementHashMap.get("pipe1"));
                    elementHashMap.get("pipe1").removeConnection(elementHashMap.get("pump"));
                    elementHashMap.get("pump").removeConnection(elementHashMap.get("pipe1"));
                    inventory.addItem(elementHashMap.get("pipe1"));
                } else {
                    System.out.println("Invalid input");
                }
            } else if(playerPosition.equals("3")) {
                if(inventoryStatus.equals("0")){
                    playerHashMap.get("plumber").disconnectPipe(elementHashMap.get("pipe1"));
                    INDENT++;
                    indentPrint("Inventory is full");
                    INDENT--;
                } else if(inventoryStatus.equals("1")) {
                    elementHashMap.get("pipe1").isConnected(elementHashMap.get("source"));
                    INDENT++;
                    indentPrint("Element is connected");
                    INDENT--;
                    playerHashMap.get("plumber").disconnectPipe(elementHashMap.get("pipe1"));
                    elementHashMap.get("pipe1").removeConnection(elementHashMap.get("source"));
                    elementHashMap.get("source").removeConnection(elementHashMap.get("pipe1"));
                    inventory.addItem(elementHashMap.get("pipe1"));
                } else {
                    System.out.println("Invalid input");
                }
            } else {
                System.out.println("Invalid input");
            }
        } else if(playerType.equals("1")) {
            playerHashMap.get("nomad").disconnectPipe(elementHashMap.get("pipe1"));
        } else {
            System.out.println("Invalid input");
        }
        INDENT--;
        scanner.nextLine();
    }
    //DONE Marki
    public static void connectPipe(){
        init();
        System.out.println("What kind of player do you want to connect a pipe with?");
        System.out.println("""
                                0. Plumber
                                1. Nomad""");
        String playerType = scanner.nextLine();

        String playerPosition = "0";
        String connectionTarget = "0";
        if(playerType.equals("0")) {
            System.out.println("Where is the player standing?");
            System.out.println("""
                    0. Cistern
                    1. Pipe
                    2. Pump
                    3. Source
                    """);
            playerPosition = scanner.nextLine();
        }

        System.out.println("Creating conditions for placing pump");
        INDENT++;
        Inventory inventory = new Inventory();
        inventory.addItem(elementHashMap.get("pipe3"));
        INDENT--;
        System.out.println("Simulating connectPipe():");
        INDENT++;
        if(playerType.equals("0")) {
            if(playerPosition.equals("0")) {
                playerHashMap.get("plumber").connectPipe();
                INDENT++;
                elementHashMap.get("cistern").addConnection(elementHashMap.get("pipe3"));
                elementHashMap.get("pipe3").addConnection(elementHashMap.get("cistern"));
                inventory.removeItem(elementHashMap.get("pipe3"));
                INDENT--;
            } else if(playerPosition.equals("1")) {
                playerHashMap.get("plumber").connectPipe();
                INDENT++;
                indentPrint("Cannot connect a pipe to a pipe");
                INDENT--;
            } else if(playerPosition.equals("2")) {
                playerHashMap.get("plumber").connectPipe();
                INDENT++;
                elementHashMap.get("pump").addConnection(elementHashMap.get("pipe3"));
                elementHashMap.get("pipe3").addConnection(elementHashMap.get("pump"));
                inventory.removeItem(elementHashMap.get("pipe3"));
                INDENT--;
            } else if(playerPosition.equals("3")) {
                playerHashMap.get("plumber").connectPipe();
                INDENT++;
                elementHashMap.get("source").addConnection(elementHashMap.get("pipe3"));
                elementHashMap.get("pipe3").addConnection(elementHashMap.get("source"));
                inventory.removeItem(elementHashMap.get("pipe3"));
                INDENT--;
            } else {
                System.out.println("Invalid input");
            }
        } else if(playerType.equals("1")) {
            playerHashMap.get("nomad").connectPipe();
        } else {
            System.out.println("Invalid input");
        }
        INDENT--;
        scanner.nextLine();
    }
    //TODO Toti
    public static void endOfRound() {
        init();
        game.tick();
        scanner.nextLine();
    }

    public static void indentPrint(String string){
        for(int i = 0; i < INDENT; i++){
            System.out.print("\t");
        }
        System.out.println(string);
    }
}