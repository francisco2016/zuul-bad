import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
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
    //para añadir items a las habitaciones.//--------------------------------------------------------------------- 0117
    private ArrayList<Item> listaItems;//------------------------------------------------------------------------- 0118

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) {
        this.description = description;
        exit = new HashMap<>();//----------------------------------------------------------------------------- 0112
        listaItems = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor){
        exit.put(direction, neighbor);
    }

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
        String exitsDescription = "Salidas: ";
        for(String direction : namesOfDirections){
            exitsDescription += direction + " ";
        }
        return exitsDescription + "\n";
    } 

    /**
     * Return a long description of this room, of the form:---------------------------------------- 0114
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        //return "Estamos " + description + ".\n" + getExitString();
        String descripcion = "Estamos " + description;
        if(listaItems.size() !=0){
            descripcion += ".\n En esta habitación tenemos\n";
            for(Item item : listaItems){
                
                descripcion += item.getDescripcionItem()+ ", su  peso es de: " +item.getPesoItem()+ " kg.\n ";
            }
        }
        else{descripcion += "En esta habitación no tenemos ningún items.\n";}
        descripcion += getExitString();
        return descripcion;
    }

    /**
     * para poder añadir items en las habitaciones ------------------------------------------------ 0118 
     */
    public void addItem(Item item){
        listaItems.add(item);
    }

    /**
     * mt para buscar items en las habitaciones --------------------------------------------------- 0120
     */
    public Item buscarItem(String descripcion){
        int i = 0;
        boolean encontrado = false;
        Item item = null;
        while(i < listaItems.size() && !encontrado){
            if(listaItems.get(i).getDescripcionItem().equals(descripcion)){
                item = listaItems.get(i);
                encontrado = true;
            }
            i++;
        }
        return item;
    }

    /**
     * mt para eliminar los items de las habitaciones.--------------------------------------------------- 0120
     */
    public void eliminaItemHabitacion(Item item){
        int i = 0;
        boolean encontrado = false;
        while(i < listaItems.size() && !encontrado){
            if( listaItems.get(i).getDescripcionItem().equals(item.getDescripcionItem())){
                listaItems.remove(listaItems.get(i));
                encontrado = true;
            }
            i++;
        }
    }
}











