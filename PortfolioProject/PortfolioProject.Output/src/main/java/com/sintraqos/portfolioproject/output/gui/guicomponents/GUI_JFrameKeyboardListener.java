package com.sintraqos.portfolioproject.output.gui.guicomponents;

import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.GameSettings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI_JFrameKeyboardListener extends JFrame implements KeyListener {

    public GUI_JFrameKeyboardListener(String s) {
        super(s);
        addKeyListener(this);
        setSize(GameSettings.getInstance().getWindowSize().width, GameSettings.getInstance().getWindowSize().height);
        this.setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for (String key : GameSettings.getInstance().getKeyBindings().values()) {
            if (e.getKeyCode() == GameSettings.getInstance().getKeyBinding(key)) {
                Console.writeLine("Key: " + key);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (String key : GameSettings.getInstance().getKeyBindings().values()) {
            if (e.getKeyCode() == GameSettings.getInstance().getKeyBinding(key)) {
                Console.writeLine("Key Pressed: " + key);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (String key : GameSettings.getInstance().getKeyBindings().values()) {
            if (e.getKeyCode() == GameSettings.getInstance().getKeyBinding(key)) {
                Console.writeLine("Key Released: " + key);
            }
        }
    }
}
