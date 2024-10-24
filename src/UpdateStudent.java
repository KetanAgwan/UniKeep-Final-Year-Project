import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class UpdateStudent extends JFrame implements ActionListener {
    Conn con = new Conn();
    FormValidation val = new FormValidation();
    JTextField tfname,tffname,tfaddress,tfphone,tfemail,tfx,tfxii,tfadhar;
    JLabel labelrollno;
    JDateChooser dcdob;
    JComboBox cbcourse,cbbranch,crollno;
    JButton search,update,cancel;
    Choice cyear = new Choice();
    UpdateStudent(){
        setSize(820,700);
        setLocation(350,50);
        setLayout(null);

        JLabel heading = new JLabel("Update Student Details");
        heading.setBounds(250,20,500,50);
        heading.setFont(new Font("Gilroy",Font.BOLD,30));
        add(heading);

        JLabel lblsearchrno = new JLabel("Search by Student ID");
        lblsearchrno.setFont(new Font("Gilroy",Font.BOLD,15));
        lblsearchrno.setBounds(50,100,200,20);
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
            crollno.setBounds(250,100,200,20);
            crollno.setBackground(Color.WHITE);
            crollno.setEditable(true);
            add(crollno);
        }catch (Exception e){
            e.printStackTrace();
        }

        search = new JButton("Search");
        search.setBounds(470,100,80,20);
        search.addActionListener(this);
        add(search);


        JLabel lblname = new JLabel("Name");
        lblname.setBounds(50,150,100,30);
        lblname.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblname);
        tfname = new JTextField();
        tfname.setBounds(200,150,150,30);
        add(tfname);

        JLabel lblfname = new JLabel("Father's Name");
        lblfname.setBounds(400,150,200,30);
        lblfname.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblfname);
        tffname = new JTextField();
        tffname.setBounds(600,150,150,30);
        add(tffname);

        JLabel lblrollno = new JLabel("Student ID");
        lblrollno.setBounds(50,200,100,30);
        lblrollno.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblrollno);
        labelrollno = new JLabel("");
        labelrollno.setBounds(200,200,100,30);
        labelrollno.setFont(new Font("Gilroy",Font.BOLD,20));
        add(labelrollno);

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

        update = new JButton("Update");
        update.setBounds(250,550,130,40);
        update.setBackground(Color.decode("#333"));
        update.setForeground(Color.white);
        update.setFont(new Font("Tahoma",Font.BOLD,15));
        update.addActionListener(this);
        add(update);

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
        if (ae.getSource() == update) {
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
                String year = cyear.getSelectedItem();

                try{
                    String query = "update student set name='"+name+"',fname='"+fname+"',dob= '"+dob+"',address='"+address+"',phone='"+phone+"',email='"+email+"', class_x='"+x+"', class_xii='"+xii+"', adhar='"+adhar+"', course='"+course+"', admissionyear='"+ year +"' where studentid = '"+rollno+"';";
                    con.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Student details updated sucessfully.");
                    System.exit(0);
                }catch(Exception e) {
                    JOptionPane.showMessageDialog(null,"Operation failed");
                    e.printStackTrace();
                }
            }
        }else if(ae.getSource() == cancel) {
            dispose();
        }else if(ae.getSource() == search) {
            fillFormByRollno((String) Objects.requireNonNull(crollno.getSelectedItem()));
        }
    }

//    public void fillFormByRollno(String rno){
//        if (rno.isEmpty()){
//            JOptionPane.showMessageDialog(null, "Please select Student ID", "Warning", JOptionPane.WARNING_MESSAGE);
//        }
//        else {
//            try{
//                Conn c = new Conn();
//                ResultSet rs = c.s.executeQuery("select * from student where studentid='"+rno+"'");
//                rs.last();
//                if (rs.getRow() > 0){
//                    rs.first();
//                    tfname.setText(rs.getString("name"));
//                    tffname.setText(rs.getString("fname"));
//                    labelrollno.setText(rs.getString("studentid"));
//
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy"); // Notice the spaces instead of '-'
//                    String stringDate = rs.getString("dob");  // Assuming the date is in '3 Jul 2004' format
//                    LocalDate localDate = LocalDate.parse(stringDate, formatter);
//                    Date dobDate = Date.valueOf(localDate);
//                    dcdob.setDate(dobDate);
//
//                    dcdob.setDate(dobDate);
//                    tfaddress.setText(rs.getString("address"));
//                    tfphone.setText(rs.getString("phone"));
//                    tfemail.setText(rs.getString("email"));
//                    tfx.setText(rs.getString("class_x"));
//                    tfxii.setText(rs.getString("class_xii"));
//                    tfadhar.setText(rs.getString("adhar"));
//                    cbcourse.setSelectedItem(rs.getString("course"));
//                    cyear.select(rs.getString("admissionyear"));
//                }else{
//                    JOptionPane.showMessageDialog(null, "Invalid Student ID", "Warning", JOptionPane.WARNING_MESSAGE);
//                }
//            }catch (Exception e){
//                JOptionPane.showMessageDialog(null, "Operation failed", "Warning", JOptionPane.WARNING_MESSAGE);
//                e.printStackTrace();
//            }
//        }
//    }

    public void fillFormByRollno(String rno){
        if (rno.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please select Student ID", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else {
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from student where studentid='"+rno+"'");
                rs.last();
                if (rs.getRow() > 0){
                    rs.first();
                    tfname.setText(rs.getString("name"));
                    tffname.setText(rs.getString("fname"));
                    labelrollno.setText(rs.getString("studentid"));

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
                    String stringDate = rs.getString("dob");
                    LocalDate localDate = LocalDate.parse(stringDate, formatter);
                    Date dobDate = Date.valueOf(localDate);
                    dcdob.setDate(dobDate);


                    tfaddress.setText(rs.getString("address"));
                    tfphone.setText(rs.getString("phone"));
                    tfemail.setText(rs.getString("email"));
                    tfx.setText(rs.getString("class_x"));
                    tfxii.setText(rs.getString("class_xii"));
                    tfadhar.setText(rs.getString("adhar"));
                    cbcourse.setSelectedItem(rs.getString("course"));
                    cyear.select(rs.getString("admissionyear"));
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Student ID", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Operation failed", "Warning", JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
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
        new UpdateStudent();
    }
}