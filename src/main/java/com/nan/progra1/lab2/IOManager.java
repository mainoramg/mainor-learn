package com.nan.progra1.lab2;

/**
 * handles graphical interaction with the user
 *
 * @author Wendy Ram&iacute;rez
 */
import javax.swing.JOptionPane;

public class IOManager {

    public static int requestInt(String message) {
        int result = -2;

        try {
            String input = JOptionPane.showInputDialog(message);
            if (input == null) { // null means cancel option
                result = -1;
            } else {
                result = Integer.parseInt(input);
            }
        } catch (Exception e) {}

        return result;
    }

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static int showInputForm(String label, boolean allowZero) {
        boolean continueMenu = true;
        int value = 0;

        while (continueMenu) {
            value = IOManager.requestInt(label);

            if ((value >= 0 && allowZero) || (value > 0 && !allowZero)) {
                continueMenu = false;
            } else {
                IOManager.showMessage("Valor invalido!");
            }
        }

        return value;
    }
}
