package org.jusecase.ui.style;

import org.jusecase.scenegraph.color.Color;
import org.jusecase.scenegraph.texture.Texture;
import org.jusecase.ui.font.Font;

public class ButtonStyle extends Style {

    public State active = new State();
    public State pressed = new State();
    public State hovered = new State();

    @Override
    public Style clone() {
        ButtonStyle clone = (ButtonStyle) super.clone();
        clone.active = active.clone();
        clone.pressed = pressed.clone();
        clone.hovered = hovered.clone();
        return clone;
    }

    public static class State implements Cloneable {
        public Color color = new Color(1.0f);
        public Texture texture;
        public Font font;

        @Override
        public State clone() {
            try {
                State clone = (State) super.clone();
                clone.color = color.clone();
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
