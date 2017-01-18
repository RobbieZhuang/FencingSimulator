/* MenuPanel.java
 *
 */

package client.gui;

import client.Client;
import graphics.SpriteLoader;

import javax.swing.JPanel;

public class MenuPanel extends JPanel {
    static SpriteLoader menuBackground;
    private final int WIDTH = Client.WIDTH;
    private final int HEIGHT = Client.HEIGHT;

    MenuPanel() {


        this.setVisible(true);
        Screen.refresh();
    }

//    // Paints the background
//    static class PaintPanel extends JPanel {
//        PaintPanel() {
//            setPreferredSize(new Dimension(WIDTH, HEIGHT));
//            setBackground(Color.blue);
//        }
//
//        @Override
//        protected void paintComponent(Graphics g) {
//            System.out.println("here");
//            super.paintComponent(g);
//            g.drawImage(menuBackground.getImage(), 0, 0, WIDTH, HEIGHT, null);
//        }
//    }
}