package dk.sdu.srm.common.data;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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

    public String getID() {
        return ID.toString();
    }

}
