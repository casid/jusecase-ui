package org.jusecase.scenegraph.node2d;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jusecase.scenegraph.node2d.ImageAnimation.Sequence;
import org.jusecase.scenegraph.texture.SubTexture;
import org.jusecase.scenegraph.texture.TextureAtlas;
import org.jusecase.scenegraph.texture.TextureFrame;
import org.jusecase.scenegraph.texture.TextureMock;
import org.jusecase.scenegraph.tween.Tweens;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ImageAnimationTest {
    TextureAtlas textureAtlas;
    Tweens tweens;

    Sequence sequence;

    ImageAnimation imageAnimation;

    @BeforeEach
    void setUp() {
        TextureMock texture = new TextureMock();
        texture.givenSize(512, 1024);
        textureAtlas = new TextureAtlas(texture);

        textureAtlas.put("running0000", 0, 0, 19, 20, new TextureFrame(1, 2, 2, 2));
        textureAtlas.put("running0001", 20, 0, 18, 20, new TextureFrame(2, 2, 2, 2));
        textureAtlas.put("running0002", 0, 20, 17, 20, new TextureFrame(3, 2, 2, 2));

        tweens = new Tweens();

        sequence = new Sequence();
        sequence.textureAtlas = textureAtlas;
        sequence.name = "running";
        sequence.frameCount = 3;

        imageAnimation = new ImageAnimation(tweens, sequence);
    }

    @Test
    void initial() {
        assertThat(getCurrentTexture().getFrame().left).isEqualTo(1);
    }

    @Test
    void playing1() {
        imageAnimation.play();
        tweens.update(1);
        assertThat(getCurrentTexture().getFrame().left).isEqualTo(2);
    }

    @Test
    void playing2() {
        imageAnimation.play();
        tweens.update(2);
        assertThat(getCurrentTexture().getFrame().left).isEqualTo(3);
    }

    @Test
    void playing3_stopsAtEnd() {
        imageAnimation.play();
        tweens.update(3);
        assertThat(getCurrentTexture().getFrame().left).isEqualTo(3);
    }

    @Test
    void pivot() {
        sequence.pivotX = 0.25f;
        sequence.pivotY = 0.75f;
        imageAnimation.setCurrentSequence(sequence);

        imageAnimation.play();

        assertThat(getCurrentImage().getPivotX()).isEqualTo(0.25f);
        assertThat(getCurrentImage().getPivotY()).isEqualTo(0.75f);
    }

    @Test
    void speedFactor() {
        sequence.speedFactor = 2.0f;
        imageAnimation.play();

        tweens.update(0.5f);

        assertThat(getCurrentTexture().getFrame().left).isEqualTo(2);
    }

    @Test
    void changeSequence_stopsPlayback() {
        imageAnimation.play();
        imageAnimation.setCurrentSequence(sequence);

        tweens.update(1);

        assertThat(getCurrentTexture().getFrame().left).isEqualTo(1);
    }

    @Test
    void textureLookup_42() {
        sequence.frameCount = 9999;
        textureAtlas.put("running0042", 0, 20, 42, 42);

        imageAnimation.setCurrentFrame(42);

        assertThat(getCurrentTexture().getWidth()).isEqualTo(42);
    }

    @Test
    void textureLookup_420() {
        sequence.frameCount = 9999;
        textureAtlas.put("running0420", 0, 20, 42, 42);

        imageAnimation.setCurrentFrame(420);

        assertThat(getCurrentTexture().getWidth()).isEqualTo(42);
    }

    @Test
    void textureLookup_4200() {
        sequence.frameCount = 9999;
        textureAtlas.put("running4200", 0, 20, 42, 42);

        imageAnimation.setCurrentFrame(4200);

        assertThat(getCurrentTexture().getWidth()).isEqualTo(42);
    }

    @Test
    void textureLookup_insane() {
        sequence.frameCount = 20000;

        Throwable throwable = catchThrowable(() -> imageAnimation.setCurrentFrame(10000));

        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    private Image getCurrentImage() {
        return (Image)imageAnimation.getChild(0);
    }

    private SubTexture getCurrentTexture() {
        return (SubTexture) getCurrentImage().getTexture();
    }
}