package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.util.Random;

import static sample.Main.N;

public class Controller {

    private boolean[][] world = new boolean[N + 2][N + 2];
    private Human[][] humans = new Human[N][N];

    @FXML
    private GridPane grid;

    @FXML
    public void initialize() {
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                world[i + 1][j + 1] = random.nextBoolean();
                humans[i][j] = new Human(Human.State.valueOf(world[i + 1][j + 1]));
                grid.add(humans[i][j], i, j);
            }
        }

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                while (true) {
                    Thread.sleep(100);
                    develop();
                    // repaint();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


    private void develop() {
        boolean[][] temp = new boolean[N][N];
        int count;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                count = count(i, j);
                if (world[i][j] && count < 2) temp[i - 1][j - 1] = false;
                else if (world[i][j] && count > 3) temp[i - 1][j - 1] = false;
                else if (world[i][j]) temp[i - 1][j - 1] = world[i][j];
                else if (!world[i][j] && count == 3) temp[i - 1][j - 1] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                world[i + 1][j + 1] = temp[i][j];
                // repaint
                humans[i][j].setState(Human.State.valueOf(temp[i][j]));
            }
        }
    }

    private int count(int i, int j) {
        int count = 0;
        if (world[i - 1][j - 1]) count++;
        if (world[i - 1][j]) count++;
        if (world[i - 1][j + 1]) count++;
        if (world[i][j - 1]) count++;
        if (world[i][j + 1]) count++;
        if (world[i + 1][j - 1]) count++;
        if (world[i + 1][j]) count++;
        if (world[i + 1][j + 1]) count++;
        return count;
    }
}
