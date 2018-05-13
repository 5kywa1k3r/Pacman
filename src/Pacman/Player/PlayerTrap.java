package Pacman.Player;

import Pacman.DeadBox.DeadBox;
import Pacman.Platform.Platform;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

public class PlayerTrap extends GameObject implements PhysicsBody{
    private FrameCounter explosionDelay;
    private BoxCollider boxCollider;
    private int size;
    private boolean khongTheDiXuyen;

    public PlayerTrap() {
        super();
        khongTheDiXuyen = false;
        this.size = size;
        this.boxCollider = new BoxCollider(48,48);
        this.children.add(boxCollider);
        explosionDelay = new FrameCounter(180);
        this.renderer = new Animation(20,false,false,
                SpriteUtils.loadImage("assets/images/trap/0.png"),
                SpriteUtils.loadImage("assets/images/trap/1.png"),
                SpriteUtils.loadImage("assets/images/trap/2.png"),
                SpriteUtils.loadImage("assets/images/trap/3.png"),
                SpriteUtils.loadImage("assets/images/trap/4.png"),
                SpriteUtils.loadImage("assets/images/trap/5.png"),
                SpriteUtils.loadImage("assets/images/trap/6.png"),
                SpriteUtils.loadImage("assets/images/trap/7.png"),
                SpriteUtils.loadImage("assets/images/trap/8.png")
        );
    }

    @Override
    public void reset() {
        super.reset();
        explosionDelay.reset();
        this.renderer = new Animation(20,false,false,
                SpriteUtils.loadImage("assets/images/trap/0.png"),
                SpriteUtils.loadImage("assets/images/trap/1.png"),
                SpriteUtils.loadImage("assets/images/trap/2.png"),
                SpriteUtils.loadImage("assets/images/trap/3.png"),
                SpriteUtils.loadImage("assets/images/trap/4.png"),
                SpriteUtils.loadImage("assets/images/trap/5.png"),
                SpriteUtils.loadImage("assets/images/trap/6.png"),
                SpriteUtils.loadImage("assets/images/trap/7.png"),
                SpriteUtils.loadImage("assets/images/trap/8.png")
        );;
    }

    @Override
    public void run(Vector2D parentPosition) {
        Player player = Physics.collideWith(this.screenPosition,boxCollider.getWidth(),boxCollider.getHeight(), Player.class);
        if (player == null){
            khongTheDiXuyen = true;
        }
        super.run(parentPosition);
        DeadBox deadBox = Physics.collideWith(this.position,48,48,DeadBox.class);
        if (explosionDelay.run() || deadBox != null){
            this.setActive(false);
            player = Player.getInstance();
            player.setTrapNum(player.getTrapNum() + 1);
            createDeadBox();
        }
    }

    private void createDeadBox() {
        DeadBox deadBox;
        deadBox = GameObjectPool.recycle(DeadBox.class);
        deadBox.setPosition(this.position);
        deadBox.setNewDownDeadBox(true);
        deadBox.setNewUpDeadBox(true);
        deadBox.setNewLeftDeadBox(true);
        deadBox.setNewRigthDeadBox(true);
        deadBox.setSize(size);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public boolean isKhongTheDiXuyen() {
        return khongTheDiXuyen;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
