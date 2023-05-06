package dk.sdu.srm.mapsystem;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.physics.box2d.World;

public class TiledObjectParser {
    public static void parseTiledObjectLayer(World world, MapObjects objects) {
        for (MapObject object : objects) {
            if (object instanceof PolylineMapObject) {

            }
        }
    }
}
