package org.jusecase.scenegraph.texture;

public class SubTexture implements Texture {

    private final Texture texture;
    private final TexCoords texCoords;
    private final TextureFrame frame;
    private final int width;
    private final int height;

    public SubTexture(Texture texture, int x, int y, int w, int h) {
        this(texture, x, y, w, h, null);
    }

    public SubTexture(Texture texture, int x, int y, int w, int h, TextureFrame frame) {
        this.texture = texture;
        if (frame == null) {
            this.width = w;
            this.height = h;
        } else {
            this.width = w + frame.left + frame.right;
            this.height = h + frame.top + frame.bottom;
        }

        float left = (float)x / texture.getWidth();
        float bottom = (float)(y + h) / texture.getHeight();
        float right = (float)(x + w) / texture.getWidth();
        float top = (float)y / texture.getHeight();
        this.texCoords = new TexCoords(left, bottom, right, top);

        this.frame = frame;
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
    public TextureFrame getFrame() {
        return frame;
    }

    @Override
    public void dispose() {
        // Nothing to dispose
    }
}
