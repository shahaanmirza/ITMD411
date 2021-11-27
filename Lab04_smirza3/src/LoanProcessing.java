import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class LoanProcessing extends BankRecords {
    public static void main(String[] args) throws SQLException {
        BankRecords br = new BankRecords();
        br.readData();
        Dao dao = new Dao();
        dao.createTable();
        dao.insertRecords(robjs); // perform inserts
        ResultSet rs = dao.retrieveRecords(); // fill result set object
        // Create heading for display
        System.out.println("Loan Analysis Report\n");
        System.out.println("ID\t\tIncome\t\tPep");
        // Extract data from result set
        while (rs.next()) {
            // Retrieve data by column name (i.e., for id,income,pep)
            try {
                String retrieveData =
                        rs.getInt("id") + "\t\t" +
                        rs.getDouble("income") + "\t" +
                        rs.getString("pep");
                // Display values for id,income,pep
                System.out.println(retrieveData);
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
        }
        rs.close(); // closes result set object
    }
}
