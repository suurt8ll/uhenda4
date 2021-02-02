package com.connectfour;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetsLoader {
    public AssetManager manager = new AssetManager();
    public final String playButtonImg = "core/assets/play.png";
    public final String settingsButtonImg = "core/assets/settings.png";

    public void load() {
        manager.load(playButtonImg, Texture.class);
        manager.load(settingsButtonImg, Texture.class);
        manager.finishLoading();
    }

}
