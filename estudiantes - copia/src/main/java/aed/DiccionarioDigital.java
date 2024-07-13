package aed;
import java.util.ArrayList;
/*
 * Invariante de reprecentacion: 
 * Para todo nodo, nodo ϵ DiccionarioDigital, implica que |nodo.hijos| = 256
 * Para todo (i:Z), 0 <= i < 256 implica que nodo.hijos[i] = null o nodo.hijos[i] = un nodo tipo Nodo que reprecenta el caracter de una clave.
 * Para todo (nodo:Nodo), nodo.valor != null, implica que, para todo (i:Z), 0 <= i < 256 implica que nodo.hijos[i] = null.
 * Para todo (nodo:Nodo), nodo.valor == null, implica que existe un (i:Z), 0 <= i < 256 tal que nodo.hijos[i] != null.
 * La raiz no tiene nunca ni valor asociado ni caracter.
 */
public class DiccionarioDigital<T> {
    private Nodo raiz;

    private class Nodo{
        T valor;
        char caracter;
        ArrayList<Nodo> hijos;

        public Nodo() {
            this.valor = null;
            hijos = new ArrayList<>(256); 
            for (int i = 0; i < 256; i++) {
                hijos.add(null);
            }
        }
    }
    public DiccionarioDigital(){
        this.raiz = new Nodo();
    }

    public void definir(String palabra, T valor) {
        Nodo actual = raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            Nodo siguiente = actual.hijos.get(caracter);
            if (siguiente == null) {
                siguiente = new Nodo();
                siguiente.caracter = caracter;
                actual.hijos.set(caracter, siguiente);
            }
            actual = siguiente;
        }
        actual.valor = valor;
    }

    public boolean buscar(String palabra) {
        Nodo actual = raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            Nodo siguiente = actual.hijos.get(letra);
            if (siguiente == null) {
                return false; 
            }
            actual = siguiente;
        }
        return actual.valor != null; 
    }

    public T obtener(String palabra) {
        Nodo actual = raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            actual = actual.hijos.get(letra);
        }
        return actual.valor; 
    }

    public void borrar(String palabra) {
        Nodo actual = raiz;
        ArrayList<Nodo> camino = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            camino.add(actual);
            indices.add(i); 
            actual = actual.hijos.get(c);
        }
        actual.valor = null; 
        for (int i = palabra.length() - 1; i >= 0; i--) {
            Nodo padre = camino.get(i);
            int indice = indices.get(i);
            padre.hijos.set(indice, null);
            actual = padre; 
        }
    }
    
    public String[] claves() {
        ArrayList<String> claves = new ArrayList<>();
        obtenerClaves(raiz, new StringBuilder(), claves);
        return claves.toArray(new String[0]);
    }

    private void obtenerClaves(Nodo nodo, StringBuilder actual, ArrayList<String> claves) {
        if (nodo.valor != null) {
            claves.add(actual.toString());
        }
        for (int i = 0; i < nodo.hijos.size(); i++) {
            Nodo hijo = nodo.hijos.get(i);
            if (hijo != null) {
                actual.append(hijo.caracter); 
                obtenerClaves(hijo, actual, claves);
                actual.deleteCharAt(actual.length() - 1);
            }
        }
    }
}
