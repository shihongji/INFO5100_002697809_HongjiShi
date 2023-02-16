public class Song {
    private String title;
    private String artist;
    private String album;
    private int year;
    private int duration; // in seconds
    private String genre;
    private String language;
    private String composer;

    public String getTitle() {
        return title;
    }
    public String getArtist() {
        return artist;
    }
    public String getDuration() {
        int min = duration / 60;
        int sec = duration % 60;
        return String.format("%d:%02d", min,sec);
    }
    public Song(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        System.out.println("It's from " + this.artist + ", do you like? ");
    }
}
