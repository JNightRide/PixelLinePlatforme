package com.nightridegame.pixellineplatformer.core.player;

/**
 * Un <code>BooleanKeyHandlerPlayer</code> se encarga de gestionar el estado
 * de una entrada por parte del jugador para el control de su personaje.
 * 
 * @author wil
 * @version 1.0-SNAPSHOT
 * 
 * @see 1.0.0
 */
public class BooleanKeyHandlerPlayer {
    
    /**
     * {@code true} Si el estado de la tecla está activo (presionado), de lo
     * contrario su estado será {@code false}.
     */
    private boolean active;
    
    /** {@code true} si se ha manejado el estado activo */
    private boolean hasBeenHandled;
    
    /** Clave para está tecla. */
    private final String key;

    /**
     * Constructor predeterminado de la clase <code>BooleanKeyHandlerPlayer</code>
     * @param key nombre-clave
     */
    public BooleanKeyHandlerPlayer(String key) {
        this.key = key;
    }
    
    /**
     * Método encargado de activar el estado de esta entrada.
     * @param state estado de entrada.
     */
    public void onAction(boolean state) {
        if (state) {
            // guarda el estado anterior
            boolean flag = this.active;
            
            // establecer el estado en activo
            this.active = true;
            
            // si el estado pasó de inactivo a activo
            // marca que necesita ser manejado
            if (!flag) {
                this.hasBeenHandled = false;
            }
        } else {
            this.active = false;
        }
    }
    
    /**
     * Devuelve el estado de la entrada, activo o inactivo.
     * @return estado-entrada.
     */
    public boolean isActive() {
        return active;
    }
    
    /**
     * Método encargado de verificar si el estado esta activa pero no manejada
     * @return estado actual.
     */
    public boolean isActiveButNotHandled() {
        if (this.hasBeenHandled) {
            return false;
        }
        return this.active;
    }
    
    /**
     * Método encargado de restablecer el valor de está entrada para poder
     * determinar si ha sido manejado o no.
     * @param hasBeenHandled estado.
     */
    public void setHasBeenHandled(boolean hasBeenHandled) {
        this.hasBeenHandled = hasBeenHandled;
    }

    /**
     * Devuelve el nombre clave de está entrada.
     * @return nombre clave.
     */
    public String getKey() {
        return key;
    }
}
