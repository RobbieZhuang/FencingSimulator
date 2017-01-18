/* SpriteSheet.java
 *
 */

package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SpriteSheet {

    public String path;
    public int width;
    public int height;
    public int[] pixels;
    public BufferedImage image = null;

    public SpriteSheet(String path) {

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

        this.path = path;
        this.width = image.getWidth();
        this.height = image.getHeight();

        // Importing colour data
        pixels = image.getRGB(0, 0, width, height, null, 0, width);

        // Getting rid of alpha channel
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = (pixels[i] & 0xff) / 64;
        }

        //////////////// Debugging
        for (int i = 0; i < 16; i++) {
            System.out.println(pixels[i]);
        }
    }
}
