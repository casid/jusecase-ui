package org.jusecase.ui.font;


import org.jusecase.scenegraph.texture.Texture;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BitmapFont {
    private final Map<Character, BitmapFontCharacter> characters;
    private final Map<String, Integer> kernings;
    private final Collection<Texture> textures;
    private int lineHeight;

    public BitmapFont(List<BitmapFontCharacter> characters, Map<String, Integer> kernings, Collection<Texture> textures) {
        this.characters = new HashMap<>(characters.size());
        for (BitmapFontCharacter character : characters) {
            this.characters.put(character.id, character);
        }

        this.kernings = kernings;
        this.textures = textures;
    }

    public BitmapFontCharacter getCharacter(char character) {
        return characters.get(character);
    }

    public int getKerning(char first, char second) {
        return kernings.getOrDefault("" + first + second, 0);
    }

    public void dispose() {
        textures.forEach(Texture::dispose);
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }
}
