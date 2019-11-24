package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.util.Random;

import static sample.Main.N;

public class Controller {

    private int[][] world = new int[N][N];
    private Human[][] humans = new Human[N][N];

    @FXML
    private GridPane grid;

    @FXML
    public void initialize() {
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                world[i][j] = random.nextInt() & 1;
                humans[i][j] = new Human(Human.State.valueOf(world[i][j]));
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
        int count;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count = count(i, j);
                if (count == 3 || count - world[i][j] == 3) {
                    world[i][j] |= 2;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                world[i][j] >>= 1;
                // repaint
                humans[i][j].setState(Human.State.valueOf(world[i][j]));
            }
        }
    }

    private int count(int x, int y) {
        int count = 0;
        for (int i = Math.max(0, x - 1); i < Math.min(N, x + 2); i++) {
            for (int j = Math.max(0, y - 1); j < Math.min(N, y + 2); j++) {
                count += world[i][j] & 1;
            }
        }
        return count;
    }
}
