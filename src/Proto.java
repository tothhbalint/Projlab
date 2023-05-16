import java.util.ArrayList;
import java.util.Scanner;


/**
 * TODO : jobb fuggvenyek, foleg a modellel valo kommunikacion kene javitani
 * TODO : test modeban az argsra kell mukodni amugy loopban varjon uj inputot,
 * TODO : irja ki a palya allasat, nem gond ha vissza kell scrollolni es picit rakos
 * TODO READY: legyenek lekezelve a hibak, a modellben minel tobb helyen dobjon exceptiont(foleg abstract osztalyoknal a //NOTHING helyett)
 * TODO : write javadoc
 * TODO : cleanup
 */
public class Proto {
    static boolean verbose = false;

    static int tab = 0;

    static boolean test = false;

    static private class Situation {
        String name;

        String[] options;

        public Situation(String name, String[] options) {
            this.name = name;
            this.options = options;
        }
    }

    static ArrayList<Situation> situations = new ArrayList<Situation>();

    public static void processInput(String[] args) {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if (arg.charAt(0) != '-') {
                    if (arg.equals("help")) {
                        printHelp();
                    } else if (arg.equals("test")) {
                        test = true;
                    } else {
                        ArrayList<String> options = new ArrayList<>();
                        int x = i;
                        do {
                            if (++x == args.length) break;
                            if(args[x].charAt(0) != '-') break;
                            options.add(args[x].replace("-", ""));
                        } while (args[x].charAt(0) == '-');
                        Situation situation = new Situation(arg, options.toArray(new String[options.size()]));
                        situations.add(situation);
                    }
                } else if (arg.equals("-v") || arg.equals("--verbose")) {
                    verbose = true;
                } else if (arg.equals("-h") || arg.equals("--help")) {
                    printHelp();
                } else if (arg.equals("-t") || arg.equals("--test")) {
                    test = true;
                }
            }
        }
        if (test) verbose = false;
    }

    static Game gameHandle = new Game();

    public static void main(String[] args) {
        processInput(args);

        gameHandle.startGame();

        boolean gameEnded = false;

        Scanner sc = new Scanner(System.in);

        while (!gameEnded) {
            if (!test) {
                drawCurrentState();
                args = sc.nextLine().split(" ");
                processInput(args);
            }

            for (Situation situation : situations) {
                processSituation(situation);
            }

            if(test){break;}

            situations.clear();

            sc.nextLine();
        }

    }

    //TODO draw out the map in ascii for navigation or maybe not xd

    private static void drawCurrentState() {
        if (!verbose) throw new RuntimeException("Cannot draw map in non-verbose mode");

        NetworkMap networkMap = gameHandle.getMap();

//        for (NetworkElement networkElement : networkMap.getElements()) {
//            networkElement.printMatrix();
//        }

        print("plumber team: ");
        for (int i = 0; i < gameHandle.getPlumberTeam().getNoPlayers(); i++) {
            Player player = gameHandle.getPlumberTeam().getPlayer(i);
            print("\t" + player.toString());
            tab++;
            System.out.print("\t");
            player.getPosition().printMatrix();
            tab--;
        }

        print("nomad team: ");
        for (int i = 0; i < gameHandle.getNomadTeam().getNoPlayers(); i++) {
            Player player = gameHandle.getNomadTeam().getPlayer(i);
            print("\t" + player.toString());
            tab++;
            System.out.print("\t");
            player.getPosition().printMatrix();
            tab--;
        }
    }

    private static void processSituation(Situation situation) {
        String[] splitSituation = situation.name.split("(?<=\\D)(?=\\d)");
        switch (splitSituation[0]) {
            case "step":
                step(situation.options);
                break;
            case "pickup":
                pickup(situation.options);
                break;
            case "place":
                place(situation.options);
                break;
            case "break":
                break_(situation.options);
                break;
            case "fix":
                fix(situation.options);
                break;
            case "oil":
                oil(situation.options);
                break;
            case "glue":
                glue(situation.options);
                break;
            case "flow":
                flow();
                break;
            default:
                print("Invalid command: " + situation.name);
                break;
        }
    }

    //overseeing required
    private static void step(String[] options) {
        String who = options[0];
        String where = options[1];
        print("step:" + who + " " + where);

        String[] whoSplit = who.split("(?<=\\D)(?=\\d)");
        who = whoSplit[0];
        int whoId = Integer.parseInt(whoSplit[1]);

        String[] whereSplit = where.split("(?<=\\D)(?=\\d)");
        int whereId = Integer.parseInt(whereSplit[1]);

        Player player = null;
        try {
            if (who.equals("plumber")) {
                player = gameHandle.getPlumberTeam().getPlayer(whoId);
            } else if (who.equals("nomad")) {
                player = gameHandle.getNomadTeam().getPlayer(whoId);
            } else {
                throw new RuntimeException("Invalid team: " + who);
            }

            NetworkElement destination = player.getPosition().getConnections().get(whereId);

            player.move(destination);
        } catch (Exception e) {
            print("Invalid parameters in: step");
        }
    }

    //TODO try to pickup the current position

    private static void pickup(String[] options) {
        String who = options[0];
        String what = options[1];
        print("pickup " + who + " " + what);


        String[] whoSplit = who.split("(?<=\\D)(?=\\d)");
        who = whoSplit[0];
        int whoId = Integer.parseInt(whoSplit[1]);

        Player player = null;

        if (who.equals("plumber")) {
            player = gameHandle.getPlumberTeam().getPlayer(whoId);
        } else if (who.equals("nomad")) {
            player = gameHandle.getNomadTeam().getPlayer(whoId);
        } else {
            throw new RuntimeException("Invalid team: " + who);
        }

        NetworkElement position = player.getPosition();

        ArrayList<NetworkElement> connections = position.getConnections();

        switch (what) {
            case "pump":
                player.takePump();
                break;
            case "pipe":
                player.takePipe(connections.get(0));
                break;
            default:
                throw new RuntimeException("Invalid pickup: " + what);
        }
    }

    //TODO try to place the current position
    private static void place(String[] options) {
        String who = options[0];
        String what = options[1];
        print("place " + who + " " + what);
    }

    private static void break_(String[] options) {
        if (options.length > 1) throw new RuntimeException("Too many arguments for break");

        String who = options[0];

        String[] whoSplit = who.split("(?<=\\D)(?=\\d)");
        who = whoSplit[0];
        int whoId = Integer.parseInt(whoSplit[1]);
        Player player = null;

        if (who.equals("plumber")) {
            player = gameHandle.getPlumberTeam().getPlayer(whoId);
        } else if (who.equals("nomad")) {
            player = gameHandle.getNomadTeam().getPlayer(whoId);
        } else {
            throw new RuntimeException("Invalid team: " + who);
        }

        Proto.log(player.getPosition().toString());

        player.breakPipe();
    }

    private static void fix(String[] options) {
        if (options.length > 1) throw new RuntimeException("Too many arguments for fix");

        String who = options[0];

        String[] whoSplit = who.split("(?<=\\D)(?=\\d)");
        who = whoSplit[0];
        int whoId = Integer.parseInt(whoSplit[1]);

        Player player = null;

        if (who.equals("plumber")) {
            player = gameHandle.getPlumberTeam().getPlayer(whoId);
        } else if (who.equals("nomad")) {
            throw new RuntimeException("Nomad cannot fix anything");
        } else {
            throw new RuntimeException("Invalid team: " + who);
        }

        Plumber plumber = (Plumber) player;

        plumber.repair();

    }

    private static void oil(String[] options) {
        String who = options[0];
        print("oil " + who);

        String[] whoSplit = who.split("(?<=\\D)(?=\\d)");
        who = whoSplit[0];
        int whoId = Integer.parseInt(whoSplit[1]);

        Player player = null;

        if (who.equals("plumber")) {
            player = gameHandle.getPlumberTeam().getPlayer(whoId);
        } else if (who.equals("nomad")) {
            player = gameHandle.getNomadTeam().getPlayer(whoId);
        } else {
            throw new RuntimeException("Invalid team: " + who);
        }

        player.getPosition().setSlippery();
    }

    private static void glue(String[] options) {
        String who = options[0];
        print("glue " + who);

        String[] whoSplit = who.split("(?<=\\D)(?=\\d)");
        who = whoSplit[0];
        int whoId = Integer.parseInt(whoSplit[1]);

        Player player = null;

        if (who.equals("plumber")) {
            player = gameHandle.getPlumberTeam().getPlayer(whoId);
        } else if (who.equals("nomad")) {
            player = gameHandle.getNomadTeam().getPlayer(whoId);
        } else {
            throw new RuntimeException("Invalid team: " + who);
        }

        player.getPosition().setSticky();
    }

    private static void flow() {
        gameHandle.tick();

        print("flow ");
    }

    public static void print(String arg) {
        if (verbose || test) {
            if (true) {
                for (int i = 0; i < tab; i++) {
                    System.out.print("\t");
                }
                System.out.println(arg);
            }
        }
    }

    public static void log(String arg) {
        if (true) print(arg);
//        else System.out.println(arg);
    }

    public static void printHelp() {
        System.out.println("Usage: java Proto [options] [situations] [situation options]");
        //TODO add list of situations and options
        //dont make test show up in help
        System.out.println("Options:");
        System.out.println("""
                -v, verbose: verbose mode
                -h, help: print this help
                """);
        System.out.println("Situations:");
        //TODO Fix this because it is not correct (ie. break has only one parameter)
        System.out.println("""
                step : Move $who to $where
                     -who -where
                pickup : Pick up $what with $who
                     -who -what
                place : Place $what with $who
                     -who -what
                break : Break $what with $who
                     -who -what
                fix : Fix $who-s position
                     -who
                place : Place $what with $who
                     -who -what
                oil : Make a pipe slippery with $who
                     -who
                glue : Make a pipe sticky with $who
                     -who
                flow : Simulate water flow from $from
                     -from
                 """);
    }


}
