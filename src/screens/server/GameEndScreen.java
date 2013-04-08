package screens.server;

import controllers.server.GameEndController;
import views.server.GameEndView;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameEndScreen implements GameEndView {
    private final IMainFrame mainFrame;
    private final GameEndController controller;
    private ImagePanel panel;
    private JLabel gameStatus;
    private JButton exit;

    public GameEndScreen(IMainFrame mainFrame, GameEndController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel("images/gameEndScreen.jpg");
        exit = createButton(100, 800, "Exit");
        panel.add(exit);
        addListeners();
    }

    private void addListeners() {
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goToHomeScreen();
            }
        });
    }

    @Override
    public void display(String s) {
        gameStatus = new JLabel(s);
        gameStatus.setSize(300, 300);
        gameStatus.setLocation(400, 600);
        gameStatus.setFont(new Font("monospaced", Font.BOLD, 20));
        gameStatus.setForeground(Color.RED);
        panel.add(gameStatus);
    }

    private JButton createButton(int x_axis, int y_axis, String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        return button;
    }
}
