package org.jusecase.scenegraph.texture;

import java.nio.file.Path;

public interface TextureAtlasLoader {
    TextureAtlas load(Path definitionPath);

    TextureAtlas load(String definitionResource);
}
