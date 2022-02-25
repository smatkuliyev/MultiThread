package package1;

import java.sql.*;
import java.util.ArrayList; // import eklendi
import java.util.List;

public class InterviewQuestionMain {

    private String connectionurl = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";

    public static void main(String[] args) {
        InterviewQuestionMain obj = new InterviewQuestionMain(); // diger method static olmadigi icin bu class'dan obje ureterek ulastik.
        //veya gidip method'u static yapip o sekilde de ulasabilirdik
        obj.getlistofpersonnamesagebiggerthan(30);
    }


    public List<String> getlistofpersonnamesagebiggerthan(int age) { // get_listofpersonnamesagebiggerthan -> getlistofpersonnamesagebiggerthan, method isimleri uyusmuyordu, duzeltildi
        List<String> names = new ArrayList<>(); // List<int> names; --> List<String> names = new ArrayList<>(); initialize edilde ve Data type'i int->String' cevrildi
        try {
            Connection c = DriverManager.getConnection(connectionurl, "username", "password");
            Statement statement = c.createStatement(); // prepared statement must be used
            ResultSet rs = statement.executeQuery("SELECT * FROM PERSON WHERE AGE > " + age);
            while (rs.next()) {
                names.add(rs.getObject("NAME").toString());
            }

            //veritabani islemlerimiz bittigi icin, baglantiyi kapatmaliyiz asagidaki gibi. veritabani acik kalmamasi lazim.
            c.close();
            statement.close();
            rs.close();

            //asagidaki islemin bir amaci yok, yoruma alabiliriz
          //  int max = 0;
          //  int min = 9999999;
          //  for (int i=0;i<names.size();i++) {
          //      if (i > max) max = i;
          //      if (i < min) min = i;
          //  }

          //  return Arrays.asList(min + "", max + ""); buraya gerek yok, bosuna calisiyor.
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return names;
    }
}
