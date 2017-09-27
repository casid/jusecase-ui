package org.jusecase.scenegraph;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Quad_CloneTest {
    @Test
    public void name() {
        Quad quad = new Quad();

        Quad clone = quad.clone();

        assertThat(clone.getColor()).isNotSameAs(quad.getColor());
        assertThat(clone.getColor()).isEqualTo(quad.getColor());
    }
}