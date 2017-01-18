/* SpriteLoader.java
 *
 */

package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SpriteLoader {

    private BufferedImage image;
    private int width;
    private int height;

    public SpriteLoader(String path) {
        // Loading image
        try {
            image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
        } catch (Exception e) {
            System.out.println("*** Error loading image source ***");
            e.printStackTrace();
        }

        // Just in case the image is null
        if (image == null) {
            System.out.println("*** Image is null ***");
            return;
        }

        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public BufferedImage getImage() {
        return image;
    }
}
