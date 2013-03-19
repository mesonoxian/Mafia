import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen {
    public void display() {
        JFrame HomeFrame = new JFrame("Mafia");
        HomeFrame.setLayout(null);

        JButton startServer = createStartServerButton();
        JButton joinServer = createJoinServerButton();
        JButton quit = createQuitButton();

        HomeFrame.setBounds(100, 100, 600, 600);
        HomeFrame.setVisible(true);

        addButtonsToHomepage(HomeFrame, startServer, joinServer, quit);

        joiningServer(joinServer);
        quitHomePage(HomeFrame, quit);
        startingServer(HomeFrame, startServer);
    }

    private void addButtonsToHomepage(JFrame joinServerFrame, JButton createServer, JButton joinServer, JButton quit) {
        joinServerFrame.add(createServer);
        joinServerFrame.add(joinServer);
        joinServerFrame.add(quit);
    }

    private JButton createQuitButton() {
        JButton quit = new JButton("Quit");
        quit.setSize(145, 50);
        quit.setLocation(100, 500);
        Font font = new Font("Verdana", Font.BOLD, 14);
        quit.setFont(font);
        setColor(quit);
        return quit;
    }

    private void setColor(JButton buttonName) {
        buttonName.setForeground(Color.WHITE);
        buttonName.setBackground(Color.BLACK);
    }

    private JButton createJoinServerButton() {
        JButton joinServer = new JButton("Join Server");
        joinServer.setSize(145, 50);
        joinServer.setLocation(100, 400);
        Font font = new Font("Verdana", Font.BOLD, 14);
        joinServer.setFont(font);
        setColor(joinServer);
        return joinServer;
    }

    private JButton createStartServerButton() {
        JButton createServerButton = new JButton("Start Server");
        createServerButton.setSize(145, 50);
        createServerButton.setLocation(100, 300);
        Font font = new Font("Verdana", Font.BOLD, 14);
        createServerButton.setFont(font);
        createServerButton.setForeground(Color.WHITE);
        createServerButton.setBackground(Color.BLACK);
        return createServerButton;
    }

    private void joiningServer(JButton joinServer) {
        joinServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enterIpAddress();
            }

            private void enterIpAddress() {
                String serverName = JOptionPane.showInputDialog("Enter the server name");
                connectToServer(serverName);
            }

            private void connectToServer(String serverName) {

            }
        });
    }

    private void quitHomePage(final JFrame joinServerFrame, JButton quit) {
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedOption = JOptionPane.showConfirmDialog(null, "Do you really want to quit?",
                        "", JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    joinServerFrame.setVisible(false);
                }
            }
        });
    }

    private void startingServer(final JFrame joinServerFrame, JButton startServer) {
        startServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                joinServerFrame.setVisible(false);
                JFrame startingServer = new JFrame("Starting Server");
                JLabel playersList = new JLabel("Players Joined");
                playersList.setForeground(Color.BLACK);
                Font font = new Font("Verdana", Font.ITALIC, 12);
                playersList.setFont(font);
                JButton startGame = new JButton("Start Game");
                setColor(startGame);
                startingServer.setLayout(null);
                startingServer.add(playersList);
                startingServer.add(startGame);

                playersList.setSize(100, 200);
                playersList.setLocation(100, -50);

                startGame.setSize(100, 50);
                startGame.setLocation(400, 500);
                startingServer.setBounds(100, 100, 600, 600);
                startingServer.setVisible(true);
            }
        });
    }
}