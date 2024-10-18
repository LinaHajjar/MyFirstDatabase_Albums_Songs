import javax.sound.midi.Track;
import java.sql.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
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
        //new
        String artist_name = null;
        Tracks track;
        ArrayList<Tracks> tracks =new ArrayList<>();
        ArrayList<Tracks> artistTracks =new ArrayList<>(); // arrayList that contains all the tracks to a given artist
        try {
            con = DriverManager.getConnection(DATABASE_URL, user, password);
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT t.*, ar.artist_name FROM tracks t JOIN album a ON t.album_id = a.album_id join artist ar ON a.artist_id = ar.artist_id");

            while (rs.next()) {
                track_id = rs.getInt("track_id");
                track_name = rs.getString("track_name");
                track_duration = rs.getTime("track_duration");
                album_id = rs.getInt("album_id");
                artist_name = rs.getString("artist_name");
                track = new Tracks(track_id, track_name, track_duration, album_id, artist_name);
                tracks.add(track);
            }
            //s.close();
            //con.close();
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
            con.close();
        }

        /*for(Tracks t: tracks){
            System.out.println(t.toString());
        }*/

        System.out.println("How do you want to see the tracks' list ordered?");
        System.out.println("1. By alphabetical order according to the tracks' names");
        System.out.println("2. By the tracks's duration");
        System.out.println("3. See all the tracks for a specific artist");
        int choice = UserInput.getIntInput("choose the option: ","wrong input, choose only an integer between 1 and 3",1,3);
        String answer;
        switch(choice){
            case 1: //alphabetic order

                Collections.sort(tracks, (track1, track2) -> track1.getTrack_name().compareTo(track2.getTrack_name()));
                for(Tracks t: tracks){
                    System.out.println(t.toString());
                }
                System.out.println("do you want to see the tracks' list ordered in another way? yes/no");
                answer=input.nextLine();
                if(UserInput.containsIgnoreCase("yes", answer)){
                    seeListTracks(input);
                }else{
                    System.out.println("returning to the main menu: ");
                    UI.hovedMenu(input);
                }
                break;

            case 2: //duration
                Collections.sort(tracks, (track1, track2) -> {
                    long duration1 = track1.getTrack_duration().getTime();
                    long duration2 = track2.getTrack_duration().getTime();
                    return Long.compare(duration1, duration2);
                });

                for(Tracks t: tracks){
                    System.out.println(t.toString());
                }

                System.out.println("do you want to see the tracks' list ordered in another way? yes/no");
                answer=input.nextLine();
                if(UserInput.containsIgnoreCase("yes", answer)){
                    seeListTracks(input);
                }else{
                    System.out.println("returning to the main menu: ");
                    UI.hovedMenu(input);
                }
                break;

            case 3: //for a specific artist
                System.out.println("Enter the artist name: ");
                String artistName = input.nextLine();

                for (Tracks t: tracks) {
                    if (UserInput.containsIgnoreCase(t.getArtist_name(), artistName)) {
                        artistTracks.add(t);
                    }
                }

                if (artistTracks.isEmpty()) {
                    System.out.println("No tracks found for the given artist: " + artistName);
                } else {
                    System.out.println("all the tracks for the artist: " + artistName);
                }

                for (Tracks t : artistTracks) {
                    System.out.println(t.toString());
                }

                System.out.println("do you want to see the tracks' list ordered in another way? yes/no");
                answer = input.nextLine();
                if (UserInput.containsIgnoreCase("yes", answer)) {
                    seeListTracks(input);
                } else {
                    System.out.println("returning to the main menu: ");
                    UI.hovedMenu(input);
                }

            break;

            default:
                System.out.println("wrong input, choose only an integer between 1 and 3");
                seeListTracks(input);
                break;

        }// end switch

    }// end of seeListTracks
}
