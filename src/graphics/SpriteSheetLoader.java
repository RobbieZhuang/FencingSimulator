/* SpriteSheetLoader.java
 *
 */

package graphics;

import java.awt.image.BufferedImage;

public class SpriteSheetLoader {

    public static int rows;
    public static int columns;
    public static BufferedImage[][] sprites;
    public int width;
    public int height;
    public SpriteSheet spriteSheet;

    /**
     * Default constructor for SpriteSheetLoader
     *
     * @param width       is the width of screen
     * @param height      is the height of screen
     * @param spriteSheet is the png file with all the sprites
     * @param rows        is the numbers of rows that the sprite sheet has
     * @param columns     is the number of columns that the sprite sheet has
     * @param spriteSheet is the sprite sheet
     */
    public SpriteSheetLoader(int width, int height, int rows, int columns, SpriteSheet spriteSheet) {
        // Initializing variables
        this.width = width;
        this.height = height;
        SpriteSheetLoader.rows = rows;
        SpriteSheetLoader.columns = columns;
        this.spriteSheet = spriteSheet;

        // Making the big sprite sheet into individual sprites
        sprites = new BufferedImage[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sprites[i][j] = spriteSheet.image.getSubimage(i * width, j * height, width, height);
            }
        }
    }
}