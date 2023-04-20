package Notepad;

import java.awt.event.*;

public class KeyHandler implements KeyListener {

    GUI gui;

    public KeyHandler(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isShiftDown() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
            gui.file.saveAs();
        } else if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_E) {
            gui.menuEdit.doClick();
        } else if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_F) {
            gui.menuFile.doClick();
        }  else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_H) {
            gui.about.showAbout();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}

