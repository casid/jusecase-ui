package org.jusecase.scenegraph;

import org.junit.Test;
import org.jusecase.scenegraph.texture.TextureMock;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageTest {
    Image image = new Image();
    TextureMock textureMock = new TextureMock();

    @Test
    public void name() {
        textureMock.givenSize(512, 256);

        image.setTexture(textureMock);

        assertThat(image.getWidth()).isEqualTo(512);
        assertThat(image.getHeight()).isEqualTo(256);
    }
}