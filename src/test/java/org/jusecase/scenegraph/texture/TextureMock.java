package org.jusecase.scenegraph.texture;

import org.jusecase.scenegraph.math.DrawHash;

import static org.assertj.core.api.Assertions.assertThat;

public class TextureMock implements Texture {

    private int width;
    private int height;
    private boolean disposed;

    public void givenSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getId() {
        return 0;
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
        return TexCoords.DEFAULT;
    }

    @Override
    public TextureFrame getFrame() {
        return null;
    }

    @Override
    public void dispose() {
        disposed = true;
    }

    public void thenTextureIsDisposed() {
        assertThat(disposed).isTrue();
    }

    @Override
    public void hash(DrawHash hash) {
    }
}