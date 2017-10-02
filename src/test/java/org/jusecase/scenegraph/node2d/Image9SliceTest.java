package org.jusecase.scenegraph.node2d;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jusecase.scenegraph.node2d.Image;
import org.jusecase.scenegraph.node2d.Image9Slice;
import org.jusecase.scenegraph.texture.TextureAtlas;
import org.jusecase.scenegraph.texture.TextureMock;
import org.jusecase.ui.UiTest;

import static org.assertj.core.api.Assertions.assertThat;

public class Image9SliceTest extends UiTest {
    TextureMock atlasTexture = new TextureMock();
    TextureAtlas atlas = new TextureAtlas(atlasTexture);

    Image9Slice image;

    @BeforeEach
    public void setUp() {
        atlas.put("image-lt", 0, 0, 5, 5);
        atlas.put("image-lc", 0, 5, 5, 1);
        atlas.put("image-lb", 0, 6, 5, 5);

        atlas.put("image-ct", 5, 0, 1, 5);
        atlas.put("image-cc", 5, 5, 1, 1);
        atlas.put("image-cb", 5, 6, 1, 5);

        atlas.put("image-rt", 6, 0, 5, 5);
        atlas.put("image-rc", 6, 5, 5, 1);
        atlas.put("image-rb", 6, 6, 5, 5);

        image = new Image9Slice(atlas, "image");
    }

    @Test
    public void initialLayout() {
        assertThat(image.getWidth()).isEqualTo(11);
        assertThat(image.getHeight()).isEqualTo(11);

        thenNodeIsAt((Image) image.getChild(0), 0, 0, 5, 5); // lt
        thenNodeIsAt((Image) image.getChild(1), 0, 5, 5, 1); // lc
        thenNodeIsAt((Image) image.getChild(2), 0, 6, 5, 5); // lb

        thenNodeIsAt((Image) image.getChild(3), 5, 0, 1, 5); // ct
        thenNodeIsAt((Image) image.getChild(4), 5, 5, 1, 1); // cc
        thenNodeIsAt((Image) image.getChild(5), 5, 6, 1, 5); // cb

        thenNodeIsAt((Image) image.getChild(6), 6, 0, 5, 5); // rt
        thenNodeIsAt((Image) image.getChild(7), 6, 5, 5, 1); // rc
        thenNodeIsAt((Image) image.getChild(8), 6, 6, 5, 5); // rb
    }

    @Test
    public void changeSize() {
        image.setSize(200, 100);

        assertThat(image.getWidth()).isEqualTo(200);
        assertThat(image.getHeight()).isEqualTo(100);

        thenNodeIsAt((Image) image.getChild(0), 0, 0, 5, 5); // lt
        thenNodeIsAt((Image) image.getChild(1), 0, 5, 5, 90); // lc
        thenNodeIsAt((Image) image.getChild(2), 0, 95, 5, 5); // lb

        thenNodeIsAt((Image) image.getChild(3), 5, 0, 190, 5); // ct
        thenNodeIsAt((Image) image.getChild(4), 5, 5, 190, 90); // cc
        thenNodeIsAt((Image) image.getChild(5), 5, 95, 190, 5); // cb

        thenNodeIsAt((Image) image.getChild(6), 195, 0, 5, 5); // rt
        thenNodeIsAt((Image) image.getChild(7), 195, 5, 5, 90); // rc
        thenNodeIsAt((Image) image.getChild(8), 195, 95, 5, 5); // rb
    }
}