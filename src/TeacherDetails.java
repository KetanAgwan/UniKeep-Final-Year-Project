import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class TeacherDetails extends JFrame implements ActionListener {
    JComboBox cempid;
    JTable table;
    JButton search,print,update,add,cancel;
    TeacherDetails(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by Employee Id");
        heading.setFont(new Font("Gilroy",Font.BOLD,15));
        heading.setBounds(20,20,200,20);
        add(heading);

        try{
            Conn c = new Conn();
            int i = 0;
            ResultSet rs = c.s.executeQuery("select * from teacher");
            rs.last();
            String rollnumbers[] = new String[rs.getRow()];
            rs.beforeFirst();
            while(rs.next()){
                rollnumbers[i] = rs.getString("empId");
                i++;
            }
            cempid = new JComboBox(rollnumbers);
            cempid.setBounds(200,20,150,20);
            cempid.setBackground(Color.WHITE);
            cempid.setEditable(true);
            add(cempid);
        }catch (Exception e){
            e.printStackTrace();
        }

        table = new JTable();

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from teacher");
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

        setSize(1100,700);
        setLocation(300,100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String query;
        if (ae.getSource() == search){
            if (!((String) cempid.getSelectedItem()).isEmpty())
                query = "select name,fname,empId,dob,address,phone,email,class_x,class_xii,adhar,department,dsgntype from teacher where empId='"+ cempid.getSelectedItem()+"'";
            else
                query = "select name,fname,empId,dob,address,phone,email,class_x,class_xii,adhar,department,dsgntype from teacher";
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
            new UpdateTeacher();
        } else if (ae.getSource() == add) {
            new AddTeacher();
        } else if (ae.getSource() == cancel) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new TeacherDetails();
    }
}