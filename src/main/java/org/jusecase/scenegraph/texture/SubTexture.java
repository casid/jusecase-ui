package org.jusecase.scenegraph.texture;

public class SubTexture implements Texture {

    private final Texture texture;
    private final TexCoords texCoords;
    private final int width;
    private final int height;

    public SubTexture(Texture texture, int x, int y, int w, int h) {
        this.texture = texture;
        this.width = w;
        this.height = h;

        float left = (float)x / texture.getWidth();
        float bottom = (float)(y + h) / texture.getHeight();
        float right = (float)(x + w) / texture.getWidth();
        float top = (float)y / texture.getHeight();
        this.texCoords = new TexCoords(left, bottom, right, top);
    }

    @Override
    public int getId() {
        return texture.getId();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public TexCoords getTexCoords() {
        return texCoords;
    }

    @Override
    public void dispose() {
        // Nothing to dispose
    }
}
