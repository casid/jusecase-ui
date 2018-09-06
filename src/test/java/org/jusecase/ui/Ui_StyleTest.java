package org.jusecase.ui;

import org.junit.jupiter.api.Test;
import org.jusecase.scenegraph.color.Color;
import org.jusecase.ui.elements.Button;
import org.jusecase.ui.elements.Element;
import org.jusecase.ui.style.ButtonStyle;
import org.jusecase.ui.style.Style;
import org.jusecase.ui.input.TouchPhase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class Ui_StyleTest extends UiTest {
    @Test
    public void styleAvailable() {
        ui.setDefaultStyle(Button.class, new ButtonStyle());

        Button button = new Button();
        ui.add(button);

        assertThat(button.getStyle()).isInstanceOf(ButtonStyle.class);
    }

    @Test
    public void styleIsCloned() {
        ButtonStyle style = new ButtonStyle();
        style.active.color = new Color("#333");
        ui.setDefaultStyle(Button.class, style);

        Button button = new Button();
        ui.add(button);
        button.getStyle().active.color = new Color("#FFF");

        assertThat(style.active.color).isEqualTo(new Color("#333"));
    }

    @Test
    public void styleNotAvailable() {
        Button button = new Button();
        ui.add(button);

        assertThat(button.getStyle()).isNull();
    }

    @Test
    public void styleAlreadySet() {
        ui.setDefaultStyle(Button.class, new ButtonStyle());

        Button button = new Button();
        button.setStyle(new MyButtonStyle());
        ui.add(button);

        assertThat(button.getStyle()).isInstanceOf(MyButtonStyle.class);
    }

    @Test
    public void unsupportedStyle() {
        Button button = new Button();
        Throwable throwable = catchThrowable(() -> button.setStyle(new UnsupportedStyle()));

        assertThat(throwable).isInstanceOf(IllegalStateException.class).hasMessage("Unsupported style class org.jusecase.ui.Ui_StyleTest$UnsupportedStyle");
    }

    @Test
    public void styleIsResolvedForChildrenToo() {
        ui.setDefaultStyle(Button.class, new ButtonStyle());

        Element parent = new Element();
        ui.add(parent);
        Button child = new Button();
        parent.add(child);

        assertThat(child.getStyle()).isInstanceOf(ButtonStyle.class);
    }

    @Test
    public void styleIsResolvedForChildrenToo_2() {
        ui.setDefaultStyle(Button.class, new ButtonStyle());

        Button parent = new Button();
        Button child = new Button();
        parent.add(child);
        ui.add(parent);

        assertThat(parent.getStyle()).isInstanceOf(ButtonStyle.class);
        assertThat(child.getStyle()).isInstanceOf(ButtonStyle.class);
    }

    @Test
    public void removedFromUi() {
        ui.setDefaultStyle(Button.class, new ButtonStyle());

        Button parent = new Button();
        Button child = new Button();
        parent.add(child);
        ui.add(parent);
        ui.remove(parent);

        // Styles are kept
        assertThat(parent.getStyle()).isInstanceOf(ButtonStyle.class);
        assertThat(child.getStyle()).isInstanceOf(ButtonStyle.class);

        // Ui reference not
        assertThat(ui.getUi()).isSameAs(ui);
        assertThat(parent.getUi()).isNull();
        assertThat(child.getUi()).isNull();
    }

    @Test
    public void hierarchyIsPreservedOnStyleInit() {
        ui.setDefaultStyle(Button.class, new ButtonStyle());
        Button parent = new Button();
        Button child = new Button();
        parent.add(child);
        ui.add(parent);

        assertThat(parent.getChild(parent.getChildCount() - 1)).isSameAs(child);
    }

    @Test
    public void hierarchyIsPreservedOnStyleChange() {
        ui.setDefaultStyle(Button.class, new ButtonStyle());
        Button parent = new Button();
        Button child = new Button();
        parent.add(child.setPosition(10, 10));
        ui.add(parent.setSize(10, 10));
        givenTouchEvent(1, 1, TouchPhase.Hover);

        assertThat(parent.getChild(parent.getChildCount() - 1)).isSameAs(child);

        whenEventsAreProcessed();

        assertThat(parent.getChild(parent.getChildCount() - 1)).isSameAs(child);
    }

    public static class MyButtonStyle extends ButtonStyle {
    }

    public static class UnsupportedStyle extends Style {
    }
}