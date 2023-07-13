package com.nightridegame.pixellineplatformer.core.player;

import com.epagagames.particles.Emitter;
import com.epagagames.particles.emittershapes.EmitterCone;
import com.epagagames.particles.influencers.ColorInfluencer;
import com.epagagames.particles.influencers.SizeInfluencer;
import com.epagagames.particles.particle.ParticleDataPointMesh;
import com.epagagames.particles.valuetypes.ColorValueType;
import com.epagagames.particles.valuetypes.Curve;
import com.epagagames.particles.valuetypes.Gradient;
import com.epagagames.particles.valuetypes.ValueType;

import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

import com.nightridegame.pixellineplatformer.PixelLinePlatformer;
import com.nightridegame.pixellineplatformer.core.state.PlayState;
import com.nightridegame.pixellineplatformer.core.utils.Utils;

import jMe3GL2.geometry.jMe3GL2Geometry;
import jMe3GL2.physics.Dyn4jAppState;
import jMe3GL2.physics.control.PhysicsBody2D;
import jMe3GL2.physics.control.RigidBody2D;
import jMe3GL2.scene.control.IndexAnimatedSprite;
import jMe3GL2.scene.shape.Sprite;
import jMe3GL2.util.input.InputHandlerAppState;
import jMe3GL2.util.jMe3GL2Utils;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

/**
 * Un objeto de la clase <code>Player</code> se encarga de gestionar al
 * personaje del jugador.
 * 
 * @author wil
 * @version 1.0-SNAPSHOT
 * 
 * @see 1.0.0
 */
@SuppressWarnings(value = {"unchecked"})
public final class Player extends RigidBody2D {
    
    /*
        Constantes encargado de nombrar las animaciones de esta clase.
    */
    public static final String ANIM_IDLE  = "Idle";
    public static final String ANIM_JUMP  = "Jump";
    public static final String ANIM_WALK  = "Walk";
    
    /*
        Entradas de datos que controla al jugador.
    */
    private final InputHandlerAppState handlerAppState;
    private InputHandlerPlayer handlerPlayer;
    
    /*
        Aplicación principal.
    */
    protected final PixelLinePlatformer app;
    private final PlayState state;
    
    
    /** Espacio físico. */
    private final Dyn4jAppState<PhysicsBody2D> dyn4jAppState;
    
    /** Velocidad con que se mueve el jugador. */
    private final double speed = 3;

    /*
        Variables que controlan al jugador.
    */    
    private Vector2 velocity;    
    private boolean doubleJump = true;
    private boolean previouslyFloored = false;
    
    /** Particual de polvo. */
    private Emitter dust;
    
    public Player(PixelLinePlatformer app) {
        this.app = app;
        
        AppStateManager stateManager = app.getStateManager();        
        this.handlerAppState = stateManager.getState(InputHandlerAppState.class);
        this.dyn4jAppState   = stateManager.getState(Dyn4jAppState.class);
        this.state           = stateManager.getState(PlayState.class);
    }

    @Override
    protected void _ready() {
        handlerPlayer = handlerAppState.getInputHandler(InputHandlerPlayer.class);
        velocity = new Vector2(0.0, 0.0);
    }
    
    @Override
    protected void _physics_process(float delta) {
        applyControls();
        applyAnimation();
        
        // aplicar movimiento
        if (velocity.getMagnitude() > 0) {
            velocity = velocity.getNormalized().multiply(speed);
        }
        
        Vector2 position = getTransform().getTranslation();
        position = position.add(velocity.multiply(delta));
        
        getTransform().setRotation(0);
        getTransform().setTranslation(position);
        setAtRest(false);
        
        // efectos
        spatial.setLocalScale(spatial.getLocalScale().interpolateLocal(new Vector3f(1.0F, 1.0F, 0.0F), delta * 8));
        dust.setLocalTranslation(spatial.getLocalTranslation().add(0.0F, -0.5F, 0.0F));
        
        if (state.isOnGround() && !previouslyFloored) {
            spatial.setLocalScale(1.25F, 0.75F, 1.1F);
        }
        previouslyFloored = state.isOnGround();
        
        
        if (!state.isOnGround() && (velocity.x != 0 || velocity.y != 0)) {
            dust.setParticlesPerEmission(2);
        } else {
            dust.setParticlesPerEmission(0);
        }
        
        if (spatial.getLocalTranslation().y < -9 && !state.isOnGround()) {
            reset(new Vector2(0, 1));
        }
    }
    
