package Pacman.settings;

import java.awt.*;

public class Settings {
    private int CREPNUMBER;
    private int windowWidth;
    private int windowHeight;

    private int gamePlayWidth;
    private int gamePlayHeight;

    private Insets windowInsets;

    public static final Settings instance = new Settings();
    private int currentLevel = 0;

    private Settings(int windowWidth, int windowHeight, int gamePlayWidth, int gamePlayHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.gamePlayWidth = gamePlayWidth;
        this.gamePlayHeight = gamePlayHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getGamePlayWidth() {
        return gamePlayWidth;
    }

    public int getGamePlayHeight() {
        return gamePlayHeight;
    }

    public Insets getWindowInsets() {
        return windowInsets;
    }

    public void setWindowInsets(Insets windowInsets) {
        this.windowInsets = windowInsets;
    }

    //private Settings() {
      //  this(1024, 768, 384, 768);
    //}


    public int getCREPNUMBER() {
        return CREPNUMBER;
    }

    public void setCREPNUMBER(int CREPNUMBER) {
        this.CREPNUMBER = CREPNUMBER;
    }

    private Settings() {
        this(1100, 768, 1100, 768);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
