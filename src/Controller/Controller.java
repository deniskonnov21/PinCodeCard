package Controller;

import Model.Model;

public class Controller {
    private static Model model;

    public Controller(Model model) {
        Controller.model = model;
    }

    public static boolean checkPin(int pin) {
        return model.getPin() == pin;
    }
}
