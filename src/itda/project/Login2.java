package itda.project;


import javax.swing.JFrame;
//main method for the login2

public class Login2 {
    //creates the frame to display with all these parameters
    public static void main(String[] a) {
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Employee Login");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setBounds(500, 500, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);  
        frame.setLocationRelativeTo(null);
         
    
    }

   

}