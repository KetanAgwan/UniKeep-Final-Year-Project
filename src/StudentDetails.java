import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class StudentDetails extends JFrame implements ActionListener {
    JComboBox crollno;
    JTable table;
    JButton search,print,update,add,cancel;
    StudentDetails(String accessingPerson){
        setSize(1100,700);
        setLocation(300,100);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by Student ID");
        heading.setFont(new Font("Gilroy",Font.BOLD,15));
        heading.setBounds(20,20,200,20);
        add(heading);

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
            crollno.setBounds(200,20,150,20);
            crollno.setBackground(Color.WHITE);
            crollno.setEditable(true);
            add(crollno);
        }catch (Exception e){
            e.printStackTrace();
        }

        table = new JTable();

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,100,1100,600);
        add(jsp);

        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        if (accessingPerson.equals("teacher")){
            cancel = new JButton("cancel");
            cancel.setBounds(220,70,80,20);
            cancel.addActionListener(this);
            add(cancel);
        }else if (accessingPerson.equals("admin")){
            update = new JButton("Update");
            update.setBounds(220,70,80,20);
            update.addActionListener(this);
            add(update);

            add = new JButton("Add");
            add.setBounds(320,70,80,20);
            add.addActionListener(this);
            add(add);

            cancel = new JButton("cancel");
            cancel.setBounds(420,70,80,20);
            cancel.addActionListener(this);
            add(cancel);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String query;
        if (ae.getSource() == search){
            if (!((String) crollno.getSelectedItem()).isEmpty())
                query = "select * from student where studentid='"+crollno.getSelectedItem()+"'";
            else
                query = "select * from student";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == print){
            try{
                table.print();
            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
//            setVisible(false);
            new UpdateStudent();
        } else if (ae.getSource() == add) {
//            setVisible(false);
            new AddStudent();
        } else if (ae.getSource() == cancel) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new StudentDetails("admin");
    }
}