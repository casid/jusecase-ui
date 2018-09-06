package org.jusecase.ui.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jusecase.ui.UiTest;
import org.jusecase.ui.input.TouchPhase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ButtonTest extends UiTest {
    Button button = new Button();

    List<Element> buttonClickEvents = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        button.setWidth(100);
        button.setHeight(40);
        button.onClick.add(e -> buttonClickEvents.add(e));
        ui.add(button);
    }

    @Test
    public void buttonClick_miss() {
        givenTouchEvent(101, 10, TouchPhase.Begin);
        givenTouchEvent(101, 10, TouchPhase.End);

        whenEventsAreProcessed();

        assertThat(buttonClickEvents).isEmpty();
    }

    @Test
    public void buttonClick_hit() {
        givenTouchEvent(60, 10, TouchPhase.Begin);
        givenTouchEvent(60, 10, TouchPhase.End);

        whenEventsAreProcessed();

        assertThat(buttonClickEvents).hasSize(1);
    }

    @Test
    public void buttonClick_hit_moveOutAndIn() {
        givenTouchEvent(60, 10, TouchPhase.Begin);
        givenTouchEvent(200, 200, TouchPhase.Move);
        givenTouchEvent(60, 10, TouchPhase.End);

        whenEventsAreProcessed();

        assertThat(buttonClickEvents).hasSize(1);
    }

    @Test
    public void buttonClick_hit_moveOutAndRelease() {
        givenTouchEvent(60, 10, TouchPhase.Begin);
        givenTouchEvent(200, 200, TouchPhase.Move);
        givenTouchEvent(200, 200, TouchPhase.End);

        whenEventsAreProcessed();

        assertThat(buttonClickEvents).isEmpty();
        assertThat(button.isPressed()).isFalse();
    }

    @Test
    public void buttonClick_multitouch() {
        givenTouchEvent(1, 60, 10, TouchPhase.Begin);
        givenTouchEvent(1, 200, 200, TouchPhase.Move);
        givenTouchEvent(2, 60, 10, TouchPhase.Begin);
        givenTouchEvent(2, 200, 200, TouchPhase.Move);
        givenTouchEvent(1, 60, 10, TouchPhase.End);
        givenTouchEvent(2, 200, 10, TouchPhase.End);

        whenEventsAreProcessed();

        assertThat(buttonClickEvents).hasSize(1);
    }

    @Test
    public void buttonDown_hit() {
        givenTouchEvent(60, 10, TouchPhase.Begin);
        whenEventsAreProcessed();
        assertThat(buttonClickEvents).isEmpty();
    }

    @Test
    public void focusList_noDanglingReferences() {
        // Cause entry in focused elements
        givenTouchEvent(60, 10, TouchPhase.Begin);
        whenEventsAreProcessed();

        // Clear all references to our button and keep a week reference for sanity checking
        WeakReference<Button> reference = new WeakReference<>(button);
        ui.remove(button);
        button = null;
        events.clear();

        System.gc();

        // UI must no longer hold a reference to the touched control (if the first assert fails the test setup is flawed)
        assertThat(reference.get()).isNull();
        thenTouchedElementsAreEmpty();
    }

    @Test
    public void buttonUp_hit() {
        givenTouchEvent(60, 10, TouchPhase.End);
        whenEventsAreProcessed();
        assertThat(buttonClickEvents).isEmpty();
    }

    @Test
    public void buttonClick_move() {
        givenTouchEvent(60, 10, TouchPhase.Move);
        whenEventsAreProcessed();
        assertThat(buttonClickEvents).isEmpty();
    }

    @Test
    public void buttonHover_miss() {
        givenTouchEvent(101, 10, TouchPhase.Hover);
        whenEventsAreProcessed();
        assertThat(button.isHovered()).isFalse();
    }

    @Test
    public void buttonHover() {
        givenTouchEvent(60, 10, TouchPhase.Hover);
        whenEventsAreProcessed();
        assertThat(button.isHovered()).isTrue();
    }

    @Test
    public void buttonHover_movedInside() {
        givenTouchEvent(60, 10, TouchPhase.Hover);
        givenTouchEvent(61, 10, TouchPhase.Hover);

        whenEventsAreProcessed();

        assertThat(button.isHovered()).isTrue();
    }

    @Test
    public void buttonHoverEnd() {
        givenTouchEvent(60, 10, TouchPhase.Hover);
        whenEventsAreProcessed();
        assertThat(button.isHovered()).isTrue();

        givenTouchEvent(101, 10, TouchPhase.Hover);
        whenEventsAreProcessed();
        assertThat(button.isHovered()).isFalse();
    }
}