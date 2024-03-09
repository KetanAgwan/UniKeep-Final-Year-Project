import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class FormValidation{
    Date currentDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yy");
    Random ran = new Random();

    public boolean validateString(String str){
        if (str.isEmpty())
            return false;
        else {
            for (char c : str.toCharArray()) {
                if (Character.isDigit(c))
                    return false;
            }
        }
        return true;
    }
    public long generateIdentityNo(String year){
        String twoDigitYear = sdf.format(currentDate);
        long rno = Math.abs((ran.nextLong() % 9000L) + 1000L);

        try{
            String query = "select studentid from student";
            Conn con = new Conn();
            ResultSet rs = con.s.executeQuery(query);
            while (true) {
                if(rs.next()){
                    String rollno = rs.getString(1);
                    rno = Math.abs((ran.nextLong() % 9000L) + 1000L);
                    if (year.equals("First_Year")){
                        rno = Long.parseLong(twoDigitYear + "01" + rno);
                    }else if (year.equals("Second_Year")) {
                        rno = Long.parseLong(twoDigitYear + "02" + rno);
                    }else if (year.equals("Third_Year")) {
                        rno = Long.parseLong(twoDigitYear + "03" + rno);
                    }else if (year.equals("Fourth_Year")) {
                        rno = Long.parseLong(twoDigitYear + "04" + rno);
                    }
                    if (!(rno == Long.parseLong(rollno)))
                        break;
                }else{
                    rno = Math.abs((ran.nextLong() % 9000L) + 1000L);
                    if (year.equals("First_Year")){
                        rno = Long.parseLong(twoDigitYear + "01" + rno);
                    }else if (year.equals("Second_Year")) {
                        rno = Long.parseLong(twoDigitYear + "02" + rno);
                    }else if (year.equals("Third_Year")) {
                        rno = Long.parseLong(twoDigitYear + "03" + rno);
                    }else if (year.equals("Fourth_Year")) {
                        rno = Long.parseLong(twoDigitYear + "04" + rno);
                    }
                    break;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rno;
    }

    public long generateEmployeeId(){
        String twoDigitYear = sdf.format(currentDate);
        long rno = Math.abs((ran.nextLong() % 9000L) + 1000L);
        try{
            String query = "select studentid from student";
            Conn con = new Conn();
            ResultSet rs = con.s.executeQuery(query);
            while(true){
                if (rs.next()){
                    String rollno = rs.getString(1);
                    rno = Math.abs((ran.nextLong() % 9000L) + 1000L);
                    rno = Long.parseLong(twoDigitYear + rno);
                    if (!(rno == Long.parseLong(rollno)))
                        break;
                }else{
                    rno = Math.abs((ran.nextLong() % 9000L) + 1000L);
                    rno = Long.parseLong(twoDigitYear + rno);
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return rno;
    }
    public boolean isPhonenoExists(String phno){
        if (phno.length() != 10)
            return false;
        else{
            for (char c : phno.toCharArray()){
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEmailValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean checkPercentage(String per){
        if (per.isEmpty() || Integer.parseInt(per) > 100 || Integer.parseInt(per) < 0)
            return false;
        else{
            for (char c : per.toCharArray()){
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validateAdhar(String adrno){
        if (adrno.length() != 12)
            return false;
        else{
            for (char c : adrno.toCharArray()){
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }
}