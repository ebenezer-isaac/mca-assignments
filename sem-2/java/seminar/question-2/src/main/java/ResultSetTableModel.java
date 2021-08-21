import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel {
    private final Connection connection;
    private final Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;

    private boolean connectedToDatabase;

    public ResultSetTableModel(String url, String username, String password, String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        connectedToDatabase = true;
        setQuery(query);
    }

    public Class getColumnClass(int column) throws IllegalStateException {
        if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
        try {
            String className = metaData.getColumnClassName(column + 1);
            return Class.forName(className);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return Object.class; // if problems occur above, assume type Object69

    }

    public String getColumnName(int column) throws IllegalStateException {
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected to Database");// determine column name
        try {
            return metaData.getColumnName(column + 1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "";
    }

    public void setQuery(String query) throws SQLException, IllegalStateException {
        System.out.println("inside set query");
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");     // specify query and execute it152
        }
        resultSet = statement.executeQuery(query);// obtain metadata for ResultSet155
        metaData = resultSet.getMetaData();     // determine number of rows in ResultSet158
        resultSet.last(); // move to last row159
        numberOfRows = resultSet.getRow();
        System.out.println(numberOfRows);
        fireTableStructureChanged();

    }

    public void disconnectFromDatabase() {
        if (connectedToDatabase) {
            // close Statement and Connection
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } finally {
                connectedToDatabase = false;
            }
        }
    }

    @Override
    public int getRowCount() {
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected to Database");
        return numberOfRows;
    }

    @Override
    public int getColumnCount() {
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected to Database");  // determine number of columns79
        try {
            return metaData.getColumnCount();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
        try {
            resultSet.absolute(rowIndex + 1);
            return resultSet.getObject(columnIndex + 1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "";
    }
}