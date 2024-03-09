import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public  class FeeStructure extends JFrame {
    FeeStructure(){
        setSize(500,400);
        setLocation(500,100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Fee Structure");
        heading.setBounds(50,10,400,30);
        heading.setFont(new Font("Gilroy",Font.BOLD,30));
        add(heading);

        JTable table = new JTable();

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from fee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,60,500,400);
        add(jsp);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FeeStructure();
    }
}