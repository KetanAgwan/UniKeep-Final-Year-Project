import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class Project extends JFrame implements ActionListener {
    String accessingPerson;
    static int instanceCount = 0;
    Project thisInstance = this;

    ImageIcon backgroundImageIcon; // Background image icon
    Image scaledBackgroundImage; // Scaled background image
    String accessingPersonName;

    Project(String accessingPerson,String accessingPersonName){
        instanceCount++;
        this.accessingPerson = accessingPerson;
        this.accessingPersonName = accessingPersonName;
        setSize(1540,850);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar mb = new JMenuBar();
        mb.setBackground(Color.decode("#333"));
        mb.setLayout(new FlowLayout(FlowLayout.LEFT));


//        add bg image and a glass box

        backgroundImageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        scaledBackgroundImage = backgroundImageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);

        ImageIcon iconImage = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));

        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
//                super.paintComponent(g);

                // Draw background image
                if (scaledBackgroundImage != null) {
                    g.drawImage(scaledBackgroundImage, 0, 0, this);
                }

                Graphics2D g2d = (Graphics2D) g.create();
                // Set rendering hints for smoother graphics
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                // Draw glass box with rounded corners
                int x = 350;
                int y = 100;
                int width = 800;
                int height = 500;
                int cornerRadius = 20;
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(x, y, width, height, cornerRadius, cornerRadius);

                g2d.setColor(new Color(181, 190, 192, 200)); // Semi-transparent color
                g2d.fill(roundedRectangle);

//                add text on the box
                g2d.setColor(Color.BLACK);
                Font font1 = new Font("Arial", Font.BOLD, 25);
                Font font2 = new Font("Arial", Font.BOLD, 30);
                g2d.setFont(font1);
                String text = "Current User : " + accessingPersonName;
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth1 = fm.stringWidth(text);
                int textHeight1 = fm.getHeight();
                int textX1 = x + (width - textWidth1) / 2;
                int textY1 = y + (height + textHeight1) / 2 - 50;
                g2d.drawString(text, textX1, textY1);

                g2d.setFont(font2);
                String greetingmsg = "Welcome back " + accessingPersonName + ", Continue your work.";
                int textWidth2 = fm.stringWidth(greetingmsg);
                int textHeight2 = fm.getHeight();
                int textX2 = x + (width - textWidth2) / 2 - 65;
                int textY2 = y + (height + textHeight2) / 2 + 25;
                g2d.drawString(greetingmsg, textX2, textY2);


//                add image on the box

                int imageX = x + (width - iconImage.getIconWidth()) / 2;
                int imageY = y + (height - iconImage.getIconHeight()) / 3 - 100; // Adjust position
                g2d.drawImage(iconImage.getImage(), imageX, imageY, this);


                g2d.dispose(); // Clean up graphics resources
            }
        };

        add(panel);



