package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player {
    static final int IDLE = 0;
    static final int MOVE = 1;
    static final int JUMP = 2;
    static final int SPAWN = 3;
    static final int DYING = 4;
    static final int DEAD = 5;
    static final int LEFT = -1;
    static final int RIGHT = 1;
    static final float VELOCITY = 30f;
    static final float JUMP_VELOCITY = 12f;
    static final float BRAKE_SPEED = 0.8f;
    static final float MAX_VEL = 6f;
    static final float GRAVITY = 30f;

    Vector2 pos = new Vector2();
    Vector2 accel = new Vector2();
    Vector2 vel = new Vector2();
    public Rectangle hitbox = new Rectangle();
    public Texture img;

    int state = IDLE;
    float stateTime = 0;
    int dir = RIGHT;
    //map
    boolean on_ground = false;

    public Player(float x, float y) {
        pos.x = x;
        pos.y = y;
        hitbox.width = 8;
        hitbox.height = 16;
        hitbox.x = pos.x + 1;
        hitbox.y = pos.y;
        img = new Texture("char.png");
        state = SPAWN;
        stateTime = 0;
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
        accel.y = -GRAVITY;
        accel.scl(deltaTime);

        if (accel.x == 0) vel.x *= BRAKE_SPEED;
        if (vel.x > MAX_VEL) vel.x = MAX_VEL;
        if (vel.x < -MAX_VEL) vel.x = -MAX_VEL;

        vel.scl(deltaTime * 50);
        vel.add(accel.x, accel.y);
        pos.x += vel.x;
        pos.y += vel.y;
        keysProcessing();
        checkCollision();

        //vel.scl(1f / deltaTime);
        if (state == SPAWN){
            if (stateTime > 0.3f){
                state = IDLE;
            }
        }
        if (state == DYING) {
            if (stateTime > 0.3f){
                state = DEAD;
            }
        }


    }

    private void keysProcessing() {
        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            //camera.unproject(touchPos);
            pos.x = touchPos.x;
            pos.y = touchPos.y;
        }
        if (Gdx.input.isKeyPressed(Keys.SPACE) && state != JUMP) {
            state = JUMP;
            vel.y = JUMP_VELOCITY;
            on_ground = false;
        }
        if (on_ground) state = IDLE;
        if (Gdx.input.isKeyPressed(Keys.A)) {
            if (state != JUMP) state = MOVE;
            dir = LEFT;
            accel.x = VELOCITY * dir;
        }
        else if (Gdx.input.isKeyPressed(Keys.D)){
            if (state != JUMP) state = MOVE;
            dir = RIGHT;
            accel.x = VELOCITY * dir;
        }
        else {
            if (state != JUMP) state = IDLE;
            accel.x = 0;
        }
    }

    private void checkCollision() {
        if (pos.x + hitbox.width > 320) pos.x = 320 - hitbox.width;
        if (pos.x < 0) pos.x = 0;
        if (pos.y < 0) pos.y = 0;
        if (pos.y + hitbox.height > 180) pos.y = 180 - hitbox.height;
        if (pos.y <= 0) {
            on_ground = true;
        }
    }
}
