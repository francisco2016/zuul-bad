

/**
 * Write a description of class Items here.
 * Clase para poder añadir nuevo Items para el juego.
 * autor francisco
 */
public class Item
{
    private String descripcionItem;
    private int pesoItem;

    /**
     * Constructor for objects of class Items
     */
    public Item(String descripcionItem, int pesoItem )
    {
        this.descripcionItem = descripcionItem;
        this.pesoItem = pesoItem;
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
}