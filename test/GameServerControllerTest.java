import channels.Server.SocketServer;
import controllers.Workflow;
import controllers.server.GameServerController;
import controllers.server.Player;
import gameMessages.PlayersConnectedMessage;
import org.junit.Test;
import views.server.GameServerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class GameServerControllerTest {
    GameServerView gameServerView = mock(GameServerView.class);
    Workflow workflow = mock(Workflow.class);
    GameServerController gameServerController = new GameServerController(workflow);

    @Test
    public void when_gameServerController_calls_players_updated_it_invokes_updatePlayers_method_in_startServerScreen() throws IOException {

        gameServerController.bind(gameServerView);
        gameServerController.playersUpdated();

        List<Player> players = new ArrayList<Player>();
        verify(gameServerView).updatePlayers(players);
    }

    @Test
    public void players_connected_message_is_sent_whenever_a_new_player_is_updated() {
        //setup
        String playerName = "MafiaPlayer";

        Player mockPlayer = mock(Player.class);
        when(mockPlayer.getName()).thenReturn(playerName);
        GameServerController gameServerController = new GameServerController(workflow);
        gameServerController.bind(gameServerView);
        gameServerController.addPlayer(mockPlayer);

        // actual method to test
        gameServerController.playersUpdated();

        // verification
        verify(mockPlayer).sendMessage(PlayersConnectedMessage.createPlayersConnectedMessage(playerName + "\n")); // verification
    }

    @Test
    public void stopServer_stops_the_server() {
        SocketServer mockServer = mock(SocketServer.class);
        GameServerController gameServerController = new GameServerController(mock(Workflow.class));
        gameServerController.start(mockServer);

        gameServerController.stop();

        verify(mockServer).stop();
    }

    @Test
    public void stopServer_transitions_to_the_homeScreen() {
        Workflow mockWorkflow = mock(Workflow.class);

        GameServerController gameServerController = new GameServerController(mockWorkflow);
        gameServerController.stop();

        verify(mockWorkflow).start();
    }
}