import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable{

    Thread t;
    Splash(){

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000,700,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        t = new Thread(this);
        t.start();

        setVisible(true);
        for (int i = 0,x = 1; i <= 600; i+=5, x++) {
            setLocation(600 - ((i + x) / 2),380 - (i / 2));
            setSize(i + 3 * x,i + x);
        }
    }

    public void run(){
        try{
            Thread.sleep(3000);
            dispose();
            new Login();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Something is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
