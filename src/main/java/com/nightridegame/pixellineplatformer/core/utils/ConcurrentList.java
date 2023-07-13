package com.nightridegame.pixellineplatformer.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Un <code>ConcurrentList</code> es una clase que se encarga de gestionar una 
 * lista de objetos de manera lineal.
 * <p>
 * Cuenta con 2 métodos para el movimiento:
 * <code>next() & back()</code>
 * que se encargan se recorrer esta lista de inicio a final, para luego
 * repeter el siclo
 * </p>
 * 
 * @author wil
 * @version 1.0-SNAPSHOT
 * 
 * @param <E> el tipo de dato.
 * @see 1.0.0
 */
public class ConcurrentList<E extends Object> extends ArrayList<E> {
    
    /**
     * Tipo de lista.
     */
    public static enum TypeList {
        /**
         * Se recorre los elementos en forma circular.
         * <p>
         * Es decir, de principio a fin, para luego repetir el
         * ciclo.
         */
        Circular,
        
        /**
         * La lista de recorre de manera acendente, cuando llega a su
         * ultimo elemento de regersa de manera decendente.
         */
        Transient;
    }
    
    /**
     * Índice actual de la lista. Con etsa posición podemos recorrer la
     * lista de inicio a fin.
     */
    int index = 0;
    
    /** Bandera. */
    boolean des = false;
    
    /** Tilo de lista {@link TypeList}. */
    TypeList typeList = TypeList.Circular;

    /*
        Constructores.
    */
    public ConcurrentList(int initialCapacity) { super(initialCapacity); }    
    public ConcurrentList(Collection<? extends E> c) { super(c); }
    public ConcurrentList() { }

    /*
        Establece el tipo de la lista.
    */
    public void setTypeList(TypeList typeList) {
        this.typeList = Objects.requireNonNull(typeList);
    }
        
    /*
        Métodos propios de esta clase para complir con su función de avanzar
        y/o retroceder en la lista.
    */
    public E next() {        
        if (TypeList.Circular == typeList) {
            index++;
            if (index >= size()) {
                index = 0;
            }
        } else if (TypeList.Transient == typeList) {
            if (!des) {
                index++;                
                if (index >= size()) {
                    des = true;
                    index = size() - 1;
                }
            } else {
                index--;                
                if (index < 0) {
                    index = 0;
                    des = false;
                }
            }
        }
        return get(index);
    }
    public E back() {
        if (TypeList.Circular == typeList) {
            index--;
            if (index < 0) {
                index = size() - 1;
            }
        } else if (TypeList.Transient == typeList) {
            if (!des) {
                index--;
                if (index < size()) {
                    des = true;
                    index = 0;
                }
            } else {
                index++;
                if (index >= size()) {
                    index = size() - 1;
                    des = false;
                }
            }
        }
        return get(index);
    }
    
    /*
        Métodos que se encargan de devolver los objetos actual sin incrementar o
        decrementar la posición actual.
    */
    public E get() {
        if (index >= size()) {
            index = size() - 1;
        }
        if (index < 0) {
            index = 0;
        }
        return get(index);
    }
    public E getDef() {
        if (index < 0 || index >= size()) 
            return null;
            
        return get(index);
    }
}
