package Pacman.Enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;

public class EnemySpawner extends GameObject {

    private FrameCounter frameCounter;
    private int enemiesNumber = 0;
    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (enemiesNumber == 0) {
            Enemies enemies = new Enemies();
            enemies.setPosition(new Vector2D(200, 175));
//            System.out.println(enemies.getPosition());
            add(enemies);
            ++enemiesNumber;

            enemies = new Enemies();
            enemies.setPosition(new Vector2D(200, 415));
//            System.out.println(enemies.getPosition());
            add(enemies);
            ++enemiesNumber;
        }
    }
    //TODO: Create Enemies
}
