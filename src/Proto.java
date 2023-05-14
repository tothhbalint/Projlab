import java.util.ArrayList;


/**
 * TODO : jobb fuggvenyek, foleg a modellel valo kommunikacion kene javitani
 * TODO : test modeban az argsra kell mukodni amugy loopban varjon uj inputot,
 * TODO : irja ki a palya allasat, nem gond ha vissza kell scrollolni es picit rakos
 * TODO : legyenek lekezelve a hibak, a modellben minel tobb helyen dobjon exceptiont(foleg abstract osztalyoknal a //NOTHING helyett)
 * TODO : write javadoc
 * TODO :cleanup
 */
public class Proto {
    static boolean verbose = false;

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
                    ArrayList<String> options = new ArrayList<>();
                    int x = ++i;
                    do {
                        options.add(args[x].replace("-", ""));
                        x++;
                        if (x == args.length) break;
                    } while (args[x].charAt(0) == '-');
                    Situation situation = new Situation(arg, options.toArray(new String[options.size()]));
                    situations.add(situation);
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

        drawMap();

        for (Situation situation : situations) {
            processSituation(situation);
        }
    }

    //TODO draw out the map in ascii for navigation or maybe not xd

    private static void drawMap() {
        if(!verbose) throw new RuntimeException("Cannot draw map in non-verbose mode");

       NetworkMap networkMap = gameHandle.getMap();

        for (NetworkElement networkElement : networkMap.getElements()) {
            networkElement.printMatrix();
        }
    }

    private static void processSituation(Situation situation) {
        String[] splitSituation = situation.name.split("(?<=\\D)(?=\\d)");
        switch (splitSituation[0] ) {
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
                flow(situation.options);
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
        print("step " + who + " " + where);

        String[] whoSplit = who.split("(?<=\\D)(?=\\d)");
        who = whoSplit[0];
        int whoId = Integer.parseInt(whoSplit[1]);

        String[] whereSplit = where.split("(?<=\\D)(?=\\d)");
        where = whereSplit[0];
        NetworkElement position = null;
        int whereId = Integer.parseInt(whereSplit[1]);

        Player player = null;
        try{
            if (who.equals("plumber")) {
                player = gameHandle.getPlumberTeam().getPlayer(whoId);
            } else if (who.equals("nomad")) {
                player = gameHandle.getNomadTeam().getPlayer(whoId);
            } else {
                throw new RuntimeException("Invalid team: " + who);
            }

            NetworkElement destination = player.getPosition().getConnections().get(whereId);

            player.move(destination);
        }
        catch (Exception e){
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

    private static void flow(String[] options) {
        String from = options[0];

        String[] fromSplit = from.split("(?<=\\D)(?=\\d)");
        from = fromSplit[0];
        int fromId = Integer.parseInt(fromSplit[1]);

        if(from.equals("source")) {
            gameHandle.getMap().getSources().get(fromId).tick();
            return;
        }
        print("flow " + from);
    }

    public static void print(String arg) {
        if (verbose) System.out.println(arg);
    }

    public static void printHelp() {
        System.out.println("Usage: java Proto [options] [situations] [situation options]");
        //TODO add list of situations and options
        //dont make test show up in help
        System.out.println("Options:");
        System.out.println("""
                -v, --verbose: verbose mode
                -h, --help: print this help
                """);
        System.out.println("Situations:");
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
