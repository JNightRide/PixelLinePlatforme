package com.nightridegame.pixellineplatformer.core.enemy;

import com.jme3.math.Vector2f;
import com.nightridegame.pixellineplatformer.PixelLinePlatformer;
import jMe3GL2.physics.control.RigidBody2D;
import jMe3GL2.util.Converter;

/**
 * Clase abastracta par el control de los enemigos.
 * <p>
 * Todo enemigo del juego extendera de esta clase.
 * </p>
 * 
 * @author wil
 */
public abstract class AbstractEnemy extends RigidBody2D {
    
    /** Aplicacion principañ. */
    protected PixelLinePlatformer app;

    /*
        Constructor de la clase.
    */
    public AbstractEnemy(PixelLinePlatformer app) {
        this.app = app;
    }

    /*
        Posicones en 'float'.
    */
    public Vector2f getCenter2f() {
        return Converter.toVector2f(getTransform().getTranslation());
    }
    
    /*
        Método cuando de activa la muerte de un ememigo.
    */
    public abstract void onDead();
    
    /*
        Métodos abstractos.
    */
    @Override protected abstract void _physics_process(float delta);
    @Override protected abstract void _ready();
}