//        this section is for admin

        if (accessingPerson.equals("admin")){
            //        New information
            JMenu  newInformation = new JMenu("New Information");
            newInformation.setForeground(Color.WHITE);
            newInformation.setBackground(Color.decode("#333"));
            newInformation.setPreferredSize(new Dimension(110, 30));
            newInformation.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(newInformation);

            JMenuItem facultyInfo = createMenuItem("New Faculty Information");
            facultyInfo.addActionListener(this);
            newInformation.add(facultyInfo);

            JMenuItem studentInfo = createMenuItem("New Student Information");
            studentInfo.addActionListener(this);
            newInformation.add(studentInfo);

//        Details
            JMenu details = new JMenu("View Details");
            details.setForeground(Color.WHITE);
            details.setBackground(Color.decode("#333"));
            details.setPreferredSize(new Dimension(100, 30));
            details.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(details);

            JMenuItem facultyDetails = createMenuItem("View Faculty Details");
            facultyDetails.addActionListener(this);
            details.add(facultyDetails);

            JMenuItem studentDetails = createMenuItem("View Student Details");
            studentDetails.addActionListener(this);
            details.add(studentDetails);

//        Apply leave
            JMenu leave = new JMenu("Apply Leave");
            leave.setForeground(Color.WHITE);
            leave.setBackground(Color.decode("#333"));
            leave.setPreferredSize(new Dimension(100, 30));
            leave.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(leave);

            JMenuItem facultyLeave = createMenuItem("Faculty Leave");
            facultyLeave.addActionListener(this);
            leave.add(facultyLeave);

            JMenuItem studentLeave = createMenuItem("Student Leave");
            studentLeave.addActionListener(this);
            leave.add(studentLeave);

//      Leave Details
            JMenu leaveDetails = new JMenu("Leave Details");
            leaveDetails.setForeground(Color.WHITE);
            leaveDetails.setBackground(Color.decode("#333"));
            leaveDetails.setPreferredSize(new Dimension(100, 30));
            leaveDetails.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(leaveDetails);

            JMenuItem facultyLeaveDetails = createMenuItem("Faculty Leave Details");
            facultyLeaveDetails.addActionListener(this);
            leaveDetails.add(facultyLeaveDetails);

            JMenuItem studentLeaveDetails = createMenuItem("Student Leave Details");
            studentLeaveDetails.addActionListener(this);
            leaveDetails.add(studentLeaveDetails);


//        Examination
            JMenu exam = new JMenu("Examination");
            exam.setForeground(Color.WHITE);
            exam.setBackground(Color.decode("#333"));
            exam.setPreferredSize(new Dimension(100, 30));
            exam.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(exam);

            JMenuItem examinationDetails = createMenuItem("Examination Results");
            examinationDetails.addActionListener(this);
            exam.add(examinationDetails);

            JMenuItem enterMarks = createMenuItem("Enter Marks");
            enterMarks.addActionListener(this);
            exam.add(enterMarks);

//        Update info
            JMenu updateInfo = new JMenu("Update Information");
            updateInfo.setForeground(Color.WHITE);
            updateInfo.setBackground(Color.decode("#333"));
            updateInfo.setPreferredSize(new Dimension(130, 30));
            updateInfo.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(updateInfo);

            JMenuItem updateFacultyInfo = createMenuItem("Update Faculty Details");
            updateFacultyInfo.addActionListener(this);
            updateInfo.add(updateFacultyInfo);

            JMenuItem updateStudentInfo = createMenuItem("Update Student Details");
            updateStudentInfo.addActionListener(this);
            updateInfo.add(updateStudentInfo);

//        Fees management
            JMenu fee = new JMenu("Fee Details");
            fee.setForeground(Color.WHITE);
            fee.setBackground(Color.decode("#333"));
            fee.setPreferredSize(new Dimension(100, 30));
            fee.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(fee);

            JMenuItem feeStructure = createMenuItem("Fee Structure");
            feeStructure.addActionListener(this);
            fee.add(feeStructure);

            JMenuItem feeForm = createMenuItem("Student Fee Form");
            feeForm.addActionListener(this);
            fee.add(feeForm);

            JMenuItem feeStatus = createMenuItem("Check Fee Status");
            feeStatus.addActionListener(this);
            fee.add(feeStatus);

//        Utility

            JMenu utility = new JMenu("Utilities");
            utility.setForeground(Color.WHITE);
            utility.setBackground(Color.decode("#333"));
            utility.setPreferredSize(new Dimension(80, 30));
            utility.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(utility);

            JMenuItem calc = createMenuItem("Calculator");
            utility.add(calc);
            calc.addActionListener(this);

            JMenuItem notepad = createMenuItem("Notepad");
            utility.add(notepad);
            notepad.addActionListener(this);


//            Add new admin

            JMenu newAdmin = new JMenu("Add new Admin");
            newAdmin.setForeground(Color.WHITE);
            newAdmin.setBackground(Color.decode("#333"));
            newAdmin.setPreferredSize(new Dimension(100, 30));
            newAdmin.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(newAdmin);

            JMenuItem admn = createMenuItem("Add new Admin");
            newAdmin.add(admn);
            admn.addActionListener(this);
        }


