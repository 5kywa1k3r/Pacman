package Pacman.DeadBox;

import Pacman.Platform.Platform;
import Pacman.Player.Item.Item;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.actions.Action;
import bases.actions.ActionRepeatForever;
import bases.actions.SequenceAction;
import bases.actions.WaitAction;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import bases.renderers.ImageRenderer;
import bases.renderers.Renderer;
import tklibs.SpriteUtils;


public class DeadBox extends GameObject implements PhysicsBody{
    private static final int boxSize = 30;
    private BoxCollider boxCollider;
    private int damage;
    private FrameCounter timeToDisappear;
    private boolean newRigthDeadBox, newLeftDeadBox, newUpDeadBox, newDownDeadBox;
    private int size;
    private FrameCounter createNewDeadBoxTime;
    private static final int bombSize = 49;

    public DeadBox() {
        super();
        this.boxCollider = new BoxCollider(boxSize,boxSize);
        this.createNewDeadBoxTime = new FrameCounter(0);
        this.children.add(boxCollider);
        this.damage = 1;
        this.size = size;
        this.timeToDisappear = new FrameCounter(70);
        this.renderer = new Animation(
                SpriteUtils.loadImage("assets/images/deadbox/mid.png")
//                SpriteUtils.loadImage("assets/images/deadbox/1.png"
        );
        this.newDownDeadBox = false;
        this.newLeftDeadBox = false;
        this.newRigthDeadBox = false;
        this.newUpDeadBox = false;
    }

    @Override
    public void reset() {
        super.reset();
        this.newDownDeadBox = false;
        this.newLeftDeadBox = false;
        this.newRigthDeadBox = false;
        this.newUpDeadBox = false;
        this.renderer = new Animation(
                SpriteUtils.loadImage("assets/images/deadbox/mid.png")
//                SpriteUtils.loadImage("assets/images/deadbox/1.png"
        );
        timeToDisappear.reset();
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (this.size > 0 && createNewDeadBoxTime.run()){
            createNewDeadBox();
            this.size--;
        }
        if (timeToDisappear.run()){
            setActive(false);
        }
    }

    private void createNewDeadBox() {
        boolean check = newLeftDeadBox && newRigthDeadBox && newDownDeadBox && newUpDeadBox;
        if(newRigthDeadBox) {
            setBox(bombSize,0,0);
        }
        if (newLeftDeadBox) {
            setBox(-bombSize,0,1);
        }
        if (newUpDeadBox) {
            setBox(0,bombSize,2);
        }
        if (newDownDeadBox) {
            setBox(0,-bombSize,3);
        }
    }

    private void setBox(int x, int y, int boxDirection) {
        //1: right, 2: left, 3: up, 4: down
        DeadBox deadBoxA;
        deadBoxA = GameObjectPool.recycle(DeadBox.class);
        deadBoxA.getPosition().set(this.screenPosition.add(x, y));
        deadBoxA.setSize(size-1);
        deadBoxA.setTimeToDisappear(new FrameCounter(this.timeToDisappear.getCountMax() - 1));
        if (boxDirection < 2) deadBoxA.setRenderer(new ImageRenderer(SpriteUtils.loadImage("assets/images/deadbox/l&r.png")));
        else deadBoxA.setRenderer(new ImageRenderer(SpriteUtils.loadImage("assets/images/deadbox/up.png")));
        boolean check = checkDeadBox(deadBoxA.getPosition());
        if (!check) {
            deadBoxA.setActive(false);
            return;
        }
        DeadBox deadBox = Physics.collideWith(deadBoxA.getPosition(),boxCollider.getWidth(),boxCollider.getHeight(),DeadBox.class);
        if (deadBox != null){

        }
        switch (boxDirection) {
            case 0:
                deadBoxA.setNewRigthDeadBox(true);
                break;
            case 1:
                deadBoxA.setNewLeftDeadBox(true);
                break;
            case 2:
                deadBoxA.setNewUpDeadBox(true);
                break;
            case 3:
                deadBoxA.setNewDownDeadBox(true);
                break;
        }
    }

    private boolean checkDeadBox(Vector2D checkPosition) {
        Platform platform = Physics.collideWith(checkPosition,boxCollider.getWidth(),boxCollider.getHeight(), Platform.class);
        if(platform != null){
            if (platform.isBreakable()) {
                Action breakBrickAction = new Action() {
                    @Override
                    public boolean run(GameObject owner) {
                        platform.setActive(false);
                        Item item = GameObjectPool.recycle(Item.class);
                        if (item.isActive()) {
                            item.setPosition(platform.getPosition());
                            GameObject.add(item);
                        }
                        return true;
                    }

                    @Override
                    public void reset() {

                    }
                };
                Action action = new SequenceAction(
                        new WaitAction(70),
                        breakBrickAction
                );
                this.addAction(new ActionRepeatForever(action));

            }
            return false;
        }
        return true;
    }

    public void setTimeToDisappear(FrameCounter timeToDisappear) {
        this.timeToDisappear = timeToDisappear;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setNewRigthDeadBox(boolean newRigthDeadBox) {
        this.newRigthDeadBox = newRigthDeadBox;
    }

    public void setNewLeftDeadBox(boolean newLeftDeadBox) {
        this.newLeftDeadBox = newLeftDeadBox;
    }

    public void setNewUpDeadBox(boolean newUpDeadBox) {
        this.newUpDeadBox = newUpDeadBox;
    }

    public void setNewDownDeadBox(boolean newDownDeadBox) {
        this.newDownDeadBox = newDownDeadBox;
    }
}
