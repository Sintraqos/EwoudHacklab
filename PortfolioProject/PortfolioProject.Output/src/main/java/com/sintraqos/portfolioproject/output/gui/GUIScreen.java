package com.sintraqos.portfolioproject.output.gui;

import com.sintraqos.portfolioproject.output.gui.guicomponents.GUI_JPanelBackground;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.GameSettings;
import com.sintraqos.portfolioproject.statics.StaticUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Component.CENTER_ALIGNMENT;

public class GUIScreen {
    // Set references
    GameGUIManager gameGUIManager;
    JPanel rootPanel;

    public GameGUIManager getGameGUIManager() {
        return gameGUIManager;
    }

    //region Setup

    public void setup(JPanel rootPanel) {
        setup(rootPanel,new BoxLayout(rootPanel, BoxLayout.Y_AXIS) );
    }

    public void setup(JPanel rootPanel, LayoutManager layout) {
        // Set references
        gameGUIManager = GameGUIManager.getInstance();

        this.rootPanel = rootPanel;

        setLayout(this.rootPanel, layout);                                           // Set the window layout group
        setWindowSize();
    }

    //endregion

    //region Set window size

    public void setWindowSize() {

        // Apply settings
        gameGUIManager.getFrame().setContentPane(rootPanel);    // Set the window object
        gameGUIManager.getFrame().add(Box.createVerticalGlue());
        gameGUIManager.getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Make sure everything is properly set in the layout
        gameGUIManager.getFrame().addKeyListener(gameGUIManager.getFrame());
        gameGUIManager.getFrame().setSize(GameSettings.getInstance().getWindowSize().width, GameSettings.getInstance().getWindowSize().height); // Set the window size
        gameGUIManager.getFrame().setForeground(StaticUtils.GUI_FOREGROUND_COLOR);
        gameGUIManager.getFrame().setBackground(StaticUtils.GUI_BACKGROUND_COLOR);
        gameGUIManager.getFrame().setResizable(false);
        gameGUIManager.getFrame().setLocationRelativeTo(null);  // Center the window object
        gameGUIManager.getFrame().setVisible(true);
    }

    //endregion

    public void clearFrame(){
        gameGUIManager.getFrame().removeAll();
    }

    //region Add JPanel Background

    public JPanel addJPanelBackground(int width, int height, String imagePath) {
        return addJPanelBackground(width, height, imagePath, CENTER_ALIGNMENT, CENTER_ALIGNMENT);
    }

    public GUI_JPanelBackground addJPanelBackground(int width, int height, String imagePath, float alignmentX, float alignmentY) {
        // Create new button
        GUI_JPanelBackground jPanelBackground = new GUI_JPanelBackground();
        Dimension size = new Dimension(width, height);
        jPanelBackground.setMaximumSize(size);
        jPanelBackground.setPreferredSize(size);
        jPanelBackground.setSize(width, height);

        // Set graphics
        jPanelBackground.setForeground(StaticUtils.GUI_FOREGROUND_COLOR);
        jPanelBackground.setBackground(StaticUtils.GUI_BACKGROUND_COLOR);
        getGameGUIManager().setImage(jPanelBackground, imagePath, width, height);

        // Set alignment
        setAlignment(jPanelBackground, alignmentX, alignmentY);

        // Add to panel
        return jPanelBackground;
    }

    //endregion

    //region Add JPanel

    public JPanel addJPanel(int width, int height) {
        return addJPanel(width, height, CENTER_ALIGNMENT, CENTER_ALIGNMENT);
    }

    public JPanel addJPanel(int width, int height, float alignmentX, float alignmentY) {
        // Create new button
        JPanel jPanel = new JPanel();
        Dimension size = new Dimension(width, height);
        jPanel.setMaximumSize(size);
        jPanel.setPreferredSize(size);
        jPanel.setSize(size);

        // Set graphics
        jPanel.setForeground(StaticUtils.GUI_FOREGROUND_COLOR);
        jPanel.setBackground(StaticUtils.GUI_BACKGROUND_COLOR);

        // Set alignment
        setAlignment(jPanel, alignmentX, alignmentY);

        // Add to panel
        return jPanel;
    }

    //endregion

    //region Add JLabel

    //region ---- Unscaled

    public JLabel addUnscaledLabel(int width, int height, String labelText, String imageName) {
        // Create base label
        JLabel label = createBaseLabel(width, height, labelText);
        getGameGUIManager().setScaledImage(label, imageName, width, height);

        // Set alignment
        setAlignment(label, SwingConstants.CENTER, SwingConstants.CENTER, CENTER_ALIGNMENT, CENTER_ALIGNMENT, SwingConstants.CENTER, SwingConstants.CENTER);

        return label;
    }

    //endregion

    //region ---- Scaled

    public JLabel addLabel(int width, int height, String labelText, String imageName) {
        return addLabel(width, height, labelText, imageName, SwingConstants.CENTER, SwingConstants.CENTER, CENTER_ALIGNMENT, CENTER_ALIGNMENT, SwingConstants.CENTER, SwingConstants.CENTER, true);
    }

