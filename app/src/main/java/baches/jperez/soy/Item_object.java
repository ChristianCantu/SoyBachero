package baches.jperez.soy;

/**
 * Created by crimelabpc4 on 16/01/15.
 */
public class Item_object {

    private String titulo;
    private int icono;
    public Item_object(String title, int icon) {
        this.titulo = title;
        this.icono = icon;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getIcono() {
        return icono;
    }
    public void setIcono(int icono) {
        this.icono = icono;
    }
}


