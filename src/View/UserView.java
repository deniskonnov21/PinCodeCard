package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;


public class UserView extends JFrame {
    private JTextArea text;
    protected JButton sendButton;
    private JLabel info;
    private int pinCount;

    public UserView(Controller controller) throws HeadlessException {
    }

    public void init() {
        setSize(400, 100);
        text = new JTextArea();
        sendButton = new JButton("Подтвердить");
        info = new JLabel("Введите пин-код карты");

        add(text, BorderLayout.CENTER);
        add(sendButton, BorderLayout.AFTER_LAST_LINE);
        add(info, BorderLayout.BEFORE_FIRST_LINE);


        sendButton.addActionListener(e -> {
            int pin;
            String textPin = this.text.getText();
            text.setText("");

            try {
                pin = Integer.parseInt(textPin);
                info.setText("Обработка. Ожидайте...");

                Thread thread = new Thread(() -> {
                    sendButton.setEnabled(false);
                    boolean result;
                    result = Controller.checkPin(pin);
                    String resultMessage;

                    if (result) {
                        resultMessage = "Карта подтверждена!";
                    } else {
                        resultMessage = "Неверный пин-код!";
                        pinCount = pinCount + 1;
                        sendButton.setEnabled(true);
                        if (pinCount == 0) {
                            resultMessage = resultMessage + " Осталось 3 попытки.";
                        }
                        if (pinCount == 1) {
                            resultMessage = resultMessage + " Осталось 2 попытки.";
                        }
                        if (pinCount == 2) {
                            resultMessage = resultMessage + " Осталось 1 попытка.";
                        }
                        if (pinCount == 3) {
                            sendButton.setEnabled(false);
                            text.setEnabled(false);
                            resultMessage = "Карта заблокирована. Обратитесь в информационную службу.";
                        }
                    }

                    info.setText(resultMessage);
                });
                thread.start();
            } catch (NumberFormatException e1) {
                info.setText("Введите цифры...");
            }
        });
        setVisible(true);
    }
}
