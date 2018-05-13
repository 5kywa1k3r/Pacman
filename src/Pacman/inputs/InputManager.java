package Pacman.inputs;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class InputManager {
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean spacePressed;
    public boolean wPressed;
    public boolean aPressed;
    public boolean sPressed;
    public boolean dPressed;
    public boolean qPressed;


    public static final InputManager instance = new InputManager();

    private InputManager() {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_RIGHT:
                rightPressed = true;
                break;
            case VK_LEFT:
                leftPressed = true;
                break;
            case VK_UP:
                upPressed = true;
                break;
            case VK_DOWN:
                downPressed = true;
                break;
            case VK_SPACE:
                spacePressed = true;
                break;
            case VK_D:
                rightPressed = true;
                break;
            case VK_A:
                leftPressed = true;
                break;
            case VK_W:
                upPressed = true;
                break;
            case VK_S:
                downPressed = true;
                break;
            case VK_Q:
                spacePressed = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_RIGHT:
                rightPressed = false;
                break;
            case VK_LEFT:
                leftPressed = false;
                break;
            case VK_UP:
                upPressed = false;
                break;
            case VK_DOWN:
                downPressed = false;
                break;
            case VK_SPACE:
                spacePressed = false;
                break;
            case VK_D:
                rightPressed = false;
                break;
            case VK_A:
                leftPressed = false;
                break;
            case VK_W:
                upPressed = false;
                break;
            case VK_S:
                downPressed = false;
                break;
            case VK_Q:
                spacePressed = false;
                break;
        }
    }
}
