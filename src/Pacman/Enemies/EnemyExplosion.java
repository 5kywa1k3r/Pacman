package Pacman.Enemies;

import bases.GameObject;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

import java.awt.*;

public class EnemyExplosion extends GameObject {
    private Animation animation;

    public EnemyExplosion() {

        super();

        this.animation = new Animation(
                10,
                true,
                false,
                SpriteUtils.loadImage("assets/images/enemies/explosion/dead.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/dead.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/dead.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/dead.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/dead.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/dead.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/dead.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/dead.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/dead.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/dead.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/dead.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/dead.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/1.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/2.png")
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
