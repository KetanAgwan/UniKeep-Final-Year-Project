import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CheckFeeStatus extends JFrame implements ActionListener {
    JButton search,back;
    JComboBox crollno;
    JTable table;
    CheckFeeStatus(){
        setSize(350,600);
        setLocation(600,100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Check Fee Status Of Students");
        heading.setBounds(30,20,300,20);
        heading.setFont(new Font("Gilroy",Font.BOLD,18));
        add(heading);

        JLabel lblrollno = new JLabel("Search by ID");
        lblrollno.setFont(new Font("Gilroy",Font.PLAIN,15));
        lblrollno.setBounds(60,80,200,20);
        add(lblrollno);

        try{
            Conn c = new Conn();
            int i = 0;
            ResultSet rs = c.s.executeQuery("select * from student");
            rs.last();
            String rollnumbers[] = new String[rs.getRow()];
            rs.beforeFirst();
            while(rs.next()){
                rollnumbers[i] = rs.getString("studentid");
                i++;
            }
            crollno = new JComboBox(rollnumbers);
            crollno.setBounds(60,110,200,25);
            crollno.setBackground(Color.WHITE);
            crollno.setEditable(true);
            add(crollno);
        }catch (Exception e){
            e.printStackTrace();
        }

        search = new JButton("Search");
        search.setBounds(60,155,80,20);
        search.addActionListener(this);
        add(search);

        back = new JButton("Back");
        back.setBounds(150,155,80,20);
        back.addActionListener(this);
        add(back);

        table = new JTable();
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select name,studentid,feestatus from student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,200,getWidth(),600);
        add(jsp);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String query = "select name,studentid,feestatus from student";
        Conn c = new Conn();
        if (ae.getSource() == search){
            if (!((String) crollno.getSelectedItem()).isEmpty()){
                try{
                    ResultSet rs = c.s.executeQuery("select * from student where studentid = '"+crollno.getSelectedItem()+"'");
                    if (rs.next()){
                        query = "select name,studentid,feestatus from student where studentid = '"+crollno.getSelectedItem()+"'";
                    }else {
                        JOptionPane.showMessageDialog(null, "ID Not Found", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Something went wrong!", "Warning", JOptionPane.WARNING_MESSAGE);
                    e.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Please Enter Student ID", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            try{
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            dispose();
        }
    }

    public static void main(String[] args) {
        new CheckFeeStatus();
    }
}