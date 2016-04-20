import java.util.HashMap;
import java.util.Set;
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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    //HashMap para enlazar cadenas con objetos de tipo Room
    private HashMap<String, Room> exit;//-------------------------------------------------------------------------- 0112

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) {
        this.description = description;
        exit = new HashMap<>();//----------------------------------------------------------------------------- 0112

    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.--------------------------------------------------------------------- 0113
     */
    //-0113 para facilitar el que la clase Game pueda elegir la salida de una localización, tenemos que eliminar el mt setExits(R) 
    // porque con sus parámetros está limitando las posibilidades.
    //para ello almacenamos en el HashMap la cadena que se indique como parámetro enlazada con la habitación que es indique
    // como parámetro. En direction podemos especificar cualquier tipo de dirección,,, subir, bajar,noroeste etc. y siempre 
    //quedará almacenada en el HashMap exit
    public void setExit(String direction, Room neighbor){
        exit.put(direction, neighbor);
    }//-0113 esto supone tener que hacer cambios en la clase Game. Ahora podemos borrar el mt setExits(R)

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * toma como parámetro una cadena que represente una dirección y devuelva el objeto de la clase Room asociado a esa salida 
     * o null si no hay salida.
     */
    public Room getExit(String adress){//----------------------------------------------------------------- 0111
        
        return exit.get(adress);
       
    }

    /**
     * Return a description of the room's exits.   
     * For example: "Exits: north east west"
     * @ return A description of the available exits.
     */
    public String getExitString(){//----------------------------------------------------------------- 0111
 
        Set<String> namesOfDirections = exit.keySet();//---------------------------------------0113    0113
        String exitsDescription = "";
        for(String direction : namesOfDirections){
            exitsDescription += direction + " ";
        }
        return exitsDescription;
    }
    
    /**
     * Return a long description of this room, of the form:---------------------------------------- 0114
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
 	 */
 	public String getLongDescription(){
 	  return "Estamos " + description + ".\n" + getExitString();
 	}


}