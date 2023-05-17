package dk.sdu.srm.common.data.entityparts;

import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
public class LifePart implements EntityPart {
    private boolean dead = false;
    private int life;
    private boolean isHit = false;


    public LifePart(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isDead() {
        return dead;
    }

    @Override
    public void process(GameData gameData, Entity entity) {

    }
}
