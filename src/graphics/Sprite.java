/* Sprite.java
 *
 * Contains all the sprites
 */

package graphics;

public class Sprite {
    private SpriteLoader menuBackground;
    private SpriteLoader winBackground;
    private SpriteLoader loseBackground;
    private SpriteLoader rules;
    private SpriteLoader controls;
    private SpriteLoader lobby;

    /**
     * Default constructor
     */
    public Sprite() {
        menuBackground = new SpriteLoader("/resources/MenuBackground.png");
        winBackground = new SpriteLoader("/resources/WinBackground.png");
        loseBackground = new SpriteLoader("/resources/LoseBackground.png");
        rules = new SpriteLoader("/resources/Rules.png");
        controls = new SpriteLoader("/resources/Controls.png");
        lobby = new SpriteLoader("/resources/Lobby.png");
    }

    /**
     * getMenuBackground
     *
     * @return menuBackground
     */
    public SpriteLoader getMenuBackground() {
        return menuBackground;
    }

    /**
     * getWinBackground
     *
     * @return winBackground
     */
    public SpriteLoader getWinBackground() {
        return winBackground;
    }

    /**
     * getLoseBackground
     *
     * @return loseBackground
     */
    public SpriteLoader getLoseBackground() {
        return loseBackground;
    }

    /**
     * getRules
     *
     * @return rules the BufferedImage
     */
    public SpriteLoader getRules() {
        return rules;
    }

    /**
     * getControles
     *
     * @return controls the Bufferedimage
     */
    public SpriteLoader getControls() {
        return controls;
    }

    /**
     * getLobby
     *
     * @return lobby background in BufferedImage
     */
    public SpriteLoader getLobby() {
        return lobby;
    }
}
