## The Game
The Greatest Race is a top down two player game. For each level, the aim is to arrive first at the goal. There's no real multiplayer functionality, both players must play on the same keyboard. Unfortunately, it turns out that by holding down several keys, one player can interfere with the other's controls.

## Requirements
This game is written in Java and there are no precompiled jars available, so a JDK is necessary to compile the code. The JavaFX library is also required, preferably 13, although 12 should work as well. Lower versions will not compile as they don't provide `GraphicsContext#setImageSmoothing`.

## Compiling and Running
Throughout development, `ant` was used to ease compilation.

```
$ ant compile
*compile compile*
$ ant game
*compile and play the game*
$ ant mapmaker
*create and edit levels*
$ ant clean
*remove compiled code*
```

Before compiling, however, you'll need to set the environment variable `JAVAFX_HOME` to the installation location of JavaFX.

## Assets
See [src/assets/credits.txt](src/assets/credits.txt).

## Screenshots
Actual gameplay.

![src/assets/screenshots/screenshot-1-selection.png](src/assets/screenshots/screenshot-1-selection.png)

![src/assets/screenshots/screenshot-3-gameplay.png](src/assets/screenshots/screenshot-3-gameplay.png)

![src/assets/screenshots/screenshot-2-gameover.png](src/assets/screenshots/screenshot-2-gameover.png)
