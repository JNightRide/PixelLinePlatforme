package com.nightridegame.pixellineplatformer.core;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.nightridegame.pixellineplatformer.PixelLinePlatformer;
import com.nightridegame.pixellineplatformer.core.enemy.Ghost;
import com.nightridegame.pixellineplatformer.core.enemy.Mob;
import com.nightridegame.pixellineplatformer.core.utils.ColorUtils;
import com.nightridegame.pixellineplatformer.core.utils.ConcurrentList;
import com.nightridegame.pixellineplatformer.core.utils.Utils;
import jMe3GL2.geometry.jMe3GL2Geometry;
import jMe3GL2.physics.collision.CollisionShape;
import jMe3GL2.physics.collision.RectangleCollisionShape;
import jMe3GL2.scene.tile.Properties;
import jMe3GL2.scene.tile.Tile;
import jMe3GL2.scene.tile.TileMap;
import jMe3GL2.util.jMe3GL2Utils;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

/**
 * Mapa 001.
 */
public final 
class Mapa001 {
    
    /** Constructor privado.*/
    private Mapa001() {}
    
    public static void enemyes(PixelLinePlatformer app, Node rootNode) {
        Node node1 = Mob.getInstance(app, new Vector2f(15, -6.5F), new Vector2f(14, -2), new Vector2f(15, 1.5F));
        Geometry geom = (Geometry) node1.getChild("Mob");
        
        app.getDyn4jAppState().getPhysicsSpace().addBody(geom.getControl(Mob.class));
        rootNode.attachChild(node1);
        
        node1 = Mob.getInstance(app, new Vector2f(16, -5.5F), new Vector2f(16, 1.5F));
        geom = (Geometry) node1.getChild("Mob");
        
        app.getDyn4jAppState().getPhysicsSpace().addBody(geom.getControl(Mob.class));
        rootNode.attachChild(node1);
        
        node1 = Ghost.getInstance(app, ConcurrentList.TypeList.Circular, new Vector2f(6, 0),  new Vector2f(8, -1),
                                                                                new Vector2f(9, -3), new Vector2f(7, -3),  
                                                                                new Vector2f(3, 2),  new Vector2f(3, 1),
                                                                                new Vector2f(5, -1), new Vector2f(6, 0));
        geom = (Geometry) node1.getChild("Ghost");
        
        app.getDyn4jAppState().getPhysicsSpace().addBody(geom.getControl(Ghost.class));
        rootNode.attachChild(node1);
        
        node1 = Ghost.getInstance(app, ConcurrentList.TypeList.Transient, new Vector2f(8, 2), new Vector2f(5, 1),
                                                                                 new Vector2f(7, -2), new Vector2f(10, -3), 
                                                                                 new Vector2f(8, -2), new Vector2f(7, 0));
        geom = (Geometry) node1.getChild("Ghost");
        
        app.getDyn4jAppState().getPhysicsSpace().addBody(geom.getControl(Ghost.class));
        rootNode.attachChild(node1);
    }
    
