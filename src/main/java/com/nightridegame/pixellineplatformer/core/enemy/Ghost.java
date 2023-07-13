package com.nightridegame.pixellineplatformer.core.enemy;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.nightridegame.pixellineplatformer.PixelLinePlatformer;
import com.nightridegame.pixellineplatformer.core.utils.ColorUtils;
import com.nightridegame.pixellineplatformer.core.utils.ConcurrentList;
import com.nightridegame.pixellineplatformer.core.utils.Utils;
import jMe3GL2.geometry.jMe3GL2Geometry;
import jMe3GL2.scene.control.IndexAnimatedSprite;
import jMe3GL2.scene.shape.Sprite;
import jMe3GL2.util.Converter;
import jMe3GL2.util.jMe3GL2Utils;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.MassType;

/**
 * Control de enemigos de tipo fantasma.
 */
public class Ghost extends Volables {
    
    public Ghost(PixelLinePlatformer app) {
        super(app);
    }
    
    @Override
    protected void _ready() {
        super._ready();
        following = true;
        
        ThreadLocalRandom random = ThreadLocalRandom.current();        
        maxVel = Double.valueOf(random.nextDouble(0.5, 0.8)).floatValue();
        paths.setTypeList(ConcurrentList.TypeList.Circular);
    }

    public void setTypeList(ConcurrentList.TypeList typeList) {
        paths.setTypeList(typeList);
    }
    
    @Override
    public void onDead() {
        super.onDead(); 
        spatial.getControl(IndexAnimatedSprite.class).playAnimation("dead", 0.5F);
    }
    
    public static Node getInstance(PixelLinePlatformer app, ConcurrentList.TypeList typeList,  Vector2f...paths) {
        AssetManager assetManager = app.getAssetManager();
        
        Node node = new Node("NODE_MOB");        
        Sprite sprite   = new Sprite(1.0F, 1.0F, 20, 20, 3, 19);
        
        Geometry geom = new Geometry("Ghost", sprite);
        Material mat  = jMe3GL2Utils.loadMaterial(assetManager, "Textures/Spritesheets/monochrome_tilemap_transparent_packed.png");
        
        mat.setFloat("AlphaDiscardThreshold", 1.0F);
        mat.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        mat.setColor("Color", ColorUtils.brighter(ColorRGBA.Gray.clone()));
        
        IndexAnimatedSprite ctrlAnim = new IndexAnimatedSprite();
        ctrlAnim.addAnimation("idle", new Integer[] {383, 384});
        ctrlAnim.addAnimation("dead", new Integer[] {385});
        ctrlAnim.setSpeed(0.60F);
        
        geom.setMaterial(mat);
        geom.setQueueBucket(RenderQueue.Bucket.Transparent);
        geom.setUserData(Utils.USER_DATA, Utils.ENEMY);
        
        Ghost player = new Ghost(app);
        BodyFixture bf = new BodyFixture(jMe3GL2Geometry.createCircle(0.4));
        
        List<Vector2f> path = Arrays.asList(paths);
        
        player.addPoints(path);
        player.addFixture(bf);
        player.setMass(MassType.NORMAL);        
        player.translate(Converter.toVector2(path.get(0)));
        
        geom.addControl(player);
        geom.addControl(ctrlAnim);
         
        player.setTypeList(typeList);
        ctrlAnim.playAnimation("idle", 0.15F);
        node.attachChild(geom);
        return node;
    }
    
}
