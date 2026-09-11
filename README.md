# Playlist Builder

Software Design Patterns — Assignment 1 (Builder pattern), Astana IT University.

## What this is

The task was to pick some product that makes sense to build step by step and implement the Builder pattern for it. I went with a `Playlist` — it's tied to the "music streaming platform" theme our group is working on this semester, and a playlist naturally has a bunch of optional settings (shuffle, repeat, privacy, cover image) that don't all need to be set every time, which is exactly the kind of thing Builder is good for.

Instead of one constructor with six parameters where you have to remember what each `true`/`false` means, you build it up piece by piece:

```java
Playlist workout = new PlaylistBuilder()
        .setName("Workout")
        .addTrack("Track 1")
        .addTrack("Track 2")
        .setShuffle(true)
        .build();
```

## Structure

```
src/playlist/
  Playlist.java          - the finished, immutable product
  PlaylistBuilder.java    - builds it up step by step, validates on build()
  PlaylistDirector.java   - a couple of ready-made presets (Daily Mix, Deep Focus)
  Client.java             - main(), runs both approaches and prints results
```

**Playlist** just holds data once it's built — name, tracks, shuffle/repeat/private flags, cover image. No setters on it, only getters, so once it exists it can't be changed from outside.

**PlaylistBuilder** has the chainable setters plus `build()`, which checks that a name was actually given and that there's at least one track before it lets you have a `Playlist`.

**PlaylistDirector** exists for the cases where you keep building the same "type" of playlist over and over — instead of repeating the same 3-4 setter calls every time, `createDailyMix()` and `createFocusPlaylist()` do it for you.

**Client** is just where I demonstrate both the Director-based way and building one manually.

## Running it

```
javac src/playlist/*.java -d out
java -cp out playlist.Client
```

Output looks like:

```
Playlist{name='Daily Mix', tracks=[Song A, Song B, Song C], shuffle=true}
Playlist{name='Workout', tracks=[Track 1, Track 2], shuffle=true}
Expected error: Playlist must contain at least one track
```

That last line is on purpose — I try to `build()` a playlist with no tracks to show the validation actually catches it.

## Clean code choices

**1. Descriptive method names.** `setShuffle`, `addTrack`, `build` — you can tell what they do without reading the body. I avoided vague names like `set()` or `process()`.

**2. Validation happens at construction, not later.**
```java
// without this check, an "empty" playlist would just silently exist,
// and you'd hit a confusing NullPointerException somewhere much later
if (tracks.isEmpty()) {
    throw new IllegalStateException("Playlist must contain at least one track");
}
```
Failing fast in `build()` means the error points to the actual problem instead of showing up three method calls later.

**3. Small, single-purpose methods.** Every setter in `PlaylistBuilder` does exactly one thing — sets one field, returns `this`. No setter tries to also validate or do something else on the side.

**4. Immutable product.** All fields in `Playlist` are `final`, and there's no way to modify one after it's built. Once you have a `Playlist` object, you know its state won't change under you somewhere else in the code.

**5. Construction is locked to the Builder.** The `Playlist` constructor has no access modifier (package-private), so `new Playlist(...)` only compiles from inside the `playlist` package. From anywhere else, the only way to get a `Playlist` is through `PlaylistBuilder.build()` — which is where the validation lives, so it can't be skipped.

## Notes

Went with Go originally since that's what I'm more comfortable with, but switched to Java to match the assignment requirements after confirming with the instructor.
