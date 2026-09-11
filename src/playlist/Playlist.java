package playlist;
import java.util.List;
public class Playlist {
    private final String name;
    private final List<String> tracks;
    private final boolean shuffle;
    private final boolean repeat;
    private final boolean isPrivate;
    private final String coverImage;
    Playlist(PlaylistBuilder builder) {
        this.name = builder.name;
        this.tracks = builder.tracks;
        this.shuffle = builder.shuffle;
        this.repeat = builder.repeat;
        this.isPrivate = builder.isPrivate;
        this.coverImage = builder.coverImage;
    }
    public String getName() { return name; }
    public List<String> getTracks() { return tracks; }
    public boolean isShuffle() { return shuffle; }
    public boolean isRepeat() { return repeat; }
    public boolean isPrivate() { return isPrivate; }
    public String getCoverImage() { return coverImage; }
    @Override
    public String toString() {
        return "Playlist{name='" + name + "', tracks=" + tracks + ", shuffle=" + shuffle + "}";
    }
}
