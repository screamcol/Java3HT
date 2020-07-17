package Lesson_6.homework.server;

//import org.apache.log4j.Logger;
import org.slf4j.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;
    DBHelper dbHelper;
    private AuthService authService;
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public AuthService getAuthService() {
        return authService;
    }

    public Server() {
        clients = new Vector<>();
        dbHelper = new DBHelper();
        dbHelper.connect();
        authService = new SimpleAuthService(dbHelper);
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
//            System.out.println("Сервер запущен на порту 8189");
            logger.debug("Сервер запущен на порту 8189");
            while (true) {
                Socket socket = serverSocket.accept();
                logger.debug("Подключился новый клиент", new ClientHandler(this, socket));
//                System.out.println("Подключился новый клиент");
            }
        } catch (IOException e) {
            logger.debug("Сервер сокет не создался");
            e.printStackTrace();
        } finally {
            dbHelper.disconnect();
        }
        logger.debug("Сервер завершил свою работу");
//        System.out.println("Сервер завершил свою работу");
    }

    public void broadcastMsg(String msg) {
        logger.debug("Трансляция сообщения: {}", msg);
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public void privateMsg(ClientHandler sender, String receiverNick, String msg) {
        if (sender.getNickname().equals(receiverNick)) {
            logger.debug("Клиент {} прислал сообщение для себя с текстом: {}", sender.getNickname(), msg);
            sender.sendMsg("заметка для себя: " + msg);
            return;
        }
        for (ClientHandler o : clients) {
            if (o.getNickname().equals(receiverNick)) {
                logger.debug("Клиент {} прислал сообщение {} для {}", sender.getNickname(), msg, receiverNick);
                o.sendMsg("от " + sender.getNickname() + ": " + msg);
                sender.sendMsg("для " + receiverNick + ": " + msg);
                return;
            }
        }
        sender.sendMsg("Клиент " + receiverNick + " не найден");
        logger.debug("Клиент {} не наден", sender.getNickname());
    }

    public void subscribe(ClientHandler clientHandler) {
        logger.debug("Клиент добавлен в подписку: ", clients.add(clientHandler));
        broadcastClientsList();
    }

    public void unsubscribe(ClientHandler clientHandler) {
        logger.debug("Клиент удален из подписки: ", clients.remove(clientHandler));
        broadcastClientsList();
    }

    public boolean isNickBusy(String nickname) {
        for (ClientHandler o : clients) {
            if (o.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public boolean nickExist(String nickname) {
        return dbHelper.selectAllNicknames(nickname).size() > 0;
    }


    public void broadcastClientsList() {
        StringBuilder sb = new StringBuilder(15 * clients.size());
        sb.append("/clients ");
        // '/clients '
        for (ClientHandler o : clients) {
            sb.append(o.getNickname()).append(" ");
        }
        // '/clients nick1 nick2 nick3 '
        sb.setLength(sb.length() - 1);
        // '/clients nick1 nick2 nick3'
        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }
}

