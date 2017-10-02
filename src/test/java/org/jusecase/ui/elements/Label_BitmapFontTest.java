package org.jusecase.ui.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jusecase.scenegraph.node2d.Image;
import org.jusecase.scenegraph.math.Vector2;
import org.jusecase.scenegraph.texture.TextureMock;
import org.jusecase.ui.UiTest;
import org.jusecase.ui.font.Align;
import org.jusecase.ui.font.BitmapFont;
import org.jusecase.ui.font.BitmapFontCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jusecase.Builders.*;

class Label_BitmapFontTest extends UiTest {

    BitmapFont font;
    Align align = Align.LEFT;

    Label text;

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
    void textIsReset() {
        whenTextIsSet("A");
        whenTextIsSet("B");

        thenImageCountIs(1);
    }

    @Test
    void kerningAndOffset() {
        whenTextIsSet("ABA");

        thenImageCountIs(3);
        thenNodeIsAt(getImage(0), 0, 0, 10, 20); //  - A = 0 kerning
        thenNodeIsAt(getImage(1), 10 - 2 + 1, -1, 20, 20); // A - B = -2 kerning + offset(1, -1)
        thenNodeIsAt(getImage(2), 10 - 2 + 15, 0, 10, 20); // B - A = 0 kerning
    }

    @Test
    void size_empty() {
        whenTextIsSet("");
        thenSizeIs(0, 14);
    }

    @Test
    void size_oneLine() {
        whenTextIsSet("AAA");
        thenSizeIs(30, 14);
    }

    @Test
    void size_twoLines() {
        whenTextIsSet("AAA\nAAAA\nA");
        thenSizeIs(40, 3 * 14);
    }

    @Test
    void lineBreak() {
        whenTextIsSet("A\nA");

        thenImageCountIs(2);
        thenNodeIsAt(getImage(1), 0, 14, 10, 20);
    }

    @Test
    void align_right() {
        align = Align.RIGHT;

        whenTextIsSet("A");

        thenImageCountIs(1);
        thenNodeIsAt(getImage(0), -10, 0, 10, 20);
    }

    @Test
    void align_center() {
        align = Align.CENTER;

        whenTextIsSet("A");

        thenImageCountIs(1);
        thenNodeIsAt(getImage(0), -5, 0, 10, 20);
    }

    @Test
    void align_multipleLines() {
        align = Align.CENTER;

        whenTextIsSet("A\nAA");

        thenImageCountIs(3);
        thenNodeIsAt(getImage(0), -5, 0, 10, 20);
        thenNodeIsAt(getImage(1), -10, 14, 10, 20);
    }

    @Test
    void align_change() {
        align = Align.RIGHT;

        whenTextIsSet("A");
        text.setAlign(Align.CENTER);

        thenImageCountIs(1);
        thenNodeIsAt(getImage(0), -5, 0, 10, 20);
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
        font.setLineHeight(14);
    }

    private void whenTextIsSet(String text) {
        if (this.text == null) {
            this.text = new Label(font);
            this.text.setAlign(align);
        }
        this.text.setText(text);
    }

    private void thenImageCountIs(int expected) {
        assertThat(getImages()).hasSize(expected);
    }

    private void thenSizeIs(int width, int height) {
        assertThat(text.getWidth()).isEqualTo(width);
        assertThat(text.getHeight()).isEqualTo(height);
    }

    private void thenNodeIsAt(Image node, float x, float y, float w, float h) {
        Vector2 pos = node.getGlobalMatrix().transformPoint(0, 0);

        assertThat(pos.x).describedAs("x").isEqualTo(x);
        assertThat(pos.y).describedAs("y").isEqualTo(y);
        assertThat(node.getWidth()).describedAs("w").isEqualTo(w);
        assertThat(node.getHeight()).describedAs("h").isEqualTo(h);
    }

    private Image getImage(int index) {
        return getImages().get(index);
    }

    private List<Image> getImages() {
        List<Image> images = new ArrayList<>();
        text.visitBottomUpChildren(Image.class, images::add);
        return images;
    }
}