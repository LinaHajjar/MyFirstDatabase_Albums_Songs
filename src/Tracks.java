import java.sql.Time;

public class Tracks {
    private int track_id;
    private String track_name;
    private Time track_duration;
    private int album_id;

    public Tracks(int track_id, String track_name, Time track_duration, int album_id) {
        this.track_id = track_id;
        this.track_name = track_name;
        this.track_duration = track_duration;
        this.album_id = album_id;
    }

    public int getTrack_id() {
        return track_id;
    }

    public String getTrack_name() {
        return track_name;
    }

    public Time getTrack_duration() {
        return track_duration;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public void setTrack_duration(Time track_duration) {
        this.track_duration = track_duration;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    public String toString() {
        return ("Track_id= " + track_id + "\nTrack_name= " + track_name +"\nTrack_duration= " + track_duration + "\nAlbum_id= " + album_id+ "\n");
    }

}
