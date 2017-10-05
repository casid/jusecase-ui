package org.jusecase.scenegraph.node2d;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class Node2d_GlobalMatrixTest {
    private static final Offset<Float> TOLERANCE = offset(0.00001f);

    Node2d parent = new Node2d();
    Node2d child = new Node2d();

    @BeforeEach
    public void setUp() {
        parent.add(child);
        parent.setX(20);
        parent.setY(20);
    }

    @Test
    public void identity() {
        assertThat(child.getGlobalMatrix().a).isEqualTo(1.0f);
        assertThat(child.getGlobalMatrix().b).isEqualTo(0.0f);
        assertThat(child.getGlobalMatrix().c).isEqualTo(0.0f);
        assertThat(child.getGlobalMatrix().d).isEqualTo(1.0f);
        assertThat(child.getGlobalMatrix().tx).isEqualTo(20.0f);
        assertThat(child.getGlobalMatrix().ty).isEqualTo(20.0f);
    }

    @Test
    public void translate() {
        child.setX(100).setY(50);

        assertThat(child.getGlobalMatrix().tx).isEqualTo(120f);
        assertThat(child.getGlobalMatrix().ty).isEqualTo(70f);
    }

    @Test
    public void scale() {
        parent.setScaleX(2.0f).setScaleY(1.3f);
        assertThat(child.getGlobalMatrix().a).isEqualTo(2.0f);
        assertThat(child.getGlobalMatrix().d).isEqualTo(1.3f);
    }

    @Test
    public void rotate() {
        parent.setRotation(90);
        assertThat(child.getGlobalMatrix().a).isEqualTo(0.0f, TOLERANCE);
        assertThat(child.getGlobalMatrix().b).isEqualTo(1.0f, TOLERANCE);
        assertThat(child.getGlobalMatrix().c).isEqualTo(-1.0f, TOLERANCE);
        assertThat(child.getGlobalMatrix().d).isEqualTo(0.0f, TOLERANCE);
    }

    @Test
    public void rotate_scale() {
        parent.setRotation(90);
        parent.setScaleX(10).setScaleY(100);
        assertThat(child.getGlobalMatrix().a).isEqualTo(0.0f, TOLERANCE);
        assertThat(child.getGlobalMatrix().b).isEqualTo(10.0f, TOLERANCE);
        assertThat(child.getGlobalMatrix().c).isEqualTo(-100.0f, TOLERANCE);
        assertThat(child.getGlobalMatrix().d).isEqualTo(0.0f, TOLERANCE);
    }

    @Test
    public void manyChildren_globalMatrixIsRecalculated() {
        Node2d grandChild = new Node2d();
        child.add(grandChild);
        Node2d grandGrandChild = new Node2d();
        grandChild.add(grandGrandChild);
        assertThat(grandGrandChild.getGlobalMatrix().tx).isEqualTo(20.0f);
        assertThat(grandGrandChild.getGlobalMatrix().ty).isEqualTo(20.0f);

        parent.setX(50).setY(100);
        assertThat(grandGrandChild.getGlobalMatrix().tx).isEqualTo(50.0f);
        assertThat(grandGrandChild.getGlobalMatrix().ty).isEqualTo(100.0f);
    }
}