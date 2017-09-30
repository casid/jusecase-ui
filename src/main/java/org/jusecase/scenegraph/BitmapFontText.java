package org.jusecase.scenegraph;

import org.jusecase.ui.font.BitmapFont;
import org.jusecase.ui.font.BitmapFontCharacter;

public class BitmapFontText extends Node2d {
    private final BitmapFont bitmapFont;
    private String text;

    public BitmapFontText(BitmapFont bitmapFont) {
        this.bitmapFont = bitmapFont;
    }

    public void setText(String text) {
        this.text = text;
        rebuildImages();
    }

    public String getText() {
        return text;
    }

    private void rebuildImages() {
        removeAll();

        int x = 0;
        int y = 0;
        char previousCharacter = 0;

        int length = text.length();
        for (int i = 0; i < length; ++i) {
            char currentCharacter = text.charAt(i);
            int kerning = bitmapFont.getKerning(previousCharacter, currentCharacter);
            previousCharacter = currentCharacter;

            if (currentCharacter == '\n') {
                x = 0;
                y += bitmapFont.getLineHeight();
            } else {
                BitmapFontCharacter character = bitmapFont.getCharacter(currentCharacter);
                if (character != null) {
                    Image image = new Image(character.texture);
                    add(image.setPosition(x + kerning + character.offsetX, y + character.offsetY));

                    x += character.advanceX + kerning;
                }
            }
        }
    }
}
