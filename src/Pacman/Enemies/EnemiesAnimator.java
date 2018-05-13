package Pacman.Enemies;

import Pacman.Player.Player;
import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class EnemiesAnimator implements Renderer {

    private Animation leftAnimation = new Animation(20,false,false,
            SpriteUtils.loadImage("assets/images/enemies/left/0.png"),
            SpriteUtils.loadImage("assets/images/enemies/left/1.png"),
            SpriteUtils.loadImage("assets/images/enemies/left/2.png")
    );

    private Animation rightAnimation = new Animation(20,false,false,
            SpriteUtils.loadImage("assets/images/enemies/right/0.png"),
            SpriteUtils.loadImage("assets/images/enemies/right/1.png"),
            SpriteUtils.loadImage("assets/images/enemies/right/2.png")
    );

    private Animation upAnimation = new Animation(20,false,false,
            SpriteUtils.loadImage("assets/images/enemies/up/0.png"),
            SpriteUtils.loadImage("assets/images/enemies/up/1.png"),
            SpriteUtils.loadImage("assets/images/enemies/up/2.png")
    );

    private Animation downAnimation = new Animation(20,false,false,
            SpriteUtils.loadImage("assets/images/enemies/down/0.png"),
            SpriteUtils.loadImage("assets/images/enemies/down/1.png"),
            SpriteUtils.loadImage("assets/images/enemies/down/2.png")
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
