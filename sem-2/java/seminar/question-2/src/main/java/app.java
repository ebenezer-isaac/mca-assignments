import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;

public class app extends JFrame {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DEFAULT_QUERY = "SELECT * FROM Authors";
    private static ResultSetTableModel tableModel;

    public static void main(String[] args) {
        try {
            tableModel = new ResultSetTableModel(DATABASE_URL, USERNAME, PASSWORD, DEFAULT_QUERY);
            final JTextArea queryArea = new JTextArea(DEFAULT_QUERY, 10, 150);
            queryArea.setWrapStyleWord(true);
            queryArea.setLineWrap(true);

            JScrollPane scrollPane = new JScrollPane(queryArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            JButton submitButton = new JButton("Submit Query");

            Box boxNorth = Box.createHorizontalBox();
            boxNorth.add(scrollPane);
            boxNorth.add(submitButton);

            JTable resultTable = new JTable(tableModel);

            JLabel filterLabel = new JLabel("Filter : ");
            final JTextField filterText = new JTextField();
            JButton filterButton = new JButton("Apply Filter");
            Box boxSouth = Box.createHorizontalBox();

            boxSouth.add(filterLabel);
            boxSouth.add(filterText);
            boxSouth.add(filterButton);

            JFrame window = new JFrame("Displaying Query Results");

            window.add(boxNorth, BorderLayout.NORTH);
            window.add(new JScrollPane(resultTable), BorderLayout.CENTER);
            window.add(boxSouth, BorderLayout.SOUTH);

            submitButton.addActionListener(event -> {
                        System.out.println("inside submit button event");
                try {
                    tableModel.setQuery(queryArea.getText());
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);

                    try {
                        tableModel.setQuery(DEFAULT_QUERY);
                        queryArea.setText(DEFAULT_QUERY);
                    } catch (SQLException sqlException2) {
                        JOptionPane.showMessageDialog(null, sqlException2.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
                        tableModel.disconnectFromDatabase();
                        System.exit(1);
                    }
                }
            }
            );

            final TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
            resultTable.setRowSorter(sorter);

            // pass filter text to listener
            filterButton.addActionListener(e -> {
                System.out.println("inside filter action");
                String text = filterText.getText();
                System.out.println(text);
                if (text.length() == 0)
                    sorter.setRowFilter(null);
                else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter(text));
                    } catch (PatternSyntaxException pse) {
                        JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
            );
            window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            window.setSize(1000, 500);
            window.setVisible(true);

            window.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent event) {
                    tableModel.disconnectFromDatabase();
                    System.exit(0);
                }
            });

        } catch (Exception sqlException) {
            JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
            sqlException.printStackTrace();
            tableModel.disconnectFromDatabase();
            System.exit(1);
        }
    }
}