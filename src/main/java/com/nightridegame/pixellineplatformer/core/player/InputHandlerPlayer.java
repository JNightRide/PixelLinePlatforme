package com.nightridegame.pixellineplatformer.core.player;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;

import jMe3GL2.util.input.AbstractInputHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase <code>InputHandlerPlayer</code> encargado de gestionar las entradas
 * que controla el modelo del jugador en escena.
 * 
 * @author wil
 * @version 1.0-SNAPSHOT
 * 
 * @since 1.0.0
 */
public final 
class InputHandlerPlayer extends AbstractInputHandler implements ActionListener {

    /*
        Nombre de la entradas a utilizar.
    */
    public static final String HANDLER_JUMP  = "HANDLER_JUMP";
    public static final String HANDLER_LEFT  = "HANDLER_LEFT";
    public static final String HANDLER_RIGHT = "HANDLER_RIGHT";
    public static final String HANDLER_DOWN  = "HANDLER_DOWN";
    
    public static final String HANDLER_SHOOT      = "HANDLER_SHOOT";
    
    /** Lista de entradas/estado de las teclas para el jugador. */
    private final List<BooleanKeyHandlerPlayer> handlerPlayers
                    = new ArrayList<>();

    /**
     * Constructor predeterminado.
     */
    public InputHandlerPlayer() {
    }
    
    @Override
    public void install() {
        InputManager inputManager = getInputManager();
        inputManager.addMapping(HANDLER_LEFT,  new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping(HANDLER_RIGHT, new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping(HANDLER_JUMP,  new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping(HANDLER_DOWN,  new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping(HANDLER_SHOOT, new KeyTrigger(KeyInput.KEY_SPACE));
        
        inputManager.addListener(this, HANDLER_JUMP, HANDLER_LEFT, HANDLER_RIGHT, HANDLER_SHOOT, HANDLER_DOWN);
        
        handlerPlayers.add(new BooleanKeyHandlerPlayer(HANDLER_DOWN));
        handlerPlayers.add(new BooleanKeyHandlerPlayer(HANDLER_JUMP));
        handlerPlayers.add(new BooleanKeyHandlerPlayer(HANDLER_LEFT));
        handlerPlayers.add(new BooleanKeyHandlerPlayer(HANDLER_RIGHT));        
        handlerPlayers.add(new BooleanKeyHandlerPlayer(HANDLER_SHOOT));
    }

    @Override
    public void uninstall() { }
    
    /**
     * Devuele un estado de entrada en donde se almacena el estado de ello.
     * @param key nombre clave de la entrada.
     * @return entrada.
     */
    public BooleanKeyHandlerPlayer getKeyHandlerPlayer(String key) {
        for (final BooleanKeyHandlerPlayer element : this.handlerPlayers) {
            if (element == null)
                continue;
            
            if (element.getKey().equals(key)) {
                return element;
            }
        }
        return null;
    }
    
    @Override
    public boolean isActive() {
        for (final BooleanKeyHandlerPlayer element : this.handlerPlayers) {
            if (element == null)
                continue;
            
            if (element.isActive()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        BooleanKeyHandlerPlayer handlerPlayer = this.getKeyHandlerPlayer(name);
        if ( handlerPlayer != null ) {
            if (isEnabled() && !isDependentBehaviorActive()) {
                handlerPlayer.onAction(isPressed);
            }
        }
    }
}
