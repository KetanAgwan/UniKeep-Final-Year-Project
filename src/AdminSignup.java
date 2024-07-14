import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AdminSignup extends JFrame implements ActionListener {
    JButton adminsignup,cancel;
    JTextField tfname,tfemail;
    JPasswordField tfpassword,tfconfirm;
    FormValidation val = new FormValidation();

    AdminSignup(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setTitle("Admin Signup");

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(40,20,100,20);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(150,20,200,30);
        add(tfname);

        JLabel lblusername = new JLabel("Email");
        lblusername.setBounds(40,60,100,20);
        add(lblusername);

        tfemail = new JTextField();
        tfemail.setBounds(150,60,200,30);
        add(tfemail);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40,100,100,20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150,100,200,30);
        add(tfpassword);

        JLabel lblcnfpassword = new JLabel("Confirm Password");
        lblcnfpassword.setBounds(40,140,150,20);
        add(lblcnfpassword);

        tfconfirm = new JPasswordField();
        tfconfirm.setBounds(150,140,200,30);
        add(tfconfirm);

        adminsignup = new JButton("Signup");
        adminsignup.setBounds(40,180,120,40);
        adminsignup.setBackground(Color.decode("#333"));
        adminsignup.setForeground(Color.white);
        adminsignup.setFont(new Font("Tahoma",Font.BOLD,12));
        adminsignup.addActionListener(this);
        add(adminsignup);

        cancel = new JButton("Cancel");
        cancel.setBounds(180,180,120,40);
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

        setSize(550,350);
        setLocation(500,250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == adminsignup) {
            String name = tfname.getText();
            String email = tfemail.getText();
            String password = tfpassword.getText();
            String confirmpass = tfconfirm.getText();
            if (val.isEmailValid(email)){
                if (password.equals(confirmpass)){
                    String query = "insert into adminlogin values('"+name+"','"+email+"','"+password+"')";
                    try{
                        Conn c = new Conn();
                        int k = c.s.executeUpdate(query);
                        if (k > 0){
                            JOptionPane.showMessageDialog(null, "Admin Signed up Sucessfully!");
                            int choice = JOptionPane.showConfirmDialog(null, "Login As Admin Now ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION){
                                dispose();
                                new Login();
                            }else{
                                dispose();
                            }
                        }
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Something wen wrong!", "Warning", JOptionPane.WARNING_MESSAGE);
                        e.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Password do not match!", "Warning", JOptionPane.WARNING_MESSAGE);
                }

            }else{

            }
       }else if (ae.getSource() == cancel) {
            dispose();
        }
    }
    public static void main(String[] args) {
        new AdminSignup();
    }
}
