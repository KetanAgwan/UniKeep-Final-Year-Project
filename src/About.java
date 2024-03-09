import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {

    About(){
        setSize(700,450);
        setLocation(400,150);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel heading = new JLabel("<html>UniKeep - <br> College Management System</html>");
        heading.setBounds(50,10,500,80);
        heading.setFont(new Font("Gilroy",Font.BOLD,30));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/mypic.png"));
        Image i2 = i1.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(385,70,300,300);
        add(image);

        JLabel name = new JLabel("<html>Developer - <br>Agwan Ketan Harish</html>");
        name.setBounds(50,100,500,40);
        name.setFont(new Font("Gilroy",Font.BOLD,15));
        add(name);

        JLabel desc = new JLabel("<html><b>Discription</b>  -  <br> <br> UniKeep College Management System is a Java-based <br> project designed to streamline administrative tasks within<br> educational institutions. It offers a user-friendly interface to <br> manage various aspects such as student and teacher information,<br> admissions, leave requests, and fee structures. With features <br> like student and teacher info management, designation tracking, and <br> centralized fee management, UniKeep simplifies day-to-day operations <br> for colleges, making it easier to maintain accurate records and <br> improve administrative efficiency.</html>");
        desc.setBounds(50,10,500,500);
        desc.setFont(new Font("Gilroy",Font.PLAIN,13));
        add(desc);


        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {

    }

    public static void main(String[] args) {
        new About();
    }
}