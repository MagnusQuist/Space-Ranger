package dk.sdu.srm.playersystem;

import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.player.Player;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlayerPluginTest {
    private static GameData mockedGameData;
    private static World mockedWorld;
    private static PlayerPlugin playerPlugin;

    @BeforeAll
    static void setUp() {
        mockedGameData = mock(GameData.class);
        mockedWorld = mock(World.class);
        playerPlugin = new PlayerPlugin();
    }

    @Test
    public void testAddPlayer() {
        playerPlugin.start(mockedGameData, mockedWorld);

        verify(mockedWorld).addEntity(any(Player.class));
    }
    @Test
    public void testRemovePlayer() {
        playerPlugin.stop(mockedGameData, mockedWorld);

        verify(mockedWorld).removeEntity(any(Player.class));
    }

}