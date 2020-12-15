package server;

import game.*;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server {
    public static final List<player> players = new ArrayList<>();
    public static final List<color> colorList = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        if(args.length != 1){
            throw new Exception("No players number found. Please choose 2, 3, 4 or 6 players mode");
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

        colorList.addAll(Arrays.asList(color.values()));

        ExecutorService executorService = Executors.newFixedThreadPool(mode);
        try (ServerSocket socket = new ServerSocket(50000)) {
            System.out.println("Server is running at port 50000");
            while(players.size() < mode) {
                player tmp = new player(socket.accept(), colorList.get(players.size()));
                players.add(tmp);
                executorService.execute(tmp);
                System.out.println("Connected players:" + players.size());
            }
            new game(players);
        }
    }
}
