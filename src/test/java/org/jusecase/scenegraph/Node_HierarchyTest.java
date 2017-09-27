package org.jusecase.scenegraph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jusecase.ui.UiTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class Node_HierarchyTest extends UiTest {

    Node parent = new Node();
    Node child = new Node();


    @BeforeEach
    public void setUp() {
        ui.add(parent);
        parent.add(child);
    }

    @Test
    public void parent() {
        assertThat(child.getParent()).isSameAs(parent);
    }

    @Test
    public void parent_removeChild() {
        parent.remove(child);
        assertThat(child.getParent()).isNull();
    }

    @Test
    public void removeFromParent() {
        child.removeFromParent();
        assertThat(child.getParent()).isNull();
        assertThat(parent.getChildren()).isEmpty();
    }

    @Test
    public void childrenReferenceNotModifiable() {
        Throwable throwable = catchThrowable(() -> parent.getChildren().remove(child));
        assertThat(throwable).isInstanceOf(UnsupportedOperationException.class);
        assertThat(parent.getChildren()).contains(child);
    }

    @Test
    public void getChildAt() {
        Throwable throwable = catchThrowable(() -> child.getChild(0));
        assertThat(throwable).isInstanceOf(IndexOutOfBoundsException.class).hasMessage("Index: 0, Size: 0");
    }
}