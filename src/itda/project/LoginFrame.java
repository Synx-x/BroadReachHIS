package itda.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginFrame extends JFrame implements ActionListener {
// create and initalize variables

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN PATIENT");
    JButton loginButton2 = new JButton("LOGIN Employee");
    public String pwdText;
    public String userText;

    LoginFrame() {
        //create the layout
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        this.setBounds(500, 500, 500, 500);
        this.setTitle("Patient Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }
    // set layout for buttton, text and inputs

    public void setLocationAndSize() {
        userLabel.setBounds(200, 120, 100, 30);
        passwordLabel.setBounds(200, 190, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        loginButton.setBounds(50, 320, 170, 30);
        loginButton2.setBounds(265, 320, 170, 30);

    }
//add buttons, text, and inputs to the frame

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(loginButton);
        container.add(loginButton2);

    }
// adding the action listener to the buttons

    public void addActionEvent() {

        loginButton.addActionListener(this);
        loginButton2.addActionListener(this);
    }

    // adding the action listener to the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            try {
                FileWriter LoginDetailsWriter = new FileWriter("LoginDetails.txt");
                PrintWriter PrintToFile = new PrintWriter(LoginDetailsWriter);

                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/broadreach_health_information_system", "root", "");

                final String userText = userTextField.getText();
                final String pwdText = passwordField.getText();

                String query = "Select loginName, password from logindetails, patient where loginName=? and password=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, userText);
                statement.setString(2, pwdText);

                ResultSet set = statement.executeQuery();
                if (set.next()) {

                    JOptionPane.showMessageDialog(this, "Login Successful \n" + "" + "Logged in as: " + userText);

                    PrintToFile.print(userText);
                    PrintToFile.close();

                    patientView PDetails = new patientView();

                    PDetails.setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                }

            } catch (SQLException ex) {
                Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == loginButton2) {

            JOptionPane.showMessageDialog(null, "Switching to Employee Login...");

            LoginFrame2 lframe = new LoginFrame2();

            lframe.setVisible(true);

            this.setVisible(false);
        } else {
            return;
        }

    }

}
