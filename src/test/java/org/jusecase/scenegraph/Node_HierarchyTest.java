package org.jusecase.scenegraph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jusecase.ui.UiTest;
import org.jusecase.ui.elements.Button;

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

    @Test
    void getSibling() {
        assertThat(parent.getSibling(child, 0)).isSameAs(child);
        assertThat(parent.getSibling(child, 1)).isNull();
        assertThat(parent.getSibling(child, -1)).isNull();
    }

    @Test
    void getSibling_typed() {
        Button button1 = new Button();
        Button button2 = new Button();
        Button button3 = new Button();
        parent.add(button1);
        parent.add(button2);
        parent.add(new Node());
        parent.add(button3);

        assertThat(parent.getSibling(button1, 0, Button.class)).isSameAs(button1);
        assertThat(parent.getSibling(button1, 1, Button.class)).isSameAs(button2);
        assertThat(parent.getSibling(button1, 2, Button.class)).isSameAs(button3);
        assertThat(parent.getSibling(button1, -1, Button.class)).isNull();
        assertThat(parent.getSibling(button2, -1, Button.class)).isSameAs(button1);
        assertThat(parent.getSibling(button3, 1, Button.class)).isNull();
    }
}