import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class AddTeacher extends JFrame implements ActionListener {
    Conn con = new Conn();
    FormValidation val = new FormValidation();
    JTextField tfname,tffname,tfaddress,tfphone,tfemail,tfx,tfxii,tfadhar;
    JLabel labelempId;
    JDateChooser dcdob;
    JComboBox cbeducation, desgntype;
    JButton submit,cancel;

    AddTeacher(){
        setSize(850,700);
        setLocation(350,50);
        setLayout(null);

        JLabel heading = new JLabel("New Teacher details");
        heading.setBounds(310,30,500,50);
        heading.setFont(new Font("Gilroy",Font.BOLD,30));
        add(heading);

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

        JLabel lblempid = new JLabel("Employee Id");
        lblempid.setBounds(50,150,150,30);
        lblempid.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblempid);
        labelempId = new JLabel("" + val.generateEmployeeId());
        labelempId.setBounds(200,150,150,30 );
        labelempId.setFont(new Font("Gilroy",Font.BOLD,20));
        add(labelempId);

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

        JLabel lblcourse = new JLabel("Department");
        lblcourse.setBounds(50,400,200,30);
        lblcourse.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblcourse);

        String department[];
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from fee");
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();
            department = new String[row];
            int i = 0;
            while (rs.next()){
                department[i] = rs.getString(1);
                i++;
            }
            cbeducation = new JComboBox(department);
            cbeducation.setBounds(200,400,150,30);
            cbeducation.setBackground(Color.WHITE);
            add(cbeducation);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Something is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }

        JLabel lblbranch = new JLabel("Designation Type");
        lblbranch.setBounds(400,400,200,30);
        lblbranch.setFont(new Font("Gilroy",Font.BOLD,20));
        add(lblbranch);

        String dsgtype[] = {"Permanant","Temporary"};
        desgntype = new JComboBox(dsgtype);
        desgntype.setBounds(600,400,150,30);
        desgntype.setBackground(Color.WHITE);
        add(desgntype);

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
                String empid = labelempId.getText();
                String dob = ((JTextField)dcdob.getDateEditor().getUiComponent()).getText();
                String address = tfaddress.getText();
                String phone = tfphone.getText();
                String email = tfemail.getText();
                String x = tfx.getText();
                String xii = tfxii.getText();
                String adhar = tfadhar.getText();
                String education = (String) cbeducation.getSelectedItem();
                String dsgType = (String) desgntype.getSelectedItem();

                try{
                    String query = "insert into teacher values('"+name+"','"+fname+"','"+empid+"','"+dob+"','"+address+"','"+phone+"','"+email+"','"+x+"','"+xii+"','"+adhar+"','"+education+"','"+ dsgType +"')";
                    con.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Teacher details inserted sucessfully.");
                    dispose();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }else if(ae.getSource() == cancel) {
            dispose();
        }
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
        new AddTeacher();
    }
}