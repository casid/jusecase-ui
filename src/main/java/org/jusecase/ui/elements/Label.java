package org.jusecase.ui.elements;

import org.jusecase.scenegraph.node2d.BitmapFontText;
import org.jusecase.scenegraph.node2d.Text;
import org.jusecase.ui.font.Align;
import org.jusecase.ui.font.BitmapFont;
import org.jusecase.ui.font.Font;

public class Label extends Element {

    private final Text text;

    public Label(Font font) {
        if (font instanceof BitmapFont) {
            text = new BitmapFontText((BitmapFont) font);
            add(text);
        } else {
            throw new IllegalStateException("Unsupported font " + font);
        }
    }

    public void setAlign(Align align) {
        text.setAlign(align);
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public Align getAlign() {
        return text.getAlign();
    }

    public String getText() {
        return text.getText();
    }

    @Override
    public float getWidth() {
        return text.getWidth();
    }

    @Override
    public float getHeight() {
        return text.getHeight();
    }
}
