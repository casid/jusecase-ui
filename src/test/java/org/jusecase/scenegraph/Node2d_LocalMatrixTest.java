package org.jusecase.scenegraph;

import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class Node2d_LocalMatrixTest {
    private static final Offset<Double> TOLERANCE = offset(0.000000001);

    Node2d node = new Node2d();

    @Test
    public void identity() {
        assertThat(node.getLocalMatrix().a).isEqualTo(1.0);
        assertThat(node.getLocalMatrix().b).isEqualTo(0.0);
        assertThat(node.getLocalMatrix().c).isEqualTo(0.0);
        assertThat(node.getLocalMatrix().d).isEqualTo(1.0);
        assertThat(node.getLocalMatrix().tx).isEqualTo(0.0);
        assertThat(node.getLocalMatrix().ty).isEqualTo(0.0);
    }

    @Test
    public void translate() {
        node.setX(100).setY(50);

        assertThat(node.getLocalMatrix().tx).isEqualTo(100);
        assertThat(node.getLocalMatrix().ty).isEqualTo(50);
    }

    @Test
    public void scale() {
        node.setScaleX(2.0).setScaleY(1.3);
        assertThat(node.getLocalMatrix().a).isEqualTo(2.0);
        assertThat(node.getLocalMatrix().d).isEqualTo(1.3);
    }

    @Test
    public void rotate() {
        node.setRotation(90);
        assertThat(node.getLocalMatrix().a).isEqualTo(0.0, TOLERANCE);
        assertThat(node.getLocalMatrix().b).isEqualTo(1.0, TOLERANCE);
        assertThat(node.getLocalMatrix().c).isEqualTo(-1.0, TOLERANCE);
        assertThat(node.getLocalMatrix().d).isEqualTo(0.0, TOLERANCE);
    }

    @Test
    public void rotate_scale() {
        node.setRotation(90);
        node.setScaleX(10).setScaleY(100);
        assertThat(node.getLocalMatrix().a).isEqualTo(0.0, TOLERANCE);
        assertThat(node.getLocalMatrix().b).isEqualTo(10.0, TOLERANCE);
        assertThat(node.getLocalMatrix().c).isEqualTo(-100.0, TOLERANCE);
        assertThat(node.getLocalMatrix().d).isEqualTo(0.0, TOLERANCE);
    }

    @Test
    public void pivot() {
        node.setSize(10, 5);

        node.setPivot(0.5, 0.5);

        assertThat(node.getLocalMatrix().tx).isEqualTo(-5.0, TOLERANCE);
        assertThat(node.getLocalMatrix().ty).isEqualTo(-2.5, TOLERANCE);
    }

    @Test
    public void pivot_scale() {
        node.setSize(10, 5).setScale(2.0);

        node.setPivot(0.5, 0.5);

        assertThat(node.getLocalMatrix().tx).isEqualTo(-10.0, TOLERANCE);
        assertThat(node.getLocalMatrix().ty).isEqualTo(-5.0, TOLERANCE);
    }

    @Test
    public void pivot_rotate_180() {
        node.setSize(10, 5).setScale(2.0).setRotation(180);

        node.setPivot(0.5, 0.5);

        assertThat(node.getLocalMatrix().tx).isEqualTo(10.0, TOLERANCE);
        assertThat(node.getLocalMatrix().ty).isEqualTo(5.0, TOLERANCE);
    }

    @Test
    public void pivot_rotate_90() {
        node.setSize(10, 5).setScale(2.0).setRotation(90);

        node.setPivot(0.5, 0.5);

        assertThat(node.getLocalMatrix().tx).isEqualTo(-5.0, TOLERANCE);
        assertThat(node.getLocalMatrix().ty).isEqualTo(10.0, TOLERANCE);
    }
}