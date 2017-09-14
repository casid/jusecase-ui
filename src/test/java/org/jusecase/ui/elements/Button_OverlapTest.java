package org.jusecase.ui.elements;

import org.junit.Before;
import org.junit.Test;
import org.jusecase.ui.UiTest;
import org.jusecase.ui.touch.TouchPhase;

import static org.assertj.core.api.Assertions.assertThat;

public class Button_OverlapTest extends UiTest {
    Button button1 = new Button();
    Button button2 = new Button();

    @Before
    public void setUp() {
        ui.add(button1.setX(100).setY(100).setWidth(200).setHeight(50));
        ui.add(button2.setX(120).setY(120).setWidth(200).setHeight(50));
    }

    @Test
    public void dragToOtherButtonAndRelease() {
        givenTouchEvent(105, 105, TouchPhase.Begin); // Button 1 will be pressed
        givenTouchEvent(125, 125, TouchPhase.Move); // Move over to button 2, button 1 will still be pressed
        givenTouchEvent(125, 125, TouchPhase.End); // Release over button 2

        whenTouchEventsAreProcessed();

        assertThat(button1.isPressed()).isFalse();
    }

    @Test
    public void hoverToOtherButton() {
        givenTouchEvent(105, 105, TouchPhase.Hover);
        givenTouchEvent(125, 125, TouchPhase.Hover);

        whenTouchEventsAreProcessed();

        assertThat(button1.isHovered()).isFalse();
        assertThat(button2.isHovered()).isTrue();
    }

    @Test
    public void hoverToOtherButtonAndHoverThereAgain() {
        givenTouchEvent(105, 105, TouchPhase.Hover);
        givenTouchEvent(125, 125, TouchPhase.Hover);
        givenTouchEvent(126, 125, TouchPhase.Hover);

        whenTouchEventsAreProcessed();

        assertThat(button1.isHovered()).isFalse();
        assertThat(button2.isHovered()).isTrue();
    }

}