package server;

import entities.Move;
import game.*;
import recordingSpring.RecordingController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The type Server.
 */
public class server {
    /**
     * The constant players.
     */
    public static final List<Player> players = new ArrayList<>();
    /**
     * The constant colorList.
     */
    public static final List<CustomColor> colorList = new ArrayList<>();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {

        if(args.length < 1){
            throw new Exception("No players number found. Please choose 2, 3, 4 or 6 players mode, r [id] - for replay mode of game of given id");
        }

        try {
        if(args[0].equals("r") && args.length >= 2) {
           int id = Integer.parseInt(args[1]);
           runReplay(id);
        }

        int[] modes = {2, 3, 4, 6};
        int mode = Integer.parseInt(args[0]);
        boolean validMode = false;

        for(int m : modes){
            if (mode == m) {
                validMode = true;
                break;
            }
        }

        if(!validMode) {
            throw new Exception("Given mode is not valid, Please choose 2, 3, 4 or 6 players mode");
        }

        colorList.addAll(Arrays.asList(CustomColor.values()));

        ExecutorService executorService = Executors.newFixedThreadPool(mode);
        try (ServerSocket socket = new ServerSocket(50000)) {
            System.out.println("Server is running at port 50000");
            while(players.size() < mode) {
                Player tmp = new Player(socket.accept(), colorList.get(players.size()));
                players.add(tmp);
                executorService.execute(tmp);
                System.out.println("Connected players:" + players.size());
            }
            new Game(players);
        }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect argument");
        }
    }

    private static void runReplay(final int id) {
        RecordingController RC = new RecordingController();
        if(!RC.getGameFromDatabase(id)) {
            System.out.println("Could not find the game of id = " + id);
            System.exit(1);
        }
        System.out.println("dziala:)");

        entities.Game gameInfo = RC.getGame();

        //polaczyc z 1 klientem
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try (ServerSocket socket = new ServerSocket(50000)) {
            System.out.println("Server is running at port 50000, ready to play replay");
            while(players.size() < 1) {
                Player tmp = new Player(socket.accept(), CustomColor.RED);
                executorService.execute(tmp);
                //w petli wyslac mu ruchy graczy
                playReplay(tmp, gameInfo, RC.getMoves());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void playReplay(Player tmp, entities.Game gameInfo, Set<Move> moves) {
        //setup
        tmp.sendMessage(gameInfo.getPlayers() + ":" + moves.iterator().next().getColor());
        for(Move mv : moves) {
            String msg = "MOVE:" + mv.getFromX() + "," + mv.getFromY() + "," + mv.getToX() + "," + + mv.getToY() + ";ROUND:" + mv.getColor();
            System.out.println(msg);
            tmp.sendMessage(msg);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tmp.sendMessage("QUIT");
    }
}