//        this section is for teacher
        else if (accessingPerson.equals("teacher")){
            JMenu details = new JMenu("View Details");
            details.setForeground(Color.WHITE);
            details.setBackground(Color.decode("#333"));
            details.setPreferredSize(new Dimension(100, 30));
            details.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(details);

            JMenuItem studentDetails = createMenuItem("View Student Details");
            studentDetails.addActionListener(this);
            details.add(studentDetails);

            JMenu leave = new JMenu("Apply Leave");
            leave.setForeground(Color.WHITE);
            leave.setBackground(Color.decode("#333"));
            leave.setPreferredSize(new Dimension(100, 30));
            leave.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(leave);

            JMenuItem studentLeave = createMenuItem("Student Leave");
            studentLeave.addActionListener(this);
            leave.add(studentLeave);

            JMenu leaveDetails = new JMenu("Leave Details");
            leaveDetails.setForeground(Color.WHITE);
            leaveDetails.setBackground(Color.decode("#333"));
            leaveDetails.setPreferredSize(new Dimension(100, 30));
            leaveDetails.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(leaveDetails);

            JMenuItem studentLeaveDetails = createMenuItem("Student Leave Details");
            studentLeaveDetails.addActionListener(this);
            leaveDetails.add(studentLeaveDetails);

            JMenu exam = new JMenu("Examination");
            exam.setForeground(Color.WHITE);
            exam.setBackground(Color.decode("#333"));
            exam.setPreferredSize(new Dimension(100, 30));
            exam.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(exam);

            JMenuItem examinationDetails = createMenuItem("Examination Results");
            examinationDetails.addActionListener(this);
            exam.add(examinationDetails);

            JMenuItem enterMarks = createMenuItem("Enter Marks");
            enterMarks.addActionListener(this);
            exam.add(enterMarks);

            JMenu fee = new JMenu("Fee Details");
            fee.setForeground(Color.WHITE);
            fee.setBackground(Color.decode("#333"));
            fee.setPreferredSize(new Dimension(100, 30));
            fee.setHorizontalAlignment(SwingConstants.CENTER);
            mb.add(fee);

            JMenuItem feeStructure = createMenuItem("Fee Structure");
            feeStructure.addActionListener(this);
            fee.add(feeStructure);

            JMenuItem feeStatus = createMenuItem("Check Fee Status");
            feeStatus.addActionListener(this);
            fee.add(feeStatus);
        }

//        About

        JMenu about = new JMenu("About");
        about.setForeground(Color.WHITE);
        about.setBackground(Color.decode("#333"));
        about.setPreferredSize(new Dimension(70, 30));
        about.setHorizontalAlignment(SwingConstants.CENTER);
        mb.add(about);

        JMenuItem ab = createMenuItem("About");
        about.add(ab);
        ab.addActionListener(this);

//        Exit

        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.decode("#333"));
        exit.setPreferredSize(new Dimension(70, 30));
        exit.setHorizontalAlignment(SwingConstants.CENTER);
        mb.add(exit);

        JMenuItem ex = createMenuItem("Exit");
        exit.add(ex);
        ex.addActionListener(this);

        setJMenuBar(mb);
        setVisible(true);
    }

    public static int getInstanceCount(){
        return instanceCount;
    }

    public Project stopInstance(){
        return thisInstance;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if (msg.equals("Exit")){
            System.exit(0);
        } else if (msg.equals("Calculator")) {
            try{
                Runtime.getRuntime().exec("calc.exe");
            }catch (Exception ex){
                System.out.println(ex);
            }
        }else if (msg.equals("Notepad")) {
            try{
                Runtime.getRuntime().exec("notepad.exe");
            }catch (Exception ex){
                System.out.println(ex);
            }
        } else if (msg.equals("New Faculty Information")) {
            new AddTeacher();
        }else if (msg.equals("New Student Information")) {
            new AddStudent();
        }else if (msg.equals("View Faculty Details")) {
            new TeacherDetails();
        }else if (msg.equals("View Student Details")) {
            new StudentDetails(accessingPerson);
        }else if (msg.equals("Faculty Leave")) {
            new TeacherLeave();
        }else if (msg.equals("Student Leave")) {
            new StudentLeave();
        }else if (msg.equals("Faculty Leave Details")) {
            new TeacherLeaveDetails();
        }else if (msg.equals("Student Leave Details")) {
            new StudentLeaveDetails();
        }else if (msg.equals("Update Faculty Details")) {
            new UpdateTeacher();
        }else if (msg.equals("Update Student Details")) {
            new UpdateStudent();
        }else if (msg.equals("Enter Marks")) {
            new EnterMarks();
        }else if (msg.equals("Examination Results")) {
            new ExaminationDetails();
        }else if (msg.equals("Fee Structure")) {
            new FeeStructure();
        }else if (msg.equals("Student Fee Form")) {
            new StudentFeeForm();
        }else if (msg.equals("Check Fee Status")) {
            new CheckFeeStatus();
        }else if (msg.equals("About")) {
            new About();
        }else if (msg.equals("Add new Admin")) {
            new AdminSignup();
        }
    }

    private JMenuItem createMenuItem(String text) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.setForeground(Color.WHITE);
        menuItem.setBackground(Color.decode("#333"));
        menuItem.setPreferredSize(new Dimension(150, 30));
        menuItem.setHorizontalAlignment(SwingConstants.LEFT);

        // Add hover effect
        menuItem.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                menuItem.setBackground(Color.decode("#111"));
            }

            public void mouseExited(MouseEvent e) {
                menuItem.setBackground(Color.decode("#333"));
            }
        });

        return menuItem;
    }

    public static void main(String[] args) {
        new Project("admin","Ketan");
    }
}