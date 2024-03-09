import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class StudentLeave extends JFrame implements ActionListener {
    JComboBox crollno;
    Choice ctime;
    JDateChooser dcstartdate,dcenddate;
    JButton submit,cancel;
    JTextField noofdays;
    StudentLeave(){
        setSize(335,600);
        setLocation(600,100);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Apply Student Leave");
        heading.setBounds(40,50,300,30);
        heading.setFont(new Font("Gilroy",Font.BOLD,25));
        add(heading);

        JLabel lblrollno = new JLabel("Search by Student ID");
        lblrollno.setFont(new Font("Gilroy",Font.PLAIN,15));
        lblrollno.setBounds(60,100,200,20);
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
            crollno.setBounds(60,130,200,25);
            crollno.setBackground(Color.WHITE);
            crollno.setEditable(true);
            add(crollno);
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel lblstartdate = new JLabel("Start Date");
        lblstartdate.setFont(new Font("Gilroy",Font.PLAIN,15));
        lblstartdate.setBounds(60,180,200,20);
        add(lblstartdate);

        dcstartdate = new JDateChooser();
        dcstartdate.setBounds(60,210,200,25);
        add(dcstartdate);

        JLabel lblenddate = new JLabel("End Date");
        lblenddate.setFont(new Font("Gilroy",Font.PLAIN,15));
        lblenddate.setBounds(60,260,200,20);
        add(lblenddate);


        JLabel lblnoofdays = new JLabel("Number of Days");
        lblnoofdays.setFont(new Font("Gilroy",Font.PLAIN,15));
        lblnoofdays.setBounds(60,340,200,20);
        add(lblnoofdays);

        noofdays = new JTextField();
        noofdays.setFont(new Font("Gilroy",Font.PLAIN,15));
        noofdays.setBounds(60,370,200,25);

        dcenddate = new JDateChooser();
        dcenddate.setBounds(60,290,200,25);
        dcenddate.addPropertyChangeListener("date", evt -> {
            if ("date".equals(evt.getPropertyName())) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
//                    this is for start date
                LocalDate date_1 = dcstartdate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String formattedDate_1 = date_1.format(formatter);
                LocalDate localDate_1 = LocalDate.parse(formattedDate_1, formatter);
                Date startdate = Date.valueOf(localDate_1);

//                    this is for end date
                LocalDate date_2 = dcenddate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String formattedDate_2 = date_2.format(formatter);
                LocalDate localDate_2 = LocalDate.parse(formattedDate_2, formatter);
                Date enddate = Date.valueOf(localDate_2);

                noofdays.setText("" + clacDateDifference(startdate,enddate));
            }
        });



        add(noofdays);
        add(dcenddate);

        JLabel lbltime = new JLabel("Time Duration");
        lbltime.setFont(new Font("Gilroy",Font.PLAIN,15));
        lbltime.setBounds(60,420,200,20);
        add(lbltime);
        ctime = new Choice();
        ctime.setBounds(60,450,200,25);
        ctime.add("Full Day");
        ctime.add("Half Day");
        add(ctime);

        submit = new JButton("Submit");
        submit.setBounds(40,500,100,25);
        submit.setBackground(Color.decode("#333"));
        submit.setForeground(Color.white);
        submit.setFont(new Font("Tahoma",Font.BOLD,14));
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(180,500,100,25);
        cancel.setBackground(Color.decode("#333"));
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Tahoma",Font.BOLD,14));
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    public long clacDateDifference(Date startDate,Date endDate){
        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(null, "Please select both start and end dates.", "Warning", JOptionPane.WARNING_MESSAGE);
        }else{
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endDate);

            // Calculate difference in days
            long differenceInMillis = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
            long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMillis);
            return differenceInDays;
        }
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String rollno = (String) crollno.getSelectedItem();
            String startdate = ((JTextField) dcstartdate.getDateEditor().getUiComponent()).getText();
            String enddate = ((JTextField) dcenddate.getDateEditor().getUiComponent()).getText();
            String noofdaysText = noofdays.getText();
            String duration = ctime.getSelectedItem();

            String query = "insert into studentleave values('"+ rollno +"','"+ startdate +"','"+enddate+"','"+noofdaysText+"','"+duration+"')";

            try{
                Conn c = new Conn();
                int rowsAffected = c.s.executeUpdate(query);
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null,"Leave Confirmed");
                }else {
                    JOptionPane.showMessageDialog(null,"Leave Denied");
                }
                dispose();
            }catch (Exception e){
                e.printStackTrace();
            }

        }else{
            dispose();
        }
    }

    public static void main(String[] args) {
        new StudentLeave();
    }
}