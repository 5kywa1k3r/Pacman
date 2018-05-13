package Pacman.Player;

import bases.GameObject;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

import java.awt.*;

public class PlayerExplosion extends GameObject {
    private Animation animation;

    public PlayerExplosion() {

        super();

        this.animation = new Animation(
                10,
                true,
                false,
                SpriteUtils.loadImage("assets/images/player/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/player/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/player/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/player/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/player/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/player/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/player/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/player/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/player/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/player/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/player/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/player/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/player/explosion/1.png"),
                SpriteUtils.loadImage("assets/images/player/explosion/2.png")
        );

        this.renderer = animation;
    }

    @Override
    public void reset() {
        super.reset();
        animation.reset();
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);

        if (animation.isStopped()) {
            this.isActive = false;
        }
    }
}
