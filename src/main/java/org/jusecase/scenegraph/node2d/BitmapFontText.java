package org.jusecase.scenegraph.node2d;

import org.jusecase.ui.font.Align;
import org.jusecase.ui.font.BitmapFont;
import org.jusecase.ui.font.BitmapFontCharacter;

public class BitmapFontText extends Text {
    private final BitmapFont bitmapFont;


    public BitmapFontText(BitmapFont bitmapFont) {
        this.bitmapFont = bitmapFont;
    }

    @Override
    protected void updateLayout() {
        removeAll();

        if (text == null) {
            return;
        }

        Node2d line = addLine();

        int x = 0;
        int y = 0;
        int maxX = 0;
        char previousCharacter = 0;

        int length = text.length();
        for (int i = 0; i < length; ++i) {
            char currentCharacter = text.charAt(i);
            int kerning = bitmapFont.getKerning(previousCharacter, currentCharacter);
            previousCharacter = currentCharacter;

            if (currentCharacter == '\n') {
                line = addLine();
                x = 0;
                y += bitmapFont.getLineHeight();
            } else {
                BitmapFontCharacter character = bitmapFont.getCharacter(currentCharacter);
                if (character != null) {
                    Image image = new Image(character.texture);
                    line.add(image.setPosition(x + kerning + character.offsetX, y + character.offsetY));

                    x += character.advanceX + kerning;
                    if (x > maxX) {
                        maxX = x;
                        line.setWidth(maxX);
                    }
                }
            }
        }

        setSize(maxX, y + bitmapFont.getLineHeight());
    }

    private Node2d addLine() {
        Node2d line = new Node2d();
        if (align == Align.RIGHT) {
            line.setPivotX(1);
        } else if (align == Align.CENTER) {
            line.setPivotX(0.5f);
        }
        add(line);
        return line;
    }
}
