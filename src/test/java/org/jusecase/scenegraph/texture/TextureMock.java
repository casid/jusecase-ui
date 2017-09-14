package org.jusecase.scenegraph.texture;

public class TextureMock implements Texture {

    private int width;
    private int height;

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
    public void dispose() {
    }
}