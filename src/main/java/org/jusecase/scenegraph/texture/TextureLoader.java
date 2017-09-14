package org.jusecase.scenegraph.texture;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface TextureLoader {
    Texture load(InputStream inputStream) throws IOException;

    Texture load(Path path) throws IOException;

    Texture load(String resource) throws IOException;
}
