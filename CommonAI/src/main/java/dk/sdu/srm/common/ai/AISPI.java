package dk.sdu.srm.common.ai;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;

import java.util.ArrayList;
import java.util.Vector;

public interface AISPI {
    ArrayList<Vector2> findPath(ArrayList<ArrayList<TiledMapTileLayer.Cell>> cells, int startX, int startY, int targetX, int targetY);

}
