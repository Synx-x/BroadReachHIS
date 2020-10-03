package itda.project;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class HCWView extends JFrame {

    public HCWView() {

        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database
        String url = "jdbc:mysql://127.0.0.1:3306/broadreach_health_information_system";
        String password = "";
        //run query
        String sql = "select * from scenario2hcworker;";

        try {
   // creating object to read from file
            FileReader NewFileReader = new FileReader("LoginDetails.txt");
            BufferedReader Read = new BufferedReader(NewFileReader);

         
            // removing @localhost string from password to allow for verification on database
            String master = Read.readLine();
            String target = "@localhost";
            String replacement = "";
            String processed = master.replace(target, replacement);

            String user = processed;

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            for (int i = 1; i <= columns; i++) {
                columnNames.add(md.getColumnName(i));
            }

            //  Get row data
            while (rs.next()) {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++) {
                    row.add(rs.getObject(i));
                }

                data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(patientView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(patientView.class.getName()).log(Level.SEVERE, null, ex);
        }

        // parse elements from ArrayLists to Vectors
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i < data.size(); i++) {
            ArrayList subArray = (ArrayList) data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++) {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++) {
            columnNamesVector.add(columnNames.get(i));
        }

        //  Create table with database data    
        JTable table = new JTable(dataVector, columnNamesVector) {
            public Class getColumnClass(int column) {
                for (int row = 0; row < getRowCount(); row++) {
                    Object o = getValueAt(row, column);

                    if (o != null) {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        //sets the tables size
        this.setBounds(10, 10, 1370, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
