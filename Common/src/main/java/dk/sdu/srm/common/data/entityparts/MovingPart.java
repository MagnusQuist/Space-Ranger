package dk.sdu.srm.common.data.entityparts;

import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;

public class MovingPart implements EntityPart {

    private float dx, dy;

    private float speed;
    private int facingState = 0;

    public MovingPart(float speed){
        this.speed = speed;
    }


    @Override
    public void process(GameData gameData, Entity entity) {

    }
}
