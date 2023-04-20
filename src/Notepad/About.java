package Notepad;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class About extends JFrame {

    GUI gui;

    public About(GUI gui) {
        this.gui = gui;
    }

    public void showAbout() {
        setSize(500, 400);
        setLocationRelativeTo(gui.window);
        setTitle("About Notepad Application");
        ImageIcon icon = new ImageIcon(getClass().getResource("Notepad.png"));
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        getContentPane().setLayout(null);

        JLabel iconLabel = new JLabel(new ImageIcon(getClass().getResource("Notepad.png")));
        iconLabel.setBounds(200, 20, 120, 120);
        add(iconLabel);

        JLabel textLabel = new JLabel(
                "<html>Welcome to Notepad.<br>Notepad is a simple text editor for Microsoft Windows and a basic text-editing program which enables coputer users to create documents.<br>All rights reserved@2023</html>");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        textLabel.setBounds(80, 40, 350, 300);
        add(textLabel);

        setVisible(true);
    }

}

