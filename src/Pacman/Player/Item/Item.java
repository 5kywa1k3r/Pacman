package Pacman.Player.Item;

import Pacman.Player.Player;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

import java.util.Random;

public class Item extends GameObject implements PhysicsBody {
    private int typeItem = 3;
    private BoxCollider boxCollider;

    public Item() {
        super();
        this.boxCollider = new BoxCollider(50,50);
        this.children.add(boxCollider);
        Random random = new Random();
        typeItem = random.nextInt(5);
        switch (typeItem){
            case 0:
                this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/status/bomb.png"));
                break;
            case 1:
                this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/status/fire.png"));
                break;
            case 2:
//                this.renderer = new ImageRenderer(SpriteUtils.loadImage(""));
                this.isActive = false;
                //TODO: add speed boot
                break;
            default:
                this.isActive  = false;
                break;
        }
        System.out.println(typeItem);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
//        Player player = Player.getInstance();
//        if (this.boxCollider.intersects(player.getBoxCollider())){
//            switch (typeItem){
//                case 0:
//                    player.setTrapNum(Math.min(player.getTrapNum() + 1,10));
//                    break;
//                case 1:
//                    if (player.getTrapLength() <= 30) {
//                        player.setTrapLength(Math.min(player.getTrapLength() + 5,30));
//                    }
//                    break;
//                case 2:
//                    player.setSpeed(Math.min((float) (player.getSpeed() + 0.5),5));
//                    break;
//                default:
//                    break;
//            }
//            this.isActive = false;
//        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
