package Pacman.Platform;

import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class Platform extends GameObject implements PhysicsBody{
    BoxCollider boxCollider;
    private boolean isBreakable;

    public Platform(){
        super();
        isBreakable = false;
        boxCollider = new BoxCollider(50,50);
        children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public void setIsBreakable(boolean isBreakable) {
        this.isBreakable = isBreakable;
    }

    public boolean isBreakable() {
        return isBreakable;
    }
}
