package controllers;


import controllers.server.GameResult;
import entities.ClientPlayer;
import entities.Players;

public interface Workflow {
    void startServer();

    void joinServer();

    void connectedToServer(ClientPlayer clientPlayer);

    void start();

    void startVillagerScreen();

    void startMafiaScreen();

    void startGame(Players players);

    void showGameStatus();

    void gameEnd(GameResult gameStatus);
}