package com.sintraqos.portfolioproject.output.gui.characterscreen;

import com.sintraqos.portfolioproject.output.gui.GUIScreen;
import com.sintraqos.portfolioproject.output.gui.guicomponents.GUI_JPanelBackground;
import com.sintraqos.portfolioproject.statics.*;

import javax.swing.*;

public class CharacterScreenGUI_Base  extends GUIScreen {
    private JPanel rootPanel;
    private JPanel contentPanel;
    private JPanel objectPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;

    JLabel topLabel;

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

    //endregion

    //region Setup

    public void createBase(JPanel rootPanel) {
        createBase(rootPanel, "", true, true);
    }

    public void createBase(JPanel rootPanel, String labelText) {
        createBase(rootPanel, labelText, true, true);
    }

    public void createBase(JPanel rootPanel, String labelText, boolean createBasePanels, boolean hasLoadScreen) {
        this.rootPanel = rootPanel;
        setup(this.rootPanel);

        setGUISizes();

        if (hasLoadScreen) {
            setupLoadScreen();
        }
        if (!hasLoadScreen) {
            setupScreen(labelText, createBasePanels);
        }
    }

    void setupLoadScreen() {
        rootPanel.removeAll();

        // Background
        rootPanel = new GUI_JPanelBackground();
        getGameGUIManager().setScaledImage((GUI_JPanelBackground) rootPanel, ResourcePaths.GUI_LOAD_SCREEN, getWindowSizeX(), getWindowSizeY());
        setup(rootPanel);

        // Create panel that contains all gui elements
        objectPanel = setParent(rootPanel, addJPanel(getGuiPanelWidth(), getGuiPanelHeight()));
        setLayout(objectPanel, new BoxLayout(objectPanel, BoxLayout.Y_AXIS));

        // Add the label to the top
        topLabel = setParent(objectPanel, addLabel(getGuiPanelWidth(), getGuiHeight(), "", ResourcePaths.LABEL_IMAGE));
        setLoadingText();

        // Finally set the window size to make sure everything is properly sized
        setWindowSize();
    }

    public void setupScreen(String labelText, boolean createBasePanels) {
        rootPanel.removeAll();

        // Background
        rootPanel = new GUI_JPanelBackground();
        getGameGUIManager().setScaledImage((GUI_JPanelBackground) rootPanel, ResourcePaths.GUI_BACKGROUND,  getWindowSizeX(),  getWindowSizeY());
        setup(rootPanel);

        // Create panel that contains all gui elements
        objectPanel = setParent(rootPanel, addJPanel(getGuiPanelWidth(),  getGuiPanelHeight()));
        setLayout(objectPanel, new BoxLayout(objectPanel, BoxLayout.Y_AXIS));

        // Add the label to the top
        topLabel = setParent(objectPanel, addLabel(getGuiPanelWidth(), getGuiHeight(), labelText, ResourcePaths.LABEL_IMAGE));
        // Create empty
        setParent(objectPanel, addJPanel(getGuiPanelWidth(), getGuiPadding()));
        contentPanel = setParent(objectPanel, addJPanel(getGuiPanelWidth(),  getGuiPanelHeight() - (2 * (getGuiHeight() + getGuiPadding()))));

        if (createBasePanels) {
            setLayout(contentPanel, new BoxLayout(contentPanel, BoxLayout.X_AXIS));
            leftPanel = setParent(contentPanel, addJPanelBackground(getGuiPanelWidth() / 2, getGuiPanelHeight(), ResourcePaths.LABEL_IMAGE));
            setLayout(leftPanel, new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            rightPanel = setParent(contentPanel, addJPanelBackground(getGuiPanelWidth() / 2,  getGuiPanelHeight(), ResourcePaths.LABEL_IMAGE));
            setLayout(rightPanel, new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        }

        // Finally set the window size to make sure everything is properly sized
        setWindowSize();
    }

    //endregion

    String[] loadingText = new String[]{"Loading", "Loading.", "Loading..", "Loading..."};
    int currentLoadText;
    public void setLoadingText(){
        String loadText = loadingText[currentLoadText];

        currentLoadText++;
        if(currentLoadText >= loadingText.length){
            currentLoadText = 0;
        }

        Console.writeLine(loadText);

        setLabelText(loadText);
    }

    public void setLabelText(String labelText) {
        topLabel.setText(labelText);
    }
}
