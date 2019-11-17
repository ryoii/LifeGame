package sample;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import static sample.Main.WIDTH;

public class Human extends Rectangle {

    private State state;

    public Human() {
        super(WIDTH, WIDTH);
    }

    public Human(State state) {
        super(WIDTH, WIDTH, state.getPaint());
    }

    public void setState(State state) {
        this.state = state;
        this.setFill(state.getPaint());
    }

    public enum State {
        ALIVE,
        DIED;

        private static Paint alive = Paint.valueOf("black");
        private static Paint died = Paint.valueOf("white");

        public static State valueOf(boolean alive) {
            return alive ? ALIVE : DIED;
        }

        public Paint getPaint() {
            switch (this) {
                case ALIVE:
                    return alive;
                case DIED:
                    return died;
            }
            return null;
        }
    }
}
