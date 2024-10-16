import java.time.LocalDate;

public class Album {
    private int album_id;
    private String album_name;
    private int artist_id;
    private int company_id;
    private LocalDate date_of_publication;

    public Album(int album_id, LocalDate date_of_publication, int company_id, int artist_id, String album_name) {
        this.album_id = album_id;
        this.date_of_publication = date_of_publication;
        this.company_id = company_id;
        this.artist_id = artist_id;
        this.album_name = album_name;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public LocalDate getDate_of_publication() {
        return date_of_publication;
    }

    public void setDate_of_publication(LocalDate date_of_publication) {
        this.date_of_publication = date_of_publication;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }
}
