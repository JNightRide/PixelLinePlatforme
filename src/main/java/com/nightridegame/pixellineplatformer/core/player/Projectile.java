package com.nightridegame.pixellineplatformer.core.player;

import com.jme3.app.state.AppStateManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import com.nightridegame.pixellineplatformer.PixelLinePlatformer;
import com.nightridegame.pixellineplatformer.core.enemy.AbstractEnemy;
import com.nightridegame.pixellineplatformer.core.utils.Utils;

import jMe3GL2.geometry.jMe3GL2Geometry;
import jMe3GL2.physics.Dyn4jAppState;
import jMe3GL2.physics.control.KinematicBody2D;
import jMe3GL2.physics.control.PhysicsBody2D;
import jMe3GL2.scene.shape.Sprite;
import jMe3GL2.util.Converter;
import jMe3GL2.util.Timer;
import jMe3GL2.util.TimerTask;
import jMe3GL2.util.jMe3GL2Utils;

import java.util.List;

import org.dyn4j.dynamics.TimeStep;
import org.dyn4j.dynamics.contact.ContactConstraint;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.PhysicsWorld;
import org.dyn4j.world.listener.StepListenerAdapter;

/**
 * Clase <code>Projectile</code> encargado de gestionar el movimiento y el
 * cuerpo físico de los proyectiles.
 * 
 * @author wil
 * @version 1.0-SNAPSHOT
 * 
 * @see 1.0.0
 */
@SuppressWarnings(value = {"unchecked"})
public class Projectile extends KinematicBody2D {

    public static final int IZQ = 0;
    public static final int DER = 1;
    
    private Dyn4jAppState<PhysicsBody2D> dyn4jAppState = null;
    private PhysicsBody2D otherBody;
    private Timer timer;
    
    private int direction = IZQ;
    private boolean onDead = false;
    private final double speed = 2.0;
    
    public Projectile(PixelLinePlatformer app) { 
        AppStateManager stateManager = app.getStateManager();
        this.dyn4jAppState = stateManager.getState(Dyn4jAppState.class);
    }
    
    @Override
    protected void _ready() {
        timer = new Timer(0.20f);
        timer.addTask(_queue_free_projectile);
        
        spatial.setLocalScale(0.5F, 0.25F, 1.0F);
        dyn4jAppState.getPhysicsSpace().getPhysicsWorld().addStepListener(_on_Step);
    }
    
    @Override
    protected void _physics_process(float delta) {
        if (onDead) {
            if (timer.isRun() && !timer.finished()) {
                queueFree(otherBody);
            }
        } else {        
            if (!timer.isRun() && !timer.finished()) {
                timer.start();
            }

            timer.update(delta,  0.60F);
            spatial.setLocalScale(spatial.getLocalScale().interpolateLocal(new Vector3f(1.0F, 1.0F, 0.0F), delta * 12));

            Sprite sprite = (Sprite) ((Geometry) spatial).getMesh();
            Vector2 position = getTransform().getTranslation();
            if (direction == IZQ) {
                position = position.subtract(new Vector2(speed * 10 * delta, 0));
                sprite.flipH(true);
            } else {
                position = position.add(new Vector2(speed * 10 * delta, 0));
                sprite.flipH(false);
            }

            getTransform().setTranslation(position);
        }
    }
    
    private final TimerTask _queue_free_projectile = () -> {
        queueFree(null);
    };
    
    private final StepListenerAdapter<PhysicsBody2D> _on_Step = new StepListenerAdapter<>() {
        @Override
        public void begin(TimeStep step, PhysicsWorld<PhysicsBody2D, ?> world) {
            List<ContactConstraint<PhysicsBody2D>> contacts = world.getContacts(Projectile.this);
		for (final ContactConstraint<PhysicsBody2D> cc : contacts) {
                    PhysicsBody2D otherBody = cc.getOtherBody(Projectile.this);
                    
                    if(isEnemy(otherBody)) {
                        Projectile.this.otherBody = otherBody;
                        Projectile.this.dead();
                    }
                }
        }        
    };
    
    private void queueFree(PhysicsBody2D other) {
        dyn4jAppState.getPhysicsSpace().removeBody(this);
        spatial.removeFromParent();
        timer.stop();
        
        if (other != null) {
            if (other instanceof AbstractEnemy) {
                ((AbstractEnemy) other).onDead();
            } else {
                dyn4jAppState.getPhysicsSpace().removeBody(other);
                Spatial child = (Spatial) other.getUserData();
                Node parent   = child.getParent();

                if (parent != null) {
                    parent.removeFromParent();
                } else {
                    child.removeFromParent();
                }
            }            
        }
        dyn4jAppState.getPhysicsSpace().getPhysicsWorld().removeStepListener(_on_Step);
    }
    
    private void dead() {
        this.onDead = true;
    }
        
    private boolean isEnemy(PhysicsBody2D body) {
        Object usrData = body.getUserData();
        if (usrData != null 
                && (usrData instanceof Spatial)) {
            if (Utils.ENEMY.equals(((Spatial) usrData).getUserData(Utils.USER_DATA))) {
                return true;
            }
        }
        return false;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public void setPosition(Vector2 vector2) {
        getTransform().setTranslation(vector2);
        spatial.setLocalTranslation(Converter.toVector3f(vector2));
    }
    
    public static Projectile getInstance(PixelLinePlatformer app) {
        Sprite sprite   = new Sprite(1.0F, 1.0F, 10, 6, 4, 4);
        
        Geometry geom = new Geometry("Player", sprite);
        Material mat  = jMe3GL2Utils.loadMaterial(app.getAssetManager(), "Textures/Spritesheets/spritesheet_pixel_plataformer.png");
        
        mat.setFloat("AlphaDiscardThreshold", 1.0F);
        mat.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        
        geom.setMaterial(mat);
        geom.setQueueBucket(RenderQueue.Bucket.Transparent);
        
        Projectile p = new Projectile(app);
        p.addFixture(jMe3GL2Geometry.createCapsule(1, 0.5));
        p.setBullet(true);
        p.setMass(MassType.INFINITE);
        
        geom.addControl(p);
        return p;
    }
}
