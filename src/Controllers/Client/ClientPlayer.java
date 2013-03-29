package controllers.client;

import channels.Messages.ChannelMessage;
import channels.SocketChannel;
import channels.SocketChannelListener;
import gameMessages.MafiaRoleAssigned;
import gameMessages.PlayersConnectedMessage;
import gameMessages.VillagerRoleAssigned;

import java.io.IOException;

public class ClientPlayer implements SocketChannelListener {
    private final String serverName;
    private final String playerName;
    private SocketChannel channel;
    private ClientEngine engine;

    public ClientPlayer(SocketChannel channel, String serverName, String playerName) {
        this.channel = channel;
        this.serverName = serverName;
        this.playerName = playerName;
        channel.bind(this);
    }

    @Override
    public void onClose(SocketChannel channel, Exception e) {
       engine.ServerClosed();
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
        e.printStackTrace();
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
        if (message instanceof PlayersConnectedMessage) {
            PlayersConnectedMessage pCm = (PlayersConnectedMessage) message;
            engine.displayConnectedPlayers(pCm.getPlayersConnected());
        }
        if (message instanceof VillagerRoleAssigned)
            engine.startVillagerScreen(message);

        if (message instanceof MafiaRoleAssigned)
            engine.startMafiaScreen(message);
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
        e.printStackTrace();
    }

    public void stop() {
        channel.stop();
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getServerName() {

        return serverName;
    }

    public void sendMessage(ChannelMessage message) {
        this.channel.send(message);
    }

    public void bindClientEngine(ClientEngine engine) {
        this.engine = engine;
    }
}