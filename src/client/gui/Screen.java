package client.gui;

import client.Client;
import client.DankTings;
import graphics.Sprite;
import graphics.SpriteSheet;
import graphics.SpriteSheetLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame {

    public static final int SPRITE_SIZE = 70;
    public static final int SPRITE_ROWS = 2;
    public static final int SPRITE_COLUMNS = 32;
    public static final int SPRITE_PIXELS = 16;
    public static JPanel contentPane;
    public static Sprite spriteBackground = new Sprite();
    private static SpriteSheet spriteSheet = new SpriteSheet("/resources/SpriteSheet.png");
    public static SpriteSheetLoader spriteSheetLoader = new SpriteSheetLoader(SPRITE_PIXELS, SPRITE_PIXELS, SPRITE_ROWS, SPRITE_COLUMNS, spriteSheet);

    /**
     * Create the frame.
     */
    public Screen(String name) {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new PaintPanel();
        contentPane.setPreferredSize(new Dimension(800, 600));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.RED);

        setSize(new Dimension(Client.WIDTH, Client.HEIGHT));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Debugging by directly starting the screen
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Screen frame = new Screen("Game");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void add(JPanel panel) {
        contentPane.add(panel);
        refresh();
    }

    public static void switchComponent(Component component) {
        System.out.println("Switching component");
        contentPane.removeAll();
        contentPane.add(component, BorderLayout.CENTER);
        refresh();
    }

    public static void refresh() {
        contentPane.repaint();
    }

    // Paints the background
    static class PaintPanel extends JPanel {

        PaintPanel() {
            setPreferredSize(new Dimension(Client.WIDTH, Client.HEIGHT));
            setLayout(new BorderLayout());
            setBackground(Color.blue);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.GRAY);

            addMenuButtons();

            // Drawing the background
            g.drawImage(spriteBackground.getMenuBackgound().getImage(), 0, 0, Client.WIDTH, Client.HEIGHT, null);
        }

        void addMenuButtons() {
            System.out.println("Adding menu buttons");

            // Adding buttons
            int buttonWidth = Client.WIDTH / 8;
            int buttonHeight = Client.HEIGHT / 25;
            int y = Client.HEIGHT - buttonHeight - 85;
            int buttonSpacing = 5;

            JButton btnPlayOnline = new JButton("Play Online");
            btnPlayOnline.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    System.out.println("Play Online pressed");
//                    try {
                        Screen.switchComponent(new DankTings(Client.clientReceiver.getPlayerID(), Client.clientSender, Client.players));
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(null, "Not connected to server", "Connection Error", 0);
//                    }
                }
            });
            btnPlayOnline.setBounds(Client.WIDTH / 2 - buttonWidth * 3 / 2 - buttonSpacing, y, buttonWidth, buttonHeight);
            add(btnPlayOnline);

            JButton btnNewButton = new JButton("Rules");
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    System.out.println("Rules pressed");
                    Screen.switchComponent(new Rules());
                }
            });
            btnNewButton.setBounds(Client.WIDTH / 2 - buttonWidth / 2, y, buttonWidth, buttonHeight);
            add(btnNewButton);

            JButton btnControls = new JButton("Controls");
            btnControls.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    System.out.println("Controls pressed");
                    Screen.switchComponent(new Controls());
                }
            });
            btnControls.setBounds(Client.WIDTH / 2 + buttonWidth / 2 + buttonSpacing, y, buttonWidth, buttonHeight);
            add(btnControls);
        }
    }
}
