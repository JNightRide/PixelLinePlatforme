package com.nightridegame.pixellineplatformer.core.state;

import com.epagagames.particles.Emitter;
import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.nightridegame.pixellineplatformer.PixelLinePlatformer;
import com.nightridegame.pixellineplatformer.core.Mapa001;
import com.nightridegame.pixellineplatformer.core.Spritesheets;
import com.nightridegame.pixellineplatformer.core.player.BooleanKeyHandlerPlayer;
import com.nightridegame.pixellineplatformer.core.player.InputHandlerPlayer;
import com.nightridegame.pixellineplatformer.core.player.Player;
import com.nightridegame.pixellineplatformer.core.utils.Utils;
import jMe3GL2.physics.Dyn4jAppState;
import jMe3GL2.physics.control.PhysicsBody2D;
import jMe3GL2.renderer.Camera2DRenderer;
import jMe3GL2.scene.tile.TileMap;
import jMe3GL2.scene.tile.TileMapGroupNode;
import jMe3GL2.util.input.InputHandlerAppState;
import jMe3GL2.util.jMe3GL2Utils;
import java.util.List;
import org.dyn4j.dynamics.TimeStep;
import org.dyn4j.dynamics.contact.ContactConstraint;
import org.dyn4j.geometry.AABB;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.ContactCollisionData;
import org.dyn4j.world.PhysicsWorld;
import org.dyn4j.world.World;
import org.dyn4j.world.listener.ContactListenerAdapter;
import org.dyn4j.world.listener.StepListenerAdapter;

/**
 * Un objeto de la clase <code>PlayState</code> se encarga de manejar el mundo
 * o escenas del juego así como el jugador.
 * <p>
 * En esta clase se configura la escenas, las propiedades iniciales del
 * mundo.
 * </p>
 * 
 * @author wil
 * @version 1.0-SNAPSHOT
 * 
 * @see 1.0.0
 */
@SuppressWarnings(value = {"unchecked"})
public class PlayState extends BaseAppState {

    private TileMap mapPixel1;
    private TileMap mapBitPixel;
    private TileMapGroupNode<TileMap> mapGroupNode;
    
    private Node rootNode;
    private Node nodePlayer;
    
    private Camera2DRenderer camera2DRenderer;
    private Dyn4jAppState<PhysicsBody2D> dyn4jAppState;
    
    /*
        Entradas de datos que controla al jugador.
    */
    private InputHandlerAppState handlerAppState;
    private InputHandlerPlayer handlerPlayer;
    
    private Player character;
    private boolean onGround;
    
    @Override
    protected void initialize(Application app) {
        PixelLinePlatformer plp = (PixelLinePlatformer) app;
        AppStateManager stateManager = app.getStateManager();
        
        rootNode = plp.getRootNode();
        dyn4jAppState    = stateManager.getState(Dyn4jAppState.class);
        camera2DRenderer = stateManager.getState(Camera2DRenderer.class);
        handlerAppState = stateManager.getState(InputHandlerAppState.class);    
        
        handlerPlayer = handlerAppState.addInputHandler(new InputHandlerPlayer());        
        handlerPlayer.install(); 
        
        mapGroupNode = new TileMapGroupNode<>();
        mapGroupNode.move(0, 0, -1);
        
        mapPixel1 = jMe3GL2Utils.newInstanceTileMap("Map01", "Textures/Spritesheets/spritesheet_voxel.png", 10, 9, app.getAssetManager());
        mapBitPixel = jMe3GL2Utils.newInstanceTileMap("MapBitPixel", "Textures/Spritesheets/monochrome_tilemap_transparent_packed.png", 20, 20, app.getAssetManager());
        
        mapPixel1.setTilesHeet(Spritesheets.getInstance());
        mapPixel1.setPhysicsSpace(dyn4jAppState.getPhysicsSpace());
        mapGroupNode.attachChild(mapPixel1);
        
        mapBitPixel.setTilesHeet(Spritesheets.getInstance());
        mapBitPixel.setPhysicsSpace(dyn4jAppState.getPhysicsSpace());
        mapGroupNode.attachChild(mapBitPixel);
        
        nodePlayer = Player.getInstance(plp);
        nodePlayer.move(0, 0, 0.1F);
        
        Geometry geom = (Geometry) nodePlayer.getChild("Player");
        character = geom.getControl(Player.class);
        character.translate(0, 2);

        camera2DRenderer.setTarget(geom);
        dyn4jAppState.getPhysicsSpace().addBody(character);
        
        rootNode.attachChild(mapGroupNode);
        rootNode.attachChild(nodePlayer);
        
        setupWorld();
        setupMap();
    }
    
