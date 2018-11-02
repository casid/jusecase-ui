package org.jusecase.scenegraph.texture;

import java.io.InputStream;
import java.nio.file.Path;

public interface TextureLoader {
    Texture load(InputStream inputStream);

    Texture load(Path path);

    Texture load(String resource);
}
