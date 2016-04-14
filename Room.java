
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
   private String description;
   private Room northExit;
   private Room southExit;
   private Room eastExit;
   private Room westExit;
   private Room southeastExit;//--------------------------------------------------------------------------------------------------- 0110 

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southeast) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southeast != null)  //--------------------------------------------------------------------------------------------------- 0110 
           southeastExit = southeast;
    
    }  

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * toma como par�metro una cadena que represente una direcci�n y devuelva el objeto de la clase Room asociado a esa salida o null si no hay salida.
     */
    public Room getExit(String adress){//----------------------------------------------------------------- 0111
        Room room = null;
        if(adress.equals("north"))
        room = northExit;
        if(adress.equals("east"))
        room = eastExit;
        if(adress.equals("south"))
        room = southExit;
        if(adress.equals("west"))
        room =westExit;
        if(adress.equals("southeast"))
        room = southeastExit;
        return room;
    }
    
}
