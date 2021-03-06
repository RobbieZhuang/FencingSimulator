/* Controls.java
 *
 * This panel has all the controls
 */

package client.gui;

import client.Client;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controls extends JPanel {
    Controls() {

        // Add buttons
        addControlsButtons();

        // Set up GUI
        setLayout(null);
        setSize(new Dimension(Client.WIDTH, Client.HEIGHT));
        setLayout(new BorderLayout());
        setBackground(Color.blue);
    }

    /**
     * This paints the images and words
     *
     * @param g is used to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 24));

        g.drawImage(Screen.spriteBackground.getControls().getImage(), 0, 0, Client.WIDTH, Client.HEIGHT, null);
        g.drawString("Hold sword higher", 150, 210);
        g.drawString("Hold sword lower", 150, 360);
        g.drawString("Move left", 50, 310);
        g.drawString("Move right", 330, 310);
        g.drawString("Attack", 530, 260);
        g.drawString("Parry", 590, 360);
        g.drawString("Jump", 350, 440);
    }

    /**
     * addControlsButtons
     * <p>
     * Adds the buttons to GameOver
     */
    void addControlsButtons() {
        // Adding buttons
        int buttonWidth = Client.WIDTH / 6;
        int buttonHeight = Client.HEIGHT / 25;
        int x = 30;
        int y = Client.HEIGHT - buttonHeight - (int) (2.5 * x);
        int buttonSpacing = 5;

        JButton btnMenu = new JButton("Ok, makes sense");
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Menu pressed");
                Screen.PaintPanel paintPanel = new Screen.PaintPanel();
                Screen.switchComponent(paintPanel);
            }
        });
        btnMenu.setBounds(x, y, buttonWidth, buttonHeight);
        add(btnMenu);
    }
}