    public JLabel addLabel(int width, int height, String labelText, String imageName, boolean scaledIcon) {
        return addLabel(width, height, labelText, imageName, SwingConstants.CENTER, SwingConstants.CENTER, CENTER_ALIGNMENT, CENTER_ALIGNMENT, SwingConstants.CENTER, SwingConstants.CENTER, scaledIcon);
    }

    public JLabel addLabel(int width, int height, String labelText, String imageName, int textAlignmentX, int textAlignmentY) {
        return addLabel(width, height, labelText, imageName, textAlignmentX, textAlignmentY, CENTER_ALIGNMENT, CENTER_ALIGNMENT, SwingConstants.CENTER, SwingConstants.CENTER, true);
    }

    public JLabel addLabel(int width, int height, String labelText, String imageName, int textAlignmentX, int textAlignmentY, boolean scaledIcon) {
        return addLabel(width, height, labelText, imageName, textAlignmentX, textAlignmentY, CENTER_ALIGNMENT, CENTER_ALIGNMENT, SwingConstants.CENTER, SwingConstants.CENTER, scaledIcon);
    }

    public JLabel addLabel(int width, int height, String labelText, String imageName, int horizontalAlignmentX, int verticalAlignmentY, float alignmentX, float alignmentY, int textAlignmentX, int textAlignmentY, boolean scaledIcon) {
        // Create base label
        JLabel label = createBaseLabel(width, height, labelText);

        if (scaledIcon) {
            getGameGUIManager().setImage(label, imageName, width, height);
        } else {
            getGameGUIManager().setScaledImage(label, imageName, width, height);
        }

        // Set alignment
        setAlignment(label, horizontalAlignmentX, verticalAlignmentY, alignmentX, alignmentY, textAlignmentX, textAlignmentY);

        return label;
    }

    //endregion

    JLabel createBaseLabel(int width, int height, String labelText) {
        // Create new button
        JLabel label = new JLabel(Functions.capitalize(labelText));
        Dimension size = new Dimension(width, height);
        label.setMaximumSize(size);
        label.setMinimumSize(size);
        label.setPreferredSize(size);
        label.setSize(size);

        // Set graphics
        label.setForeground(StaticUtils.GUI_FOREGROUND_COLOR);
        label.setBackground(new Color(0, 0, 0, 0));
        getGameGUIManager().setFont(label);

        return label;
    }

    //endregion

    //region Add JButton

    public JButton addButton(int width, int height, String text, ActionListener actionListener){
        return addButton(width,height,text,actionListener, "", -1);
    }

    public JButton addButton(int width, int height, String text, ActionListener actionListener,String overlayImageName, int padding) {
        // Create new button
        JButton button = new JButton(Functions.capitalize(text));
        Dimension size = new Dimension(width, height);
        button.setMaximumSize(size);
        button.setPreferredSize(size);
        button.setSize(size);

        // Set graphics
        button.setForeground(StaticUtils.GUI_FOREGROUND_COLOR);
        button.setBackground(new Color(0, 0, 0, 0));
        getGameGUIManager().setFont(button);

        if (padding < 0 && overlayImageName.isEmpty()) {
            getGameGUIManager().setupButton(button, width, height);
        } else {
            getGameGUIManager().setupButton(button, overlayImageName, width, height, padding);
        }

        // Set alignment
        setAlignment(button, SwingConstants.CENTER, SwingConstants.CENTER, CENTER_ALIGNMENT, CENTER_ALIGNMENT, SwingConstants.CENTER, SwingConstants.CENTER);

        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        // Add listener
        button.addActionListener(actionListener);

        // Add to panel
        return button;
    }

    //endregion

    //region Add JTextPane

    public JTextPane addTextPane(int width, int height, String textPaneText) {
        // Create new button
        JTextPane textPane = new JTextPane();
        Dimension size = new Dimension(width, height);
        textPane.setMaximumSize(size);
        textPane.setMinimumSize(size);
        textPane.setPreferredSize(size);
        textPane.setSize(size);

        textPane.setText(Functions.capitalize(textPaneText));

        textPane.setEditable(false);

        // Set graphics
        textPane.setForeground(StaticUtils.GUI_FOREGROUND_COLOR);
        textPane.setBackground(new Color(0, 0, 0, 0));
        getGameGUIManager().setFont(textPane);

        // Set alignment
        setAlignment(textPane, CENTER_ALIGNMENT, CENTER_ALIGNMENT);

        return textPane;
    }

    //endregion

    //region Set Parent

    // JPanel
    public JPanel setParent(JPanel parent, JPanel panel) {
        parent.add(panel);
        parent.revalidate();

        return panel;
    }

    public JLabel setParent(JLabel parent, JLabel label) {
        parent.add(label);
        parent.revalidate();

        return label;
    }

    public JTextPane setParent(JPanel parent, JTextPane textPane) {
        parent.add(textPane);
        parent.revalidate();

        return textPane;
    }

