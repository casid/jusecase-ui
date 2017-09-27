package org.jusecase.scenegraph;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class Node2d_LocalMatrixTest {
    private static final Offset<Float> TOLERANCE = offset(0.00001f);

    Node2d node = new Node2d();

    @Test
    public void identity() {
        assertThat(node.getLocalMatrix().a).isEqualTo(1.0f);
        assertThat(node.getLocalMatrix().b).isEqualTo(0.0f);
        assertThat(node.getLocalMatrix().c).isEqualTo(0.0f);
        assertThat(node.getLocalMatrix().d).isEqualTo(1.0f);
        assertThat(node.getLocalMatrix().tx).isEqualTo(0.0f);
        assertThat(node.getLocalMatrix().ty).isEqualTo(0.0f);
    }

    @Test
    public void translate() {
        node.setX(100f).setY(50f);

        assertThat(node.getLocalMatrix().tx).isEqualTo(100f);
        assertThat(node.getLocalMatrix().ty).isEqualTo(50f);
    }

    @Test
    public void scale() {
        node.setScaleX(2.0f).setScaleY(1.3f);
        assertThat(node.getLocalMatrix().a).isEqualTo(2.0f);
        assertThat(node.getLocalMatrix().d).isEqualTo(1.3f);
    }

    @Test
    public void rotate() {
        node.setRotation(90);
        assertThat(node.getLocalMatrix().a).isEqualTo(0.0f, TOLERANCE);
        assertThat(node.getLocalMatrix().b).isEqualTo(1.0f, TOLERANCE);
        assertThat(node.getLocalMatrix().c).isEqualTo(-1.0f, TOLERANCE);
        assertThat(node.getLocalMatrix().d).isEqualTo(0.0f, TOLERANCE);
    }

    @Test
    public void rotate_scale() {
        node.setRotation(90);
        node.setScaleX(10).setScaleY(100);
        assertThat(node.getLocalMatrix().a).isEqualTo(0.0f, TOLERANCE);
        assertThat(node.getLocalMatrix().b).isEqualTo(10.0f, TOLERANCE);
        assertThat(node.getLocalMatrix().c).isEqualTo(-100.0f, TOLERANCE);
        assertThat(node.getLocalMatrix().d).isEqualTo(0.0f, TOLERANCE);
    }

    @Test
    public void pivot() {
        node.setSize(10, 5);

        node.setPivot(0.5f, 0.5f);

        assertThat(node.getLocalMatrix().tx).isEqualTo(-5.0f, TOLERANCE);
        assertThat(node.getLocalMatrix().ty).isEqualTo(-2.5f, TOLERANCE);
    }

    @Test
    public void pivot_scale() {
        node.setSize(10, 5).setScale(2.0f);

        node.setPivot(0.5f, 0.5f);

        assertThat(node.getLocalMatrix().tx).isEqualTo(-10.0f, TOLERANCE);
        assertThat(node.getLocalMatrix().ty).isEqualTo(-5.0f, TOLERANCE);
    }

    @Test
    public void pivot_rotate_180() {
        node.setSize(10, 5).setScale(2.0f).setRotation(180);

        node.setPivot(0.5f, 0.5f);

        assertThat(node.getLocalMatrix().tx).isEqualTo(10.0f, TOLERANCE);
        assertThat(node.getLocalMatrix().ty).isEqualTo(5.0f, TOLERANCE);
    }

    @Test
    public void pivot_rotate_90() {
        node.setSize(10, 5).setScale(2.0f).setRotation(90);

        node.setPivot(0.5f, 0.5f);

        assertThat(node.getLocalMatrix().tx).isEqualTo(-5.0f, TOLERANCE);
        assertThat(node.getLocalMatrix().ty).isEqualTo(10.0f, TOLERANCE);
    }
}