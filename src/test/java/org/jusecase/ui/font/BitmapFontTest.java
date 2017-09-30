package org.jusecase.ui.font;

import org.junit.jupiter.api.Test;
import org.jusecase.scenegraph.texture.TextureMock;

import static org.jusecase.Builders.a;
import static org.jusecase.Builders.list;
import static org.jusecase.Builders.map;


class BitmapFontTest {
    TextureMock textureMock = new TextureMock();

    @Test
    void dispose() {
        BitmapFont bitmapFont = new BitmapFont(a(list()), a(map()), a(list(textureMock)));

        bitmapFont.dispose();

        textureMock.thenTextureIsDisposed();
    }
}