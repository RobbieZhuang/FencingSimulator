/* Sprite.java
 *
 * Contains all the sprites
 */

package graphics;

public class Sprite {
    private SpriteLoader menuBackgound;
    private SpriteLoader winBackground;
    private SpriteLoader loseBackground;

    /**
     * Default constructor
     */
    public Sprite() {
        menuBackgound = new SpriteLoader("/resources/MenuBackground.png");
        winBackground = new SpriteLoader("/resources/WinBackground.png");
        loseBackground = new SpriteLoader("/resources/loseBackground.png");
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
}
