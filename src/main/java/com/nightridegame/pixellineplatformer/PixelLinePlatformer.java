package com.nightridegame.pixellineplatformer;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.post.filters.FogFilter;
import com.jme3.post.filters.RadialBlurFilter;
//import com.jme3.post.filters.RadialBlurFilter;
//import com.jme3.post.filters.RadialBlurFilter;
import com.jme3.post.filters.TranslucentBucketFilter;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
import com.nightridegame.pixellineplatformer.core.state.PlayState;

import jMe3GL2.awt.AWTResolution;
import jMe3GL2.awt.jMe3GL2DefaultDisplaySystem;
import jMe3GL2.awt.jMe3GL2DisplaySystem;
import jMe3GL2.physics.Dyn4jAppState;
import jMe3GL2.physics.ThreadingType;
import jMe3GL2.physics.control.PhysicsBody2D;
import jMe3GL2.renderer.Camera2DRenderer;
import jMe3GL2.util.TimerAppState;
import jMe3GL2.util.input.InputHandlerAppState;

/**
 * <code>PixelLinePlatformer</code> es la clase princiapl donde arran la
 * aplicación o juego.
 * <p>
 * De manera predeterminada el juego inicia en pantalla completa, si hay
 * problema con ello se recomienda cambiar:
 * <pre><code>
 * ...
 * //jMe3GL2DisplaySystem displaySystem = jMe3GL2DefaultDisplaySystem.getDisplaySystem();
 * //AWTResolution resolution = displaySystem.getFullScreenResolution();
 *       
 * settings.setResolution(1024, 576);
 * settings.setFullscreen(false);
 * settings.setGammaCorrection(false);
 * ...
 * </code></pre>
 * </p>
 * 
 * @author wil
 * @version 1.0-SNAPSHOT
 * 
 * @see 1.0.0
 */
public class PixelLinePlatformer extends SimpleApplication {
    
    public static void main(String[] args) {
        final PixelLinePlatformer app = new PixelLinePlatformer();
        final AppSettings settings = new AppSettings(true);

        final jMe3GL2DisplaySystem displaySystem = jMe3GL2DefaultDisplaySystem.getDisplaySystem();
        final AWTResolution resolution = displaySystem.getFullScreenResolution();

        /*
         * Para iniciar el juego 'PixelLinePlatformer' primero tenemos que
         * establecer las configuraciones para su uso en esta plataforma.
         */
        
        //settings.setResolution(1024, 576);
        settings.setResolution(resolution.getWidth(), resolution.getHeight());
        settings.setFullscreen(displaySystem.isFullScreenSupported());
        settings.setGammaCorrection(false);
        
        app.setSettings(settings);
        app.setShowSettings(false); //Settings dialog not supported on mac
        app.start();
    }

    /** Administrador de entradas. */
    private InputHandlerAppState handlerAppState;

    /** Espacio físico para los cuerpos. */
    private Dyn4jAppState<PhysicsBody2D> dyn4jAppState;

    /** Gestor de tiempos. */
    private TimerAppState timerAppState;
    
    /**
     * Objeto encargado de gestionar y configurar la cámara predeterminada
     * que proporciona {@code jme3} en una que se adapte en 2D.
     */
    private Camera2DRenderer camera2DRenderer;

    /*
        Finltros de post-procesados para las particulas a utilizar.
    */
    private FilterPostProcessor filterPostProcessor = null;
    private FilterPostProcessor fpp = null;
    
    private TranslucentBucketFilter translucentFilter;
    private BloomFilter bloom;
        
    @Override
    public void simpleInitApp() {
        setDisplayFps(false);
        setDisplayStatView(false);
        
        filterPostProcessor = new FilterPostProcessor(getAssetManager());
        getViewPort().addProcessor(filterPostProcessor);
        
        translucentFilter = new TranslucentBucketFilter(true);
        filterPostProcessor.addFilter(translucentFilter);
        
        fpp = new FilterPostProcessor(assetManager);
        bloom  = new BloomFilter(BloomFilter.GlowMode.Objects);
        bloom.setBloomIntensity(2.0F);
        bloom.setExposurePower(10.0F);
        bloom.setExposureCutOff(0.0F);
        bloom.setBlurScale(2.75F);
        fpp.addFilter(bloom);
        fpp.addFilter(new RadialBlurFilter(10, 1.2f));
        //fpp.addFilter(new FogFilter(ColorRGBA.DarkGray, 0.8f, 5));
        getViewPort().addProcessor(fpp);
                
        camera2DRenderer = new Camera2DRenderer(8.0F, 0.45F);
        camera2DRenderer.setClipping(new Vector2f(6, -1), new Vector2f(17, 1));
        stateManager.attach(camera2DRenderer);
        
        handlerAppState = new InputHandlerAppState();
        stateManager.attach(handlerAppState);
        
        timerAppState = new TimerAppState();
        stateManager.attach(timerAppState);

        dyn4jAppState = new Dyn4jAppState<>(ThreadingType.PARALLEL);
        stateManager.attach(dyn4jAppState);
        stateManager.attach(new PlayState());
        viewPort.setBackgroundColor(new ColorRGBA(0.005F, 0.005F, 0.005F, 1.0F));
    }

    public Dyn4jAppState<PhysicsBody2D> getDyn4jAppState() {
        return dyn4jAppState;
    }
    
    public FogFilter getFogFilter() {
        return fpp.getFilter(FogFilter.class);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //this method will be called every game tick and can be used to make updates
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //add render code here (if any)
    }
}
