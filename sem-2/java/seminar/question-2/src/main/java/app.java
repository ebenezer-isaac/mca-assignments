// Java program to create a blank text
// field of definite number of columns.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class app extends JFrame implements ActionListener {
    static JTextField queryField;
    static JFrame frame;
    static JButton button;
    static JLabel queryLabel;

    app() {
    }

    public static void main(String[] args) {
        frame = new JFrame("Library SQL Console");
        queryLabel = new JLabel("Query : ");
        button = new JButton("Execute");
        app te = new app();
        button.addActionListener(te);
        queryField = new JTextField();


        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(padding);
        Border black = BorderFactory.createBevelBorder(4);

        frame.setContentPane(contentPanel);
        frame.add(queryLabel);
        frame.add(queryField);
        queryField.setColumns(150);
        queryField.set
        queryField.setLineWrap(true);
        queryField.setBorder(black);
        //frame.add(panel);
        frame.add(button);

        frame.setSize(1000, 800);
        frame.setVisible(true);
    }

    // if the button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("submit")) {
            // set the text of the label to the text of the field
            queryLabel.setText(queryField.getText());

            // set the text of field to blank
            queryField.setText(" ");
        }
    }
}
