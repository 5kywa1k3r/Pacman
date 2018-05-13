package Pacman.scenes;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import Pacman.settings.Settings;

public class GameOverScene extends Scene {
    Settings settings = Settings.instance;
    @Override
    public void init() {
        ImageRenderer imageRenderer = ImageRenderer.create("assets/images/gameOverBackground/GameOverBackGround.png");
        GameObject background = new GameObject().setRenderer(imageRenderer);
        background.getPosition().set(settings.getGamePlayWidth()/2,settings.getGamePlayHeight()/2);
        GameObject.add(background);
    }
}
