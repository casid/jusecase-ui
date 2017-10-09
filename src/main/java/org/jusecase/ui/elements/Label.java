package org.jusecase.ui.elements;

import org.jusecase.scenegraph.node2d.BitmapFontText;
import org.jusecase.scenegraph.node2d.Node2d;
import org.jusecase.scenegraph.node2d.Text;
import org.jusecase.ui.font.Align;
import org.jusecase.ui.font.BitmapFont;
import org.jusecase.ui.font.Font;

public class Label extends Element {

    private final Text text;
    private float verticalAlign;

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

    public float getTextWidth() {
        return text.getWidth();
    }

    public float getTextHeight() {
        return text.getHeight();
    }

    @Override
    public Node2d setWidth(float width) {
        super.setWidth(width);

        if (getAlign() == Align.CENTER) {
            text.setX(0.5f * width);
        } else if (getAlign() == Align.RIGHT) {
            text.setX(width);
        }

        return this;
    }

    @Override
    public Node2d setHeight(float height) {
        super.setHeight(height);

        text.setY(verticalAlign * (height - text.getLineHeight()));

        return this;
    }

    public void setVerticalAlign(float verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    public float getVerticalAlign() {
        return verticalAlign;
    }
}