    public JLabel setParent(JPanel parent, JLabel label) {
        parent.add(label);
        parent.revalidate();

        return label;
    }

    public JButton setParent(JPanel parent, JButton button) {
        parent.add(button);
        parent.revalidate();

        return button;
    }

    public JTextPane setParent(JLabel parent, JTextPane textPane) {
        parent.add(textPane);
        parent.revalidate();

        return textPane;
    }

    //endregion

    //region Set Alignment

    // JPanel
    public void setAlignment(JPanel panel, float alignmentX, float alignmentY) {
        panel.setAlignmentX(alignmentX);
        panel.setAlignmentY(alignmentY);
    }

    // JLabel
    public void setAlignment(JLabel label, int horizontalAlignmentX, int verticalAlignmentY) {
        label.setHorizontalAlignment(horizontalAlignmentX);
        label.setVerticalAlignment(verticalAlignmentY);
    }

    public void setAlignment(JLabel label, int horizontalAlignmentX, int verticalAlignmentY, float alignmentX, float alignmentY, int textAlignmentX, int textAlignmentY) {
        label.setHorizontalAlignment(horizontalAlignmentX);
        label.setVerticalAlignment(verticalAlignmentY);
        label.setAlignmentX(alignmentX);
        label.setAlignmentY(alignmentY);
        label.setHorizontalTextPosition(textAlignmentX);
        label.setVerticalTextPosition(textAlignmentY);
    }

    // JButton
    public void setAlignment(JButton button, float alignmentX, float alignmentY) {
        button.setAlignmentX(alignmentX);
        button.setAlignmentY(alignmentY);
    }

    public void setAlignment(JButton button, int horizontalAlignmentX, int verticalAlignmentY) {
        button.setHorizontalAlignment(horizontalAlignmentX);
        button.setVerticalAlignment(verticalAlignmentY);
    }


    public void setAlignment(JButton button, int horizontalAlignmentX, int verticalAlignmentY, float alignmentX, float alignmentY, int textAlignmentX, int textAlignmentY) {
        button.setHorizontalAlignment(horizontalAlignmentX);
        button.setVerticalAlignment(verticalAlignmentY);
        button.setAlignmentX(alignmentX);
        button.setAlignmentY(alignmentY);
        button.setHorizontalTextPosition(textAlignmentX);
        button.setVerticalTextPosition(textAlignmentY);
    }

    // JTextPane
    public void setAlignment(JTextPane textPane, float alignmentX, float alignmentY) {
        textPane.setAlignmentX(alignmentX);
        textPane.setAlignmentY(alignmentY);
    }

    //endregion

    //region Set Layout

    public void setLayout(JPanel panel, LayoutManager layout) {
        panel.setLayout(layout);
    }

    public void setLayout(JLabel label, LayoutManager layout) {
        label.setLayout(layout);
    }

    public void setLayout(JButton button, LayoutManager layout) {
        button.setLayout(layout);
    }

    //endregion

    //region Add padding

    // JButton
    public JPanel addPadding(JButton button, int padding) {
        return addPadding(button, padding, padding);
    }

    public JPanel addPadding(JButton button, int paddingX, int paddingY) {
        // Add padding
        JPanel paddingPane = addPadding(paddingX, paddingY);
        setParent(paddingPane, button);
        return paddingPane;
    }

    // JLabel
    public JPanel addPadding(JLabel label, int padding) {
        return addPadding(label, padding, padding);
    }

    public JPanel addPadding(JLabel label, int paddingX, int paddingY) {
        // Add padding
        JPanel paddingPane = addPadding(paddingX, paddingY);
        setParent(paddingPane, label);
        return paddingPane;
    }

    // JPanel
    public JPanel addPadding(JPanel panel, int padding) {
        return addPadding(panel, padding, padding);
    }

    public JPanel addPadding(JPanel panel, int paddingX, int paddingY) {
        // Add padding
        JPanel paddingPane = addPadding(paddingX, paddingY);
        setParent(paddingPane, panel);
        return paddingPane;
    }

    // JTextPane
    public JPanel addPadding(JTextPane textPane, int padding) {
        return addPadding(textPane, padding, padding);
    }

    public JPanel addPadding(JTextPane textPane, int paddingX, int paddingY) {
        // Add padding
        JPanel paddingPane = addPadding(paddingX, paddingY);
        setParent(paddingPane, textPane);
        return paddingPane;
    }

    JPanel addPadding(int paddingX, int paddingY) {
        JPanel paddingPane = new JPanel();
        paddingPane.setLayout(new BoxLayout(paddingPane, BoxLayout.Y_AXIS));
        paddingPane.setBorder(BorderFactory.createEmptyBorder(paddingY, paddingX, paddingY, paddingX));
        setAlignment(paddingPane, CENTER_ALIGNMENT, CENTER_ALIGNMENT);
        paddingPane.setBackground(new Color(0, 0, 0, 0));
        paddingPane.setForeground(new Color(0, 0, 0, 0));

        return paddingPane;
    }

    //endregion
}
