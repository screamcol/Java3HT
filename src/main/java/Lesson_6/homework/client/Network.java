package Lesson_6.homework.client;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Network {
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static PrintWriter printWriter;

    private static Callback callOnMsgReceived;
    private static Callback callOnAuthenticated;
    private static Callback callOnException;
    private static Callback callOnCloseConnection;

    static {
        Callback empty = args -> { };
        callOnMsgReceived = empty;
        callOnAuthenticated = empty;
        callOnException = empty;
        callOnCloseConnection = empty;
    }

    public static void setCallOnMsgReceived(Callback callOnMsgReceived) {
        Network.callOnMsgReceived = callOnMsgReceived;
    }

    public static void setCallOnAuthenticated(Callback callOnAuthenticated) {
        Network.callOnAuthenticated = callOnAuthenticated;
    }

    public static void setCallOnException(Callback callOnException) {
        Network.callOnException = callOnException;
    }

    public static void setCallOnCloseConnection(Callback callOnCloseConnection) {
        Network.callOnCloseConnection = callOnCloseConnection;
    }

    public static void sendAuth(String login, String password) {
        try {
            if (socket == null || socket.isClosed()) {
                connect();
            }
            out.writeUTF("/auth " + login + " " + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void connect() {
        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread clientListenerThread = new Thread(() -> {
                try {
                    new Thread(() -> {
                        for (int i = 0; i < 120; i++) {
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    printWriter = new PrintWriter(new FileOutputStream(new File("history.txt"), true));
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/authok ")) {
                            callOnAuthenticated.callback(msg.split("\\s")[1]);
                            File file = new File("history.txt");
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            break;
                        }
                    }
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.equals("/end")) {
                            break;
                        }
                        callOnMsgReceived.callback(msg);
                        if (!msg.startsWith("/")) {
                            printWriter.println(msg);
                        }
                    }
                } catch (IOException e) {
                    callOnException.callback("Соединение с сервером разорвано");
                } finally {
                    printWriter.close();
                    closeConnection();
                }
            });
            clientListenerThread.setDaemon(true);
            clientListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean sendMsg(String msg) {
        try {
            out.writeUTF(msg);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void closeConnection() {
        callOnCloseConnection.callback();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

