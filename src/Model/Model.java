package Model;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Model {

    public int getPin() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1234;
    }

}