    private void applyControls() {        
        velocity = new Vector2(0.0, 0.0);
        Sprite sprite = (Sprite) ((Geometry) spatial).getMesh();
        if (handlerPlayer.getKeyHandlerPlayer(InputHandlerPlayer.HANDLER_LEFT).isActive()) {
            
            velocity.x -= 1;
            sprite.flipH(true);
        } else if (handlerPlayer.getKeyHandlerPlayer(InputHandlerPlayer.HANDLER_RIGHT).isActive()) {
            
            velocity.x += 1;
            sprite.flipH(false);
        }
        
        if (handlerPlayer.getKeyHandlerPlayer(InputHandlerPlayer.HANDLER_JUMP).isActiveButNotHandled()) {
            handlerPlayer.getKeyHandlerPlayer(InputHandlerPlayer.HANDLER_JUMP).setHasBeenHandled(true);
            
            if (state.isOnGround()) {
                
                jump(1.0F);
                doubleJump = true;
                
                spatial.getControl(IndexAnimatedSprite.class).playAnimation(ANIM_JUMP, 0.1f);
            } else if (doubleJump) {
                
                jump(1.0F);
                doubleJump = false;
                
                spatial.getControl(IndexAnimatedSprite.class).playAnimation(ANIM_JUMP, 0.1f);
            }
        }
        
        if (handlerPlayer.getKeyHandlerPlayer(InputHandlerPlayer.HANDLER_SHOOT).isActiveButNotHandled()) {
            handlerPlayer.getKeyHandlerPlayer(InputHandlerPlayer.HANDLER_SHOOT).setHasBeenHandled(true);
            shoot();
        }
    }
    
    private void jump(float multiplier) {
        applyImpulse(new Vector2(0, multiplier * 4.5));
        spatial.setLocalScale(0F, 1.0F, 1.0F);
    }
    
    private void applyAnimation() {
        if (state.isOnGround()) {
            if ( velocity.x != 0) {
                spatial.getControl(IndexAnimatedSprite.class).playAnimation(ANIM_WALK, 0.05F);
            } else {
                 spatial.getControl(IndexAnimatedSprite.class).playAnimation(ANIM_IDLE, 0.10f);
            }
        } else {
            spatial.getControl(IndexAnimatedSprite.class).playAnimation(ANIM_JUMP, 0.50f);
        }
    }
    
    private void shoot() {
        Projectile projectile = Projectile.getInstance(app);
        
        dyn4jAppState.getPhysicsSpace().addBody(projectile);
        spatial.getParent().attachChild((Spatial) projectile.getUserData());
        
        Vector2 position = getTransform().getTranslation().copy();
        if (!((Sprite)((Geometry) spatial).getMesh()).isFlipH()) {
            projectile.setDirection(Projectile.DER);
            position = position.add(new Vector2( 0.8, -0.125));
            translate(-0.1, 0.0);
        } else {
            projectile.setDirection(Projectile.IZQ);
            position = position.add(new Vector2(-0.8, -0.125));
            translate(0.1, 0.0);
        }
        
        projectile.setPosition(position);        
        spatial.setLocalScale(new Vector3f(0.75F, 1.25F, 1.0F));
    }

    public void reset(Vector2 initPos) {
        getTransform().setTranslation(initPos);
        
    }
    
    public void setDust(Emitter dust) {
        this.dust = dust;
    }
    
