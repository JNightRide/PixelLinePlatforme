package com.nightridegame.pixellineplatformer.core;

import com.jme3.scene.Geometry;

import jMe3GL2.physics.PhysicsSpace;
import jMe3GL2.physics.control.PhysicsBody2D;
import jMe3GL2.scene.tile.SpritesheetPhysicsAdapter;

/**
 * Clase <code>WorldTileSpacel</code> encargado de implementar un <code>TileSpace
 * </code> personalizado.
 *
 * @author wil
 * @version 1.0-SNAPSHOT
 *
 * @see 1.0.0
 */
public class SpritesheetsSpace extends SpritesheetPhysicsAdapter {

    private PhysicsSpace<PhysicsBody2D> physicsSpace;

    @Override
    public void onAttachTile(Geometry geom) {
        if (isPhysicsSpace()) {
            PhysicsBody2D body2D = geom.getControl(PhysicsBody2D.class);
            if (body2D != null) {
                physicsSpace.addBody(body2D);
            }
        }
    }

    @Override
    public void onDetachTile(Geometry geom) {
        if (isPhysicsSpace()) {
            PhysicsBody2D body2D = geom.getControl(PhysicsBody2D.class);
            if (body2D != null) {
                physicsSpace.removeBody(body2D);
            }
        }
    }

    @Override
    public void setPhysicsSpace(PhysicsSpace<PhysicsBody2D> physicsSpace) {
        this.physicsSpace = physicsSpace;
    }

    private boolean isPhysicsSpace() {
        return physicsSpace != null;
    }
}
