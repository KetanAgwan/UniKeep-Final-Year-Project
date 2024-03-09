import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;



public class AddStudent extends JFrame implements ActionListener {
    Conn con = new Conn();
    FormValidation val = new FormValidation();
    JTextField tfname,tffname,tfaddress,tfphone,tfemail,tfx,tfxii,tfadhar;
    JLabel labelrollno;
    JDateChooser dcdob;
    JComboBox cbcourse;
    Choice cyear = new Choice();
    JButton submit,cancel;
    AddStudent(){
        setSize(850,700);
        setLocation(350,50);
        setLayout(null);

        JLabel heading = new JLabel("New student details");
        heading.setBounds(310,30,500,50);
        heading.setFont(new Font("Gilroy",Font.BOLD,30));
        add(heading);

        JLabel lblrollno = new JLabel("Student ID");
        lblrollno.setBounds(50,150,100,30 );
        lblrollno.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblrollno);
        labelrollno = new JLabel("" + val.generateIdentityNo("First_Year"));
        labelrollno.setBounds(200,150,150,30 );
        labelrollno.setFont(new Font("Gilroy",Font.BOLD,20));
        add(labelrollno);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(400,150,200,30);
        lblname.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblname);
        tfname = new JTextField();
        tfname.setBounds(600,150,150,30);
        add(tfname);

        JLabel lblfname = new JLabel("Father's Name");
        lblfname.setBounds(50,200,150,30);
        lblfname.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblfname);
        tffname = new JTextField();
        tffname.setBounds(200,200,150,30);
        add(tffname);

        JLabel lbldob = new JLabel("Date of birth");
        lbldob.setBounds(400,200,200,30);
        lbldob.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lbldob);
        dcdob = new JDateChooser();
        dcdob.setBounds(600,200,150,30);
        add(dcdob);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(50,250,200,30);
        lbladdress.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lbladdress);
        tfaddress = new JTextField();
        tfaddress.setBounds(200,250,150,30);
        add(tfaddress);

        JLabel lblphone = new JLabel("Phone no");
        lblphone.setBounds(400,250,200,30);
        lblphone.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblphone);
        tfphone = new JTextField();
        tfphone.setBounds(600,250,150,30);
        add(tfphone);

        JLabel lblemail = new JLabel("Email id");
        lblemail.setBounds(50,300,200,30);
        lblemail.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblemail);
        tfemail = new JTextField();
        tfemail.setBounds(200,300,150,30);
        add(tfemail);

        JLabel lblx = new JLabel("Class X (%)");
        lblx.setBounds(400,300,200,30);
        lblx.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblx);
        tfx = new JTextField();
        tfx.setBounds(600,300,150,30);
        add(tfx);

        JLabel lblxii = new JLabel("Class XII (%)");
        lblxii.setBounds(50,350,200,30);
        lblxii.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblxii);
        tfxii = new JTextField();
        tfxii.setBounds(200,350,150,30);
        add(tfxii);

        JLabel lbladhar = new JLabel("Adhar no");
        lbladhar.setBounds(400,350,200,30);
        lbladhar.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lbladhar);
        tfadhar = new JTextField();
        tfadhar.setBounds(600,350,150,30);
        add(tfadhar);

        JLabel lblcourse = new JLabel("Course");
        lblcourse.setBounds(50,400,200,30);
        lblcourse.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblcourse);

        String course[];
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from fee");
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();
            course = new String[row];
            int i = 0;
            while (rs.next()){
                course[i] = rs.getString(1);
                i++;
            }
            cbcourse = new JComboBox(course);
            cbcourse.setBounds(200,400,150,30);
            cbcourse.addActionListener(this);
            cbcourse.setBackground(Color.WHITE);
            add(cbcourse);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Something is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }

        JLabel lbladmissiontype = new JLabel("Admission Year");
        lbladmissiontype.setBounds(400,400,200,30);
        lbladmissiontype.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lbladmissiontype);

        cyear.setBounds(600,405,150,30);
        cyear.setBackground(Color.white);
        cyear.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    String selectedValue = cyear.getSelectedItem();
                    fillIdNumber(selectedValue);
                }
            }
        });
        fillChoice((String)cbcourse.getSelectedItem());
        add(cyear);



        submit = new JButton("Submit");
        submit.setBounds(250,550,130,40);
        submit.setBackground(Color.decode("#333"));
        submit.setForeground(Color.white);
        submit.setFont(new Font("Tahoma",Font.BOLD,15));
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450,550,130,40);
        cancel.setBackground(Color.decode("#333"));
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Tahoma",Font.BOLD,15));
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            if (validateAdmission()){
                String name = tfname.getText();
                String fname = tffname.getText();
                String rollno = labelrollno.getText();
                String dob = ((JTextField)dcdob.getDateEditor().getUiComponent()).getText();
                String address = tfaddress.getText();
                String phone = tfphone.getText();
                String email = tfemail.getText();
                String x = tfx.getText();
                String xii = tfxii.getText();
                String adhar = tfadhar.getText();
                String course = (String) cbcourse.getSelectedItem();
                String adminyear = cyear.getSelectedItem();

                try{
                    String query = "insert into student values('"+name+"','"+fname+"','"+rollno+"','"+dob+"','"+address+"','"+phone+"','"+email+"','"+x+"','"+xii+"','"+adhar+"','"+course+"','"+ adminyear +"','unpaid')";
                    con.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Student details inserted sucessfully.");
                    dispose();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }else if(ae.getSource() == cancel) {
            dispose();
        } else if (ae.getSource() == cbcourse) {
            fillChoice((String) cbcourse.getSelectedItem());
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

    public void fillIdNumber(String selectedValue){
        labelrollno.setText("" + val.generateIdentityNo(selectedValue));
    }

    public boolean validateAdmission(){
        if (val.validateString(tfname.getText()))
            if (val.validateString(tffname.getText()))
                if(val.isPhonenoExists(tfphone.getText()))
                    if (val.isEmailValid(tfemail.getText()))
                        if (val.checkPercentage(tfx.getText()))
                            if (val.checkPercentage(tfxii.getText()))
                                if (val.validateAdhar(tfadhar.getText()))
                                    return true;
                                else
                                    JOptionPane.showMessageDialog(null, "invalid Adhar number.", "Warning", JOptionPane.WARNING_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(null, "Invalid class XII percentage.", "Warning", JOptionPane.WARNING_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(null, "Invalid class X percentage.", "Warning", JOptionPane.WARNING_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "Invalid Email.", "Warning", JOptionPane.WARNING_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Invalid Phone number.", "Warning", JOptionPane.WARNING_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Invalid Father's name.", "Warning", JOptionPane.WARNING_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "invalid name", "Warning", JOptionPane.WARNING_MESSAGE);
    return false;
    }

    public static void main(String[] args) {
        new AddStudent();
    }
}