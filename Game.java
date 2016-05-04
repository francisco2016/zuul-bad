/**
 *  Francisco
 */

public class Game 
{
    private Parser parser;
     
    private Player player;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
         player = new Player(5); 
        createRooms();
        parser = new Parser();
              
    }

    private void createRooms()
    {
        Room vesti, coci, traste,terraza, salon, biblio, h1 ;

        // create the rooms
        vesti = new Room("en vestíbulo de la casa de Donald¡¡¡.");
        coci = new Room("  en la cocina, registra la nevera Paul.");
        traste = new Room("en el trastero de los objetos perdidos.");
        terraza = new Room(" en la terraza con vistas al yate de Donald¡¡¡¡.");
        salon = new Room("en el salón que decoró la princesa Lity¡¡.");
        biblio = new Room("en la biblioteca que diseñó Susho.");
        h1 = new Room(" en la  h1 habitación standar, con su espejito mágico.");

        //------------------------------------------------------0118
        vesti.addItem(new Item("planos", 1));
        coci.addItem(new Item("espada", 2));
        traste.addItem(new Item("cuerda", 5));
        terraza.addItem(new Item("focos", 10));
        salon.addItem(new Item("gafas", 1));
        biblio.addItem(new Item("claves", 6));
        h1.addItem(new Item("maletines", 13));
        // initialise room exits

        vesti.setExit("north", salon);
        vesti.setExit("east", coci);

        coci.setExit(" north" , traste);
        coci.setExit("west", vesti);

        traste.setExit("north", terraza);
        traste.setExit("south", coci);
        traste.setExit("west", h1);
        traste.setExit("southeast", salon);

        terraza.setExit("podemos ir al south", traste );

        salon.setExit("north", h1);
        salon.setExit("east", traste);
        salon.setExit("south", vesti);
        salon.setExit("west", biblio);

        biblio.setExit("east", salon);

        h1.setExit("east", traste);
        h1.setExit("south", salon);

        player.fijarRoom(vesti);

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("you are in the house lobby xx ") ;
        System.out.print("Exits: ");
        player.printLocationInfo();//----------0120

        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            player.goRoom(command);
        }

        else if (commandWord.equals("look")) {//añadido para -------------------------------------------------------- 0115
            player.printLocationInfo();
        }
        else if (commandWord.equals("eat")) {//añadido para -----------------------------  0116
            System.out.println("You have eaten now and you are not hungry any more");
        }
        else if (commandWord.equals("back")) { //añadido para -----------------------------  0119
            player.goToLastRoom();
        }
        else if (commandWord.equals("take")) { //añadido para -----------------------------  0120
            player.takeItem(command.getSecondWord());
        }
        else if (commandWord.equals("drop")) { //añadido para -----------------------------  0120
            player.dropItem(command.getSecondWord());
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the vestíbulo.");
        System.out.println();
        System.out.println("Your command words are:");
        //System.out.println("   go quit help look"); -------------sustituido por....
        // parser.getCommands().showAll();  //-------------------------0116
        parser.showCommands();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

}

