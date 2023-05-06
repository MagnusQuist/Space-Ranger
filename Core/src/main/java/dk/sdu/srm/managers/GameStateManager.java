package dk.sdu.srm.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.main.states.State;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;
    public GameData gameData;
    public World world;

    public GameStateManager(GameData gameData, World world) {
        states = new Stack<>();
        this.gameData = gameData;
        this.world = world;
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() { states.pop().dispose(); }

    public void set(State state) {
        this.pop();
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}
