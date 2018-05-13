package Pacman.Player;

import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class PlayerAnimator implements Renderer {
    String str = "assets/images/player";
    private String playerId;
    public PlayerAnimator() {
    }

    private Animation noneMoveAnimation = new Animation(30,false,false,
            SpriteUtils.loadImage("assets/images/player/noneMove/0.png"),
            SpriteUtils.loadImage("assets/images/player/noneMove/1.png"),
            SpriteUtils.loadImage("assets/images/player/noneMove/2.png"),
            SpriteUtils.loadImage("assets/images/player/noneMove/3.png")
    );

    private Animation leftAnimation = new Animation(
            SpriteUtils.loadImage("assets/images/player/left/0.png"),
            SpriteUtils.loadImage("assets/images/player/left/1.png"),
            SpriteUtils.loadImage("assets/images/player/left/2.png"),
            SpriteUtils.loadImage("assets/images/player/left/3.png"),
            SpriteUtils.loadImage("assets/images/player/left/4.png")
    );

    private Animation rightAnimation = new Animation(
            SpriteUtils.loadImage("assets/images/player/right/0.png"),
            SpriteUtils.loadImage("assets/images/player/right/1.png"),
            SpriteUtils.loadImage("assets/images/player/right/2.png"),
            SpriteUtils.loadImage("assets/images/player/right/3.png"),
            SpriteUtils.loadImage("assets/images/player/right/4.png")
    );

    private Animation upAnimation = new Animation(8,false,false,
            SpriteUtils.loadImage("assets/images/player/up/0.png"),
            SpriteUtils.loadImage("assets/images/player/up/1.png"),
            SpriteUtils.loadImage("assets/images/player/up/2.png"),
            SpriteUtils.loadImage("assets/images/player/up/3.png")
    );

    private Animation downAnimation = new Animation(8,false,false,
            SpriteUtils.loadImage("assets/images/player/down/0.png"),
            SpriteUtils.loadImage("assets/images/player/down/1.png"),
            SpriteUtils.loadImage("assets/images/player/down/2.png"),
            SpriteUtils.loadImage("assets/images/player/down/3.png"),
            SpriteUtils.loadImage("assets/images/player/down/4.png")
    );

    private Animation currentAnimation = noneMoveAnimation;

    public void update(Player player) {
        Vector2D velocity = player.getVelocity();
        if (velocity.x == 0 && velocity.y == 0){
            currentAnimation = noneMoveAnimation;

        }
        else{
            if (velocity.x < 0) {
                currentAnimation = leftAnimation;
            } else if (velocity.x > 0) {
                currentAnimation = rightAnimation;
            } else {
                if (velocity.y < 0)
                    currentAnimation = upAnimation;
                else
                    currentAnimation = downAnimation;
            }
        }
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        Player player = Player.getInstance();
        if (!player.isAlreadyHit() || (System.currentTimeMillis()%5 == 0))
        currentAnimation.render(g2d, position);
    }
}
