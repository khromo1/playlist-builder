package playlist;
import java.util.List;
public class Client {
    public static void main(String[] args) {
        PlaylistDirector director = new PlaylistDirector();
        Playlist dailyMix = director.createDailyMix(List.of("Song 1", "Song 2", "Song 3"));
        System.out.println(dailyMix);
        Playlist custom = new PlaylistBuilder()
                .setName("My training session")
                .addTrack("Track 1")
                .addTrack("Track 2")
                .setShuffle(true)
                .build();
        System.out.println(custom);
        try {
            new PlaylistBuilder().setName("Empty").build();
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
