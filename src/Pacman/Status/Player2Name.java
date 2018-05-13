package Pacman.Status;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class Player2Name extends GameObject{
    public Player2Name() {
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/status/KARL.png"));
        this.position.set(1024,70);
    }
}