    private void setupWorld() {
        World<PhysicsBody2D> world = dyn4jAppState.getPhysicsSpace().getPhysicsWorld();
        world.addStepListener(new StepListenerAdapter<>() {
            @Override
            public void begin(TimeStep step, PhysicsWorld<PhysicsBody2D, ?> world) {
                
                boolean isGround = false;
                List<ContactConstraint<PhysicsBody2D>> contacts = world.getContacts(character);
		for (final ContactConstraint<PhysicsBody2D> cc : contacts) {
                    if (is(cc.getOtherBody(character), Utils.FLOOR, Utils.ONE_WAY_PLATFORM) && cc.isEnabled()) {
			isGround = true;
                    }
                    
                    if(is(cc.getOtherBody(character), Utils.ENEMY)) {
                        gameOver();
                    }
                }
                
		if (!isGround) {
                    onGround = false;
                }
            }
        });
        world.addContactListener(new ContactListenerAdapter<>() {
            @Override
            public void collision(ContactCollisionData<PhysicsBody2D> collision) {
                ContactConstraint<PhysicsBody2D> cc = collision.getContactConstraint();
                
		disableContactForOneWay(cc);
                
		trackIsOnGround(cc);
            }
        });
    }
    
    private void gameOver() {
        character.reset(new Vector2(0, 1));
    }
    
    public boolean isOnGround() {
        return onGround;
    }
    
    private boolean is(PhysicsBody2D body, String... types) {
        Spatial spatial = (Spatial) body.getUserData();
        for (String type : types) {
            String userData = spatial.getUserData(Utils.USER_DATA);
            
            if ((userData != null) 
                    && (userData.equals(type))) {
                return true;
            }
        }
        return false;
    }
    
    private void trackIsOnGround(ContactConstraint<PhysicsBody2D> contactConstraint) {
	PhysicsBody2D b1 = contactConstraint.getBody1();
        PhysicsBody2D b2 = contactConstraint.getBody2();
		
	if (is(b1, Utils.CHARACTER) && 
                is(b2, Utils.FLOOR, Utils.ONE_WAY_PLATFORM) && 
                contactConstraint.isEnabled()) {
            onGround = true;
	} else if (is(b1, Utils.FLOOR, Utils.ONE_WAY_PLATFORM) && 
                    is(b2, Utils.CHARACTER) && 
                    contactConstraint.isEnabled()) {
            onGround = true;
	}
    }
    

    
    private boolean allowOneWayUp(PhysicsBody2D character, PhysicsBody2D platform) {
        AABB wAABB = character.createAABB();
        AABB pAABB = platform.createAABB();
                
        return wAABB.getMinY() < pAABB.getMinY();
    }
    
    private void disableContactForOneWay(ContactConstraint<PhysicsBody2D> contactConstraint) {
        PhysicsBody2D b1 = contactConstraint.getBody1();
        PhysicsBody2D b2 = contactConstraint.getBody2();
		
        BooleanKeyHandlerPlayer down = handlerPlayer.getKeyHandlerPlayer(InputHandlerPlayer.HANDLER_DOWN);
                
        if (is(b1, Utils.CHARACTER) && is(b2, Utils.ONE_WAY_PLATFORM)) {            
            if (allowOneWayUp(b1, b2) || down.isActive()) {
                //down.setHasBeenHandled(true);
        	contactConstraint.setEnabled(false);
            }
        } else if (is(b1, Utils.ONE_WAY_PLATFORM) && is(b2, Utils.CHARACTER)) {
            if (allowOneWayUp(b2, b1) || down.isActive()) {
                //down.setHasBeenHandled(true);
        	contactConstraint.setEnabled(false);
            }
        }
    }
    
    @Override
    protected void cleanup(Application app) { }

    @Override
    protected void onEnable() { }

    @Override
    protected void onDisable() { }
    
    private void setupMap() {
        Mapa001.floor(mapBitPixel);
        Mapa001.follage(mapPixel1);
        Mapa001.objects(mapBitPixel);
        Mapa001.enemyes((PixelLinePlatformer) getApplication(), rootNode);
        
        Emitter emitter = Utils.getInstance(getApplication().getAssetManager(), "Textures/Sprites/Particle/PNG-Transparent/circle_01.png");
        emitter.setLocalTranslation(11f, -8f, -6);        
        rootNode.attachChild(emitter);
        
        emitter = Utils.getInstance(getApplication().getAssetManager(), "Textures/Sprites/Particle/PNG-Transparent/circle_01.png");
        emitter.setLocalTranslation(0f, -7f, -6);        
        rootNode.attachChild(emitter);
    }
}
