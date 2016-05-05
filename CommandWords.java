/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "eat", "back", "take", "drop", "items"
        // A�adido comando take, drop , items -------------------------------------------------- 0120
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    /**
     * Print all valid commands to System.out --------------------------------------------    0116
     *   muestre en la terminal todos los comandos disponibles:
     */
    public void showAll(){
        //los comandos al estar almacenados en un [], hago un bucle for each para que recorra la colecci�n y los muestre por Plla
        for(String command : validCommands){
             System.out.print(command + " ");
        }
        System.out.println();
    }
   
    
}
