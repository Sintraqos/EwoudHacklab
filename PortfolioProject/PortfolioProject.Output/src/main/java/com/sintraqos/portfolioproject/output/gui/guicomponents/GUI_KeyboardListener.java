package com.sintraqos.portfolioproject.output.gui.guicomponents;

import com.sintraqos.portfolioproject.statics.GameSettings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI_KeyboardListener extends JFrame implements KeyListener{

    public GUI_KeyboardListener(String s) {
        super(s);
        JPanel p = new JPanel();
        add(p);
        addKeyListener(this);
        setSize(800, 600);
        this.setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for(String key : GameSettings.getInstance().getKeyBindings().values()){
            if(e.getKeyCode() == GameSettings.getInstance().getKeyBinding(key))
                System.out.println("Key: " + key);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(String key : GameSettings.getInstance().getKeyBindings().values()){
            if(e.getKeyCode() == GameSettings.getInstance().getKeyBinding(key))
                System.out.println("Key Pressed: " + key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(String key : GameSettings.getInstance().getKeyBindings().values()){
            if(e.getKeyCode() == GameSettings.getInstance().getKeyBinding(key))
                System.out.println("Key Released: " + key);
        }
    }
}
