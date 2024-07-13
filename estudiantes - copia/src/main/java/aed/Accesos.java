package aed;
public class Accesos {
    DiccionarioDigital<Clase> diccionario;
    String nombreMateria;
/*
 * Invariante de reprecentacion: 
 *Usando nombreMateria en diccionario siempre se obtiene un objeto tipo "Clase". Osea, siempre nombreMateria ϵ diccionario.
 */

    public Accesos(DiccionarioDigital<Clase> diccionario, String nombreMateria) {
        this.diccionario = diccionario;//O(1)
        this.nombreMateria = nombreMateria;//O(1)
    }

    public String getNombreMateria() {
        return this.nombreMateria;//O(1)
    }

    public DiccionarioDigital<Clase> getDiccionario() {
        return this.diccionario;//O(1)
    }
}
