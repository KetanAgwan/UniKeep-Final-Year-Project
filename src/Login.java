import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JButton adminlogin,teacherlogin,cancel;
    JTextField tfusername;
    JPasswordField tfpassword;
    static Project currentInstance;

    Login(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setTitle("Admin / Teacher Login");

        JLabel lblusername = new JLabel("Email");
        lblusername.setBounds(40,20,100,20);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150,20,200,30);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40,60,100,20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150,60,200,30);
        add(tfpassword);

        adminlogin = new JButton("Admin Login");
        adminlogin.setBounds(20,140,100,30);
        adminlogin.setBackground(Color.decode("#333"));
        adminlogin.setForeground(Color.white);
        adminlogin.setFont(new Font("Tahoma",Font.BOLD,10));
        adminlogin.addActionListener(this);
        add(adminlogin);

        teacherlogin = new JButton("Teacher Login");
        teacherlogin.setBounds(140,140,120,30);
        teacherlogin.setBackground(Color.decode("#333"));
        teacherlogin.setForeground(Color.white);
        teacherlogin.setFont(new Font("Tahoma",Font.BOLD,10));
        teacherlogin.addActionListener(this);
        add(teacherlogin);

        cancel = new JButton("Cancel");
        cancel.setBounds(280,140,80,30);
        cancel.setBackground(Color.decode("#333"));
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Tahoma",Font.BOLD,12));
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350,0,200,200);
        add(image);


        setSize(550,250);
        setLocation(500,250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == adminlogin) {
            String email = tfusername.getText();
            String password = tfpassword.getText();
            String query = "select name from adminlogin where email='"+email+"' and password='"+password+"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    dispose();
                    if (Project.getInstanceCount() > 0){
                        currentInstance.stopInstance().dispose();
                    }
                    currentInstance = new Project("admin",rs.getString(1));
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid E-mail or password!");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if (ae.getSource() == teacherlogin) {
            String email = tfusername.getText();
            String password = tfpassword.getText();
            String query = "select name from teacher where email='"+email+"' and password='"+password+"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    dispose();
                    if (Project.getInstanceCount() > 0){
                        currentInstance.stopInstance().dispose();
                    }
                    currentInstance = new Project("teacher",rs.getString(1));
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Email Or Password!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if (ae.getSource() == cancel) {
            dispose();
        }
    }
    public static void main(String[] args) {
        new Login();
    }
}