    public static void follage(TileMap map) {
        Tile tile = jMe3GL2Utils.newIstanceTile("fllg-001", 10, 13, 1, 1, 3, -1F, 0, false);
        Properties properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.234F, 0.535F, 0.830F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0054.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-002", 10, 13, 1, 1, 2, -1F, 0, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.234F, 0.535F, 0.830F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0071.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-003", 10, 13, 1, 1, 4, -4F, 0, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.332F,  0.556F, 0.705F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0072.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-005", 10, 13, 0.5F, 0.5F, 4.5F, -4.25F, 0, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.332F,  0.556F, 0.705F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0072.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-004", 10, 13, 1, 1,7, -4F, 0, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.332F,  0.556F, 0.705F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0077.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-006", 10, 13, 0.5f, 0.5f,6.75F, -4.25F, 0, false);
        properties = tile.getProperties();
        
        properties.setProperty("flipH", true);
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.332F,  0.635F, 0.705F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0072.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-007", 10, 13, 0.5f, 0.5f,7.25F, -4.25F, 0, false);
        properties = tile.getProperties();
        
        properties.setProperty("flipH", true);
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.332F,  0.635F, 0.705F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0072.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-008", 10, 13, 1f, 1f,-1F, 2F, -1f, false);
        properties = tile.getProperties();
        
        properties.setProperty("flipV", true);
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        
        /** Azul oscuro. */
        properties.setProperty("Color", new ColorRGBA(0.431F, 0.443F, 0.651F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0068.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-009", 10, 13, 0.5f, 0.5f,-1.25F, 2.25F, 0, false);
        properties = tile.getProperties();
        
        properties.setProperty("flipV", true);
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.332F,  0.635F, 0.705F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0072.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-010", 10, 13, 0.5f, 0.5f,-0.75F, 2.25F, 0, false);
        properties = tile.getProperties();
        
        properties.setProperty("flipV", true);
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        
        /** Azul claro. */
        properties.setProperty("Color", new ColorRGBA(0.332F,  0.635F, 0.705F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0072.png");
        map.addTile(tile);
        
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-011", 10, 13, 1f, 1f,4F, 3F, -1f, false);
        properties = tile.getProperties();
        
        properties.setProperty("flipV", true);
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.431F, 0.443F, 0.651F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0068.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-012", 10, 13, 1f, 1f,4.85F, 3F, -1f, false);
        properties = tile.getProperties();
        
        properties.setProperty("flipV", true);
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.431F, 0.443F, 0.651F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0054.png");
        map.addTile(tile);
        
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-013", 10, 13, 1f, 1f,7.90F, -5F, 0f, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        /** Azul mitico. */
        properties.setProperty("Color", new ColorRGBA(0.431F, 0.568F, 0.651F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0059.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-014", 10, 13, 1f, 1f,8.75F, -5F, 0f, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.431F, 0.568F, 0.651F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0058.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-015", 10, 13, 1.5f, 1.5f,11F, -2.75F, 0f, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.234F, 0.535F, 0.830F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0071.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-016", 10, 13, 0.5f, 0.5f,11.75F, -3.25F, 0f, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.332F,  0.635F, 0.705F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0056.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-017", 10, 13, 1f, 1f,11F, -3F, 0f, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Color", new ColorRGBA(0.332F,  0.635F, 0.705F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0062.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-018", 10, 13, 1f, 1f, -3F, 0F, -1f, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        
        /** Azul oscuro. */
        properties.setProperty("Color", new ColorRGBA(0.431F, 0.443F, 0.651F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0073.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-019", 10, 13, 1f, 1f, -5F, -3F, -1f, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        
        /** Azul oscuro. */
        properties.setProperty("Color", new ColorRGBA(0.332F,  0.635F, 0.705F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0061.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-020", 10, 13, 1f, 1f,24F, -3F, 0f, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        /** Azul mitico. */
        properties.setProperty("Color", new ColorRGBA(0.431F, 0.568F, 0.651F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0072.png");
        map.addTile(tile);
        
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-021", 10, 13, 1f, 1f,28F, 5F, 0f, false);
        properties = tile.getProperties();
        
        properties.setProperty("flipV", true);
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        
        /** Azul oscuro. */
        properties.setProperty("Color", new ColorRGBA(0.431F, 0.443F, 0.651F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0068.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-022", 10, 13, 1f, 1f,27.5F, 5F, 1f, false);
        properties = tile.getProperties();
        
        properties.setProperty("flipV", true);
        properties.setProperty("UseSprites", true);
        properties.setProperty("SupportsGlow", true);
        
        /** Azul oscuro. */
        properties.setProperty("Color", new ColorRGBA(0.431F, 0.443F, 0.651F, 1.0F));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0068.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-023", 10, 13, 1f, 1f,24F, -7F, -2f, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("Scale", new Vector3f(5, 5, 1));
        properties.setProperty("Color", ColorRGBA.DarkGray.clone().setAlpha(0.5f));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0074.png");
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fllg-024", 10, 13, 1f, 1f,29F, -7F, -2f, false);
        properties = tile.getProperties();
        
        properties.setProperty("UseSprites", true);
        properties.setProperty("Scale", new Vector3f(5, 5, 1));
        properties.setProperty("Color", ColorRGBA.DarkGray.clone().setAlpha(0.5f));
        properties.setProperty("Texture", "Textures/Sprites/Shaded/sprite_0074.png");
        map.addTile(tile);
        // 0.8366529,  0.63668066, 0.8402336,  1.0  # ROSA_PALIDA
        // 0.09432763, 0.60825866, 0.86753756, 1.0  # AZUL_PALIDO
        // 0.9471277,  0.5949694,  0.30850244, 1.0  # NARANJA
    }
    
    public static void floor(TileMap map) {
        // Pisos
        ColorRGBA color = new ColorRGBA(0.250F, 0.250F, 0.250F, 1.0F);
        Tile tile = jMe3GL2Utils.newIstanceTile("floor-001", 10, 13, 1, 1, 0, -2F, 0, true);
        Properties properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-002", 11, 13, 1, 1, 1, -2F, 0, true);
        properties = tile.getProperties();
         properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-003", 11, 13, 1, 1, 2, -2F, 0, true);
        properties = tile.getProperties();
         properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-004", 12, 13, 1, 1, 3, -2F, 0, true);
        properties = tile.getProperties();
         properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-005", 12, 14, 1, 1, 3, -3F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-006", 12, 14, 1, 1, 3, -4F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-007", 10, 13, 1, 1, 4, -5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-008", 11, 13, 1, 1, 5, -5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-009", 11, 13, 1, 1, 6, -5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-010", 12, 13, 1, 1, 7, -5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        //tile = jMe3GL2Utils.newIstanceTile("floor-011", 12, 14, 1, 1, 7, -6F, 0, true);
        //properties = tile.getProperties();
        //properties.setProperty("Color", color.clone());
        //map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-012", 12, 14, 1, 1, 7, -7F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-013", 10, 14, 1, 1, 4, -6F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-014", 16, 11, 1, 1, 0, 3F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-015", 17, 15, 1, 1, 1, 3F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-016", 16, 15, 1, 1, -1, 3F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-017", 15, 15, 1, 1, -2, 3F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-018", 15, 10, 1, 1, -2, 4F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-019", 15, 14, 1, 1, -2, 5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-020", 15, 14, 1, 1, -2, 6F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-021", 15, 10, 1, 1, -2, 7F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
                
        tile = jMe3GL2Utils.newIstanceTile("floor-022", 15, 10, 1, 1, -2, 8F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-023", 15, 10, 1, 1, -2, 9F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-024", 17, 10, 1, 1, 1, 4F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
                
        tile = jMe3GL2Utils.newIstanceTile("floor-025", 17, 10, 1, 1, 1, 5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-026", 19, 10, 1, 1, 1, 6F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-027", 11, 14, 1, 1, 0, 4F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-028", 16, 11, 1, 1, 2, 6F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-029", 19, 11, 1, 1, 3, 6F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-030", 15, 10, 1, 1, 3, 5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-031", 15, 15, 1, 1, 3, 4F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-032", 16, 15, 1, 1, 4, 4F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-033", 16, 11, 1, 1, 5, 4F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-034", 17, 15, 1, 1, 6, 4F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-035", 17, 10, 1, 1, 6, 5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-036", 17, 10, 1, 1, 6, 6F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-037", 17, 10, 1, 1, 6, 7F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-038", 17, 10, 1, 1, 6, 8F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-039", 17, 10, 1, 1, 6, 9F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-041", 12, 14, 1, 1, 10, -7F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-042", 12, 14, 1, 1, 10, -8F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-043", 12, 14, 1, 1, 10, -9F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-044", 6, 7, 1, 1, 10, -6F, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-045", 5, 7, 1, 1, 9, -6F, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-046", 5, 7, 1, 1, 8, -6F, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-047", 4, 7, 1, 1, 7, -6F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-048", 15, 14, 1, 1, 9, 4F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        //tile = jMe3GL2Utils.newIstanceTile("floor-049", 15, 14, 1, 1, 9, 5F, 0, true);
        //properties = tile.getProperties();
        //properties.setProperty("Color", color.clone());
        //map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-050", 15, 14, 1, 1, 9, 6F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-051", 15, 14, 1, 1, 9, 7F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-052", 15, 14, 1, 1, 9, 8F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-053", 15, 14, 1, 1, 9, 9F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-054", 15, 14, 1, 1, 9, 3, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-055", 15, 15, 1, 1, 9, 2, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-056", 16, 15, 1, 1, 10, 2, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-057", 16, 15, 1, 1, 11, 2, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-058", 16, 15, 1, 1, 12, 2, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-059", 16, 15, 1, 1, 13, 2, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-060", 16, 11, 1, 1, 14, 2, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-061", 16, 11, 1, 1, 15, 2, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-062", 17, 11, 1, 1, 16, 2, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-063", 17, 10, 1, 1, 16, 3, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-064", 17, 10, 1, 1, 16, 4, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-065", 17, 10, 1, 1, 16, 5, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-066", 17, 10, 1, 1, 16, 6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-067", 17, 10, 1, 1, 16, 7, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-068", 17, 10, 1, 1, 16, 8, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-069", 17, 10, 1, 1, 16, 9, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-070", 10, 9, 1, 1, 14, -7, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-071", 10, 10, 1, 1, 14, -8, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-072", 10, 14, 1, 1, 14, -9, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-073", 11, 9, 1, 1, 15, -7, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-074", 10, 9, 1, 1, 16, -6, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-075", 10, 14, 1, 1, 16, -7, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-076", 10, 14, 1, 1, 16, -8, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-077", 10, 14, 1, 1, 16, -9, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-078", 11, 9, 1, 1, 17, -6, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-079", 12, 9, 1, 1, 18, -6, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-080", 12, 10, 1, 1, 18, -7, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-081", 12, 14, 1, 1, 18, -8, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-082", 12, 14, 1, 1, 18, -9, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-083", 10, 14, 1, 1, 0, -3, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-084", 10, 14, 1, 1, 0, -4, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-085", 10, 14, 1, 1, 0, -6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-086", 10, 14, 1, 1, 0, -7, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-087", 10, 14, 1, 1, 0, -8, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-088", 10, 14, 1, 1, 0, -9, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-089", 12, 13, 1, 1, -2, -1, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-090", 10, 13, 1, 1, -3, -1, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-091", 10, 14, 1, 1, -3, -2, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());        
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-092", 10, 14, 1, 1, -3, -3, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-093", 10, 14, 1, 1, -3, -4, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-094", 10, 14, 1, 1, -3, -5, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-095", 10, 14, 1, 1, -3, -6, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-096", 10, 14, 1, 1, -3, -7, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-097", 10, 14, 1, 1, -3, -8, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-098", 10, 14, 1, 1, -3, -9, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-099", 12, 14, 1, 1, -2, -9, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-100", 12, 14, 1, 1, -2, -8, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-101", 12, 14, 1, 1, -2, -7, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-102", 12, 14, 1, 1, -2, -6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-103", 12, 14, 1, 1, -2, -5, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-104", 12, 14, 1, 1, -2, -4, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-105", 12, 14, 1, 1, -2, -3, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-106", 12, 14, 1, 1, -2, -2, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-107", 11, 13, 1, 1, -4, -4, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-108", 11, 13, 1, 1, -5, -4, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-109", 11, 13, 1, 1, -6, -4, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-110", 11, 13, 1, 1, -7, -4, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-111", 11, 13, 1, 1, -8, -4, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-112", 13, 13, 1, 1, 22, -5, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-113", 13, 13, 1, 1, 23, -2, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-114", 13, 13, 1, 1, 24, -4, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-115", 13, 14, 1, 1, 22, -6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-116", 13, 14, 1, 1, 22, -7, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-117", 13, 14, 1, 1, 22, -8, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-118", 13, 14, 1, 1, 22, -9, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-119", 13, 14, 1, 1, 23, -3, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-120", 13, 14, 1, 1, 23, -4, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-121", 13, 15, 1, 1, 24, -5, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-122", 13, 15, 1, 1, 23, -6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-123", 15, 13, 1, 1, 28, 0, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-124", 16, 13, 1, 1, 29, 0, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-125", 16, 13, 1, 1, 30, 0, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-126", 16, 13, 1, 1, 31, 0, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-127", 15, 14, 1, 1, 28, -1, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-128", 15, 14, 1, 1, 28, -2, 0, true);
        properties = tile.getProperties();
        properties.setProperty("flipV", true);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-129", 15, 14, 1, 1, 28, -3, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-130", 11, 13, 1, 1, 28, -4, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-132", 12, 13, 1, 1, 29, -4, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-133", 10, 13, 1, 1, 27, -4, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-134", 12, 14, 1, 1, 29, -5, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-134-S", 10, 14, 1, 1, 27, -5, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-135", 10, 14, 1, 1, 27, -6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-136", 10, 14, 1, 1, 27, -7, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-137", 10, 14, 1, 1, 27, -8, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-138", 10, 14, 1, 1, 27, -9, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-139", 16, 11, 1, 1, 27, 6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-140", 16, 11, 1, 1, 28, 6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-141", 16, 11, 1, 1, 29, 6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-142", 16, 11, 1, 1, 30, 6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-143", 16, 11, 1, 1, 31, 6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-144", 15, 11, 1, 1, 26, 6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-145", 15, 10, 1, 1, 26, 7, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-146", 15, 10, 1, 1, 26, 8, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-147", 15, 10, 1, 1, 26, 9, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-148", 15, 11, 1, 1, 22, 3, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-149", 16, 11, 1, 1, 23, 3, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-150", 18, 11, 1, 1, 24, 2, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-151", 17, 10, 1, 1, 24, 3, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-152", 17, 10, 1, 1, 24, 4, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-153", 17, 10, 1, 1, 24, 5, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-154", 17, 10, 1, 1, 24, 6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-155", 17, 10, 1, 1, 24, 7, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-156", 17, 10, 1, 1, 24, 8, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-157", 17, 10, 1, 1, 24, 9, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-158", 15, 10, 1, 1, 22, 4, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-159", 15, 10, 1, 1, 22, 5, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-160", 15, 10, 1, 1, 22, 6, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-161", 15, 10, 1, 1, 22, 7, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-162", 15, 10, 1, 1, 22, 8, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("floor-163", 15, 10, 1, 1, 22, 9, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map.addTile(tile);
        
    }
    
    public static void objects(TileMap map1) {
        ColorRGBA color = new ColorRGBA(0.250F, 0.250F, 0.250F, 1.0F);
        Tile tile = jMe3GL2Utils.newIstanceTile("objects-001", 5, 10, 1, 1, 1, -1, 0, false);
        Properties properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-002", 5, 9, 1, 1, 1, 0, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-003", 11, 14, 1, 1, 5, -6F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-004", 2, 9, 1, 1, 7, -8F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-005", 0, 9, 1, 1, 6, -8F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-006", 2, 9, 1, 1, 5, -8F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-007", 1, 9, 1, 1,4, -8F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-008", 0, 9, 1, 1,3, -8F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-009", 0, 10, 1, 1,3, -9F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-010", 2, 10, 1, 1,5, -9F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-011", 0, 10, 1, 1,6, -9F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-012", 2, 10, 1, 1,7, -9F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        
        tile = jMe3GL2Utils.newIstanceTile("objects-013", 9, 16, 1, 1,7, 4F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty("CollisionShape", new RectangleCollisionShape(0.5, 1));
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        map1.addTile(tile);
        
        
        tile = jMe3GL2Utils.newIstanceTile("objects-014", 9, 16, 1, 1,8, 4F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty("CollisionShape", new RectangleCollisionShape(0.5, 1));
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-015", 3, 0, 1, 1,10, 1F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-016", 3, 2, 1, 1,10, 0F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-017", 3, 0, 1, 1,12, 1F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-018", 3, 1, 1, 1,12, 0F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-019", 3, 1, 1, 1,12, -1F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-020", 3, 1, 1, 1,12, -2F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        
        tile = jMe3GL2Utils.newIstanceTile("objects-021", 5, 0, 1, 1,12, -2F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-022", 5, 1, 1, 1,12, -3F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-023", 4, 1, 1, 1,11, -3F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-024", 6, 1, 1, 1,13, -3F, -1, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-025", 4, 2, 1, 1,11, -4F, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.ONE_WAY_PLATFORM);
        properties.setProperty("CollisionShape", new CollisionShape<>(jMe3GL2Geometry.createPolygon(new Vector2(-0.5, 0), 
                                                                                                                   new Vector2(0.5, 0), 
                                                                                                                   new Vector2(0.5, 0.5), 
                                                                                                                   new Vector2(-0.5, 0.5))));
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-026", 5, 2, 1, 1,12, -4F, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.ONE_WAY_PLATFORM);
        properties.setProperty("CollisionShape", new CollisionShape<>(jMe3GL2Geometry.createPolygon(new Vector2(-0.5, 0), 
                                                                                                                   new Vector2(0.5, 0), 
                                                                                                                   new Vector2(0.5, 0.5), 
                                                                                                                   new Vector2(-0.5, 0.5))));
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        
        tile = jMe3GL2Utils.newIstanceTile("objects-027", 6, 2, 1, 1,13, -4F, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.ONE_WAY_PLATFORM);
        properties.setProperty("CollisionShape", new CollisionShape<>(jMe3GL2Geometry.createPolygon(new Vector2(-0.5, 0), 
                                                                                                                   new Vector2(0.5, 0), 
                                                                                                                   new Vector2(0.5, 0.5), 
                                                                                                                   new Vector2(-0.5, 0.5))));
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-028", 11, 3, 1, 1,13, -3F, 0f, true);
        properties = tile.getProperties();
        properties.setProperty("MassType", MassType.NORMAL);
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("Color", ColorRGBA.Gray.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-029", 6, 0, 1, 1,13, -1F, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-030", 3, 1, 1, 1,13, 0F, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-031", 3, 0, 1, 1,13, 1F, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-032", 7, 13, 1, 1,8, 5, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-033", 9, 12, 1, 1,8, 6, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-034", 7, 12, 1, 1,8, 7, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-035", 7, 12, 1, 1,8, 8, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-036", 7, 12, 1, 1,8, 9, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-037", 8, 11, 1, 1,9, 5, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-038", 8, 11, 1, 1,10, 5, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-039", 8, 11, 1, 1,11, 5, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-040", 8, 11, 1, 1,12, 5, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-041", 9, 13, 1, 1,13, 5, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-042", 7, 12, 1, 1,13, 6, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-043", 7, 12, 1, 1,13, 7, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-044", 7, 12, 1, 1,13, 8, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-045", 7, 12, 1, 1,13, 9, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-046", 16, 14, 1, 1,14, 4, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-047", 11, 14, 1, 1,11, 3, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-048", 16, 14, 1, 1,5, 6, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-049", 11, 14, 1, 1,2, -5, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-050", 9, 16, 1, 1,-1, -3F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty(Utils.USER_DATA, Utils.FLOOR);
        properties.setProperty("CollisionShape", new RectangleCollisionShape(0.5, 1));
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-051", 8, 11, 1, 1,-1, -5, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-052", 8, 11, 1, 1,0, -5, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-053", 9, 11, 1, 1,1, -5, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-054", 7, 12, 1, 1,1, -6, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-055", 7, 12, 1, 1,1, -7, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-056", 7, 12, 1, 1,1, -8, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-057", 7, 12, 1, 1,1, -9, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-058", 5, 10, 1, 1,-6, -3, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-059", 4, 10, 1, 1,-6, -2, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-060", 6, 9, 1, 1,-6, -1, 0f, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-061", 9, 16, 1, 1,-3,5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty("CollisionShape", new RectangleCollisionShape(0.5, 1));
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-062", 9, 16, 1, 1,-4,5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty("CollisionShape", new RectangleCollisionShape(0.5, 1));
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-063", 9, 16, 1, 1,-6,5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty("CollisionShape", new RectangleCollisionShape(0.5, 1));
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-064", 9, 16, 1, 1,-7,5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty("CollisionShape", new RectangleCollisionShape(0.5, 1));
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-065", 9, 16, 1, 1,-8,5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty("CollisionShape", new RectangleCollisionShape(0.5, 1));
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        map1.addTile(tile);
        
        
        tile = jMe3GL2Utils.newIstanceTile("objects-066", 3, 1, 1, 1,-5, 5F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("CollisionShape", new RectangleCollisionShape(1.0, 0.5));
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-068", 3, 1, 1, 1,-5, 6F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-069", 3, 1, 1, 1,-5, 7F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-070", 3, 1, 1, 1,-5, 8F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-071", 3, 1, 1, 1,-5, 9F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-067", 5, 0, 1, 1,-5, 4F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-072", 5, 1, 1, 1,-5, 3F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-073", 4, 1, 1, 1,-6, 3F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-074", 6, 1, 1, 1,-4, 3F, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", ColorUtils.darker(color));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-075", 4, 2, 1, 1,-6, 2F, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.ONE_WAY_PLATFORM);
        properties.setProperty("CollisionShape", new CollisionShape<>(jMe3GL2Geometry.createPolygon(new Vector2(-0.5, 0), 
                                                                                                                   new Vector2(0.5, 0), 
                                                                                                                   new Vector2(0.5, 0.5), 
                                                                                                                   new Vector2(-0.5, 0.5))));
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-076", 5, 2, 1, 1,-5, 2F, 0, true);
        properties = tile.getProperties();
        properties.setProperty(Utils.USER_DATA, Utils.ONE_WAY_PLATFORM);
        properties.setProperty("CollisionShape", new CollisionShape<>(jMe3GL2Geometry.createPolygon(new Vector2(-0.5, 0), 
                                                                                                                   new Vector2(0.5, 0), 
                                                                                                                   new Vector2(0.5, 0.5), 
                                                                                                                   new Vector2(-0.5, 0.5))));
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        
        tile = jMe3GL2Utils.newIstanceTile("objects-077", 6, 2, 1, 1,-4, 2F, 0, true);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty(Utils.USER_DATA, Utils.ONE_WAY_PLATFORM);
        properties.setProperty("CollisionShape", new CollisionShape<>(jMe3GL2Geometry.createPolygon(new Vector2(-0.5, 0), 
                                                                                                                   new Vector2(0.5, 0), 
                                                                                                                   new Vector2(0.5, 0.5), 
                                                                                                                   new Vector2(-0.5, 0.5))));
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-078", 6, 4, 10, 1,-3.5f, 3F, 3, false);
        properties = tile.getProperties();
        properties.setProperty("UseSprites", true);        
        properties.setProperty("Color", new ColorRGBA(0, 0, 0, 1));
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        properties.setProperty("Scale", new Vector3f(10, 10, 0));
        properties.setProperty("Texture", "Textures/Sprites/Other/0001.png");
        map1.addTile(tile);

        
        tile = jMe3GL2Utils.newIstanceTile("objects-079", 6, 1, 4, 10,-10.4F, 1F, 3, true);
        properties = tile.getProperties();
        properties.setProperty("UseSprites", true);        
        properties.setProperty("Color", new ColorRGBA(0, 0, 0, 1));
        properties.setProperty("RenderQueue.Bucket", RenderQueue.Bucket.Translucent);
        properties.setProperty("Texture", "Textures/Sprites/Other/00010.png");
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-080", 6, 4, 1, 1, -3.5f, 4F, 1, false);
        properties = tile.getProperties();
        properties.setProperty("flipV", true);
        properties.setProperty("UseSprites", true);       
        properties.setProperty("Color", new ColorRGBA(1, 1, 1, 1));
        properties.setProperty("Scale", new Vector3f(10, 10, 0));
        properties.setProperty("Texture", "Textures/Sprites/Other/0001.png");
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-081", 6, 4, 1, 1, 6.5f, 4F, 1, false);
        properties = tile.getProperties();
        properties.setProperty("flipV", true);
        properties.setProperty("UseSprites", true);       
        properties.setProperty("Color", new ColorRGBA(1, 1, 1, 1));
        properties.setProperty("Scale", new Vector3f(10, 10, 0));
        properties.setProperty("Texture", "Textures/Sprites/Other/0001.png");
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-082", 6, 4, 1, 1, 16.5f, 4F, 1, false);
        properties = tile.getProperties();
        properties.setProperty("flipV", true);
        properties.setProperty("UseSprites", true);       
        properties.setProperty("Color", new ColorRGBA(1, 1, 1, 1));
        properties.setProperty("Scale", new Vector3f(10, 10, 0));
        properties.setProperty("Texture", "Textures/Sprites/Other/0001.png");
        map1.addTile(tile);
        
        //13.3, 17.3, 40.4
        
        tile = jMe3GL2Utils.newIstanceTile("objects-083", 6, 1, 1, 1,-3.5f, -5F, 1, false);
        properties = tile.getProperties();
        properties.setProperty("UseSprites", true);
        properties.setProperty("Color", ColorRGBA.DarkGray.clone());
        properties.setProperty("Scale", new Vector3f(10, 10, 0));
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Texture", "Textures/Sprites/Other/0001.png");
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-084", 6, 1, 1, 1, 6.5f, -5F, 1, false);
        properties = tile.getProperties();
        properties.setProperty("UseSprites", true);
        properties.setProperty("Color", ColorRGBA.DarkGray.clone());
        properties.setProperty("Scale", new Vector3f(10, 10, 0));
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Texture", "Textures/Sprites/Other/0001.png");
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-085", 6, 1, 1, 1, 16.5f, -5F, 1, false);
        properties = tile.getProperties();
        properties.setProperty("UseSprites", true);
        properties.setProperty("Color", ColorRGBA.DarkGray.clone());
        properties.setProperty("Scale", new Vector3f(10, 10, 0));
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Texture", "Textures/Sprites/Other/0001.png");
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-086", 6, 1, 1, 1, 26.5f, -5F, 1, false);
        properties = tile.getProperties();
        properties.setProperty("UseSprites", true);
        properties.setProperty("Color", ColorRGBA.DarkGray.clone());
        properties.setProperty("Scale", new Vector3f(10, 10, 0));
        properties.setProperty("SupportsGlow", true);
        properties.setProperty("Texture", "Textures/Sprites/Other/0001.png");
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fobjects-087", 8, 10, 1, 1, 29, 1, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fobjects-088", 9, 10, 1, 1, 30, 1, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fobjects-089", 7, 10, 1, 1, 28, 1, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fobjects-090", 8, 10, 1, 1, 29, 2, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fobjects-091", 8, 10, 1, 1, 29, 3, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fobjects-092", 8, 10, 1, 1, 29, 4, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("fobjects-093", 8, 10, 1, 1, 29, 5, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-094", 9, 16, 1, 1,17, 3, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-095", 9, 16, 1, 1,18, 3, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-096", 9, 16, 1, 1,19, 3, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-097", 9, 16, 1, 1,20, 3, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-098", 9, 16, 1, 1,21, 3, 0, false);
        properties = tile.getProperties();
        properties.setProperty("Color", color.clone());
        properties.setProperty("Rotate",-FastMath.PI  / 2);
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-099", 6, 4, 1, 1, 26.5f, 4F, 1, false);
        properties = tile.getProperties();
        properties.setProperty("flipV", true);
        properties.setProperty("UseSprites", true);       
        properties.setProperty("Color", new ColorRGBA(1, 1, 1, 1));
        properties.setProperty("Scale", new Vector3f(10, 10, 0));
        properties.setProperty("Texture", "Textures/Sprites/Other/0001.png");
        map1.addTile(tile);
        
        tile = jMe3GL2Utils.newIstanceTile("objects-0100", 6, 4, 10, 1, 26.5f, 3F, 3, false);
        properties = tile.getProperties();
        properties.setProperty("UseSprites", true);
        properties.setProperty("Color", new ColorRGBA(0, 0, 0, 1));
        properties.setProperty("Rotate",FastMath.PI  / 2);
        properties.setProperty("Scale", new Vector3f(10, 10, 0));
        properties.setProperty("Texture", "Textures/Sprites/Other/0001.png");
        map1.addTile(tile);

        tile = jMe3GL2Utils.newIstanceTile("objects-0101", 6, 1, 4, 10,33.5F, 2F, 3, true);
        properties = tile.getProperties();
        properties.setProperty("UseSprites", true);        
        properties.setProperty("Color", new ColorRGBA(0, 0, 0, 0));
        properties.setProperty("RenderQueue.Bucket", RenderQueue.Bucket.Translucent);
        properties.setProperty("Texture", "Textures/Sprites/Other/00010.png");
        map1.addTile(tile);
    }
}
