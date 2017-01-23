/* Sprite.java
 *
 * Contains all the sprites
 */

package graphics;

public class Sprite {
    private SpriteLoader menuBackgound;
    private SpriteLoader winBackground;
    private SpriteLoader loseBackground;
    private SpriteLoader rules;
    private SpriteLoader controls;

    /**
     * Default constructor
     */
    public Sprite() {
        menuBackgound = new SpriteLoader("/resources/MenuBackground.png");
        winBackground = new SpriteLoader("/resources/WinBackground.png");
        loseBackground = new SpriteLoader("/resources/LoseBackground.png");
        rules = new SpriteLoader("/resources/Rules.png");
        controls = new SpriteLoader("/resources/Controls.png");
    }

    /**
     * getMenuBackground
     *
     * @return menuBackground
     */
    public SpriteLoader getMenuBackgound() {
        return menuBackgound;
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
}
