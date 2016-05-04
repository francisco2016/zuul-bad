import java.util.Stack;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Room roomActual;
    private Stack<Room> listaRoom;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        roomActual = null;
        listaRoom = new Stack<>();
    }

    /**
     * Metodo que nos permitira fijar una calle al jugador.
     */
    public void fijarRoom(Room room){
        if(roomActual != null){
            listaRoom.push(roomActual);
        }
        roomActual = room;
    }
    
     /**
     * Metodo que imprime la informacion de localizacion
     */
    public void printLocationInfo(){
        System.out.println(roomActual.getLongDescription());
        System.out.println();
    }
    
    /**
     * Metodo que volvera a la calle anterior.
     */
    public void goToLastRoom(){
        if(!listaRoom.empty()){
            roomActual = listaRoom.pop();
            printLocationInfo();
        }
        else{
            System.out.println("No se puede volver!");
        }
    }
    
     /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("A donde vamos?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = roomActual.getExit(direction);

        if (nextRoom == null) {
            System.out.println("No hay salida!");
        }
        else {
            listaRoom.push(roomActual);
            roomActual = nextRoom;
            printLocationInfo();
            System.out.println();
        }
    }
}