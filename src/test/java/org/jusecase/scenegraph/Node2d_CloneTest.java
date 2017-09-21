package org.jusecase.scenegraph;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Node2d_CloneTest {
    @Test
    public void name() {
        Node2d node2d = new Node2d();

        Node2d clone = node2d.clone();

        assertThat(clone.getLocalMatrix()).isNotSameAs(node2d.getLocalMatrix());
        assertThat(clone.getGlobalMatrix()).isNotSameAs(node2d.getGlobalMatrix());
        assertThat(clone.getGlobalMatrixInverse()).isNotSameAs(node2d.getGlobalMatrixInverse());
    }
}