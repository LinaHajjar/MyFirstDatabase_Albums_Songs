import javax.sound.midi.Track;
import java.sql.*;
import java.time.LocalDate;
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
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
            con.close();
        }

        System.out.println("How do you want to see the tracks' list order?");
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

    public static void seeListArtists (Scanner input) throws SQLException {

        int artist_id = 0;
        String artist_name = null;
        String artist_origin_country = null;
        ArtistGender artist_gender = null;
        LocalDate artist_date_of_birth = null;

        Artist artist;
        ArrayList<Artist> artists = new ArrayList<>();

        try {
            con = DriverManager.getConnection(DATABASE_URL, user, password);
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM artist");

            while (rs.next()) {
                artist_id = rs.getInt("artist_id");
                artist_name = rs.getString("artist_name");
                artist_origin_country = rs.getString("artist_origin_country");
                artist_gender = ArtistGender.valueOf(rs.getString("artist_gender"));
                artist_date_of_birth = rs.getDate("artist_date_of_birth").toLocalDate();
                artist = new Artist(artist_id, artist_name, artist_date_of_birth, artist_gender, artist_origin_country);
                artists.add(artist);
            }
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
            con.close();
        }

        System.out.println("How do you want to see the artists' list order?");
        System.out.println("1. By their ID");
        System.out.println("2. By the alphabetic order of the artists' names");
        System.out.println("3. By their date of birth");
        int choice = UserInput.getIntInput("choose the option: ", "wrong input, choose only an integer between 1 and 3", 1, 3);
        String answer;

        switch (choice) {
            case 1: //by their ID

                for (Artist a : artists) {
                    System.out.println(a.toString());
                }

                System.out.println("do you want to see the artists' list ordered in another way? yes/no");
                answer = input.nextLine();
                if (UserInput.containsIgnoreCase("yes", answer)) {
                    seeListArtists(input);
                } else {
                    System.out.println("returning to the main menu: ");
                    UI.hovedMenu(input);
                }

                break;

            case 2: //by alphabetic order of the artists' names
                Collections.sort(artists, (artist1, artist2) -> artist1.getArtist_name().compareTo(artist2.getArtist_name()));
                System.out.println("Artists sorted by alphabetical order:");
                for (Artist a: artists) {
                    System.out.println(a.toString());
                }

                System.out.println("do you want to see the tracks' list ordered in another way? yes/no");
                answer=input.nextLine();
                if(UserInput.containsIgnoreCase("yes", answer)){
                    seeListArtists(input);
                }else{
                    System.out.println("returning to the main menu: ");
                    UI.hovedMenu(input);
                }
                break;

            case 3: //by their dates of birth
                Collections.sort(artists, (artist1, artist2) -> artist1.getArtist_date_of_birth().compareTo(artist2.getArtist_date_of_birth()));
                System.out.println("Artists sorted by date of birth:");
                for (Artist a: artists) {
                    System.out.println(a.toString());
                }

                for (Artist a: artists) {
                    System.out.println(a.toString());
                }

                System.out.println("do you want to see the tracks' list ordered in another way? yes/no");
                answer = input.nextLine();
                if (UserInput.containsIgnoreCase("yes", answer)) {
                    seeListArtists(input);
                } else {
                    System.out.println("returning to the main menu: ");
                    UI.hovedMenu(input);
                }

                break;

            default:
                System.out.println("wrong input, choose only an integer between 1 and 3");
                seeListTracks(input);
                break;
        }// end switch*/
    }// end of seeListArtists

    public static void add_artist(Scanner input) throws SQLException {
        System.out.println("Generating a new artist_id:...");

        System.out.println("what is the name of the new artist? ");
        String artist_name = input.nextLine();
        System.out.println("what is the origin country of the artist? ");
        String artist_origin_country = input.nextLine();
        System.out.println("what is his gender: male / female ? ");
        String gender = input.nextLine();
        String artist_gender = null;
        if ("male".equalsIgnoreCase(gender)){
            artist_gender="male";
        }else {
            artist_gender="female";
        }

        System.out.println("what is the date of birth of the artist? year-month-day: ");
        LocalDate artist_date_of_birth = LocalDate.parse(input.nextLine());

            try {
                con = DriverManager.getConnection(DATABASE_URL, user, password);
                Statement s = con.createStatement();
                int addingRow = s.executeUpdate("INSERT INTO artist (artist_name, artist_origin_country, artist_gender, artist_date_of_birth)\n" +
                        "VALUES ('" +artist_name + "', '" + artist_origin_country +"', '" +artist_gender+ "','"+artist_date_of_birth+"')");

                System.out.println("succesfully added the new artist's information to the database");

            } catch (SQLException sqlex) {
                System.out.println("SQL Error: " + sqlex.getMessage());
                con.close();
            }

        System.out.println("do you want to add another artist? yes/no");
        String answer = input.nextLine();
        if (UserInput.containsIgnoreCase("yes", answer)) {
            add_artist(input);
        }else{
            return;
        }
    }// end of add_artist

    public static void add_album(Scanner input) throws SQLException {
        System.out.println("Generating a new album_id:...");

        System.out.println("what is the name of the new album? ");
        String album_name = input.nextLine();

        System.out.println("what is the name of the artist? ");
        String artist_name = input.nextLine();

        System.out.println("what is the company name? ");
        String company_name = input.nextLine();

        System.out.println("what is the date of publication? year-month-day: ");
        LocalDate date_of_publication = LocalDate.parse(input.nextLine());


        /*

    // Retrieve artist_id

    // Retrieve company_id
    */

        try {
            con = DriverManager.getConnection(DATABASE_URL, user, password);
            Statement s = con.createStatement();

            // Retrieve artist_id:
            ResultSet srArtist = s.executeQuery("SELECT artist_id FROM artist WHERE artist_name = '" + artist_name + "'  ");
            int artist_id = 0;
            if (srArtist.next()) {
                artist_id = srArtist.getInt("artist_id");
            } else {
                System.out.println("Artist not found.");
                return;
            }
            srArtist.close();

            // Retrieve company_id
            ResultSet srCompany = s.executeQuery("SELECT company_id FROM record_company WHERE company_name = '" + company_name + "'");
            int company_id = 0;
            if (srCompany.next()) {
                company_id = srCompany.getInt("company_id");
            } else {
                System.out.println("Company not found.");
                return;
            }
            srCompany.close();

            int addingRow = s.executeUpdate("INSERT INTO album (album_name, artist_id, company_id, date_of_publication)\n" +
                    "VALUES ('" +album_name + "', "+ artist_id +" , "+ company_id +" ,'"+date_of_publication+"')");

            System.out.println("succesfully added the new album's information to the database");

        } catch (SQLException sqlex) {
            System.out.println("SQL Error: " + sqlex.getMessage());
            con.close();
        }

        System.out.println("do you want to add another artist? yes/no");
        String answer = input.nextLine();
        if (UserInput.containsIgnoreCase("yes", answer)) {
            add_album(input);
        }else{
            return;
        }
    }// end of add_artist


}
