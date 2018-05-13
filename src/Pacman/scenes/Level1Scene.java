package Pacman.scenes;

import Pacman.Status.Player1BombLength;
import Pacman.Status.Player1BombNumber;
import Pacman.Status.Player1Name;
import Pacman.Status.Player1Speed;
import Pacman.maps.Map;
import bases.GameObject;
import Pacman.settings.Settings;


public class Level1Scene extends Scene {
    Settings settings = Settings.instance;

    @Override
    public void init() {
        settings.setCREPNUMBER(0);
        settings.setCurrentLevel(1);
        addBackground();
        addPlatform();
        addPlayerStatus();
    }

    private void addPlayerStatus() {
        Player1Name player1Name = new Player1Name();
        Player1BombLength player1BombLength = new Player1BombLength();
        Player1BombNumber player1BombNumber = new Player1BombNumber();
        Player1Speed player1Speed = new Player1Speed();
        GameObject.add(player1BombNumber);
        GameObject.add(player1BombLength);
        GameObject.add(player1Speed);
        GameObject.add(player1Name);
    }

    private void addPlatform() {
        Map map = Map.load("assets/images/map/Stage_1.json");
        map.generate();
    }

    private void addBackground() {
        GameObject.add(new Background());
    }

}
