package playlist;
import java.util.ArrayList;
import java.util.List;
public class PlaylistBuilder {
    String name;
    List<String> tracks = new ArrayList<>();
    boolean shuffle = false;
    boolean repeat = false;
    boolean isPrivate = false;
    String coverImage;
    public PlaylistBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public PlaylistBuilder addTrack(String track) {
        this.tracks.add(track);
        return this;
    }
    public PlaylistBuilder setShuffle(boolean shuffle) { this.shuffle = shuffle; return this; }
    public PlaylistBuilder setRepeat(boolean repeat) { this.repeat = repeat; return this; }
    public PlaylistBuilder setPrivate(boolean isPrivate) { this.isPrivate = isPrivate; return this; }
    public PlaylistBuilder setCoverImage(String url) { this.coverImage = url; return this; }
    public Playlist build() {
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Playlist name is required");
        }
        if (tracks.isEmpty()) {
            throw new IllegalStateException("Playlist must contain at least one track");
        }
        return new Playlist(this);
    }
}
