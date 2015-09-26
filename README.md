What it looks like
========

[Image Here]

Usage
===

Draw to an existing `ImageView`:
---

    // Prepare data
    Resources resources = activity.getResources();
    int width = 200;
    int height = 200;
    String chordName = "Am";
    int position = 0; // fret position index (0 to 12)
    int transpose = 0; // transpose distance (-12 to 12)
    
    // Draw chord
    BitmapDrawable chord = DrawHelper.getBitmapDrawable(
        resources, width, height, chordName, position, transpose);
        
    // Display chord to your image view
    yourImageView.setImageDrawable(theReturnValue);

Use `ChordTextureView`:
---

In your layout:

    <com.dqt.libs.chorddroid.components.ChordTextureView
        android:layout_width="150dp"
        android:layout_height="150dp"
        android:layout_marginLeft="20dp"
        android:layout_centerVertical="true"
        android:id="@+id/chord_texture_view" />

In your code:

    ChordTextureView chord = (ChordTextureView) findViewById(R.id.chord_texture_view);
    chord.drawChord("Am", 0);

Chord Helper
===

    ChordHelper.transpose(String chordName, int distance)
    ChordHelper.getChord(String chordName, int position)
    ChordHelper.simplifyName(String chordName)


Build the library
===

    ./gradlew assemble

Check `chorddroid/build/outputs/arr` directory to find `chorddroid-release.arr` files.

Add library to your project
===

1. Copy `aar` file to your `libs` directory
2. Add following section to your gradle file:
  
Add `flatDir` to your `repositories` tag:
  
    repositories {
        ...
        flatDir {
            dirs 'libs/chorddroid'
        }
    }
    
Add `compile` to your `dependencies` tag:

    dependencies {
        ...
        compile(name:'chorddroid-release', ext:'aar')
    }
    
    