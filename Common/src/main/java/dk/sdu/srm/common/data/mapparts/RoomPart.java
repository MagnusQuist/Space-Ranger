package dk.sdu.srm.common.data.mapparts;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;

import java.util.ArrayList;

public class RoomPart implements MapPart {
    TiledMap map;

    ArrayList<ArrayList<TiledMapTileLayer.Cell>> mask;


    public RoomPart(TiledMap map) {
        this.map = map;
        generateMask();
    }

    public TiledMap getRoomMap() {
        return this.map;
    }

    public boolean checkRoomCleared() {
        return false;
    }

    public void generateMask() {
        ArrayList<ArrayList<TiledMapTileLayer.Cell>> mask = new ArrayList<>();
        TiledMapTileLayer floor = (TiledMapTileLayer) this.map.getLayers().get(0);
        for (int y = 0; y < floor.getWidth(); y++) {
            ArrayList<TiledMapTileLayer.Cell> row = new ArrayList<>();
            for (int x = 0; x < floor.getHeight(); x++) {
                row.add(floor.getCell(y, x));
            }
            mask.add(row);
        }
        this.mask = mask;
    }

    public ArrayList<ArrayList<TiledMapTileLayer.Cell>> getRoomMask() {
        return this.mask;
    }

    @Override
    public void process(GameData gameData, World world) {

    }

    public String toString() {
        String result = "";
        for (int i = 0; i < getRoomMask().size(); i++) {
            for (int j = 0; j < getRoomMask().get(i).size(); j++) {
                result += getRoomMask().get(i).get(j);
            } result += "\n";
        }
        return result;
    }
}