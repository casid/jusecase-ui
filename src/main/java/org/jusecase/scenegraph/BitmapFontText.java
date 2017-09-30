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
        int x = 0;
        char previousCharacter = 0;

        int length = text.length();
        for (int i = 0; i < length; ++i) {
            char currentCharacter = text.charAt(i);
            BitmapFontCharacter character = bitmapFont.getCharacter(currentCharacter);
            if (character == null) {
                continue;
            }

            int kerning = bitmapFont.getKerning(previousCharacter, currentCharacter);

            Image image = new Image(character.texture);
            add(image.setPosition(x + kerning + character.offsetX, character.offsetY));

            x += character.advanceX + kerning;
            previousCharacter = currentCharacter;
        }
    }
}
