package com.sintraqos.portfolioproject.output.gui.characterscreen;

import com.sintraqos.portfolioproject.output.gui.GUIScreen;
import com.sintraqos.portfolioproject.output.gui.guicomponents.GUI_JPanelBackground;
import com.sintraqos.portfolioproject.statics.GameSettings;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import javax.swing.*;

public class CharacterScreenGUI_Base  extends GUIScreen {
    private JPanel rootPanel;
    private JPanel contentPanel;
    private JPanel objectPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;

    JLabel topLabel;

    // Sizes
    private int windowSizeX;
    private int windowSizeY;

    private int panelPaddingX;
    private int panelPaddingY;

    private int guiHeight;
    private int guiWidth;
    private int guiPadding;

    private int guiPanelWidth;
    private int guiPanelHeight;

    //region Public

    //region ---- GUI Objects

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JPanel getObjectPanel() {
        return objectPanel;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public JLabel getTopLabel() {
        return topLabel;
    }

    //endregion

    //region ---- GUI Size

    public int getWindowSizeX() {
        return windowSizeX;
    }

    public int getWindowSizeY() {
        return windowSizeY;
    }

    public int getPanelPaddingX() {
        return panelPaddingX;
    }

    public int getPanelPaddingY() {
        return panelPaddingY;
    }

    public int getGuiHeight() {
        return guiHeight;
    }

    public int getGuiWidth() {
        return guiWidth;
    }

    public int getGuiPadding() {
        return guiPadding;
    }

    public int getGuiPanelWidth() {
        return guiPanelWidth;
    }

    public int getGuiPanelHeight() {
        return guiPanelHeight;
    }

    //endregion

    //endregion

    // Setup

    public void createBase(JPanel rootPanel, String labelText) {
        createBase(rootPanel, labelText, true);
    }

    public void createBase(JPanel rootPanel, String labelText, boolean createBasePanels) {
        this.rootPanel = rootPanel;
        setup(this.rootPanel);

        // Set sizes
        windowSizeX = GameSettings.getInstance().getWindowSize().width;
        windowSizeY = GameSettings.getInstance().getWindowSize().height;

        panelPaddingX = (int) (175 * getGameGUIManager().getGUIScale());
        panelPaddingY = (int) (75 * getGameGUIManager().getGUIScale());

        guiHeight = (int) (GameSettings.getInstance().getDefaultButtonSizeY() * getGameGUIManager().getGUIScale());
        guiWidth = (int) (GameSettings.getInstance().getDefaultButtonSizeX() * getGameGUIManager().getGUIScale());
        guiPadding = (int) (5 * getGameGUIManager().getGUIScale());

        guiPanelWidth = windowSizeX - (panelPaddingX * 2);
        guiPanelHeight = windowSizeY - (panelPaddingY * 2) - (guiHeight * 2);

        // Background
        rootPanel = new GUI_JPanelBackground();
        getGameGUIManager().setScaledImage((GUI_JPanelBackground) rootPanel, ResourcePaths.GUI_BACKGROUND, windowSizeX, windowSizeY);
        setup(rootPanel);

        // Create panel that contains all gui elements
        objectPanel = setParent(rootPanel, addJPanel(guiPanelWidth, guiPanelHeight));
        setLayout(objectPanel, new BoxLayout(objectPanel, BoxLayout.Y_AXIS));

        // Add the label to the top
        topLabel = setParent(objectPanel, addLabel(guiPanelWidth, guiHeight, labelText, ResourcePaths.LABEL_IMAGE));
        // Create empty
        setParent(objectPanel, addJPanel(guiPanelWidth, guiPadding));
        contentPanel = setParent(objectPanel, addJPanel(guiPanelWidth, guiPanelHeight - (2 * (guiHeight + guiPadding))));

        if (createBasePanels) {
            setLayout(contentPanel, new BoxLayout(contentPanel, BoxLayout.X_AXIS));
            leftPanel = setParent(contentPanel, addJPanelBackground(guiPanelWidth / 2, guiPanelHeight, ResourcePaths.LABEL_IMAGE));
            setLayout(leftPanel, new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            rightPanel = setParent(contentPanel, addJPanelBackground(guiPanelWidth / 2, guiPanelHeight, ResourcePaths.LABEL_IMAGE));
            setLayout(rightPanel, new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        }

        // Finally set the window size to make sure everything is properly sized
        setWindowSize();
    }
}
