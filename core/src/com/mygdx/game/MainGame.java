package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import helper.TileMapHelper;

public class MainGame extends Game {
	private final int _screenWidth = 320;
	private final int _screenHeight = 180;
	//private final int _playerWidth = 8;
	//private final int _playerHeight = 16;
	SpriteBatch batch;
	Texture img;
	Player player;
	Rectangle platform;
	OrthographicCamera camera;

	private TileMapHelper tileMapHelper;
	private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

	@Override
	public void create () {
		batch = new SpriteBatch();

		//platform = new Rectangle(0, 8, 320, 8);
		this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, _screenWidth, _screenHeight);

		player = new Player(10f, 10f);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);



		orthogonalTiledMapRenderer.setView(camera);
		orthogonalTiledMapRenderer.render();
		batch.begin();

		batch.draw(player.img, player.pos.x, player.pos.y);


		batch.end();
		player.update(Gdx.graphics.getDeltaTime());


		/*if (player.pos.x + _playerWidth > _screenWidth) player.pos.x = _screenWidth - _playerWidth;
		if (player.pos.x < 0) player.pos.x = 0;
		if (player.pos.y < 0) player.pos.y = 0;
		if (player.pos.y + _playerHeight > _screenHeight) player.pos.y = _screenHeight - _playerHeight;*/

		/*if (Gdx.input.isKeyPressed(Input.Keys.A)) player.x -= 80 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.D)) player.x += 80 * Gdx.graphics.getDeltaTime();*/

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
