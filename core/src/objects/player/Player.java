package objects.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import static helper.Constants.PPM;

public class Player extends GameEntity {

    public Player(float width, float height, Body body) {
        super(width, height, body);
        speed = 4f;
    }
    public void update() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
    }

    public void render(SpriteBatch spriteBatch) {

    }
}
