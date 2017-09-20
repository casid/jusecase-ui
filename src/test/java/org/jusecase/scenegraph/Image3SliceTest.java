package org.jusecase.scenegraph;

import org.junit.Before;
import org.junit.Test;
import org.jusecase.scenegraph.texture.TextureAtlas;
import org.jusecase.scenegraph.texture.TextureMock;
import org.jusecase.ui.UiTest;

import static org.assertj.core.api.Assertions.assertThat;

public class Image3SliceTest extends UiTest {
    Image3Slice image;

    TextureMock atlasTexture = new TextureMock();
    TextureAtlas atlas = new TextureAtlas(atlasTexture);

    @Before
    public void setUp() {
        atlas.put("left", 0, 0, 5, 10);
        atlas.put("center", 5, 0, 1, 10);
        atlas.put("right", 6, 0, 5, 10);

        image = new Image3Slice(atlas, "left", "center", "right");
    }

    @Test
    public void initialLayout() {
        assertThat(image.getWidth()).isEqualTo(11);
        assertThat(image.getHeight()).isEqualTo(10);

        thenNodeIsAt((Image)image.getChild(0), 0, 0, 5, 10);
        thenNodeIsAt((Image)image.getChild(1), 5, 0, 1, 10);
        thenNodeIsAt((Image)image.getChild(2), 6, 0, 5, 10);
    }

    @Test
    public void changeWidth() {
        image.setWidth(200);

        assertThat(image.getWidth()).isEqualTo(200);
        assertThat(image.getHeight()).isEqualTo(10);

        thenNodeIsAt((Image)image.getChild(0), 0, 0, 5, 10);
        thenNodeIsAt((Image)image.getChild(1), 5, 0, 190, 10);
        thenNodeIsAt((Image)image.getChild(2), 195, 0, 5, 10);
    }

    @Test
    public void changeHeight() {
        image.setHeight(100);

        assertThat(image.getWidth()).isEqualTo(11);
        assertThat(image.getHeight()).isEqualTo(100);

        thenNodeIsAt((Image)image.getChild(0), 0, 0, 5, 100);
        thenNodeIsAt((Image)image.getChild(1), 5, 0, 1, 100);
        thenNodeIsAt((Image)image.getChild(2), 6, 0, 5, 100);
    }
}