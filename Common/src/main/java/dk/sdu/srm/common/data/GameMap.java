package dk.sdu.srm.common.data;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import dk.sdu.srm.common.data.mapparts.FloorPart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class GameMap implements Serializable {
    private final UUID ID = UUID.randomUUID();
    private OrthogonalTiledMapRenderer renderer;
    private FloorPart currentFloor;
    private final ArrayList<FloorPart> floors = new ArrayList<>();

    public void render(OrthographicCamera cam) {
        renderer.setView(cam);
        renderer.render();
    }

    public void debug() {
        ShapeRenderer sr = new ShapeRenderer();
        MapLayer wallObjectsLayer = getCurrentFloor().getCurrentRoom().getRoomMap().getLayers().get("wallObjects");
        MapObjects mapWalls = wallObjectsLayer.getObjects();
        Array<RectangleMapObject> walls = mapWalls.getByType(RectangleMapObject.class);

        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.RED);
        for (RectangleMapObject wall : walls) {
            Rectangle wallRect = wall.getRectangle();
            sr.rect(wallRect.x, wallRect.y, wallRect.width, wallRect.height);
        }
        sr.end();
    }

    public void setRenderer(OrthogonalTiledMapRenderer renderer) {
        this.renderer = renderer;
    }

    public ArrayList<FloorPart> getFloors() {
        return floors;
    }

    public FloorPart getCurrentFloor() {
        return floors.get(currentFloor.getFloorNumber());
    }

    public void setCurrentFloor(FloorPart floor) {
        currentFloor = floor;
    }

    public TiledMapTileLayer getFloorLayer() {
        return (TiledMapTileLayer) currentFloor.getCurrentRoom().getRoomMap().getLayers().get("floor");
    }

    public String getID() {
        return ID.toString();
    }

}
