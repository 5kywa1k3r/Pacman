package Pacman.maps;

import Pacman.Player.Player;
import Pacman.inputs.InputManager;
import Pacman.settings.Settings;
import bases.Constraints;
import bases.GameObject;
import Pacman.Enemies.Enemies;
import Pacman.Platform.Platform;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

import java.util.List;

public class Layer {
    private List<Integer> data;
    private int height;
    private int width;
    private int x, y  = 0;
    Settings settings = Settings.instance;

    @Override
    public String toString() {
        return "Layer{" +
                "data=" + data +
                ", height=" + height +
                ", width=" + width +
                '}';
    }

    public void generate() {
        x = 33; y = 45;
        for (int tileY = 0; tileY < height; tileY++){
            for (int tileX = 0; tileX < width; tileX++){
                int mapData = data.get(tileY * width + tileX);
                if (mapData != 0){
                    Platform platform = new Platform();
                    platform.getPosition().set(tileX * 50 + x, tileY * 50 + y);
                    switch (mapData){
                        case 1:
                            platform.setRenderer(new ImageRenderer(SpriteUtils.loadImage( "assets/images/map/Wall.bmp")));
                            platform.setIsBreakable(false);
                            GameObject.add(platform);
                            break;
                        case 2:
                            Player player = new Player();
                            player.setInputManager(InputManager.instance);
                            player.setContraints(new Constraints(
                                    settings.getWindowInsets().top,
                                    settings.getGamePlayHeight(),
                                    settings.getWindowInsets().left,
                                    settings.getGamePlayWidth())
                            );
                            player.getPosition().set(tileX * 50 + x, tileY * 50 + y);
                            GameObject.add(player);
                            break;
                        case 3:
//                            if (settings.getCREPNUMBER() > 0) break;
                            Enemies enemies = new Enemies();
                            enemies.getPosition().set(tileX * 50 + x, tileY * 50 + y);
                            GameObject.add(enemies);
                            settings.setCREPNUMBER(settings.getCREPNUMBER()+1);
                            break;
                        case 4:
                            platform.setRenderer(new ImageRenderer(SpriteUtils.loadImage( "assets/images/map/Brick.bmp")));
                            platform.setIsBreakable(true);
//                            GameObject.add(platform);
                            break;
                        case 5:
//                            Player player2 = new Player();
//                            player2.setInputManager(InputManager.instance);
//                            player2.setContraints(new Constraints(
//                                    settings.getWindowInsets().top,
//                                    settings.getGamePlayHeight(),
//                                    settings.getWindowInsets().left,
//                                    settings.getGamePlayWidth())
//                            );
//                            player2.getPosition().set(tileX * 50 + x, tileY * 50 + y);
//                            GameObject.add(player2);
                        default:
                            break;
                    }
                }
            }
        }
    }
}
