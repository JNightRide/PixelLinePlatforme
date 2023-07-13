package com.nightridegame.pixellineplatformer.core;

import jMe3GL2.scene.tile.Spritesheet;
import jMe3GL2.scene.tile.SpritesheetPhysics;
import jMe3GL2.scene.tile.Tilesheet;


/**
 * Clase <code>Spritesheets</code> encargado de implementar un <code>TilesHeet
 * </code> personalizado.
 * 
 * @author wil
 * @version 1.0-SNAPSHOT
 * 
 * @see 1.0.0
 */
public final 
class Spritesheets implements Tilesheet {

    /** Objeto predeterminado. */
    private static final Spritesheets SPRITESHEETS;
    
    /*
        Inicializa los objetos estatico.
    */
    static {
        SPRITESHEETS = new Spritesheets();
    }
    
    /**
     * Devuelve una instancia de <code>Spritesheets</code>.
     * @return objeto-tileheet.
     */
    public static Tilesheet getInstance() {
        return Spritesheets.SPRITESHEETS;
    }
    
    /*
        Gestores de 'Tile' para las escenas.
    */
    private final SpritesheetsModel spritesheetsModel;
    private final SpritesheetsSpace spritesheetsSpace;
    
    /**
     * Constructor predeterminado de la clase <code>Spritesheets</code>.
     */
    private Spritesheets() {
        this.spritesheetsModel = new SpritesheetsModel();
        this.spritesheetsSpace = new SpritesheetsSpace();
    }

    @Override public Spritesheet getSpritesheet() { return this.spritesheetsModel; }
    @Override public SpritesheetPhysics getSpritesheetPhysics() { return this.spritesheetsSpace; }
}
