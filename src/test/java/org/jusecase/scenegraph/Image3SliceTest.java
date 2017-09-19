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

        Image left = (Image)image.getChild(0);
        assertThat(left.getX()).isEqualTo(0);
        assertThat(left.getY()).isEqualTo(0);
        assertThat(left.getWidth()).isEqualTo(5);
        assertThat(left.getHeight()).isEqualTo(10);

        Image center = (Image)image.getChild(1);
        assertThat(center.getX()).isEqualTo(5);
        assertThat(center.getY()).isEqualTo(0);
        assertThat(center.getWidth()).isEqualTo(1);
        assertThat(center.getHeight()).isEqualTo(10);

        Image right = (Image)image.getChild(2);
        assertThat(right.getX()).isEqualTo(6);
        assertThat(right.getY()).isEqualTo(0);
        assertThat(right.getWidth()).isEqualTo(5);
        assertThat(right.getHeight()).isEqualTo(10);
    }

    @Test
    public void changeWidth() {
        image.setWidth(200);

        assertThat(image.getWidth()).isEqualTo(200);
        assertThat(image.getHeight()).isEqualTo(10);

        Image left = (Image)image.getChild(0);
        assertThat(left.getX()).isEqualTo(0);
        assertThat(left.getY()).isEqualTo(0);
        assertThat(left.getWidth()).isEqualTo(5);
        assertThat(left.getHeight()).isEqualTo(10);

        Image center = (Image)image.getChild(1);
        assertThat(center.getX()).isEqualTo(5);
        assertThat(center.getY()).isEqualTo(0);
        assertThat(center.getWidth()).isEqualTo(190);
        assertThat(center.getHeight()).isEqualTo(10);

        Image right = (Image)image.getChild(2);
        assertThat(right.getX()).isEqualTo(195);
        assertThat(right.getY()).isEqualTo(0);
        assertThat(right.getWidth()).isEqualTo(5);
        assertThat(right.getHeight()).isEqualTo(10);
    }
}