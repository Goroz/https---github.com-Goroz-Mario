package com.goroz.mariobros.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.goroz.mariobros.MarioBros;
import com.goroz.mariobros.screen.PlayScreen;
import com.goroz.mariobros.sprites.TileObjects.Bricks;
import com.goroz.mariobros.sprites.TileObjects.Coins;
import com.goroz.mariobros.sprites.Enemies.Goomba;
import com.goroz.mariobros.sprites.TileObjects.Grounds;
import com.goroz.mariobros.sprites.TileObjects.Pipes;

/**
 * Created by HC Lim on 8/5/2016.
 */
public class B2WorldCreator {

    private Array<Goomba> goombas;
    private World world;
    private TiledMap map;
    private MarioBros game;

    public B2WorldCreator(PlayScreen screen, MarioBros game) {
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.game = game;
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            //Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Coins(screen, object, game);
        }
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            //Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Bricks(screen, object, game);
        }
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            //Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Pipes(screen, object);
        }
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            //Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Grounds(screen, object);
        }
        // create all goombas
        goombas = new Array<Goomba>();
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            goombas.add(new Goomba(screen, rect.getX() / MarioBros.PPM, rect.getY() / MarioBros.PPM, game));
        }

    }

    public Array<Goomba> getGoombas() {
        return goombas;
    }

}
