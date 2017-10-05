package org.jusecase.scenegraph.node2d;

import org.junit.jupiter.api.Test;
import org.jusecase.scenegraph.texture.TextureMock;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageTest {
    TextureMock textureMock = new TextureMock();
    Image image;

    @Test
    public void texture() {
        textureMock.givenSize(512, 256);

        image = new Image(textureMock);

        assertThat(image.getWidth()).isEqualTo(512);
        assertThat(image.getHeight()).isEqualTo(256);
    }
}