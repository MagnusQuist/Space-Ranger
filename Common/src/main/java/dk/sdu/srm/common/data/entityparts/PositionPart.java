package dk.sdu.srm.common.data.entityparts;

import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;

public class PositionPart implements EntityPart {
    private float x;
    private float y;
    private float previousX;
    private float previousY;
    private int facingState;

    public PositionPart(float x, float y, int facingState) {
        this.x = x;
        this.y = y;
        this.facingState = facingState;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getFacingState() {
        return facingState;
    }

    public void setX(float newX) {
        this.x = newX;
    }

    public void setY(float newY) {
        this.y = newY;
    }

    public void setFacingState(int newFacingState) {
        this.facingState = newFacingState;
    }

    public void setPosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setPreviousPosition(float newX, float newY) {
        this.previousX = newX;
        this.previousY = newY;
    }

    public float getPreviousX() {
        return previousX;
    }

    public float getPreviousY() {
        return previousY;
    }

    @Override
    public void process(GameData gameData, Entity entity) {

    }
}
