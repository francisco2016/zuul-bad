

/**
 * Write a description of class Items here.
 * Clase para poder añadir nuevo Items para el juego.
 * autor francisco
 */
public class Item
{
    private String descripcionItem;
    private int pesoItem;
    private boolean disponible;

    /**
     * Constructor for objects of class Items
     */
    public Item(String descripcionItem, int pesoItem, boolean disponible )
    {
        this.descripcionItem = descripcionItem;
        this.pesoItem = pesoItem;
        this.disponible = disponible; //------------------------------------------ 0120
    }

    /**
     * serie de métodos para retornar el valor de los atributos
     */
    public String getDescripcionItem(){
        return descripcionItem;
    }
    
    public int getPesoItem(){
      return pesoItem;
    }
    
    /**
     * mt para informar si el objeto está disponible.
     */
    public boolean getDisponible(){
        return disponible;
    }
    
}











