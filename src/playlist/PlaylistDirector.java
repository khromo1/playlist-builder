package playlist;
import java.util.List;
public class PlaylistDirector {
    public Playlist createDailyMix(List<String> tracks) {
        PlaylistBuilder builder = new PlaylistBuilder()
                .setName("DailyMix")
                .setShuffle(true);
        tracks.forEach(builder::addTrack);
        return builder.build();
    }
    public Playlist createFocusPlaylist(List<String> tracks) {
        PlaylistBuilder builder = new PlaylistBuilder()
                .setName("Deep Focus")
                .setRepeat(true)
                .setPrivate(true);
        tracks.forEach(builder::addTrack);
        return builder.build();
    }
}
