package com.nightridegame.pixellineplatformer.core.utils;

import com.jme3.math.ColorRGBA;

/**
 * Utilidades de colores.
 */
public final class ColorUtils {
    
    private static final float FACTOR = 0.7F;
    
    /** Constructor privado. */
    private ColorUtils() {};
    
    public static ColorRGBA darker(ColorRGBA color) {
        return new ColorRGBA(Math.max((color.getRed()   * 255) * FACTOR, 0) / 255,
                             Math.max((color.getGreen() * 255) * FACTOR, 0) / 255,
                             Math.max((color.getBlue()  * 255) * FACTOR, 0) / 255,
                             color.getAlpha());
    }
    
    public static ColorRGBA brighter(ColorRGBA color) {
        float r = color.getRed()   * 255.0F;
        float g = color.getGreen() * 255.0F;
        float b = color.getBlue()  * 255.0F;
        float alpha = color.getAlpha();
        
        float i = (1.0f/(1.0f-FACTOR));
        if ( r == 0 && g == 0 && b == 0) {
            return new ColorRGBA(i, i, i, alpha);
        }
        if ( r > 0 && r < i ) r = i;
        if ( g > 0 && g < i ) g = i;
        if ( b > 0 && b < i ) b = i;
        
        return new ColorRGBA(Math.min(r/FACTOR, 255.0F) / 255.0F,
                             Math.min(g/FACTOR, 255.0F) / 255.0F,
                             Math.min(b/FACTOR, 255.0F) / 255.0F,
                             alpha);
    }
}