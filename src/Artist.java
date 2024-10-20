import java.time.LocalDate;

public class Artist {
    private int artist_id;
    private String artist_name;
    private String artist_origin_country;
    private ArtistGender artist_gender;
    private LocalDate artist_date_of_birth;

    public Artist(int artist_id, String artist_name, LocalDate artist_date_of_birth, ArtistGender artist_gender, String artist_origin_country) {
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.artist_date_of_birth = artist_date_of_birth;
        this.artist_gender = artist_gender;
        this.artist_origin_country = artist_origin_country;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getArtist_origin_country() {
        return artist_origin_country;
    }

    public void setArtist_origin_country(String artist_origin_country) {
        this.artist_origin_country = artist_origin_country;
    }

    public ArtistGender getArtist_gender() {
        return artist_gender;
    }

    public void setArtist_gender(ArtistGender artist_gender) {
        this.artist_gender = artist_gender;
    }

    public LocalDate getArtist_date_of_birth() {
        return artist_date_of_birth;
    }

    public void setArtist_date_of_birth(LocalDate artist_date_of_birth) {
        this.artist_date_of_birth = artist_date_of_birth;
    }

    public String toString() {
        return ("Artist_id= " + artist_id + "\nArtist_name= " + artist_name +"\nArtist_origin_country= " + artist_origin_country + "\nArtist_gender= " + artist_gender+ "\nArtist_date_of_birth= " + artist_date_of_birth+"\n");
    }

}
