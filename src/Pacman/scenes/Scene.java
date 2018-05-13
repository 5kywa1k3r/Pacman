package Pacman.scenes;

import bases.GameObject;

public abstract class Scene {

    public void destroy() {
        GameObject.clearAll();
    }

    public abstract void init();
}
