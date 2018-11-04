package org.jusecase.scenegraph.node2d;

import org.jusecase.scenegraph.texture.Texture;
import org.jusecase.scenegraph.texture.TextureAtlas;
import org.jusecase.scenegraph.tween.Tweens;
import org.jusecase.scenegraph.tween.properties.IntProperty;

public class AnimatedImage extends Node2d {
    private final Tweens tweens;

    private Image image;
    private Sequence currentSequence;
    private int currentFrame;

    public AnimatedImage(Tweens tweens, Sequence sequence) {
        this.tweens = tweens;
        setCurrentSequence(sequence);
    }

    public void play() {
        tweens.tween(image)
                .duration(currentSequence.frameCount / currentSequence.speedFactor)
                .property(new IntProperty(currentFrame, currentSequence.frameCount, this::setCurrentFrame));
    }

    public void setCurrentSequence(Sequence sequence) {
        this.currentSequence = sequence;
        this.currentFrame = 0;

        if (image == null) {
            image = new Image(getCurrentTexture());
            add(image);
        } else {
            image.setTexture(getCurrentTexture());
            tweens.remove(image);
        }

        image.setPivot(sequence.pivotX, sequence.pivotY);
    }

    private Texture getCurrentTexture() {
        return currentSequence.textureAtlas.get(getCurrentTextureName());
    }

    private String getCurrentTextureName() {
        if (currentFrame < 10) {
            return currentSequence.name + "000" + currentFrame;
        }
        if (currentFrame < 100) {
            return currentSequence.name + "00" + currentFrame;
        }
        if (currentFrame < 1000) {
            return currentSequence.name + "0" + currentFrame;
        }
        if (currentFrame < 10000) {
            return currentSequence.name + currentFrame;
        }

        throw new IllegalArgumentException("Insane frame for sprite animation: " + currentFrame);
    }

    public void setCurrentFrame(int currentFrame) {
        if (currentFrame >= currentSequence.frameCount) {
            currentFrame = currentSequence.frameCount - 1;
        }

        this.currentFrame = currentFrame;
        this.image.setTexture(getCurrentTexture());
    }

    public static class Sequence {
        public String name;
        public TextureAtlas textureAtlas;
        public int frameCount;
        public float speedFactor = 1.0f;
        public float pivotX;
        public float pivotY;
    }
}
