import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Quari {

/*    public Quari(){

    }*/


    public double AVG(Connection conn) throws SQLException {
        Double avgVerbruik = null;
        PreparedStatement stmt = conn.prepareStatement("select Avg(huidigelectverbruik) from HomeData;");
        //stmt.setString(1, department);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            avgVerbruik = (rs.getDouble("Avg(huidigelectverbruik)"));
            // }
            System.out.println(avgVerbruik);

        }
        return avgVerbruik;

    }
    public double AVG(Connection conn, int dag, int maand, int jaar, int uur) throws SQLException {
        Double avgVerbruik = null;
        PreparedStatement stmt = conn.prepareStatement("select Avg(huidigelectverbruik) from HomeData where day(timestamp) =? and month(timestamp) =? and year(timestamp) =? and hour(timestamp) =?;");
        stmt.setInt(1, dag);
        stmt.setInt(2, maand);
        stmt.setInt(3, jaar);
        stmt.setInt(4, uur);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            avgVerbruik = (rs.getDouble("Avg(huidigelectverbruik)"));
            // }
            System.out.println(avgVerbruik);

        }
        return avgVerbruik;
    }

    public double AVGTEST(Connection conn, String AVG, int dag, int maand, int jaar, int uur) throws SQLException {
        Double avgVerbruik = null;
        String day = "";
        String month = "";
        String year = "";
        String hour = "";

        if(dag != 0) {
            day = " day(timestamp) =" + dag;
        }
        if(maand != 0) {
            month = " and month(timestamp) =" + maand;
        }
        if(jaar != 0) {
            year = " and year(timestamp) =" + jaar;
        }
        if(uur != 0) {
            hour = " and hour(timestamp) =" + uur;
        }
        String maakstring ="";
        maakstring = maakstring.concat("select Avg(" + AVG + ") from HomeData where" +  day + month +  year + hour + ";");

        if (maakstring.contains("where and")){
            maakstring = maakstring.replaceAll("where and", "where");
            System.out.println("bad request");
        }
        //PreparedStatement stmt = conn.prepareStatement("select Avg(huidigelectverbruik) from HomeData where" +  day + "and" + month + "and" + year + "and" + hour + ";");
        System.out.println(maakstring);
        PreparedStatement stmt = conn.prepareStatement(maakstring);
        //PreparedStatement stmt = conn.prepareStatement("select Avg(huidigelectverbruik) from HomeData where day(timestamp) =? and month(timestamp) =? and year(timestamp) =? and hour(timestamp) =?;");
        //stmt.setInt(1, dag);
        //stmt.setInt(2, maand);
        //stmt.setInt(3, jaar);
        //stmt.setInt(4, uur);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            avgVerbruik = (rs.getDouble("Avg(" + AVG + ")"));
            // }
            System.out.println("gemiddelde " + AVG + " = " + avgVerbruik);

        }
        return avgVerbruik;
    }
}
