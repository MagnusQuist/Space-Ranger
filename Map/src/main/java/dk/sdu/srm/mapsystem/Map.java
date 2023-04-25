package dk.sdu.srm.mapsystem;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.sdu.srm.mapsystem.floor.Floor;
import dk.sdu.srm.mapsystem.room.Room;

import java.util.ArrayList;

public class Map {
    private OrthogonalTiledMapRenderer renderer;
    private Floor currentFloor;
    private ArrayList<Floor> floors = new ArrayList<>();

    public Map() {
        renderer = new OrthogonalTiledMapRenderer(currentFloor.getCurrentRoom().getRoomMap(), 1 / 16f);
        generateFloors();
        populateFloors();
    }

    public void render() {
        renderer.render();
    }

    public void generateFloors() {
        floors.add(new Floor(1, 3, 1));
        currentFloor = floors.get(0);
    }

    public void populateFloors() {
        for (Floor floor : floors) {
            floor.addRoom(new Room(new TmxMapLoader().load("assets/maps/floor01/room01.tmx")));
        }
    }

    public Floor getCurrentFloor() {
        return floors.get(currentFloor.getFloorNumber());
    }

    public void setCurrentFloor(int floorNumber) {
        this.currentFloor = floors.get(floorNumber);
    }
}
