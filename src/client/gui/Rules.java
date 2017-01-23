/* Rules.java
 *
 * This is a panel that displays the rules
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

public class Rules extends JPanel {
    Rules() {

        // Add buttons
        addRulesButtons();

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

        int top = 250;
        int spacing = 50;

        // Draws the background and words
        g.drawImage(Screen.spriteBackground.getRules().getImage(), 0, 0, Client.WIDTH, Client.HEIGHT, null);
        g.drawString("The objective is to get to the other side", 30, top);
        g.drawString("You can stab the other dank ting to advance to the next screen", 30, top + spacing);
        g.drawString("Good luck and have fun!", 30, top + 2 * spacing);
    }

    /**
     * addRulesButtons
     * <p>
     * Adds the buttons to GameOver
     */
    void addRulesButtons() {
        // Adding buttons
        int buttonWidth = Client.WIDTH / 6;
        int buttonHeight = Client.HEIGHT / 25;
        int x = 30;
        int y = Client.HEIGHT - buttonHeight - (int) (2.5 * x);
        int buttonSpacing = 5;

        JButton btnMenu = new JButton("I understand");
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
