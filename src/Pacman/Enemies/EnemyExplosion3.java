package Pacman.Enemies;

import bases.GameObject;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

import java.awt.*;

public class EnemyExplosion3 extends GameObject {
    private Animation animation;

    public EnemyExplosion3() {

        super();

        this.animation = new Animation(
                10,
                true,
                false,
                SpriteUtils.loadImage("assets/images/enemies2/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/enemies2/explosion/1.png"),
                SpriteUtils.loadImage("assets/images/enemies2/explosion/2.png"),
                SpriteUtils.loadImage("assets/images/enemies2/explosion/3.png"),
                SpriteUtils.loadImage("assets/images/enemies2/explosion/4.png"),
                SpriteUtils.loadImage("assets/images/enemies2/explosion/5.png")
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
