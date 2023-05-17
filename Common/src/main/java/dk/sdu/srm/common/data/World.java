package dk.sdu.srm.common.data;

import dk.sdu.srm.common.data.mapparts.MapPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.Array;

public class World {
    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    private GameMap gameMap;
    private Coords initState;
    private Coords goalState;
    private ArrayList<ArrayList<Integer>> worldMask;

    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }

    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

    public void addGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void removeMap(GameMap gameMap) {
        this.gameMap = null;
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }

    public void setGoalState(Coords c){
        goalState = c;
    }
    public Coords getGoalState(){
        return goalState;
    }
    public void setInitState(Coords c){
        initState = c;
    }

    public Coords getInitState() {
        return initState;
    }

    public void loadWorldMask(ArrayList<ArrayList<Integer>> mask){
        worldMask = mask;
    }
    public ArrayList<ArrayList<Integer>> getWorldMask(){
        return worldMask;
    }
    public int getWorldMaskRows(){ return worldMask.get(0).size()-1; }
    public int getWorldMaskColumns(){ return worldMask.size(); }
    public boolean isAvailable(int x, int y){
        return worldMask.get(x).get(y)==0;
    }
}
