package org.jusecase.scenegraph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jusecase.scenegraph.texture.TextureMock;
import org.jusecase.ui.UiTest;
import org.jusecase.ui.font.BitmapFont;
import org.jusecase.ui.font.BitmapFontCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jusecase.Builders.a;
import static org.jusecase.Builders.list;
import static org.jusecase.Builders.map;

class BitmapFontTextTest extends UiTest {

    BitmapFontText text;
    private BitmapFont font;

    @BeforeEach
    void setUp() {
        givenDefaultFont();
    }

    @Test
    void emptyFont() {
        givenEmptyFont();
        whenTextIsSet("foo");
        thenImageCountIs(0);
    }

    @Test
    void oneChar() {
        whenTextIsSet("A");

        thenImageCountIs(1);
        thenNodeIsAt(getImage(0), 0, 0, 10, 20);
    }

    @Test
    void twoChars() {
        whenTextIsSet("ABA");

        thenImageCountIs(3);
        thenNodeIsAt(getImage(0), 0, 0, 10, 20); //  - A = 0 kerning
        thenNodeIsAt(getImage(1), 10 - 2 + 1, -1, 20, 20); // A - B = -2 kerning + offset(1, -1)
        thenNodeIsAt(getImage(2), 10 - 2 + 15, 0, 10, 20); // B - A = 0 kerning
    }

    private void givenEmptyFont() {
        font = new BitmapFont(a(list()), a(map()), null);
    }

    private void givenDefaultFont() {
        List<BitmapFontCharacter> characterList = new ArrayList<>();
        BitmapFontCharacter bitmapFontCharacter;
        TextureMock texture;

        bitmapFontCharacter = new BitmapFontCharacter();
        bitmapFontCharacter.id = 'A';
        texture = new TextureMock();
        texture.givenSize(10, 20);
        bitmapFontCharacter.texture = texture;
        bitmapFontCharacter.advanceX = 10;
        characterList.add(bitmapFontCharacter);

        bitmapFontCharacter = new BitmapFontCharacter();
        bitmapFontCharacter.id = 'B';
        texture = new TextureMock();
        texture.givenSize(20, 20);
        bitmapFontCharacter.advanceX = 15;
        bitmapFontCharacter.offsetX = +1;
        bitmapFontCharacter.offsetY = -1;
        bitmapFontCharacter.texture = texture;
        characterList.add(bitmapFontCharacter);

        Map<String, Integer> kernings = new HashMap<>();
        kernings.put("AB", -2);

        font = new BitmapFont(characterList, kernings, null);
    }

    private void whenTextIsSet(String text) {
        this.text = new BitmapFontText(font);
        this.text.setText(text);
    }

    private void thenImageCountIs(int expected) {
        assertThat(text.getChildCount()).isEqualTo(expected);
    }

    private Image getImage(int index) {
        return (Image) text.getChild(index);
    }
}