import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class EnterMarks extends JFrame implements ActionListener {
    JComboBox crollno;
    Choice cbsemister;
    JTextField tfsub1,tfsub2,tfsub3,tfsub4,tfsub5,tfmarks1,tfmarks2,tfmarks3,tfmarks4,tfmarks5;
    JButton submit,cancel;
    EnterMarks(){
        setSize(500,550);
        setLocation(500,100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading = new JLabel("Enter marks of student");
        heading.setBounds(50,0,500,50);
        heading.setFont(new Font("Gilroy",Font.BOLD,20));
        add(heading);

        JLabel lblsearchrno = new JLabel("Student ID");
        lblsearchrno.setFont(new Font("Gilroy",Font.BOLD,15));
        lblsearchrno.setBounds(50,70,150,20);
        add(lblsearchrno);

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
            crollno.setBounds(200,70,150,20);
            crollno.setBackground(Color.WHITE);
            crollno.setEditable(true);
            add(crollno);
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel lblmemister = new JLabel("Select semister");
        lblmemister.setFont(new Font("Gilroy",Font.BOLD,15));
        lblmemister.setBounds(50,110,150,20);
        add(lblmemister);

        String semister[] = {"1st Semister","2nd Semister","3rd Semister","4th Semister","5th Semister","6th Semister"};
        cbsemister = new Choice();
        for (int i = 0; i < semister.length; i++) {
            cbsemister.addItem(semister[i]);
        }
        cbsemister.setBounds(200,110,150,20);
        cbsemister.setBackground(Color.white);
        add(cbsemister);

        JLabel lblentersubject = new JLabel("Enter Subjects");
        lblentersubject.setFont(new Font("Gilroy",Font.BOLD,15));
        lblentersubject.setBounds(80,170,200,40);
        add(lblentersubject);

        JLabel lblentermarks = new JLabel("Enter Marks");
        lblentermarks.setFont(new Font("Gilroy",Font.BOLD,15));
        lblentermarks.setBounds(300,170,200,40);
        add(lblentermarks);

        tfsub1 = new JTextField();
        tfsub1.setBounds(30,230,200,25);
        add(tfsub1);

        tfsub2 = new JTextField();
        tfsub2.setBounds(30,270,200,25);
        add(tfsub2);

        tfsub3 = new JTextField();
        tfsub3.setBounds(30,310,200,25);
        add(tfsub3);

        tfsub4 = new JTextField();
        tfsub4.setBounds(30,350,200,25);
        add(tfsub4);

        tfsub5 = new JTextField();
        tfsub5.setBounds(30,390,200,25);
        add(tfsub5);

        tfmarks1 = new JTextField();
        tfmarks1.setBounds(250,230,200,25);
        add(tfmarks1);

        tfmarks2 = new JTextField();
        tfmarks2.setBounds(250,270,200,25);
        add(tfmarks2);

        tfmarks3 = new JTextField();
        tfmarks3.setBounds(250,310,200,25);
        add(tfmarks3);

        tfmarks4 = new JTextField();
        tfmarks4.setBounds(250,350,200,25);
        add(tfmarks4);

        tfmarks5 = new JTextField();
        tfmarks5.setBounds(250,390,200,25);
        add(tfmarks5);

        submit = new JButton("Submit");
        submit.setBounds(90,430,130,40);
        submit.setBackground(Color.decode("#333"));
        submit.setForeground(Color.white);
        submit.setFont(new Font("Tahoma",Font.BOLD,15));
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Back");
        cancel.setBounds(260,430,130,40);
        cancel.setBackground(Color.decode("#333"));
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Tahoma",Font.BOLD,15));
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit){
            try{
                Conn c = new Conn();
                String checkQuery = "select * from subject";
                ResultSet rs = c.s.executeQuery(checkQuery);
                boolean isRecordAvailable = false;
                while (rs.next()){
                    if (crollno.getSelectedItem().equals(rs.getString(1)) && cbsemister.getSelectedItem().equals(rs.getString(2))){
                        JOptionPane.showMessageDialog(null, "Record already available!", "Warning", JOptionPane.WARNING_MESSAGE);
                        isRecordAvailable = true;
                        break;
                    }
                }

                if (!isRecordAvailable){
                    String query1 = "insert into subject values('"+crollno.getSelectedItem()+"','"+cbsemister.getSelectedItem()+"','"+tfsub1.getText()+"','"+tfsub2.getText()+"','"+tfsub3.getText()+"','"+tfsub4.getText()+"','"+tfsub5.getText()+"')";
                    String query2 = "insert into marks values('"+crollno.getSelectedItem()+"','"+cbsemister.getSelectedItem()+"','"+tfmarks1.getText()+"','"+tfmarks2.getText()+"','"+tfmarks3.getText()+"','"+tfmarks4.getText()+"','"+tfmarks5.getText()+"')";
                    if (c.s.executeUpdate(query1) > 0 && c.s.executeUpdate(query2) > 0){
                        JOptionPane.showMessageDialog(null,"Marks Inserted Sucessfully!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Record not inserted!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            dispose();
        }
    }

    public static void main(String[] args) {
        new EnterMarks();
    }
}