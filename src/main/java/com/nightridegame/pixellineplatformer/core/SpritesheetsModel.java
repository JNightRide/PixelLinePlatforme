package com.nightridegame.pixellineplatformer.core;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;

import com.nightridegame.pixellineplatformer.core.utils.ColorUtils;
import com.nightridegame.pixellineplatformer.core.utils.Utils;

import jMe3GL2.physics.collision.AbstractCollisionShape;
import jMe3GL2.physics.control.RigidBody2D;
import jMe3GL2.scene.shape.Sprite;
import jMe3GL2.scene.tile.Properties;
import jMe3GL2.scene.tile.SpritesheetAdapter;
import jMe3GL2.scene.tile.Tile;
import jMe3GL2.scene.tile.TileMap;
import jMe3GL2.util.Converter;
import jMe3GL2.util.jMe3GL2Utils;

import org.dyn4j.geometry.MassType;

/**
 * Clase <code>SpritesheetsModel</code> encargado de implementar un <code>TileModel
 * </code> personalizado.
 *
 * @author wil
 * @version 1.0-SNAPSHOT
 *
 * @see 1.0.0
 */
public class SpritesheetsModel extends SpritesheetAdapter {
    
    @Override
    @SuppressWarnings("empty-statement")
    public Geometry render(TileMap tileMap, Tile tile, AssetManager assetManager) {
        Properties property = tile.getProperties();
        ColorRGBA color = property.getProperty("Color", new ColorRGBA(1.0F, 1.0F, 1.0F, 1.0F));
        
        Sprite sprite;
        String texture;
        if (property.getProperty("UseSprites", false)) {
            sprite = new Sprite(property.getProperty("Width"), property.getProperty("Height"));            
            texture = property.getProperty("Texture", (String) null);
        } else {
            sprite = new Sprite(property.getProperty("Width"), property.getProperty("Height"), tileMap.getColumns(), tileMap.getRows(), tile.getColumn(), tile.getRow());;
            texture = tileMap.getProperties().getProperty("Texture", (String) null);
        }
        
        sprite.flipH(property.getProperty("flipH", false));
        sprite.flipV(property.getProperty("flipV", false));
        
        final Material mat = jMe3GL2Utils.loadMaterial(assetManager, texture);
        mat.setFloat("AlphaDiscardThreshold", property.getProperty("AlphaDiscardThreshold", 0.0F));
        mat.setColor("Color", color);
        mat.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        mat.setTransparent(true);
        
        if (property.getProperty("SupportsGlow", false)) {            
            mat.setColor("GlowColor", ColorUtils.brighter(color));
            mat.setTexture("GlowMap", jMe3GL2Utils.loadTexture(assetManager, property.getProperty("Texture", (String) null)));
        }
        
        final Geometry geom = new Geometry(tile.getId(), sprite);
        geom.setMaterial(mat);
        geom.setUserData(Utils.USER_DATA, property.getProperty(Utils.USER_DATA, Utils.WALL));
        geom.setQueueBucket(property.getProperty("RenderQueue.Bucket", RenderQueue.Bucket.Transparent));
        geom.setLocalScale(property.getProperty("Scale", new Vector3f(1.0F, 1.0F, 1.0F)));
        
        Vector3f translation = tile.getTranslation();
        if (property.getProperty("RigidBody2D", false)) {
            RigidBody2D rbd = new RigidBody2D();

            AbstractCollisionShape<?> collisionShape = property.getProperty("CollisionShape", null);
            if (collisionShape != null) {
                rbd.addCollisionShape(collisionShape);
            }

            rbd.rotate(property.getProperty("Rotate", 0.0F));
            rbd.setMass(property.getProperty("MassType", MassType.INFINITE));
            rbd.translate(Converter.toVector2(translation));            
            geom.addControl(rbd);
        } else {
            geom.setLocalRotation(new Quaternion().fromAngleAxis(property.getProperty("Rotate", 0.0F), new Vector3f(0.0F, 0.0F, 1.0F)));;
            geom.setLocalTranslation(translation);
        }        
        return geom;
    }
}
