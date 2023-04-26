package dk.sdu.srm.common.data;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.sdu.srm.common.data.mapparts.FloorPart;
import dk.sdu.srm.common.data.mapparts.MapPart;
import dk.sdu.srm.common.data.mapparts.RoomPart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class GameMap implements Serializable {
    private final UUID ID = UUID.randomUUID();
    private final OrthogonalTiledMapRenderer renderer;
    private FloorPart currentFloor;
    private final ArrayList<FloorPart> floors = new ArrayList<>();

    public GameMap() {
        generateFloors();
        populateFloors();
        renderer = new OrthogonalTiledMapRenderer(currentFloor.getCurrentRoom().getRoomMap(), 1 / 3f);
    }

    private void generateFloors() {
        floors.add(new FloorPart(0, 1, 3));
        currentFloor = floors.get(0);
    }

    private void populateFloors() {
        for (FloorPart floor : floors) {
            for (int i = 0; i < floor.getNumRooms(); i++) {
                // TODO: Load room maps from some list somewhere :)
                floor.addRoom(new RoomPart(new TmxMapLoader().load("assets/maps/floor01/room01.tmx")));
                System.out.println(floor.getCurrentRoom().getRoomMap());
            }
        }
    }

    public void render(OrthographicCamera cam) {
        renderer.setView(cam);
        renderer.render();
    }

    public MapPart getCurrentFloor() {
        return floors.get(currentFloor.getFloorNumber());
    }

    public void setCurrentFloor(int floorNumber) {
        this.currentFloor = floors.get(floorNumber);
    }

    public String getID() {
        return ID.toString();
    }

}
