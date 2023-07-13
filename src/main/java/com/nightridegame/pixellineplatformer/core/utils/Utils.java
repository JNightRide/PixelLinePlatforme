package com.nightridegame.pixellineplatformer.core.utils;

import com.epagagames.particles.Emitter;
import com.epagagames.particles.emittershapes.EmitterCone;
import com.epagagames.particles.influencers.ColorInfluencer;
import com.epagagames.particles.influencers.SizeInfluencer;
import com.epagagames.particles.valuetypes.ColorValueType;
import com.epagagames.particles.valuetypes.Curve;
import com.epagagames.particles.valuetypes.Gradient;
import com.epagagames.particles.valuetypes.ValueType;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.texture.Texture;

/**
 * Clase utilidades encargada de proprocionar métodos que ayuden con las
 * funciones que se utilizan de manera consecutiva.
 * 
 * @author wil
 * @version 1.0-SNAPSHOT
 * 
 * @see 1.0.0
 */
public final class Utils {
    
    /** Constructor privado. */
    private Utils() {}
    
    /** Clave para los datos de usuario dedicado al mundo. */
    public static final String USER_DATA = "USER_DATA ";
    
    /** Dato-clave para los objetos del pizo. */
    public static final String FLOOR = "FLOOR";
    
    /** Nombre clave para el jugador. */
    public static final String CHARACTER = "CHARACTER";
    
    /**  Dato-clave para las plataformas de vías. */
    public static final String ONE_WAY_PLATFORM = "ONE_WAY_PLATFORM";
    
    /** Objetos para las paredes. */
    public static final String WALL = "WALL";
    
    /** Para objetos enemigos. */
    public static final String ENEMY = "ENEMY";
    
    public static Emitter getInstance(AssetManager assetManager, String texture) {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        mat.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        Texture tex = assetManager.loadTexture(texture);
        mat.setTexture("Texture", tex);
                
        Emitter emitter = new Emitter("test", mat, 200, new ColorInfluencer(), new SizeInfluencer());
        emitter.setStartSize(new ValueType(0.5f));
        emitter.getInfluencer(SizeInfluencer.class).setSizeOverTime(new ValueType(new Curve()
            .addControlPoint(null, new Vector2f(0.0f, 0.0f), new Vector2f(0.3f, 0.0f))
            .addControlPoint(new Vector2f(0.3f, 1.0f), new Vector2f(0.5f, 1.0f), new Vector2f(0.7f, 1.0f))
            .addControlPoint(new Vector2f(0.7f, 0.0f), new Vector2f(1.0f, 0.0f), null)
        ));
        emitter.getInfluencer(ColorInfluencer.class).setColorOverTime(new ColorValueType(new Gradient()
                .addGradPoint(new ColorRGBA(0.5F, 0.5F, 0.5F, 0.0F), 0.0f)
                .addGradPoint(new ColorRGBA(0.5F, 0.5F, 0.5F, 0.1F), 0.1f)
                .addGradPoint(new ColorRGBA(0.5F, 0.5F, 0.5F, 0.2F), 0.2f)
                .addGradPoint(new ColorRGBA(0.5F, 0.5F, 0.5F, 0.3F), 0.3f)
                .addGradPoint(new ColorRGBA(0.5F, 0.5F, 0.5F, 0.4F), 0.4f)
                .addGradPoint(new ColorRGBA(0.5F, 0.5F, 0.5F, 0.5F), 0.5f)
                .addGradPoint(new ColorRGBA(0.5F, 0.5F, 0.5F, 0.6F), 0.6f)
                .addGradPoint(new ColorRGBA(0.5F, 0.5F, 0.5F, 0.7F), 0.7f)
                .addGradPoint(new ColorRGBA(0.5F, 0.5F, 0.5F, 0.8F), 0.8f)
                .addGradPoint(new ColorRGBA(0.5F, 0.5F, 0.5F, 0.9F), 0.9f)
                .addGradPoint(new ColorRGBA(0.5F, 0.5F, 0.5F, 1.0F), 1.0f)
        ));
        
        emitter.setStartSpeed(new ValueType(0.5f));
        emitter.setLifeFixedDuration(50.0f);
        emitter.setUseRandomEmissionPoint(true);
        emitter.setEmissionsPerSecond(1);
        emitter.setParticlesPerEmission(1);
        emitter.setShape(new EmitterCone());
        emitter.setQueueBucket(RenderQueue.Bucket.Translucent);
        ((EmitterCone)emitter.getShape()).setRadius(5f);
        return emitter;
    }
}
