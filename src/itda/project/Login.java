package itda.project;


import javax.swing.JFrame;

//main method for the login

public class Login  {
    public static void main(String[] a) {
        
        //creates the frame to display with all these parameters
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Patient Login");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setBounds(500, 500, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
      
      
        
    }

}