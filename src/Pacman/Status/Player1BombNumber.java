package Pacman.Status;

import Pacman.Player.Player;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class Player1BombNumber extends GameObject{
    public Player1BombNumber() {
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/status/bomb.png"));
        this.position = new Vector2D(1000,140);
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        Player player = Player.getInstance();
        for (int i=0; i<player.getTrapNum(); ++i){
            renderer.render(g2d,this.position.add(i * 20,0));
        }
    }
}
