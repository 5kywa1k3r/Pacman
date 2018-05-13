package Pacman.Status;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class Player1Name extends GameObject{
    public Player1Name() {
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/status/KARL.png"));
        this.position.set(1024,70);
    }
}
