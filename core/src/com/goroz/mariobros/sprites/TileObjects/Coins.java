package com.goroz.mariobros.sprites.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.goroz.mariobros.MarioBros;
import com.goroz.mariobros.scenes.Hud;
import com.goroz.mariobros.screen.PlayScreen;
import com.goroz.mariobros.sprites.Items.ItemDef;
import com.goroz.mariobros.sprites.Items.Mushroom;
import com.goroz.mariobros.sprites.Mario;

/**
 * Created by HC Lim on 8/5/2016.
 */
public class Coins extends InteractiveTileObject {

    private static TiledMapTileSet tileSet;
    //uniq ID of the tile
    private final int BLANK_COIN = 28;

    private MarioBros game;

    public Coins(PlayScreen screen,Rectangle bounds, MarioBros game) {
        super(screen, bounds);
        this.game = game;
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoriesFilter(MarioBros.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("coins", "collision");
        if (getCell().getTile().getId() == BLANK_COIN) {
            game.manager.get("audio/sounds/bump.wav", Sound.class).play();
        } else {
            game.manager.get("audio/sounds/coin.wav", Sound.class).play();
            screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + MarioBros.TILE_SIZE / MarioBros.PPM), Mushroom.class));
        }
        getCell().setTile(tileSet.getTile(BLANK_COIN));
        Hud.addScore(100);

    }

}
