import javax.sound.midi.Track;
import java.sql.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBaseHandler {

    public static Connection con;
    public static final String album = "com.mysql.jdbc.album";
    public static final String artist = "com.mysql.jdbc.artist";
    public static final String record_company = "com.mysql.jdbc.record_company";
    public static final String tracks = "com.mysql.jdbc.tracks";

    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/album_and_songs";
    static String user = "root";
    static String password = "Ma2404ro@@@@";



    public static void seeListTracks(Scanner input) throws SQLException{

        int track_id = 0;
        String track_name = null;
        Time track_duration = null;
        int album_id = 0;
        Tracks track;
        ArrayList<Tracks> tracks =new ArrayList<>();
        try {
            con = DriverManager.getConnection(DATABASE_URL, user, password);
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM tracks");

            while (rs.next()) {
                track_id = rs.getInt("track_id");
                track_name = rs.getString("track_name");
                track_duration = rs.getTime("track_duration");
                album_id = rs.getInt("album_id");
                track = new Tracks(track_id, track_name, track_duration, album_id);
                tracks.add(track);
            }
            //s.close();
            //con.close();
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
            con.close();
        }

        for(Tracks t: tracks){
            System.out.println(t.toString());
        }
    }
}
