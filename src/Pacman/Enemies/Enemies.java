package Pacman.Enemies;

import Pacman.DeadBox.DeadBox;
import Pacman.Platform.Platform;
import Pacman.Player.Player;
import Pacman.Player.PlayerTrap;
import Pacman.settings.Settings;
import bases.Constraints;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.ImageRenderer;
import bases.renderers.Renderer;

import java.awt.*;
import java.util.Random;
import java.util.Set;

public class Enemies extends GameObject implements PhysicsBody{
    private static final float SPEED = 2;
    private Constraints constraints;
    private int health, damage;
    private BoxCollider boxCollider;
    private Vector2D velocity, velocityTemp;
    private int enemyType = 0;
    private FrameCounter frameCounter, alreadyHit;
    private boolean isAlreadyHit;
    private Vector2D up = new Vector2D(0,-SPEED), down = new Vector2D(0,SPEED), left = new Vector2D(-SPEED,0), right = new Vector2D(SPEED,0);
    private final Vector2D direction[] = {up, left, down, right};
    Renderer rendererTemp;
    Settings settings = Settings.instance;
    public Enemies() {
        super();
        this.health = 1;
        this.damage = 1;
        Random random = new Random();
        this.enemyType = random.nextInt(3);
        isAlreadyHit = false;
        alreadyHit = new FrameCounter(100);
        alreadyHit.setCount(100);
        this.boxCollider = new BoxCollider(40,40);
        this.children.add(boxCollider);
        velocity = new Vector2D(0,0);
        switch (enemyType) {
            case 0:
                rendererTemp = new EnemiesAnimator();
                break;
            case 1:
                rendererTemp  = new EnemiesAnimator2();
                break;
            case 2:
                rendererTemp  = new EnemiesAnimator3();
                break;
        }
        this.renderer = rendererTemp;
        frameCounter = new FrameCounter(150);
    }

    @Override
    public void run(Vector2D parentPosition) {
//        System.out.println(position);
        super.run(parentPosition);
        move();
        if (alreadyHit.run()) {
            isAlreadyHit = false;
            hitDeadBox();
        }
    }

    private void move() {
        if (frameCounter.run()){
            findBestWay();
            //Find Best Way to Move
            frameCounter.reset();
        }
        velocityTemp = velocity;
        updateHorizontalPhysics(PlayerTrap.class);
        updateVerticalPhysics(PlayerTrap.class);
        velocityTemp = new Vector2D(0,0);
        updateHorizontalPhysics(Platform.class);
        updateVerticalPhysics(Platform.class);
        this.position.x += velocity.x;
        this.position.y += velocity.y;
        this.screenPosition.y += velocity.y;
        this.screenPosition.y += velocity.y;
        switch (enemyType) {
            case 0:
                EnemiesAnimator enemiesAnimator = (EnemiesAnimator) rendererTemp;
                enemiesAnimator.update(this);
                break;
            case 1:
                EnemiesAnimator2 enemiesAnimator2 = (EnemiesAnimator2) rendererTemp;
                enemiesAnimator2.update(this);
                break;
            case 2:
                EnemiesAnimator3 enemiesAnimator3 = (EnemiesAnimator3) rendererTemp;
                enemiesAnimator3.update(this);
                break;
        }
    }

    private void findBestWay() {
        Platform platform = new Platform();
        this.screenPosition= new Vector2D(((Math.round(this.screenPosition.x) - 33 + 25)/50)*50 + 33,
                ((Math.round(this.screenPosition.y) - 45 + 25)/50)*50 + 45);

        this.position= new Vector2D(((Math.round(this.position.x) - 33 + 25)/50)*50 + 33,
                ((Math.round(this.position.y) - 45 + 25)/50)*50 + 45);

//         Fix Position
        Random random = new Random();   Random random2 = new Random();
        Random random3 = new Random();  Random random4 = new Random();
        int dr1 = random.nextInt(4);    int dr2 = random2.nextInt(4);
        int dr3 = random3.nextInt(4);   int dr4 = random4.nextInt(4);
        // sort direction random
        Vector2D tmp = new Vector2D();


        for (int dr = 0; dr < 4; dr++) {
            Vector2D checkPosition = screenPosition.add(direction[dr].x * 10,direction[dr].y * 10);
            platform = Physics.collideWith(checkPosition,boxCollider.getWidth(),boxCollider.getHeight(),Platform.class);
            if (platform == null){
                velocity = direction[dr];
                tmp = direction[dr1];
                direction[dr1] = direction[dr4];
                direction[dr4] = tmp;

                tmp = direction[dr1];
                direction[dr1] = direction[dr3];
                direction[dr3] = tmp;

                return;
            }
        }
        velocity = new Vector2D(0,0);
    }

    private  <T extends PhysicsBody> void updateVerticalPhysics(Class<T> classz) {
        Vector2D checkPosition = screenPosition.add(0,velocity.y);
        T object = Physics.collideWith(checkPosition,boxCollider.getWidth(),boxCollider.getHeight(), classz);
        if(object != null){
            float dy = 1;
            if (velocity.y < 0) dy = -1;
            while (Physics.collideWith(screenPosition.add(0, dy),boxCollider.getWidth(),boxCollider.getHeight(),classz) == null){
                position.addUp(0, dy);
                screenPosition.addUp(0,dy);
            }
            findBestWay();
        }
    }

    private  <T extends PhysicsBody> void updateHorizontalPhysics(Class<T> classz) {
        Vector2D checkPosition = screenPosition.add(velocity.x, 0);
        T object = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), classz);
        if (object != null){
            float dx = 1;
            if (velocity.x < 0) dx = -1;
            while (Physics.collideWith(screenPosition.add(dx, 0), boxCollider.getWidth(), boxCollider.getHeight(), classz) == null){
                position.addUp(dx, 0);
                screenPosition.addUp(dx, 0);
            }
            findBestWay();
        }
    }

    private void hitDeadBox() {
        DeadBox deadBox = Physics.collideWith(this.screenPosition,boxCollider.getWidth(),boxCollider.getHeight(), DeadBox.class);
        if(deadBox != null){
            this.health = this.health - deadBox.getDamage();
            if (health <= 0){
                switch (enemyType) {
                    case 0:
                        EnemyExplosion explosion = GameObjectPool.recycle(EnemyExplosion.class);
                        explosion.getPosition().set(this.position);
                        break;
                    case 1:
                        EnemyExplosion2 explosion2 = GameObjectPool.recycle(EnemyExplosion2.class);
                        explosion2.getPosition().set(this.position);
                        break;
                    case 2:
                        EnemyExplosion3 explosion3 = GameObjectPool.recycle(EnemyExplosion3.class);
                        explosion3.getPosition().set(this.position);
                        break;
                }
                dissapear();
            }
            turnIntoImpossibleHit();
        }
    }

    private void dissapear() {
        setActive(false);
        settings.setCREPNUMBER(settings.getCREPNUMBER() - 1);
        if (settings.getCREPNUMBER() <= 0){
            if (settings.getCurrentLevel() < 3){
                //TODO: Chuyen Scene
            }
            else{
                //TODO: Set the Victory Player
            }
        }
    }

    public void turnIntoImpossibleHit() {
        alreadyHit.reset();
        isAlreadyHit = true;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void render(Graphics2D g2d) {
        if ((isAlreadyHit && System.currentTimeMillis()%3 == 0) || !isAlreadyHit)
            super.render(g2d);
    }
}
