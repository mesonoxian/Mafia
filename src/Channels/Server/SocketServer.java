package Channels.Server;

import Channels.SocketChannel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/*
    Job : Understands a connection between client and server.
 */
public class SocketServer {
    final private int SOCKET_TIMEOUT = 1000;
    private int port;
    private SocketServerListener listener;
    private ServerSocket serverSocket;
    private Thread thread;
    private boolean stopWaiting;

    public SocketServer(int port, SocketServerListener listener) {

        this.port = port;
        this.listener = listener;
    }

    private void waitForConnections() {
        startServerSocket();
        while (true) {
            try {
                acceptConnection();
            } catch (SocketTimeoutException e) {
                if (stopWaiting) {
                    try {
                        serverSocket.close();
                    } catch (IOException e1) {
                        listener.onError(e1);
                    }
                    return;
                }
            } catch (IOException e) {
                listener.onError(e);
            }
        }
    }

    private void acceptConnection() throws IOException {
        Socket socket = serverSocket.accept();
        listener.onConnectionEstablished(new SocketChannel(socket));
    }

    private void startServerSocket() {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(SOCKET_TIMEOUT);
        } catch (IOException e) {
            listener.onError(e);
        }
    }

    public void start() {
        stopWaiting = false;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                waitForConnections();
            }
        });
        thread.start();
    }

    public void stop() {
        stopWaiting = true;
    }
}