package Pacman.Status;

import Pacman.Player.Player;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class Player1BombLength extends GameObject{
    public Player1BombLength() {
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/status/fire.png"));
        this.position = new Vector2D(1000,200);
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        Player player = Player.getInstance();
        for (int i=0; i<player.getTrapLength()/5; ++i){
            renderer.render(g2d,this.position.add(i * 20,0));
        }
    }
}
