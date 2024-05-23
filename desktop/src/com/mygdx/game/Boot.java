package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Boot extends Game {

    public static Boot INSTANCE;
    private int screenWidth, screenHeight;
    private OrthographicCamera orthographicCamera;

    public Boot() {
        INSTANCE = this;
    }

    @Override
    public void create() {
        this.screenWidth = 320;
        this.screenHeight = 180;
        this.orthographicCamera = new OrthographicCamera();
        this.orthographicCamera.setToOrtho(false, screenWidth, screenHeight);
        setScreen(new GameScreen(orthographicCamera));
    }
}
