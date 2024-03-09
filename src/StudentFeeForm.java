import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class StudentFeeForm extends JFrame implements ActionListener {
    JComboBox crollno,cbcourse;
    JButton cancel,submit;
    JLabel name,fname,lblfee;
    Conn c = new Conn();
    String course[];
    JTextField feeTxtField;

    Choice cyear = new Choice();
    StudentFeeForm(){
        setSize(500,500);
        setLocation(500,150);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Student Fee Form");
        heading.setBounds(150,30,300,20);
        heading.setFont(new Font("Gilroy",Font.BOLD,20));
        add(heading);

        JLabel lblsearchrno = new JLabel("Select Student ID Number :");
        lblsearchrno.setFont(new Font("Gilroy",Font.BOLD,15));
        lblsearchrno.setBounds(50,100,200,20);
        add(lblsearchrno);

        try{
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
            crollno.setBounds(250,100,200,20);
            crollno.setBackground(Color.WHITE);
            crollno.setEditable(true);
            crollno.addActionListener(this);
            add(crollno);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Something is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }

        JLabel lblname = new JLabel("Name :");
        lblname.setFont(new Font("Gilroy",Font.BOLD,15));
        lblname.setBounds(50,140,200,20);
        add(lblname);

        name = new JLabel();
        name.setFont(new Font("Gilroy",Font.BOLD,15));
        name.setBounds(250,140,200,20);
        add(name);

        JLabel lblfname = new JLabel("Father's Name :");
        lblfname.setFont(new Font("Gilroy",Font.BOLD,15));
        lblfname.setBounds(50,180,200,20);
        add(lblfname);

        fname = new JLabel();
        fname.setFont(new Font("Gilroy",Font.BOLD,15));
        fname.setBounds(250,180,200,20);
        add(fname);

        try {
            ResultSet rs = c.s.executeQuery("select * from student where studentid='"+crollno.getSelectedItem()+"'");
            while(rs.next()){
                name.setText(rs.getString(1));
                fname.setText(rs.getString(2));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Something is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }

        JLabel lblcourse = new JLabel("Course :");
        lblcourse.setBounds(50,220,200,20);
        lblcourse.setFont(new Font("Gilroy",Font.BOLD,15));
        add(lblcourse);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from fee");
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();
            int i = 0;
            course = new String[row];
            while(rs.next()){
                course[i] = rs.getString(1);
                i++;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Something is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
        cbcourse = new JComboBox(course);
        cbcourse.setBounds(250,220,200,20);
        cbcourse.setBackground(Color.WHITE);
        cbcourse.addActionListener(this);
        add(cbcourse);

        JLabel lblyear = new JLabel("Year :");
        lblyear.setBounds(50,260,200,20);
        lblyear.setFont(new Font("Gilroy",Font.BOLD,15));
        add(lblyear);

        cyear.setBounds(250,260,200,20);
        cyear.setBackground(Color.white);
        cyear.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    String selectedValue = cyear.getSelectedItem();
                    calcFee(selectedValue);
                }
            }
        });
        fillChoice((String)cbcourse.getSelectedItem());
        add(cyear);

        lblfee = new JLabel("Total Fee :");
        lblfee.setBounds(50,300,200,20);
        lblfee.setFont(new Font("Gilroy",Font.BOLD,15));
        add(lblfee);

        feeTxtField = new JTextField();
        feeTxtField.setBounds(250,300,200,20);
        feeTxtField.setEditable(false);
        add(feeTxtField);
        calcFee(cyear.getSelectedItem());

        submit = new JButton("Submit");
        submit.setBounds(100,375,120,40);
        submit.setBackground(Color.decode("#333"));
        submit.setForeground(Color.white);
        submit.setFont(new Font("Tahoma",Font.BOLD,13));
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Back");
        cancel.setBounds(250,375,120,40);
        cancel.setBackground(Color.decode("#333"));
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Tahoma",Font.BOLD,13));
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cancel){
            dispose();
        } else if (ae.getSource() == submit) {
            try{
                Conn c = new Conn();
                int isInserted = c.s.executeUpdate("update student set feestatus='paid' where studentid='"+crollno.getSelectedItem()+"'");
                if (isInserted > 0){
                    JOptionPane.showMessageDialog(null,"Fee paid sucessfully.");
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Something is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Something is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
            }
        }else{
            JComboBox source = (JComboBox) ae.getSource();
            if (source == crollno){
                String selectedItem = (String) source.getSelectedItem();
                try{
                    ResultSet rs = c.s.executeQuery("select * from student where studentid='"+selectedItem+"'");
                    rs.last();
                    int row = rs.getRow();
                    rs.beforeFirst();
                    if (row > 0){
                        while(rs.next()){
                            name.setText(rs.getString(1));
                            fname.setText(rs.getString(2));
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Invalid Student ID!", "Warning", JOptionPane.WARNING_MESSAGE);
                        name.setText("");
                        fname.setText("");
                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Something is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
                    e.printStackTrace();
                }
            }else if (source == cbcourse){
                String selectedItem = (String) source.getSelectedItem();
                fillChoice(selectedItem);
                calcFee(cyear.getSelectedItem());
            }else if (ae.getSource() == cyear){
                String selectedItem = (String) source.getSelectedItem();
                fillChoice(selectedItem);
            }
        }
    }


    public void fillChoice(String courseName){
        try{
            cyear.removeAll();
            String years[] = {"First_Year","Second_Year","Third_Year","Fourth_Year"};
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from fee where course = '"+courseName+"'");
            if(rs.next()){
                int i = 2,j = 0;
                while(i <=5 ){
                    if (rs.getString(i).equals(" -- ")){
                       break;
                    }else{
                        cyear.addItem(years[j]);
                        i++;
                        j++;
                    }
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Something is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    public void calcFee(String year){
        try{
           Conn c =  new Conn();
           ResultSet rs = c.s.executeQuery("select * from fee where course = '"+cbcourse.getSelectedItem()+"'");
           if (rs.next()){
               feeTxtField.setText(rs.getString(year));
           }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Something is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new StudentFeeForm();
    }
}