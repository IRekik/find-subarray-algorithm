import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame {
    private boolean answeredDisplayed = false;
    private JPanel panel;
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, delButton, clrButton;
    private Font font = new Font("Arial", Font.PLAIN, 20);

    // Constructor
    public CalculatorApp() {
        // Set up the JFrame
        this.setTitle("Calculator");
        this.setSize(350, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        // Inizialize the components
        panel = new JPanel();
        panel.setBounds(50, 100, 250, 300);
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        textField = new JTextField();
        textField.setBounds(50, 25, 250, 50);
        textField.setFont(font);
        textField.setEditable(false);

        numberButtons = new JButton[10];
        functionButtons = new JButton[9];

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(font);
            numberButtons[i].addActionListener(new NumberButtonListener());
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("D");
        clrButton = new JButton("C");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        for (int i = 0; i < 8; i++) {
            functionButtons[i].setFont(font);
            functionButtons[i].addActionListener(new FunctionButtonListener());
        }

        // Add components to the panel
        panel.add(delButton);
        panel.add(clrButton);
        panel.add(mulButton);
        panel.add(divButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(equButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);

        // Add components to the JFrame
        this.add(panel);
        this.add(textField);

        this.setVisible(true);
    }

    // Listener for number buttons
    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (answeredDisplayed) {
                // If answer is displayed and user starts
                // clear the text and resets the flag
                if (answeredDisplayed) {
                    textField.setText("");
                    answeredDisplayed = false;
                }
            }
            JButton button = (JButton) e.getSource();
            textField.setText(textField.getText() + button.getText());

        }
    }

    // Evaluates if expression is completed
    public boolean isExpressionCompleted(String button) {
        if (button.equals("=")) {
            return true;
        }
        return false;
    }

    // Listener for number buttons
    private class FunctionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            textField.setText(textField.getText() + button.getText());
            if (isExpressionCompleted(button.getText())) {
                OperationComputer op = new OperationComputer(textField.getText());
            } else if (button.getText().equals("D")) {
                String text = textField.getText();
                System.out.println(textField.getText());
                if (!text.isEmpty()) {
                    textField.setText(text.substring(0, text.length() - 2));
                }
            } else if (button.getText().equals("C")) {
                // Clear the text field
                textField.setText("");
            }
        }
    }

    // Operation computer
    private class OperationComputer {
        public String operation;

        public OperationComputer(String operation) {
            this.operation = operation;
            doOperations(operation);
        }
        public void doOperations (String operation){
            int operatorIndex = -1;
            char operator = '0';
            double result = 0;
            int n1, n2;
            for (int i = 0; i < operation.length(); i++) {
                if (!Character.isDigit(operation.charAt(i))) {
                    operatorIndex = i;
                    operator = operation.charAt(i);
                    break;
                }
            }
            n1 = Integer.parseInt(operation.substring(0, operatorIndex));
            n2 = Integer.parseInt(operation.substring(operatorIndex+1, operation.length() - 1));
            switch (operator) {
                case '+':
                    result = n1 + n2;
                    break;
                case '-':
                    result = n1 - n2;
                    break;
                case '*':
                    result = n1 * n2;
                    break;
                case '/':
                    result = n1 / n2;
                    break;
            }
            displayAnswer(result);
        }
        public void displayAnswer(double result) {
            textField.setText(String.valueOf(result));
            answeredDisplayed = true;

        }
    }
}
