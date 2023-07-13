package com.nightridegame.pixellineplatformer.core.enemy;

import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector2f;
import com.jme3.scene.Node;
import com.nightridegame.pixellineplatformer.PixelLinePlatformer;
import com.nightridegame.pixellineplatformer.core.utils.ConcurrentList;
import jMe3GL2.physics.Dyn4jAppState;
import jMe3GL2.physics.control.PhysicsBody2D;
import jMe3GL2.util.Converter;
import jMe3GL2.util.Timer;
import jMe3GL2.util.TimerTask;
import java.util.Arrays;
import java.util.Collection;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

/**
 * Clase para enemigos volables.
 */
@SuppressWarnings(value ={"unchecked"})
public class Volables extends AbstractEnemy {
    
    protected final ConcurrentList<Vector2f> paths 
                    = new ConcurrentList<>();
    
    protected Vector2f currentPath;
    protected Vector2f velocity;
    
    private Dyn4jAppState<PhysicsBody2D> dyn4jAppState = null;
    private Timer timer;
    
    protected float maxVel;    
    protected boolean following;
    protected boolean flagAtRest;
    
    public Volables(PixelLinePlatformer app) {
        super(app);
        
        AppStateManager stateManager = app.getStateManager();
        this.dyn4jAppState = stateManager.getState(Dyn4jAppState.class);
    }
    
    @Override
    protected void _ready() {
        velocity  = new Vector2f();
        following = false;
        flagAtRest = true;
        
        timer = new Timer(0.20f);
        
        timer.addTask(_queue_free_projectile);
        paths.setTypeList(ConcurrentList.TypeList.Transient);
    }
    
    @Override
    protected void _physics_process(float delta) {        
        Vector2f pathFollowing;        
        if ( following ) {
            pathFollowing = getPathFollowing();
        } else {
            pathFollowing = new Vector2f(0.0F, 0.0F);
        }
        
        velocity = velocity.add(pathFollowing);
        if (velocity.length() > 0) {
            velocity = velocity.normalize().mult(delta * maxVel);
        }
        
        Vector2 position = getTransform().getTranslation();
        position = position.add(velocity.x, velocity.y);
        
        getTransform().setTranslation(position);
        if (flagAtRest) {
            getTransform().setRotation(0);
        }
        setAtRest(flagAtRest);
        timer.update(delta, 0.60F);
    }
    
    @Override
    public void onDead() {        
        setMass(MassType.INFINITE);
        getTransform().setRotation(Math.PI);
        
        var pos2D = getTransform().getTranslation();
        var pos  = Converter.toVector2f(pos2D);
        
        paths.clear();
        timer.start();
        
        addPoints(pos, new Vector2f(pos.x, pos.y + 1f), new Vector2f(pos.x, pos.y - 10));
        
        maxVel = 8.5F;
        flagAtRest = false;
    }
    
    private final TimerTask _queue_free_projectile = () -> {
        queueFree();
    };
    
    private void queueFree() {
        dyn4jAppState.getPhysicsSpace().removeBody(this);
        timer.stop();
        
        Node parent = spatial.getParent();
        if ( parent != null ) {
            parent.removeFromParent();
        } else {
            spatial.removeFromParent();
        }
    }

    protected Vector2f getPathFollowing() {
        currentPath = paths.get();
        
        float distanceToNode = currentPath.subtract(getCenter2f()).length();
        if (distanceToNode < 0.5F) {
            paths.next();
        }
        return seekForve(currentPath);
    }
    
    protected Vector2f seekForve(Vector2f target) {
        Vector2f desiredVelocity = target.subtract(getCenter2f());        
        desiredVelocity = desiredVelocity.normalize().mult(maxVel);
        return desiredVelocity;
    }
    
    public void addPoints(Vector2f... paths) {
        addPoints(Arrays.asList(paths));
    }
    
    public void addPoints(Collection<Vector2f> paths) {
        this.paths.addAll(paths);
    }
}
