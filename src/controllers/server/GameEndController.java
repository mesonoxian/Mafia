package controllers.server;

import controllers.Workflow;
import entities.Players;
import gameMessages.MafiaWinsMessage;
import gameMessages.VillagerWinsMessage;
import views.server.GameEndView;

/**
 * Job: Understands the end of game.
 */
public class GameEndController {
    private final Players players;
    private final Workflow workflow;
    private final GameResult gameStatus;

    private GameEndView view;

    public GameEndController(Players players, Workflow workflow, GameResult gameStatus) {

        this.players = players;
        this.workflow = workflow;
        this.gameStatus = gameStatus;
    }

    public void bind(GameEndView gameEndScreen) {
        this.view = gameEndScreen;
    }

    public void start() {
        if (gameStatus.equals(GameResult.MafiaWins)) sendMafiaWinsMessage();
        else sendVillagerWinsMessage();
        close();
    }

    private void sendVillagerWinsMessage() {

        players.sendMessage(new VillagerWinsMessage());
        view.display("Villager Won the Game");


    }

    private void sendMafiaWinsMessage() {
        players.sendMessage(new MafiaWinsMessage());
        view.display("Mafia Won the Game");
    }

    public void goToHomeScreen() {
        workflow.start("");
    }

    public void close() {
        players.quit();
    }
}
