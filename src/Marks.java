import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Marks extends JFrame implements ActionListener {
    String rollno,sem,name;
    JButton back;
    Marks(String rollno, String sem,String name){
        this.rollno  = rollno;
        this.sem = sem;
        this.name = name;

        setSize(500,600);
        setLocation(500,100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("C.D.JAIN COLLEGE OF COMMERCE");
        heading.setBounds(70,0,500,50);
        heading.setFont(new Font("Gilroy",Font.BOLD,20));
        add(heading);

        JLabel subHeading = new JLabel("Result of examination");
        subHeading.setBounds(150,20,500,50);
        subHeading.setFont(new Font("Gilroy",Font.BOLD,18));
        add(subHeading);

        JLabel lblname = new JLabel("Name : ");
        lblname.setBounds(60,100,500,20);
        lblname.setFont(new Font("Gilroy",Font.PLAIN,18));
        add(lblname);

        JLabel strname = new JLabel(name);
        strname.setBounds(150,100,500,20);
        strname.setFont(new Font("Gilroy",Font.BOLD,18));
        add(strname);

        JLabel lblrollno = new JLabel("Student ID : ");
        lblrollno.setBounds(60,130,500,20);
        lblrollno.setFont(new Font("Gilroy",Font.PLAIN,18));
        add(lblrollno);

        JLabel rno = new JLabel(rollno);
        rno.setBounds(150,130,500,20);
        rno.setFont(new Font("Gilroy",Font.BOLD,18));
        add(rno);

        JLabel lblsemester = new JLabel("Semester : " );
        lblsemester.setBounds(60,140,500,50);
        lblsemester.setFont(new Font("Gilroy",Font.PLAIN,18));
        add(lblsemester);

        JLabel semester = new JLabel(sem);
        semester.setBounds(175,140,500,50);
        semester.setFont(new Font("Gilroy",Font.BOLD,18));
        add(semester);

        JLabel sub1 = new JLabel("Sub1");
        sub1.setBounds(60,200,500,50);
        sub1.setFont(new Font("Gilroy",Font.PLAIN,18));
        add(sub1);

        JLabel mark1 = new JLabel("mark1");
        mark1.setBounds(350,200,500,50);
        mark1.setFont(new Font("Gilroy",Font.BOLD,18));
        add(mark1);

        JLabel sub2 = new JLabel("Sub2");
        sub2.setBounds(60,250,500,50);
        sub2.setFont(new Font("Gilroy",Font.PLAIN,18));
        add(sub2);

        JLabel mark2 = new JLabel("mark2");
        mark2.setBounds(350,250,500,50);
        mark2.setFont(new Font("Gilroy",Font.BOLD,18));
        add(mark2);

        JLabel sub3 = new JLabel("Sub3");
        sub3.setBounds(60,300,500,50);
        sub3.setFont(new Font("Gilroy",Font.PLAIN,18));
        add(sub3);

        JLabel mark3 = new JLabel("mark3");
        mark3.setBounds(350,300,500,50);
        mark3.setFont(new Font("Gilroy",Font.BOLD,18));
        add(mark3);

        JLabel sub4 = new JLabel("Sub3");
        sub4.setBounds(60,350,500,50);
        sub4.setFont(new Font("Gilroy",Font.PLAIN,18));
        add(sub4);

        JLabel mark4 = new JLabel("mark4");
        mark4.setBounds(350,350,500,50);
        mark4.setFont(new Font("Gilroy",Font.BOLD,18));
        add(mark4);

        JLabel sub5 = new JLabel("Sub5");
        sub5.setBounds(60,400,500,50);
        sub5.setFont(new Font("Gilroy",Font.PLAIN,18));
        add(sub5);

        JLabel mark5 = new JLabel("mark5");
        mark5.setBounds(350,400,500,50);
        mark5.setFont(new Font("Gilroy",Font.BOLD,18));
        add(mark5);

        try{
            Conn c = new Conn();
            ResultSet rs1 = c.s.executeQuery("select * from subject where studentid="+rollno+" and semester='"+sem+"'");
            while(rs1.next()){
                sub1.setText(rs1.getString(3));
                sub2.setText(rs1.getString(4));
                sub3.setText(rs1.getString(5));
                sub4.setText(rs1.getString(6));
                sub5.setText(rs1.getString(7));
            }
            ResultSet rs2 = c.s.executeQuery("select * from marks where studentid ='"+rollno+"' and semester='"+sem+"'");
            while(rs2.next()){
                mark1.setText(rs2.getString(3));
                mark2.setText(rs2.getString(4));
                mark3.setText(rs2.getString(5));
                mark4.setText(rs2.getString(6));
                mark5.setText(rs2.getString(7));
                setVisible(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        back = new JButton("Back");
        back.setBounds(175,475,120,40);
        back.setBackground(Color.decode("#333"));
        back.setForeground(Color.white);
        back.setFont(new Font("Tahoma",Font.BOLD,13));
        back.addActionListener(this);
        add(back);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back){
            dispose();
        }
    }

    public static void main(String[] args) {
        new Marks("15339543","1st Semister","Example student");
    }
}