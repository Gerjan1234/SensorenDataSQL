import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class Runner {
    private static String connectie;
    private static String user;
    private static String password;
    private static String database;
    private static String tabel;
    private static RepoUitlezen uitlezen;
    private static Quari quari;
    private static Connection conn;

    public static void main(String[] args) {
        new Runner().getRepodata();
        try {
            new Runner().getAVG();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public void getRepodata() {
        uitlezen = new RepoUitlezen();
        this.user = uitlezen.getRepodata(0);
        this.password = uitlezen.getRepodata(1);
        this.connectie = uitlezen.getRepodata(2);
        this.database = uitlezen.getRepodata(3);
        this.tabel = uitlezen.getRepodata(4);
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?serverTimezone=EST", connectie, database), user, password);
    }

    public static void getAVG() throws SQLException {
        HashMap<Integer, String> H = new HashMap<>();
        H.put(1, "temperatuur_binnen");
        H.put(2, "temperatuur_buiten");
        H.put(3, "temperatuur_kruipruimte");
        H.put(4, "luchtvochtigheid_kruipruimte");
        H.put(5, "huidigelectverbruik");
        H.put(6, "totaalstandgasmeter");
        H.put(7, "totaalstandelectmeter");

        try
                (Scanner s = new Scanner(System.in);
                Connection conn = getConnection()) {

        for(int i =1; i <= H.size(); i++ ) {
            System.out.println(i + " " + H.get(i));
        }
            System.out.println("kies AVG waarde");
            int AVG = s.nextInt();
            System.out.println("geef dag op");
            int dag = s.nextInt();
            System.out.println("geef maand op");
            int maand = s.nextInt();
            System.out.println("geef jaar op");
            int jaar = s.nextInt();
            System.out.println("geef uur op");
            int uur = s.nextInt();
            new Quari().AVGTEST(conn, H.get(AVG), dag, maand, jaar, uur);
        }

    }
}