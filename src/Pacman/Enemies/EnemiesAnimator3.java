package Pacman.Enemies;

import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class EnemiesAnimator3 implements Renderer {

    private Animation leftAnimation = new Animation(10,false,false,
            SpriteUtils.loadImage("assets/images/enemies3/left/0.png"),
            SpriteUtils.loadImage("assets/images/enemies3/left/1.png"),
            SpriteUtils.loadImage("assets/images/enemies3/left/2.png"),
            SpriteUtils.loadImage("assets/images/enemies3/left/3.png"),
            SpriteUtils.loadImage("assets/images/enemies3/left/4.png"),
            SpriteUtils.loadImage("assets/images/enemies3/left/5.png")
    );

    private Animation rightAnimation = new Animation(10,false,false,
            SpriteUtils.loadImage("assets/images/enemies3/right/0.png"),
            SpriteUtils.loadImage("assets/images/enemies3/right/1.png"),
            SpriteUtils.loadImage("assets/images/enemies3/right/2.png"),
            SpriteUtils.loadImage("assets/images/enemies3/right/3.png"),
            SpriteUtils.loadImage("assets/images/enemies3/right/4.png"),
            SpriteUtils.loadImage("assets/images/enemies3/right/5.png")
    );

    private Animation upAnimation = new Animation(10,false,false,
            SpriteUtils.loadImage("assets/images/enemies3/up/0.png"),
            SpriteUtils.loadImage("assets/images/enemies3/up/1.png"),
            SpriteUtils.loadImage("assets/images/enemies3/up/2.png"),
            SpriteUtils.loadImage("assets/images/enemies3/up/3.png"),
            SpriteUtils.loadImage("assets/images/enemies3/up/4.png"),
            SpriteUtils.loadImage("assets/images/enemies3/up/5.png")
    );

    private Animation downAnimation = new Animation(10,false,false,
            SpriteUtils.loadImage("assets/images/enemies3/down/0.png"),
            SpriteUtils.loadImage("assets/images/enemies3/down/1.png"),
            SpriteUtils.loadImage("assets/images/enemies3/down/2.png"),
            SpriteUtils.loadImage("assets/images/enemies3/down/3.png"),
            SpriteUtils.loadImage("assets/images/enemies3/down/4.png"),
            SpriteUtils.loadImage("assets/images/enemies3/down/5.png")
    );

    private Animation currentAnimation = downAnimation;

    public void update(Enemies enemies) {
        Vector2D velocity = enemies.getVelocity();
        if (velocity.x < 0) {
            currentAnimation = leftAnimation;
        } else if(velocity.x > 0) {
            currentAnimation = rightAnimation;
        } else {
            if (velocity.y < 0)
                currentAnimation = upAnimation;
            else
                currentAnimation = downAnimation;
        }
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        currentAnimation.render(g2d, position);
    }
}
