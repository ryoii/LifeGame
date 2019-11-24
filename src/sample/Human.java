package sample;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import static sample.Main.SIZE;

class Human extends Rectangle {

    Human(State state) {
        super(SIZE, SIZE, state.getPaint());
    }

    void setState(State state) {
        this.setFill(state.getPaint());
    }

    public enum State {
        ALIVE,
        DIED;

        private static Paint alive = Paint.valueOf("black");
        private static Paint died = Paint.valueOf("white");

        public static State valueOf(int alive) {
            return (alive & 1) == 0 ? DIED : ALIVE;
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
