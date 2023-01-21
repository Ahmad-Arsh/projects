import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {
    private JTextField displayField;
    private double result = 0;
    private String operator = "=";
    private boolean calculating = true;

    public Main() {
        setTitle("Simple Calculator");
        setSize(350, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        contentPane.add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        JButton button;
        for (int i = 0; i < buttonLabels.length; i++) {
            button = new JButton(buttonLabels[i]);
            button.addActionListener(new ButtonHandler());
            buttonPanel.add(button);
        }

        contentPane.add(buttonPanel, BorderLayout.CENTER);
        setContentPane(contentPane);
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if ('0' <= command.charAt(0) && command.charAt(0) <= '9' || command.equals(".")) {
                if (calculating) {
                    displayField.setText(command);
                } else {
                    displayField.setText(displayField.getText() + command);
                }
                calculating = false;
            } else {
                if (calculating) {
                    if (command.equals("-")) {
                        displayField.setText(command);
                        calculating = false;
                    } else {
                        operator = command;
                    }
                } else {
                    double x = Double.parseDouble(displayField.getText());
                    calculate(x);
                    operator = command;
                    calculating = true;
                }
            }
        }

        private void calculate(double n) {
            if (operator.equals("+")) {
                result += n;
            } else if (operator.equals("-")) {
                result -= n;
            } else if (operator.equals("*")) {
                result *= n;
            } else if (operator.equals("/")) {
                result /= n;
            } else if (operator.equals("=")) {
                result = n;
            }

            displayField.setText("" + result);
        }
    }

    public static void main(String[] args) {
        Main calculator = new Main();
        calculator.setVisible(true);
    }
}
