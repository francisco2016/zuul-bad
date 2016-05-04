import java.util.Stack;
import java.util.ArrayList;//---------------------- 0120
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
    private ArrayList<Item> listaItems;//------------------- 0120
    private float pesoMaximo;
    private float pesoActual;

    /**
     * Constructor for objects of class Player
     */
    public Player(float pesoMaximo)
    {
        roomActual = null;
        listaRoom = new Stack<>();
         listaItems = new ArrayList<>();//-------------------------------- 0120
        this.pesoMaximo = pesoMaximo;
        pesoActual = 0;
        
    }
    
    /**
     * mt. para que el jugador pueda tomar items de las habitaciones.-------------------------------- 0120
     */
    public void takeItem(String descripcion){
        Item item = roomActual.buscarItem(descripcion);
        if(item != null && pesoActual + item.getPesoItem() < pesoMaximo){
            listaItems.add(item);
            pesoActual += item.getPesoItem();
            roomActual.eliminaItemHabitacion(item);
        }
        else{
            System.out.println("Imposible añadir el item.");
        }
    }
    
    /**
     * mt para que el jugador pueda dejar items en las habitaciones.------------------------------------ 0120
     */
    public  void dropItem(String descripcion){
        int i = 0;
        boolean encontrado = false;
        while(i < listaItems.size() && !encontrado){
            if(listaItems.get(i).getDescripcionItem().equals(descripcion)){
                roomActual.addItem(listaItems.get(i));
                pesoActual -= listaItems.get(i).getPesoItem();
                listaItems.remove(listaItems.get(i));
                encontrado = true;
                System.out.println("Item dejado");
            }
            i++;
        }
        if(!encontrado){
            System.out.println("No hay items para dejar");
        }
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