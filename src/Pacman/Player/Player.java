package Pacman.Player;

import Pacman.DeadBox.DeadBox;
import Pacman.Enemies.Enemies;
import Pacman.Platform.Platform;
import Pacman.inputs.InputManager;
import Pacman.scenes.GameOverScene;
import Pacman.scenes.SceneManager;
import bases.Constraints;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.actions.Action;
import bases.actions.SequenceAction;
import bases.actions.WaitAction;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;

import java.util.Vector;

public class Player extends GameObject implements PhysicsBody {
    private float speed = (float) 1.5;
    private InputManager inputManager;
    private Constraints constraints;
    private int health, trapNum;
    private static Player instance;
    private BoxCollider boxCollider;
//    private FrameCounter frameCounter;
    private Vector2D velocity;
    private PlayerAnimator animator;
    private FrameCounter alreadyHit;
    private boolean isAlreadyHit;
    private int trapLength;

    public Player() {
        super();
        this.trapLength = 1; //10 15 20 25 30
        this.health = 1;
        this.trapNum = 3;
        this.boxCollider = new BoxCollider(30,40);
        this.alreadyHit = new FrameCounter(100);
        this.alreadyHit.setCount(this.alreadyHit.getCountMax());
        velocity = new Vector2D(0,0);
        animator = new PlayerAnimator();
        this.renderer = animator;
        isAlreadyHit = false;
        instance = this;
        this.children.add(boxCollider);
    }

    public static Player getInstance() {
        return instance;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager.instance;
    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        updatePhysics();
        if (alreadyHit.run()) {
            isAlreadyHit = false;
            hitEnemy();
            hitDeadBox();
        }
        gameOver();
    }

    private void updatePhysics() {
        move();
        updateVerticalPhysics(Platform.class);
        updateHorizontalPhysics(Platform.class);
//        updateVerticalPhysics(PlayerTrap.class);
//        updateHorizontalPhysics(PlayerTrap.class);
        animator.update(this);
        velocity.set(0,0);
    }

    private  <T extends PhysicsBody> void updateVerticalPhysics(Class<T> classz) {
        Vector2D checkPosition = screenPosition.add(0,velocity.y);
        T object = Physics.collideWith(checkPosition,boxCollider.getWidth(),boxCollider.getHeight(), classz);
        if(object!= null){
            float dy = Math.signum(velocity.y);
            while (Physics.collideWith(screenPosition.add(0, dy),boxCollider.getWidth(),boxCollider.getHeight(),classz) == null){
                position.addUp(0, dy);
                screenPosition.addUp(0,dy);
            }
            velocity.y = 0;
        }
        this.position.y += velocity.y;
        this.screenPosition.y += velocity.y;
    }

    private  <T extends PhysicsBody> void updateHorizontalPhysics(Class<T> classz) {
        Vector2D checkPosition = screenPosition.add(velocity.x, 0);
        T object = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), classz);
        if (object != null){
            float dx = Math.signum(velocity.x);
            while (Physics.collideWith(screenPosition.add(dx, 0), boxCollider.getWidth(), boxCollider.getHeight(), classz) == null){
                position.addUp(dx, 0);
                screenPosition.addUp(dx, 0);
            }
            velocity.x = 0;
        }
        this.position.x += velocity.x;
        this.screenPosition.x += velocity.x;
    }

    private void hitEnemy() {
        Enemies enemy = Physics.collideWith(this.screenPosition,boxCollider.getWidth(),boxCollider.getHeight(), Enemies.class);
        if(enemy != null){
            enemy.setHealth(enemy.getHealth() - 1);
            enemy.turnIntoImpossibleHit();
            //TODO: turn this void to private?
            this.setHealth(this.getHealth() - enemy.getDamage());
            turnIntoImpossibleHit();
        }
    }
    private void hitDeadBox() {
        DeadBox deadBox = Physics.collideWith(this.screenPosition,boxCollider.getWidth(),boxCollider.getHeight(), DeadBox.class);
        if(deadBox != null){
            this.setHealth(this.getHealth() - deadBox.getDamage());
            turnIntoImpossibleHit();
        }
    }

    private void turnIntoImpossibleHit() {
        alreadyHit.reset();
        isAlreadyHit = true;
    }

    private void move() {
        if (inputManager.upPressed)
            velocity.y -= speed;
        if (inputManager.downPressed)
            velocity.y += speed;
        if (inputManager.leftPressed)
            velocity.x -= speed;
        if (inputManager.rightPressed)
            velocity.x += speed;
        if (inputManager.spacePressed && trapNum > 0){
            int x = Math.round(this.position.x);
            int y = Math.round(this.position.y);
            int trapX = ((x - 33 + 25)/50)*50 + 33;
            int trapY = ((y - 45 + 25)/50)*50 + 45 ;
            PlayerTrap playerTrap = Physics.collideWith(new Vector2D(trapX,trapY),48,48, PlayerTrap.class);
            if (playerTrap == null){
                PlayerTrap trap = GameObjectPool.recycle(PlayerTrap.class);
                trap.setSize(trapLength);
                trap.reset();
                trapNum--;
                trap.setPosition(new Vector2D(trapX,trapY));
            }
        }

        if (constraints != null)
            constraints.make(position);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    private static void clearInstance() {
        instance = null;
    }

    private void gameOver(){
        if (!this.isActive){
//            SceneManager.changeScene(new GameOverScene());
//            Player.clearInstance();
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (health <=0){
            PlayerExplosion playerExplosion = GameObjectPool.recycle(PlayerExplosion.class);
            playerExplosion.getPosition().set(this.position);
            this.setActive(false);
        }
    }

    public boolean isAlreadyHit() {
        return isAlreadyHit;
    }

    public int getTrapNum() {
        return trapNum;
    }

    public void setTrapNum(int trapNum) {
        this.trapNum = trapNum;
    }

    public int getTrapLength() {
        return trapLength;
    }

    public void setTrapLength(int trapLength) {
        this.trapLength = trapLength;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