    /**
     * Método encargado de crear el personaje del jugador.
     * <p>
     * En esta parte se configuara el modelo 2D del jugador, es decir que 
     * es donde se personaliza.
     * </p>
     * 
     * @param app aplicación princiál(juego)
     * @return nodo-jugador.
     */
    public static Node getInstance(PixelLinePlatformer app) {
        AssetManager assetManager = app.getAssetManager();
        
        Node nodePlayer = new Node("NODE_PLAYER");        
        Sprite sprite   = new Sprite(1.0F, 1.0F, 10, 6, 0, 4);
        
        Geometry geom = new Geometry("Player", sprite);
        Material mat  = jMe3GL2Utils.loadMaterial(assetManager, "Textures/Spritesheets/spritesheet_pixel_plataformer.png");
        
        mat.setFloat("AlphaDiscardThreshold", 1.0F);
        mat.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        
        IndexAnimatedSprite ctrlAnim = new IndexAnimatedSprite();
        ctrlAnim.addAnimation(ANIM_WALK, new Integer[] {40, 41, 42});
        ctrlAnim.addAnimation(ANIM_JUMP, new Integer[] {41});
        ctrlAnim.addAnimation(ANIM_IDLE, new Integer[] {40});
        ctrlAnim.setSpeed(0.60F);
        
        geom.setMaterial(mat);
        geom.setQueueBucket(RenderQueue.Bucket.Transparent);
        geom.setUserData(Utils.USER_DATA, Utils.CHARACTER);
        
        Material matDust = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        matDust.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        Texture tex = assetManager.loadTexture("Textures/Sprites/Particle/PNG-Transparent/window_04.png");
        matDust.setTexture("Texture", tex);
        
        Emitter emitter = new Emitter("test", matDust, 100, 
                new ColorInfluencer(),
                new SizeInfluencer());
        
        emitter.getInfluencer(SizeInfluencer.class).setSizeOverTime(new ValueType(
            new Curve()
                .addControlPoint(null, new Vector2f(0, 1), new Vector2f(0.4f, 1.0f))
                .addControlPoint(new Vector2f(0.4f, 1.0f), new Vector2f(1.0f, 1), null)
        ));
        emitter.getInfluencer(ColorInfluencer.class).setColorOverTime(new ColorValueType(
            new Gradient()
                .addGradPoint(new ColorRGBA(1, 1, 1, 0.0f), 0.0f)
                .addGradPoint(new ColorRGBA(1, 1, 1, 0.1f), 0.1f)
                .addGradPoint(new ColorRGBA(1, 1, 1, 0.2f), 1.2f)
                .addGradPoint(new ColorRGBA(1, 1, 1, 0.3f), 1.3f)
                .addGradPoint(new ColorRGBA(1, 1, 1, 0.4f), 1.4f)
                .addGradPoint(new ColorRGBA(1, 1, 1, 0.5f), 1.5f)
                .addGradPoint(new ColorRGBA(1, 1, 1, 0.6f), 1.6f)
                .addGradPoint(new ColorRGBA(1, 1, 1, 0.7f), 1.7f)
                .addGradPoint(new ColorRGBA(1, 1, 1, 0.8f), 1.8f)
                .addGradPoint(new ColorRGBA(1, 1, 1, 0.9f), 1.0f)
                .addGradPoint(new ColorRGBA(1, 1, 1, 1.0f), 1.0f)
        ));

        emitter.setStartSize(new ValueType(0.25f));

        emitter.setLifeFixedDuration(0.5f);
        emitter.setStartSpeed(new ValueType(1.5f));
        emitter.setEmissionsPerSecond(20);
        emitter.setParticlesPerEmission(0);
        emitter.setEnabled(true);
        emitter.setShape(new EmitterCone());
        emitter.setUseRandomEmissionPoint(true);
        ((EmitterCone)emitter.getShape()).setRadius(0.005f);
        emitter.rotate(0, 0, FastMath.PI);
        emitter.setParticleMeshType(ParticleDataPointMesh.class, null);
        emitter.setParticlesFollowEmitter(false);
        emitter.getMaterial().setBoolean("PointSprite", true);
        emitter.setQueueBucket(RenderQueue.Bucket.Translucent);
        
        Player player = new Player(app);
        BodyFixture bf = new BodyFixture(jMe3GL2Geometry.createRectangle(0.8, 1));
        
        player.addFixture(bf);
        player.setMass(MassType.NORMAL);
        player.setDust(emitter);
        
        geom.addControl(player);
        geom.addControl(ctrlAnim);
                
        nodePlayer.attachChild(geom);
        nodePlayer.attachChild(emitter);
        return nodePlayer;
    }
}
