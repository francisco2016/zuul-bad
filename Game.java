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
 * @author  Michael K�lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    private void createRooms()
    {
        Room vesti, coci, traste,terraza, salon, biblio, h1 ;

        // create the rooms
        vesti = new Room("este vest�bulo me dice que estamos dentro.");
        coci = new Room("  en la cocina, me tomo un vaso de agua.");
        traste = new Room("en este trastero hay cosas interesantes.");
        terraza = new Room("terraza con vistas al mar.");
        salon = new Room("en este sal�n ya se ve algo m�s de color.");
        biblio = new Room("en esta biblioteca est� lo que buscamos.");
        h1 = new Room(" h1 habitaci�n standar, nada nuevo");

        // initialise room exits
        
        vesti.setExit("north", salon);
        vesti.setExit("east", coci);
        
        coci.setExit("north" , traste);
        coci.setExit("west", vesti);
        
        traste.setExit("north", terraza);
        traste.setExit("south", coci);
        traste.setExit("west", h1);
        traste.setExit("southeast", salon);
        
        terraza.setExit("south", traste );
        
        salon.setExit("north", h1);
        salon.setExit("east", traste);
        salon.setExit("south", vesti);
        salon.setExit("west", biblio);
        
        biblio.setExit("east", salon);
        
        h1.setExit("east", traste);
        h1.setExit("south", salon);

        currentRoom = vesti; 
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
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        printLocationInfo();
 
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
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            printLocationInfo();
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
        System.out.println("around at the vest�bulo.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);//------------------------------------------------- 0111

   
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println("You are " + currentRoom.getDescription());
            System.out.print("Exits: ");
            printLocationInfo();
                        }
            System.out.println();
       
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

    /**
     *  resuelve la repetici�n de c�digo existente en los metodos printWelcome ygoRoom 
     */
    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());//--------------------------------------------------- 0114
         
    }

}