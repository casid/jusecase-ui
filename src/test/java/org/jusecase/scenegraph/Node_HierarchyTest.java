package org.jusecase.scenegraph;

import org.junit.Before;
import org.junit.Test;
import org.jusecase.ui.UiTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class Node_HierarchyTest extends UiTest {

    Node parent = new Node();
    Node child = new Node();


    @Before
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

    @Test(expected = IndexOutOfBoundsException.class)
    public void getChildAt() {
        child.getChild(0);
    }
}