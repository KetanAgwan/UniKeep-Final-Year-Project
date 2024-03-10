import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class ExaminationDetails extends JFrame implements ActionListener{
    JComboBox crollno;
    Choice csem;
    String name;
    JButton search,back;
    JTable table;
    ExaminationDetails(){
        setSize(1000,475);
        setLocation(300,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Check Result");
        heading.setBounds(50,0,500,50);
        heading.setFont(new Font("Gilroy",Font.BOLD,30));
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
            crollno.setBounds(50,70,150,25);
            crollno.setBackground(Color.WHITE);
            crollno.setEditable(true);
            crollno.addActionListener(this);
            add(crollno);
        }catch (Exception e){
            e.printStackTrace();
        }

        csem = new Choice();
        fillChoice((String ) crollno.getSelectedItem());
        csem.setBounds(225,70,150,30);
        csem.setBackground(Color.white);
        add(csem);

        search = new JButton("Show Result");
        search.setBounds(420,70,120,25);
        search.addActionListener(this);
        add(search);
        back = new JButton("Back");
        back.setBounds(560,70,80,25);
        back.addActionListener(this);
        add(back);

        table = new JTable();
        table.setFont(new Font("Gilroy",Font.BOLD,13));

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,130,1000,310);
        add(jsp);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }

        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me) {
                int row = table.getSelectedRow();
                crollno.setSelectedItem(table.getModel().getValueAt(row,2).toString());
            }
        });
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search){
            try{
                Conn c = new Conn();
                ResultSet rs1 = c.s.executeQuery("select * from marks where studentid='"+crollno.getSelectedItem()+"'");
                if (rs1.next()){
                    try{
                        name = rs1.getString(1);
                        new Marks((String) crollno.getSelectedItem(),csem.getSelectedItem(),name);
//                        ResultSet r =c.s.executeQuery("select course from student where studentid='"+crollno.getSelectedItem()+"'");
//                        if (rs1.next()){
//                            fillChoice((String) crollno.getSelectedItem());
//                        }
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null, "Something is wrong!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    rs1.close();
                    ResultSet rs2 = c.s.executeQuery("select * from marks where studentid='"+crollno.getSelectedItem()+"' and semester='"+csem.getSelectedItem()+"'");
                    if (!rs2.next()){
                        JOptionPane.showMessageDialog(null, "Record not found!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Record not found!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if (ae.getSource() == crollno) {
            fillChoice((String) crollno.getSelectedItem());
        }else{
            dispose();
        }
    }

    public void fillChoice(String rollno){
        Conn c = new Conn();
        Conn c1 = new Conn();
        try{
            csem.removeAll();
            ResultSet rs1 = c1.s.executeQuery("select course from student where studentid= '"+rollno+"'");
            String sems[] = {"1st_semester","2nd_semester","3rd_semester","4th_semester","5th_semester","6th_semester","7th_semester","8th_semester"};
            if(rs1.next()){
                String course = rs1.getString(1);
                ResultSet rs = c.s.executeQuery("select * from fee where course = '"+course+"'");
                if (rs.next()){
                    int i = 2,j = 0;
                    while(i <= 5 ){
                        if (rs.getString(i).equals(" -- ")){
                            break;
                        }else{
                            csem.addItem(sems[j++]);
                            csem.addItem(sems[j++]);
                            i++;
                        }
                    }
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Something is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ExaminationDetails();
    }
}