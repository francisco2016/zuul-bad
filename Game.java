/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    // private Room currentRoom;//permite saber donde se encuentra el jugador.
    // private Room lastRoom; //--------------------------------------------------------- 0119
    // private Stack<Room> visitedRooms; // -----------------------------------------2º parte del 0119
    private Player player;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
         player = new Player(); 
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
        vesti.addItem(new Item(" los planos de la casa", 1));
        coci.addItem(new Item(" una espada japonesa", 2));
        traste.addItem(new Item(" una cuerda de escalar", 5));
        terraza.addItem(new Item(" focos de colores", 10));
        salon.addItem(new Item(" las gafas de infrarrojos", 1));
        biblio.addItem(new Item(" las claves secretas de los diamantes", 6));
        h1.addItem(new Item(" los maletines grises.", 13));
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
        else if (commandWord.equals("back")) { 
            player.goToLastRoom();
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

    //     /** 
    //      * Try to go in one direction. If there is an exit, enter
    //      * the new room, otherwise print an error message.
    //      */
    //     private void goRoom(Command command) 
    //     {
    //         if(!command.hasSecondWord()) {
    //             // if there is no second word, we don't know where to go...
    //             System.out.println("Go where?");
    //             return;
    //         }
    // 
    //         String direction = command.getSecondWord();
    // 
    //         // Try to leave current room.
    //         Room nextRoom = currentRoom.getExit(direction);//------------------------------------------------- 0111
    // 
    //         if (nextRoom == null) {
    //             System.out.println("There is no door!");
    //         }
    //         else {
    //             //lastRoom = currentRoom; //----------------------------------------------------------- 0119
    //             //utilizamos el mt push() de la claes Stack para en la pila de habitaciones "visitedRoom" para menter en ella 
    //             // el objeto curretnRoom  -----------------------------------------2º parte del 0119
    //             visitedRooms.push(currentRoom);// -----------------------------------------2º parte del 0119
    //             currentRoom = nextRoom;
    //             printLocationInfo();
    //         }
    //         System.out.println();
    // 
    //     }

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

    //     /**
    //      *  resuelve la repetición de código existente en los metodos printWelcome ygoRoom 
    //      */
    //     private void printLocationInfo(){
    //         System.out.println(currentRoom.getLongDescription());//--------------------------------------------------- 0114
    // 
    //     }

    //     /**
    //      * The player eat, para añadir un nuevo comando eat------------------------------------------------------------ 0116
    //      */
    //     private void eat(){
    //         System.out.println("You have eaten now and you are not hungry any more"); 
    //     }

    //     /**
    //      * mt que permite volver a la habitación anterior, para añadir un nuevo comando back -------------------------- 0119
    //      */
    //     private void back(){
    //         // currentRoom = lastRoom; //le decimos que la habitación en la que está es la última en la que estuvo.----0199  1º parte
    //         //para que pueda ir regresando más de una posición, utilizo el mt pop() para decirle que la habitación actual va a ser
    //         // el elemento que está en la posición de más arriba de visitedRooms, y que lo imprima.
    //         if( !visitedRooms.empty() ){
    //             currentRoom = visitedRooms.pop();     // -----------------------------------------2º parte del 0119
    //             printLocationInfo();//cada vez que me muevo invoco a este método.
    //         }
    //         else{
    //             System.out.println("Estás al principio del juego, no puedes ir más atrás.");
    //         }
    //     }
}

