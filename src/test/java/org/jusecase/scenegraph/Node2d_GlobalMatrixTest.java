package org.jusecase.scenegraph;

import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class Node2d_GlobalMatrixTest {
    private static final Offset<Double> TOLERANCE = offset(0.000000001);

    Node2d parent = new Node2d();
    Node2d child = new Node2d();

    @Before
    public void setUp() {
        parent.add(child);
        parent.setX(20);
        parent.setY(20);
    }

    @Test
    public void identity() {
        assertThat(child.getGlobalMatrix().a).isEqualTo(1.0);
        assertThat(child.getGlobalMatrix().b).isEqualTo(0.0);
        assertThat(child.getGlobalMatrix().c).isEqualTo(0.0);
        assertThat(child.getGlobalMatrix().d).isEqualTo(1.0);
        assertThat(child.getGlobalMatrix().tx).isEqualTo(20.0);
        assertThat(child.getGlobalMatrix().ty).isEqualTo(20.0);
    }

    @Test
    public void translate() {
        child.setX(100).setY(50);

        assertThat(child.getGlobalMatrix().tx).isEqualTo(120);
        assertThat(child.getGlobalMatrix().ty).isEqualTo(70);
    }

    @Test
    public void scale() {
        parent.setScaleX(2.0).setScaleY(1.3);
        assertThat(child.getGlobalMatrix().a).isEqualTo(2.0);
        assertThat(child.getGlobalMatrix().d).isEqualTo(1.3);
    }

    @Test
    public void rotate() {
        parent.setRotation(90);
        assertThat(child.getGlobalMatrix().a).isEqualTo(0.0, TOLERANCE);
        assertThat(child.getGlobalMatrix().b).isEqualTo(1.0, TOLERANCE);
        assertThat(child.getGlobalMatrix().c).isEqualTo(-1.0, TOLERANCE);
        assertThat(child.getGlobalMatrix().d).isEqualTo(0.0, TOLERANCE);
    }

    @Test
    public void rotate_scale() {
        parent.setRotation(90);
        parent.setScaleX(10).setScaleY(100);
        assertThat(child.getGlobalMatrix().a).isEqualTo(0.0, TOLERANCE);
        assertThat(child.getGlobalMatrix().b).isEqualTo(10.0, TOLERANCE);
        assertThat(child.getGlobalMatrix().c).isEqualTo(-100.0, TOLERANCE);
        assertThat(child.getGlobalMatrix().d).isEqualTo(0.0, TOLERANCE);
    }

    @Test
    public void manyChildren_globalMatrixIsRecalculated() {
        Node2d grandChild = new Node2d();
        child.add(grandChild);
        Node2d grandGrandChild = new Node2d();
        grandChild.add(grandGrandChild);
        assertThat(grandGrandChild.getGlobalMatrix().tx).isEqualTo(20.0);
        assertThat(grandGrandChild.getGlobalMatrix().ty).isEqualTo(20.0);

        parent.setX(50).setY(100);
        assertThat(grandGrandChild.getGlobalMatrix().tx).isEqualTo(50.0);
        assertThat(grandGrandChild.getGlobalMatrix().ty).isEqualTo(100.0);
    }
}