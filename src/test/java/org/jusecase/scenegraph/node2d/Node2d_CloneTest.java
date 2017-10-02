package org.jusecase.scenegraph.node2d;

import org.junit.jupiter.api.Test;
import org.jusecase.scenegraph.node2d.Node2d;

import static org.assertj.core.api.Assertions.assertThat;

public class Node2d_CloneTest {
    @Test
    public void deepCopies() {
        Node2d node2d = new Node2d();

        Node2d clone = node2d.clone();

        assertThat(clone.getLocalMatrix()).isNotSameAs(node2d.getLocalMatrix());
        assertThat(clone.getLocalMatrix()).isEqualTo(node2d.getLocalMatrix());

        assertThat(clone.getGlobalMatrix()).isNotSameAs(node2d.getGlobalMatrix());
        assertThat(clone.getGlobalMatrix()).isEqualTo(node2d.getGlobalMatrix());

        assertThat(clone.getGlobalMatrixInverse()).isNotSameAs(node2d.getGlobalMatrixInverse());
        assertThat(clone.getGlobalMatrixInverse()).isEqualTo(node2d.getGlobalMatrixInverse());
    }
}