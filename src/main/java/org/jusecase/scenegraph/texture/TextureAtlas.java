package org.jusecase.scenegraph.texture;

import java.util.HashMap;
import java.util.Map;

public class TextureAtlas {
    private final Texture texture;
    private final Map<String, SubTexture> subTextures = new HashMap<>();

    public TextureAtlas(Texture texture) {
        this.texture = texture;
    }

    public Texture get(String name) {
        return subTextures.get(name);
    }

    public void put(String name, int x, int y, int w, int h) {
        put(name, x, y, w, h, null);
    }

    public void put(String name, int x, int y, int w, int h, TextureFrame textureFrame) {
        subTextures.put(name, new SubTexture(texture, x, y, w, h, textureFrame));
    }

    public void dispose() {
        texture.dispose();
    }
}
